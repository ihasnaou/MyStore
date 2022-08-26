package mystore;
// Ilyes Hasnaou 101146081 //
// Marina Latif - 101149148 //


import java.net.URL;

public class Product {
    private final String name;
    private final String id;
    private final double price;
    private final String fileName;

    /**
     * Constructor for the Product class
     * @param name String name of the product
     * @param id String id of the product
     * @param price String price of the product
     */
    public Product(String name, String id, double price, String fileName) {
        this.name = name;
        this.id = id;
        this.price = price;
        this.fileName = fileName;
    }

    /**
     * Getter for the name
     * @return String name
     */
    public String getName() { return name; }

    /**
     * Getter for the ID
     * @return String ID
     */
    public String getID() { return id; }

    /**
     * Getter for the price
     * @return Double price
     */
    public double getPrice() { return price; }

    /**
     * Checks 2 products to see if they are equal
     * @param product Product 2nd product to compare
     * @return True if they have the same name, same id and same price, false otherwise
     */
    public boolean equals(Product product) {
        if (name.equals(product.getName())) {
            if (id.equals(product.getID())) {
                if (price == product.getPrice()) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Getter for the filename attribute
     * @return String name of the png file needed for display
     */
    public String getFileName() {
        return fileName;
    }
}
