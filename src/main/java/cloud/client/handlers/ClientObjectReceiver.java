package cloud.client.handlers;

import cloud.files.FileInfo;
import cloud.files.UploadResponseOK;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.concurrent.EventExecutorGroup;

public class ClientObjectReceiver extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (msg instanceof FileInfo) {
            System.out.println("Получил обратно FileInfo");
            ctx.fireChannelRead(msg);
            /** next ->
                        * @see FileSenderHandler
             */
        }
    }
}
