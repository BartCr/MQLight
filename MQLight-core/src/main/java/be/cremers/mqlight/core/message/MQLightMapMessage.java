package be.cremers.mqlight.core.message;

import be.cremers.mqlight.core.exception.MQLightException;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public final class MQLightMapMessage extends BaseMQLightMessage {

    private Map<String, String> map;

    MQLightMapMessage(MessageType type) {
        super(type);
        if (type != MessageType.MAP) throw new IllegalArgumentException("Should only be called with MessageType.MAP");
    }

    public MQLightMapMessage() {
        super(MessageType.MAP, UUID.randomUUID().toString(), System.currentTimeMillis());
        this.map = new HashMap<>();
    }

    @Override
    void readBody(DataInputStream dis) throws IOException, MQLightException {
        int length = dis.readInt();
        map = new HashMap<>(length);
        for (int i = 0; i < length; i++) {
            map.put(dis.readUTF(), dis.readUTF());
        }
    }

    @Override
    void writeBody(DataOutputStream dos) throws IOException {
        dos.writeInt(map.size());
        for (Map.Entry<String, String> entry : map.entrySet()) {
            dos.writeUTF(entry.getKey());
            dos.writeUTF(entry.getValue());
        }
    }
}
