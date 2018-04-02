package offer.data;

import java.util.Date;

public class Offer {
    private String description;
    private Double sellingPrice;
    private Date startDate;
    private Date expiryDate;

    public Date getStartDate() { return startDate; }

    public void setStartDate(Date startDate) { this.startDate = startDate; }

    public Date getExpiryDate() { return expiryDate; }

    public void setExpiryDate(Date expiryDate) { this.expiryDate = expiryDate; }

    public String getDescription() { return description; }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(Double sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

}
