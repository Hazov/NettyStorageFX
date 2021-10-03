package cloud.client.handlers;

import cloud.files.FileError;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.concurrent.EventExecutorGroup;

public class FileErrReceiver extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if(msg instanceof FileError){
            System.out.println("НЕЛЬЗЯ ЗАПИСАТЬ ФАЙЛ В ДАННЫЙ МОМЕНТ ПОПРОБУЙТЕ ПОЗЖЕ");
        }else {
            ctx.fireChannelRead(msg);
        }
    }
}
