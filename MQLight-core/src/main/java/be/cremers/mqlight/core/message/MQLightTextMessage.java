package be.cremers.mqlight.core.message;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.UUID;

public final class MQLightTextMessage extends BaseMQLightMessage {

    private String text;

    MQLightTextMessage(MessageType type) {
        super(type);
        if (type != MessageType.TEXT) throw new IllegalArgumentException("Should only be called with MessageType.TEXT");
    }

    public MQLightTextMessage(String text) {
        super(MessageType.TEXT, UUID.randomUUID().toString(), System.currentTimeMillis());
        this.text = text;
    }

    @Override
    void readBody(DataInputStream dis) throws IOException {
        this.text = dis.readUTF();
    }

    @Override
    void writeBody(DataOutputStream dos) throws IOException {
        dos.writeUTF(this.text);
    }
}
