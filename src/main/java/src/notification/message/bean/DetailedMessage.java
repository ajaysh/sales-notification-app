package src.notification.message.bean;

public class DetailedMessage extends BasicMessage {
	private long count;

    public DetailedMessage() {}

    public DetailedMessage(long count) {
        this.count = count;
    }

    public DetailedMessage(String productName, Double sellingPrice, long instanceCount) {
        super(productName, sellingPrice);
        this.count = instanceCount;
    }

    public long getCount() {
		return count;
	}

	public void setCount(long count) {
		this.count = count;
	}

}
