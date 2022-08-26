package mystore;
// Ilyes Hasnaou 101146081 //
// Marina Latif - 101149148 //

import java.util.ArrayList;
import java.lang.StringBuilder;

public class ShoppingCart implements ProductStockContainer{
    private ArrayList<WrapperProduct> sC;

    /**
     * Adds a given product to cart
     * @param product Product product to be added
     * @param stock Integer amount of product to be added
     */
    public void addProduct(Product product, Integer stock) {
        for (int i = 0; i < sC.size(); i++) {
            if (sC.get(i).getProduct().getID().equals(product)) {
                sC.get(i).stock += stock;
                return;
            }
        }
        sC.add( new WrapperProduct(product, stock));
    }

    /**
     * Removes a given product from cart
     * @param productName String name of the product to be removed
     * @param quantity Integer quantity to be removed of said product
     */
    public void removeProduct(String productName, Integer quantity) {
        for (int i = 0; i < sC.size(); i++) {
            if (productName.equals(sC.get(i).getProduct().getName())) {
                sC.get(i).stock -= quantity;
                return;
            }
        }
    }

    /**
     * Prints the items of a given cart
     */
    public void printCartItems() {
        StringBuilder sb = new StringBuilder();
        sb.append("You currently have:");
        for (int i = 0; i < sC.size(); i++) {
            sb.append(" " + sC.get(i).stock + " "+ sC.get(i).getProduct().getName() +  " + ");
        }
        sb.deleteCharAt(sb.length() - 2);
        System.out.println(sb);
    }

    /**
     * Getter method for sC
     * @return ArrayList<WrapperProduct> sC
     */
    public ArrayList<WrapperProduct> getsC() {
        return sC;
    }

    /**
     * Default constructor for the shopping cart class
     */
    public ShoppingCart() {
        this.sC = new ArrayList<WrapperProduct>();
    }

    /**
     * Gets the stock of a product given a productID
     * @param productID String product ID of the product to get stock of
     * @return Integer stock of the product chosen
     */
    public Integer getStock(String productID) {
        int stock = 0;
        boolean found = false;
        try {
            for (int i = 0; i < sC.size(); i++) {
                if (productID.equals(sC.get(i).getProduct().getID())) {
                    stock = sC.get(i).stock;
                    found = true;
                }
            }
            if (!found) {
                throw new IllegalArgumentException();
            }
        } catch (IllegalArgumentException e){
            System.out.println("This product was not found in the cart, therefore we cannot give you the correct stock.");
            stock = -1;
        } finally {
            return stock;
        }
    }

    /**
     * Gives the number of Products in the ShoppingCart
     * @return number of Products in the ShoppingCart
     */
    public int getNumOfProducts() {return sC.size(); }
}
