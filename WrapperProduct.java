package mystore;
// Ilyes Hasnaou 101146081 //
// Marina Latif - 101149148 //

public class WrapperProduct {
    private Product product;
    public Integer stock;

    /**
     * Constructor for a wrapper class of product
     * @param product Product of this WrapperProduct
     * @param stock Integer amount of this product in WrapperProduct
     */
    public WrapperProduct (Product product, Integer stock) {
        this.product = product;
        this.stock = stock;
    }

    /**
     * Getter for the product
     * @return Product product of our WrapperProduct
     */
    public Product getProduct() {
        return product;
    }
}
