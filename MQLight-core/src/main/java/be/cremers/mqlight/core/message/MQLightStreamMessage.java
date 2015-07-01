package be.cremers.mqlight.core.message;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public final class MQLightStreamMessage extends BaseMQLightMessage {

    MQLightStreamMessage(MessageType type) {
        super(type);
        if (type != MessageType.STREAM) throw new IllegalArgumentException("Should only be called with MessageType.STREAM");
        throw new UnsupportedOperationException("Stream messages not yet supported");
    }

    @Override
    void readBody(DataInputStream dis) throws IOException {
        // Not yet implemented
    }

    @Override
    void writeBody(DataOutputStream dos) throws IOException {
        // Not yet implemented
    }
}
