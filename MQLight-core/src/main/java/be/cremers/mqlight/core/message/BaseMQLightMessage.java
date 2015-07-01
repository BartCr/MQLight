package be.cremers.mqlight.core.message;

import be.cremers.mqlight.core.exception.MQLightException;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public abstract class BaseMQLightMessage {
    private final MessageType type;

    private String id;
    private long timestamp;

    protected BaseMQLightMessage(MessageType type) {
        this.type = type;
    }

    protected BaseMQLightMessage(MessageType type, String id, long timestamp) {
        this.type = type;
        this.id = id;
        this.timestamp = timestamp;
    }

    final MessageType getType() {
        return type;
    }

    final void readBaseProperties(DataInputStream dis) throws IOException {
        this.id = dis.readUTF();
        this.timestamp = dis.readLong();
    }

    final void writeBaseProperties(DataOutputStream dos) throws IOException {
        dos.writeUTF(this.id);
        dos.writeLong(this.timestamp);
    }

    abstract void readBody(DataInputStream dis) throws IOException, MQLightException;
    abstract void writeBody(DataOutputStream dos) throws IOException;

}
