package Beans;

public class Transaction {
    private Long id;
    private Long itemId;
    private Long buyerId;
    private Long pickupTime;
    private Long dropOffTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public Long getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(Long buyerId) {
        this.buyerId = buyerId;
    }

    public Long getPickupTime() {
        return pickupTime;
    }

    public void setPickupTime(Long pickupTime) {
        this.pickupTime = pickupTime;
    }

    public Long getDropOffTime() {
        return dropOffTime;
    }

    public void setDropOffTime(Long dropOffTime) {
        this.dropOffTime = dropOffTime;
    }
}
