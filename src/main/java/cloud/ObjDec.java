package cloud;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.serialization.ClassResolver;
import io.netty.handler.codec.serialization.ObjectDecoder;



public class ObjDec extends ObjectDecoder {
    public ObjDec(ClassResolver classResolver) {
        super(Integer.MAX_VALUE, classResolver);
    }

    public ObjDec(int maxObjectSize, ClassResolver classResolver) {
        super(maxObjectSize, classResolver);
    }

    @Override
    protected Object decode(ChannelHandlerContext ctx, ByteBuf in) throws Exception {
        System.out.println("decode()");
        System.out.println("DEC");
        return super.decode(ctx, in);
    }
}
