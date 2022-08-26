package mystore;
// Ilyes Hasnaou 101146081 //
// Marina Latif - 101149148 //

public class WrapperShoppingCart {
    private ShoppingCart shoppingCart;
    private Integer cartID;

    /**
     * Constructor for WrapperShoppingCart class
     * @param sc ShoppingCart Shopping cart of WrapperShoppingCart class
     * @param cartID
     */
    public WrapperShoppingCart (ShoppingCart sc, Integer cartID) {
        this.shoppingCart = sc;
        this.cartID = cartID;
    }

    /**
     * Getter for the shoppingCart
     * @return ShoppingCart shoppingCart of WrapperShoppingCart
     */
    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }

    /**
     * Getter for the Cart ID
     * @return String cartID of WrapperShoppingCart
     */
    public Integer getCartID() {
        return cartID;
    }
}
