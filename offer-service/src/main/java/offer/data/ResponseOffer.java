package offer.data;

import java.util.Date;

public class ResponseOffer {
    private Long id;
    private String description;
    private Double sellingPrice;
    private Date startDate;
    private Date expiryDate;

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getDescription() { return description; }

    public void setDescription(String description) { this.description = description; }

    public Double getSellingPrice() { return sellingPrice; }

    public void setSellingPrice(Double sellingPrice) { this.sellingPrice = sellingPrice; }

    public Date getStartDate() { return startDate; }

    public void setStartDate(Date startDate) { this.startDate = startDate; }

    public Date getExpiryDate() { return expiryDate; }

    public void setExpiryDate(Date expiryDate) { this.expiryDate = expiryDate; }

    @Override
    public String toString(){
        return String.format(
                "Offer[id=%d, description='%s', selling_price='%s', start_date='%s', expiry_date='%s']",
                id, description, sellingPrice, startDate, expiryDate );
    }
}
