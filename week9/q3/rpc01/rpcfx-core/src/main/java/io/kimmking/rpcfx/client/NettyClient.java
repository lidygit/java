package io.kimmking.rpcfx.client;


import com.alibaba.fastjson.JSON;
import io.kimmking.rpcfx.api.RpcfxRequest;
import io.kimmking.rpcfx.api.RpcfxResponse;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.*;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

public class NettyClient {

    public RpcfxResponse post(RpcfxRequest req, String url) throws IOException, InterruptedException, URISyntaxException {
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {

            URL netUrl = new URL(url);
            String host = netUrl.getHost();
            int port = netUrl.getPort();

            NettyHttpClientHandler clientHandler = new NettyHttpClientHandler();

            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(workerGroup)
                    .channel(NioSocketChannel.class)
                    .remoteAddress(new InetSocketAddress(host, port))
                    .option(ChannelOption.SO_KEEPALIVE, true)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel channel) throws Exception {
                            channel.pipeline()
                                    .addLast(new HttpRequestEncoder())
                                    .addLast(new HttpResponseDecoder())
                                    .addLast(new HttpObjectAggregator(1024 * 1024))
                                    .addLast(clientHandler);
                        }
                    });
            ChannelFuture future = bootstrap.connect().sync();

            URI uri = new URI(url);
            FullHttpRequest request = new DefaultFullHttpRequest(HttpVersion.HTTP_1_0,
                    HttpMethod.POST,
                    uri.toASCIIString(),
                    Unpooled.wrappedBuffer(JSON.toJSONBytes(req)));


            request.headers()
                    .set(HttpHeaderNames.CONTENT_TYPE, HttpHeaderValues.APPLICATION_JSON)
                    //开启长连接
                    .set(HttpHeaderNames.CONNECTION, HttpHeaderValues.KEEP_ALIVE)
                    //设置传递请求内容的长度
                    .set(HttpHeaderNames.CONTENT_LENGTH, request.content().readableBytes());

            ChannelPromise promise = clientHandler.sendMsg(request);
            promise.await();
            RpcfxResponse rpcfxResponse = clientHandler.getRpcfxResponse();

            future.channel().closeFuture().sync();

            return rpcfxResponse;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }finally {
            workerGroup.shutdownGracefully();
        }
    }
}
