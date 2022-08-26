package mystore;
// Ilyes Hasnaou 101146081 //
// Marina Latif - 101149148 //

import java.util.ArrayList;

public class Inventory implements ProductStockContainer{

    private ArrayList<WrapperProduct>  inventory;

    /**
     * Adds a product to the inventory
     * @param product Product to be added
     * @param stock Integer amount of the product to be added
     */
    public void addProduct(Product product, Integer stock) {
        inventory.add( new WrapperProduct(product, stock));
    }

    /**
     * Returns the complete product information given the product ID
     * @param productID String product ID of the product in question
     */
    public void getProductInformation(String productID) {
        boolean found = false;
        try {
            for (int i = 0; i < inventory.size(); i++) {
                if (productID.equals(inventory.get(i).getProduct().getID())) {
                    System.out.println("This is a " + inventory.get(i).getProduct().getName() + "that costs $" + inventory.get(i).getProduct().getPrice());
                    found = true;
                    return;
                }
            }
            if (!found) {
                throw new IllegalArgumentException();
            }
        } catch (IllegalArgumentException illegalArgumentException) {
            System.out.println("This product does not exist.");
        }
    }

    /**
     * Gives the amount of a product in the inventory given a product ID
     * @param productID String product ID of the product in question
     * @return Integer amount of product in the Inventory
     */
    public Integer getStock(String productID) {
        int stock = 0;
        boolean found = false;
        try {
            for (int i = 0; i < inventory.size(); i++) {
                if (productID.equals(inventory.get(i).getProduct().getID())) {
                    stock = inventory.get(i).stock;
                    found = true;
                }
            }
            if (!found) {
                throw new IllegalArgumentException();
            }
        } catch(IllegalArgumentException illegalArgumentException) {
            System.out.println("This product was not found in the Inventory, therefore we cannot give you the correct stock.");
            stock = -1;
        }
        finally {
            return stock;
        }
    }

    /**
     * Adds a given amount of product in the Inventory given the product ID
     * @param productID String product ID of the product in question
     * @param productStock Integer amount of Stock to add
     */
    public void addStock(String productID, Integer productStock) {
        boolean found = false;
        try {
            for (int i = 0; i < inventory.size(); i++) {
                if (productID.equals(inventory.get(i).getProduct().getID())) {
                    inventory.get(i).stock += productStock;
                    found = true;
                    return;
                }
            }
            if (!found) {
                throw new IllegalArgumentException();
            }
        } catch (IllegalArgumentException illegalArgumentException) {
            System.out.println("This product does not exist. Please add the product first and try again.");
        }
    }

    /**
     * Removes a given amount of a product from the Inventory
     * @param productID String product ID of the product to be removed
     * @param productStock amount to be removed
     */
    public void removeStock(String productID, Integer productStock) {
        boolean found = false;
        try {
            for (int i = 0; i < inventory.size(); i++) {
                if (productID.equals(inventory.get(i).getProduct().getID())) {
                    inventory.get(i).stock -= productStock;
                    found = true;
                    if (inventory.get(i).stock < 0) {
                        inventory.get(i).stock = 0;
                    }
                }
            }
            if (!found) {
                throw new IllegalArgumentException();
            }
        } catch (IllegalArgumentException e) {
            System.out.println("This product does not exist. Please add the product first and try again.");
        }
    }

    /**
     * Removes a given product from the Inventory
     * @param productName String name of the product to be removed
     * @param quantity Integer quantity to be removed of said product
     */
    public void removeProduct(String productName, Integer quantity) {
        for (int i = 0; i < inventory.size(); i++) {
            if (productName.equals(inventory.get(i).getProduct().getName())) {
                inventory.get(i).stock -= quantity;
                return;
            }
        }
    }

    /**
     * Getter for the inventory
     * @return ArrayList<WrapperProduct> inventory
     */
    public ArrayList<WrapperProduct> getInventory() {
        return inventory;
    }

    /**
     * Gives the number of Products in the Inventory
     * @return number of Products in the Inventory
     */
    public int getNumOfProducts() {return inventory.size(); }

    /**
     * Constructor for the Inventory class that initializes itself with default values
     */
    public Inventory() {
        this.inventory = new ArrayList<>();
        Product p1 = new Product("Apple", "101", 2.50, "images/apples.png");
        Product p2 = new Product("Pear", "102", 4.00, "images/pears.png");
        Product p3 = new Product("Tomato", "103", 1.50, "images/tomatoes.png");
        Product p4 = new Product("Onion", "104", 5.00, "images/onions.png");
        Product p5 = new Product("Potato", "105", 7.50, "images/potatoes.png");
        Product p6 = new Product("Carrot", "106", 12.50, "images/carrots.png");
        this.addProduct(p1, 30);
        this.addProduct(p2, 45);
        this.addProduct(p3, 40);
        this.addProduct(p4, 55);
        this.addProduct(p5, 125);
        this.addProduct(p6, 20);
    }

    //https://docs.oracle.com/javase/8/docs/api/java/util/ArrayList.html
}
