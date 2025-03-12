package com.world.fucking.common;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;
import lombok.extern.slf4j.Slf4j;

/**
 * @author heisenberg
 * @since 1.0.0
 */
@Slf4j
public class SocketInboundHandler extends ChannelInboundHandlerAdapter {
    //用于保存客户端的通道组
    private static final ChannelGroup clients = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    /**
     * 读取客户端发送来的消息
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        //由于我们配置的是字节数组编解码器（ByteArrayDecoder），所以这里取到的msg是byte数组
        byte[] data = (byte[]) msg;

        log.info("收到消息：{}", new String(data));

        //给其他人转发消息
        for (Channel client : clients) {
            if (!client.equals(ctx.channel())) {
                client.writeAndFlush(data);
            }
        }
    }

    /**
     * 接入客户端
     */
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        log.info("新客户端连接：{}", ctx.channel().id().asShortText());
        clients.add(ctx.channel());
    }

    /**
     * 断开客户端
     */
    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        log.info("客户端连接断开：{}", ctx.channel().id().asShortText());
        clients.remove(ctx.channel());
    }

    /**
     * 异常处理
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        log.info("客户端异常：{}", cause.getMessage(), cause);
        ctx.channel().close();
        clients.remove(ctx.channel());
    }
}
