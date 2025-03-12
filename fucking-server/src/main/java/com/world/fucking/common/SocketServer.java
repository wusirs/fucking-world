package com.world.fucking.common;

/**
 * @author Heisenberg
 * @since 1.0.0

 */

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import lombok.extern.slf4j.Slf4j;

import java.net.InetSocketAddress;
import java.util.Objects;

/**
 * 服务端
 * @author Ciao
 */
@Slf4j
public class SocketServer {
    private Integer port;
    private Integer boosThreads = 1;
    private Integer workerThreads;

    public SocketServer() throws InterruptedException{
        init();
    }

    public SocketServer(Integer port, Integer boosThreads) throws InterruptedException{
        this.port = port;
        this.boosThreads = boosThreads;

        this.init();
    }

    public SocketServer(Integer port, Integer boosThreads, Integer workerThreads) throws InterruptedException{
        this.port = port;
        this.boosThreads = boosThreads;
        this.workerThreads = workerThreads;

        this.init();
    }

    private void init() throws InterruptedException {
        //1.创建一个服务端的引导类
        ServerBootstrap bootstrap = new ServerBootstrap();

        //2.创建反应器事件轮询组
        //boss轮询组（负责处理父通道：连接/接收监听（如NioServerSocketChannel））
        EventLoopGroup bossGroup = new NioEventLoopGroup(boosThreads);

        //worker轮询组（负责处理子通道：读/写监听（如NioSocketChannel））
        EventLoopGroup workerGroup;
        if(Objects.nonNull(this.workerThreads) && this.workerThreads > 0){
            workerGroup= new NioEventLoopGroup(this.workerThreads);
        }else{
            //线程数默认为cpu核心数的2倍
            workerGroup = new NioEventLoopGroup();
        }

        //3.设置父子轮询组
        bootstrap.group(bossGroup, workerGroup);

        //4.设置传输通道类型,Netty不仅支持Java NIO，也支持阻塞式的OIO
        bootstrap.channel(NioServerSocketChannel.class);

        //5.设置监听端口
        bootstrap.localAddress(new InetSocketAddress(this.port));

        //6.设置通道参数
        bootstrap.option(ChannelOption.SO_KEEPALIVE, true);
        bootstrap.option(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT);
        bootstrap.childOption(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT);

        //7.装配子通道的Pipeline流水线
        bootstrap.childHandler(new SocketChannelInitializer());

        //8.开始绑定端口，并通过调用sync()同步方法阻塞直到绑定成功
        ChannelFuture channelFuture = bootstrap.bind().sync();
        log.info("服务端启动成功，监听端口：{}", this.port);

        //9.自我阻塞，直到监听通道关闭
        ChannelFuture closeFuture = channelFuture.channel().closeFuture();
        closeFuture.sync();
        log.info("服务端已停止运行");

        //10.释放所有资源，包括创建的反应器线程
        workerGroup.shutdownGracefully();
        bossGroup.shutdownGracefully();
        log.info("已释放服务端占用资源");
    }
}

