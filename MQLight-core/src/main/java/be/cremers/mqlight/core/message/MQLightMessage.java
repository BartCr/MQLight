package be.cremers.mqlight.core.message;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.UUID;

public class MQLightMessage extends BaseMQLightMessage {

    MQLightMessage(MessageType type) {
        super(type);
        if (type != MessageType.PLAIN) throw new IllegalArgumentException("Should only be called with MessageType.PLAIN");
    }

    public MQLightMessage() {
        super(MessageType.PLAIN, UUID.randomUUID().toString(), System.currentTimeMillis());
    }

    void readBody(DataInputStream dis) throws IOException {
        // Plain message has no body
    }

    void writeBody(DataOutputStream dos) throws IOException {
        // Plain message has no body
    }

}
