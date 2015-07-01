package be.cremers.mqlight.core.message;

import be.cremers.mqlight.core.exception.MQLightException;

import java.io.*;

public class MessageFactory {

    public static <T extends BaseMQLightMessage> T fromByteArray(byte[] input) throws MQLightException {
        try (ByteArrayInputStream bais = new ByteArrayInputStream(input);
             DataInputStream dis = new DataInputStream(bais)) {

            int typeValue = dis.readInt();
            MessageType type = MessageType.fromTypeValue(typeValue);

            T message = type.newMessageInstance();
            message.readBaseProperties(dis);
            message.readBody(dis);
            return message;
        } catch (IOException e) {
            throw new MQLightException(e);
        }
    }

    public static byte[] toByteArray(BaseMQLightMessage message) throws IOException {
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream();
             DataOutputStream dos = new DataOutputStream(baos)) {
            dos.writeInt(message.getType().typeValue());
            message.writeBaseProperties(dos);
            message.writeBody(dos);
            return baos.toByteArray();
        }
    }

}
