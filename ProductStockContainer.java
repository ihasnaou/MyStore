package mystore;
// Ilyes Hasnaou 101146081 //
// Marina Latif - 101149148 //

public interface ProductStockContainer {

    public Integer getStock(String productID);

    public void addProduct(Product product, Integer stock);

    public void removeProduct(String productID, Integer stock);

    public int getNumOfProducts();
}
