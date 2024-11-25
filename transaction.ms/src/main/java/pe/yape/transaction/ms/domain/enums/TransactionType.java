package pe.yape.transaction.ms.domain.enums;

public enum TransactionType {

    DEBIT(1),
    CREDIT(2);

    private final int typeId;

    TransactionType(int typeId) {
        this.typeId = typeId;
    }

    public int getTypeId() {
        return typeId;
    }

    public static TransactionType fromTypeId(int typeId) {
        for (TransactionType type : TransactionType.values()) {
            if (type.getTypeId() == typeId) {
                return type;
            }
        }
        throw new IllegalArgumentException("Invalid transaction type id: " + typeId);
    }
}
