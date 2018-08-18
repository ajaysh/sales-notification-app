package src.notification.message.bean;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "class")
@JsonSubTypes({
        @JsonSubTypes.Type(value = DetailedMessage.class),
        @JsonSubTypes.Type(value = AdjustmentMessage.class)
})
public class BasicMessage {
    private String productName;

	private double sellingPrice;

    public BasicMessage() {
    }

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}


    public BasicMessage(String productName, Double sellingPrice) {
        this.productName = productName;
        this.sellingPrice = sellingPrice;
    }

    public double getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(double sellingPrice) {
        this.sellingPrice = sellingPrice;
    }
}
