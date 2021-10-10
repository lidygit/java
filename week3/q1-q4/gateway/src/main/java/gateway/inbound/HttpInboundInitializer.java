package gateway.inbound;

import gateway.outbound.netty.NettyHttpClientOutboundHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpContentDecompressor;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;

import java.util.List;

/**
 * @Description: java类作用描述
 * @Author: l
 * @CreateDate: 2021/10/10 18:13
 * @需求:
 * @思路说明:
 */
public class HttpInboundInitializer extends ChannelInitializer<SocketChannel> {

    private List<String> proxyServer;

    public HttpInboundInitializer(List<String> proxyServer) {
        this.proxyServer = proxyServer;
    }

    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {

        ChannelPipeline p = socketChannel.pipeline();
        //先解码
        p.addLast(new HttpServerCodec());
        p.addLast(new HttpObjectAggregator(1024 * 1024));
        //解压
        p.addLast(new HttpContentDecompressor());
        //绑定handler
//        p.addLast(new HttpInboundHandler(this.proxyServer));
        p.addLast(new NettyHttpClientOutboundHandler(this.proxyServer));

    }
}
