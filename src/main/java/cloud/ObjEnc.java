package cloud;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.serialization.ObjectEncoder;

import java.io.Serializable;

public class ObjEnc extends ObjectEncoder {
    public ObjEnc() {
        super();
    }

    @Override
    protected void encode(ChannelHandlerContext ctx, Serializable msg, ByteBuf out) throws Exception {
        System.out.println("encode()");
        super.encode(ctx, msg, out);

    }
}
