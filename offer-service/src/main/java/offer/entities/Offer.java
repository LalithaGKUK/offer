package offer.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Date;

@Entity
@JsonIgnoreProperties({"merchant", "product"})
public class Offer {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column (name = "id", updatable = false, nullable = false)
    private Long id;
    private String description;
    private Long productId;
    private Double sellingPrice;
    private String status;
    private Long merchantId;
    private Date createdAt;
    private Date updatedAt;

    @Column (nullable = false, name= "startDate")
    private Date startDate;
    @Column (nullable = false, name= "expiryDate")
    private Date expiryDate;

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn (name = "merchantId", insertable = false, updatable = false)
    private Merchant merchant;


    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "productId", insertable = false, updatable = false)
    private Product product;

    public Offer() {   }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Double getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(Double sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Long merchantId) {
        this.merchantId = merchantId;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString(){
        return String.format(
                "Offer[id=%d, description='%s', status= '%s', start_date='%s', expiry_date='%s']",
                id, description, status, startDate, expiryDate );
    }
}
