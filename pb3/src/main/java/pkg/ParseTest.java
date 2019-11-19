package pkg;

import com.google.protobuf.InvalidProtocolBufferException;
import helloworld.Helloworld;

public class ParseTest {
    public static void main(String[] args) throws InvalidProtocolBufferException {
        Helloworld.HelloReply reply = Helloworld.HelloReply.newBuilder().setMessage("message").build();
        byte[] binary = reply.toByteArray();

        Helloworld.HelloReply2 helloReply2 = Helloworld.HelloReply2.parseFrom(binary);
    }
}
