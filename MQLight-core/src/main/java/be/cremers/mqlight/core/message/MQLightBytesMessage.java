package be.cremers.mqlight.core.message;

import be.cremers.mqlight.core.exception.MQLightException;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.UUID;

public final class MQLightBytesMessage extends BaseMQLightMessage {

    private byte[] bytes;

    MQLightBytesMessage(MessageType type) {
        super(type);
        if (type != MessageType.BYTES) throw new IllegalArgumentException("Should only be called with MessageType.BYTES");
    }

    public MQLightBytesMessage(byte[] bytes) {
        super(MessageType.BYTES, UUID.randomUUID().toString(), System.currentTimeMillis());
        this.bytes = bytes;
    }

    @Override
    void readBody(DataInputStream dis) throws IOException, MQLightException {
        int length = dis.readInt();
        bytes = new byte[length];
        dis.readFully(bytes);
    }

    @Override
    void writeBody(DataOutputStream dos) throws IOException {
        dos.writeInt(bytes.length);
        dos.write(bytes);
    }
}
