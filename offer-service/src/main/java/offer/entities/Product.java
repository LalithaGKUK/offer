package offer.entities;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String description;
    private String type;
    private Double cost;

    @Column(name = "created_at")
    @CreatedDate
    private Date createdAt;

    @Column(name = "updated_at")
    @LastModifiedDate
    private Date updatedAt;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getType() {
        return type;
    }

    public Double getCost() {
        return cost;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    protected Product() {}

    public Product(offer.data.Product product) {
        name = product.getName();
        description = product.getDescription();
        type = product.getType();
        cost = product.getCost();
    }

    public void setCreatedAt(Date current) {
        createdAt = current;
    }

    public void setUpdatedAt(Date current) {
        updatedAt = current;
    }

    @Override
    public String toString() {
        return String.format(
                "Product[id=%d, name='%s', description='%s', type='%s', created_at='%s', updated_at='%s']",
                id, name, description, type, createdAt, updatedAt);
    }
}
