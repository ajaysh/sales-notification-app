package src.notification.message.bean;

import com.notification.util.OperationType;


public class AdjustmentMessage extends BasicMessage {
    OperationType operationType;

    public AdjustmentMessage() {}

    public AdjustmentMessage(OperationType operationType) {
        this.operationType = operationType;
    }

    public AdjustmentMessage(String productName, Double sellingPrice, OperationType operationType) {
        super(productName, sellingPrice);
        this.operationType = operationType;
    }

    public OperationType getOperationType() {
        return operationType;
    }

    public void setOperationType(OperationType operationType) {
        this.operationType = operationType;
    }
}
