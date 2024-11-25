package pe.yape.anti.fraud.ms.domain.enums;

public enum TransactionStatus {
    APPROVED(2),
    REJECTED(3);

    private final int typeId;

    TransactionStatus(int typeId) {
        this.typeId = typeId;
    }

    public int getTypeId() {
        return typeId;
    }

    public static TransactionStatus fromTypeId(int typeId) {
        for (TransactionStatus type : TransactionStatus.values()) {
            if (type.getTypeId() == typeId) {
                return type;
            }
        }
        throw new IllegalArgumentException("Invalid transaction status id: " + typeId);
    }
}
