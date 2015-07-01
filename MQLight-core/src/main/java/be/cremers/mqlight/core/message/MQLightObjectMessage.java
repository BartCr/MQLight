package be.cremers.mqlight.core.message;

import be.cremers.mqlight.core.exception.MQLightException;

import java.io.*;
import java.util.UUID;

public final class MQLightObjectMessage extends BaseMQLightMessage {

    private Object object;

    MQLightObjectMessage(MessageType type) {
        super(type);
        if (type != MessageType.OBJECT) throw new IllegalArgumentException("Should only be called with MessageType.OBJECT");
    }

    public MQLightObjectMessage(Object object) {
        super(MessageType.OBJECT, UUID.randomUUID().toString(), System.currentTimeMillis());
        this.object = object;
    }

    @Override
    void readBody(DataInputStream dis) throws IOException, MQLightException {
        int length = dis.readInt();
        byte[] bytes = new byte[length];
        dis.readFully(bytes);

        try {
            this.object = new ObjectInputStream(new ByteArrayInputStream(bytes)).readObject();
        } catch (ClassNotFoundException e) {
            throw new MQLightException(e);
        }
    }

    @Override
    void writeBody(DataOutputStream dos) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        new ObjectOutputStream(baos).writeObject(object);
        byte[] bytes = baos.toByteArray();
        dos.writeInt(bytes.length);
        dos.write(bytes);
    }
}
