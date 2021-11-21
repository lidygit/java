package io.kimmking.rpcfx.client;

import com.alibaba.fastjson.JSON;
import io.kimmking.rpcfx.api.RpcfxResponse;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelPromise;
import io.netty.handler.codec.http.*;


import java.nio.charset.StandardCharsets;



public class NettyHttpClientHandler extends ChannelInboundHandlerAdapter {

    private ChannelHandlerContext ctx;
    private RpcfxResponse rpcfxResponse;
    private ChannelPromise promise;

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
        this.ctx=ctx;
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.close();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        if (msg instanceof FullHttpResponse) {
            FullHttpResponse response = (FullHttpResponse) msg;
            ByteBuf byteBuf = response.content();
            String content = byteBuf.toString(StandardCharsets.UTF_8);

            rpcfxResponse = JSON.parseObject(content, RpcfxResponse.class);
            promise.setSuccess();
            byteBuf.release();
        }
    }

    public ChannelPromise sendMsg(Object msg){
        while (ctx == null){
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        promise = ctx.newPromise();
        ctx.writeAndFlush(msg);
        return promise;
    }

    public RpcfxResponse getRpcfxResponse(){
        return rpcfxResponse;
    }
}
