package offer.data;

public class RequestOffer {
    private Product product;
    private Merchant merchant;
    private Offer offer;

    public Offer getOffer() { return offer; }

    public void setOffer(Offer offer) { this.offer = offer; }

    public Merchant getMerchant() {
        return merchant;
    }

    public void setMerchant(Merchant merchant) {
        this.merchant = merchant;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
