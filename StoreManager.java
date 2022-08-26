package mystore;
// Ilyes Hasnaou 101146081 //
// Marina Latif - 101149148 //

import java.util.ArrayList;

public class StoreManager {
    private Inventory inv;
    private ArrayList<WrapperShoppingCart> carts;
    private static Integer cartID = 0;

    /**
     * Returns the stock of a given product in Inventory
     * @param product Product product needed to get stock of
     * @return Integer stock of product provided
     */
    public Integer getStock(Product product) {
        for (int i = 0; i < inv.getInventory().size(); i++) {
            if (product.equals(inv.getInventory().get(i).getProduct())) {
                return inv.getStock(product.getID());
            }
        }
        return -1;
    }

    /**
     * Adds an number of an item to a cart removes that same number of items from inv
     * @param cartID String cart ID of the cart where the product will be added
     * @param added Product product to be added to the cart and removed from inventory
     * @param stock Integer amount of the product to be added to cart and removed from inventory
     */
    public void addToCart(Integer cartID, Product added, Integer stock) {
        try {
            Integer index = getCartIndex(cartID);
            if (inv.getStock(added.getID()) >= stock) {
                carts.get(index).getShoppingCart().addProduct(added, stock);
                inv.removeStock(added.getID(), stock);
            }
        } catch(ArithmeticException e) {
            System.out.println("Not enough of " + added.getName() + " . We apologize for the inconvenience.");
        }
    }

    /**
     * Removes a product from cart and adds it back to the inventory
     * @param cartID String cart ID of the cart where the product will be removed
     * @param removed Product product to be removed to the cart and added back to inventory
     */
    public void removeFromCart(Integer cartID, Product removed) {
        Integer index = getCartIndex(cartID);
        inv.addStock(removed.getID(), carts.get(index).getShoppingCart().getStock(removed.getID()));
        carts.get(index).getShoppingCart().removeProduct(removed.getName(), carts.get(index).getShoppingCart().getStock(removed.getID()));
    }

    /**
     * Gives the index of a cart in carts based on its id
     * @param cartId String cart id needed to find the corresponding cart in carts
     * @return Integer index of the cart we need in carts
     */
    private int getCartIndex(Integer cartId) {
        int index = -1;
        try {
            for (int i = 0 ; i < carts.size(); i++) {
                if (cartId == carts.get(i).getCartID()) {
                    index = i;
                }
            }
            if (index < 0) {
                throw new ArrayIndexOutOfBoundsException();
            }
        } catch(ArrayIndexOutOfBoundsException e) {
            System.out.println("No cart with that cart ID exist.");
        }
        return index;
    }

    /**
     * Processes the transaction and checks out a given cart
     * @param cartID cart ID of the cart we will process the transaction of
     * @return total price to pay based on the contents of the cart
     */
    public Double processTransaction(Integer cartID) {
        Double totalPrice = 0.0;
        Integer index = getCartIndex(cartID);
        for (int i = 0; i < carts.get(index).getShoppingCart().getsC().size(); i++) {
            totalPrice +=  (carts.get(index).getShoppingCart().getsC().get(i).getProduct().getPrice() * carts.get(index).getShoppingCart().getsC().get(i).stock);
        }
        return totalPrice;
    }

    /**
     * Assigns a new cart ID to a new cart while adding it to carts
     * @return the cart ID given to the new cart
     */
    public Integer assignNewCartID() {
        carts.add(new WrapperShoppingCart(new ShoppingCart() , cartID ));
        cartID += 1;
        return cartID - 1;
    }

    /**
     * Default constructor for the StoreManager Class
     */
    public StoreManager() {
        this.inv = new Inventory();
        this.carts = new ArrayList<WrapperShoppingCart>();
    }

    /**
     * Getter for the inventory
     * @return Inventory
     */
    public Inventory getInv() {
        return inv;
    }

    /**
     * Getter for the carts
     * @return ArrayList<WrapperShoppingCart> all carts in StoreManager
     */
    public ArrayList<WrapperShoppingCart> getCarts() {
        return carts;
    }


}
