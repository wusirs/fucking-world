package com.world.fucking.common;

import com.world.fucking.exception.BusinessException;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.bytes.ByteArrayDecoder;
import io.netty.handler.codec.bytes.ByteArrayEncoder;

/**
 * Socket通道初始化器
 * @author heisenberg
 */
public class SocketChannelInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel channel) throws BusinessException {
        ChannelPipeline pipeline = channel.pipeline();
        //添加对byte数组的编解码，是由netty提供的
        pipeline.addLast(new ByteArrayDecoder()); //入站
        pipeline.addLast(new ByteArrayEncoder()); //出站
        //添加自己的入站处理器
        pipeline.addLast(new SocketInboundHandler());
    }
}

