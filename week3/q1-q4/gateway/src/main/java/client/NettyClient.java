package client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.*;

/**
 * @Description: java类作用描述
 * @Author: l
 * @CreateDate: 2021/10/10 17:34
 * @需求:
 * @思路说明:
 */
public class NettyClient {


    public static void main(String[] args) {
        NettyClient nettyClient = new NettyClient();
        nettyClient.start();

    }
    private static Channel serverChannel;
    String ip="127.0.0.1";
    int port=8888;
    public void start() {
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(workerGroup).channel(NioSocketChannel.class).handler(new ChannelInitializer<SocketChannel>() {
                @Override
                public void initChannel(SocketChannel ch) throws Exception {
                    ChannelPipeline p = ch.pipeline();
                    //包含编码器和解码器
                    p.addLast(new HttpClientCodec());

                    //聚合
                    p.addLast(new HttpObjectAggregator(1024 * 10 * 1024));
                    //解压
                    p.addLast(new HttpContentDecompressor());

                    p.addLast(new NettyHttpClientHandler());
                }
            });
            bootstrap.option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000)
                    .option(ChannelOption.SO_KEEPALIVE, true);
            ChannelFuture future = bootstrap.connect(ip, port).sync();

            serverChannel=future.channel();
            serverChannel.writeAndFlush("Hello Netty Server ,I am a common client");
            serverChannel.closeFuture().sync();

        } catch (InterruptedException e) {
            workerGroup.shutdownGracefully();
        }finally {
            // 优雅关闭server线程及相关资源
            workerGroup.shutdownGracefully();

        }

    }

    /** 关闭当前server */
    public static void closeServer() {
        if (serverChannel != null) {
            serverChannel.close();
            serverChannel = null;
        }
    }
}
