package be.cremers.mqlight.core.message;

import be.cremers.mqlight.core.exception.MQLightException;

public enum MessageType {
    PLAIN(MQLightMessage.class, 0b1),
    TEXT(MQLightTextMessage.class, 0b10),
    MAP(MQLightMapMessage.class, 0b100),
    BYTES(MQLightBytesMessage.class, 0b1000),
    STREAM(MQLightStreamMessage.class, 0b10000),
    OBJECT(MQLightObjectMessage.class, 0b100000);

    private final Class<? extends BaseMQLightMessage> messageClass;
    private final int typeValue;

    MessageType(Class<? extends BaseMQLightMessage> messageClass, int typeValue) {
        this.messageClass = messageClass;
        this.typeValue = typeValue;
    }

    @SuppressWarnings("unchecked")
    <T extends BaseMQLightMessage> T newMessageInstance() throws MQLightException {
        try {
            return (T) messageClass.getDeclaredConstructor(MessageType.class).newInstance(this);
        } catch (ReflectiveOperationException e) {
            throw new MQLightException(e);
        }
    }

    static MessageType fromTypeValue(int typeValue) {
        for (MessageType messageType : values()) {
            if (messageType.typeValue == typeValue) {
                return messageType;
            }
        }
        throw new IllegalArgumentException("Can't resolve type for value: " + typeValue);
    }

    int typeValue() {
        return typeValue;
    }
}
