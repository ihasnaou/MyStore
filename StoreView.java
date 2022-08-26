package mystore;
// Ilyes Hasnaou 101146081 //
// Marina Latif - 101149148 //


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;


public class StoreView {
    private StoreManager storeManager;
    private Integer cartID;
    private final JFrame frame;
    private JPanel products[] = new JPanel[6];;

    public static void main(String[] args) {
        StoreManager sm = new StoreManager();
        StoreView sv1 = new StoreView(sm);
        StoreView sv2 = new StoreView(sm);
        StoreView sv3 = new StoreView(sm);
        StoreView[] users = {sv1, sv2, sv3};
        displayGUI(users[0]);
        displayGUI(users[1]);
    }


    /**
     * Constructor for the StoreView class
     * @param sm Storemanager store manager for the StoreView class
     */
    public StoreView(StoreManager sm) {
        this.storeManager = sm;
        this.cartID = sm.assignNewCartID();

        this.frame = new JFrame();
        JPanel mainPanel = new JPanel(new BorderLayout());


        JLabel headerLabel = new JLabel("Welcome to the Store! (ID: " + cartID + ")");
        JPanel headerPanel = new JPanel();
        headerLabel.setBackground(getColour());
        headerPanel.setPreferredSize(new Dimension(250, 40));
        headerPanel.add(headerLabel, SwingConstants.CENTER);
        mainPanel.add(headerPanel, BorderLayout.PAGE_START);


        JPanel bodyPanel = new JPanel(new GridLayout(2, 3));
        for (int i = 0; i < storeManager.getInv().getInventory().size(); i++) {
            int index = i;
            products[i] = new JPanel(new BorderLayout());
            products[i].setBackground(getColour());
            JLabel productInfo = new JLabel(browseInventory(i), SwingConstants.CENTER);
            products[i].add(productInfo, BorderLayout.PAGE_START);

            JPanel buttons = new JPanel(new GridLayout());
            JButton removeFromCart = new JButton(" Remove item");
            removeFromCart.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent ae) {
                    removeFromCart(index);

                    BorderLayout layout = (BorderLayout)products[index].getLayout();
                    products[index].remove(layout.getLayoutComponent(BorderLayout.PAGE_START));;

                    JLabel productNewInfo = new JLabel(browseInventory(index), SwingConstants.CENTER);
                    products[index].add(productNewInfo, BorderLayout.PAGE_START);

                    products[index].revalidate();
                    products[index].repaint();
                }
            });
            buttons.add(removeFromCart);


            JButton addToCart = new JButton(" + ");
            addToCart.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent ae) {
                    addToCart(index);

                    BorderLayout layout = (BorderLayout)products[index].getLayout(); //https://stackoverflow.com/questions/759321/removing-the-center-element-from-a-jpanel-using-borderlayout
                    products[index].remove(layout.getLayoutComponent(BorderLayout.PAGE_START));

                    JLabel productNewInfo = new JLabel(browseInventory(index), SwingConstants.CENTER);
                    products[index].add(productNewInfo, BorderLayout.PAGE_START);

                    products[index].revalidate();
                    products[index].repaint();
                }
            });
            buttons.add(addToCart);

            ImageIcon image = new ImageIcon(getClass().getResource(storeManager.getInv().getInventory().get(i).getProduct().getFileName()));
            JLabel imageIcon = new JLabel(image);
            products[i].add(imageIcon, BorderLayout.CENTER);

            products[i].add(buttons, BorderLayout.PAGE_END);
            bodyPanel.add(products[i]);
        }

        JPanel footerPanel = new JPanel();
        footerPanel.setPreferredSize(new Dimension(250, 40));

        JButton viewCart = new JButton("View Cart");
        viewCart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                JFrame cart = new JFrame();
                cart.setTitle("Cart Viewer (ID: " + cartID + ")");
                cart.add(new JLabel("Current cart holds:", SwingConstants.CENTER), BorderLayout.PAGE_START);

                DefaultListModel<String> cartItems = new DefaultListModel<>(); // https://www.javatpoint.com/java-jlist
                for (int i = 0; i < storeManager.getCarts().get(cartID).getShoppingCart().getsC().size(); i++) {
                    cartItems.addElement(viewCart(i));
                }

                JList<String> c = new JList<>(cartItems);
                cart.add(c, BorderLayout.CENTER);
                cart.setSize(new Dimension(250, 250));
                cart.setVisible(true);
            }
        });
        footerPanel.add(viewCart, BorderLayout.WEST);

        JButton checkout = new JButton("Checkout (ID: " + cartID + ")");
        checkout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JFrame checkoutFrame = new JFrame();
                checkoutFrame.setTitle("Checkout Window");
                checkoutFrame.add(new JLabel("Proceed with these items?", SwingConstants.CENTER), BorderLayout.PAGE_START);

                DefaultListModel<String> checkoutItems = new DefaultListModel<>();
                for (int i = 0; i < storeManager.getCarts().get(cartID).getShoppingCart().getsC().size(); i++) {
                    checkoutItems.addElement(viewCart(i));
                }


                JList<String> cc = new JList<>(checkoutItems);
                checkoutFrame.add(cc, BorderLayout.CENTER);


                JPanel options = new JPanel(new BorderLayout());
                options.add(new JLabel("Your total is $" + checkout(), SwingConstants.CENTER), BorderLayout.NORTH);

                JButton yes = new JButton("Yes");
                yes.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent actionEvent) {
                        checkoutFrame.add(new JLabel("Thank you for shopping with us!"));
                        checkoutFrame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
                        frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
                    }
                });

                JButton no = new JButton("Cancel");
                no.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent actionEvent) {
                        checkoutFrame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
                    }
                });

                JPanel yesOrNo = new JPanel();
                yesOrNo.add(yes, BorderLayout.WEST);
                yesOrNo.add(no, BorderLayout.EAST);
                options.add(new JLabel("Confirm?", SwingConstants.CENTER), BorderLayout.CENTER);
                options.add(yesOrNo, BorderLayout.SOUTH);


                checkoutFrame.add(options, BorderLayout.SOUTH);
                checkoutFrame.setSize(250, 250);
                checkoutFrame.setVisible(true);
            }
        });
        footerPanel.add(checkout, BorderLayout.EAST);

        mainPanel.add(headerPanel, BorderLayout.PAGE_START);
        mainPanel.add(bodyPanel, BorderLayout.CENTER);
        mainPanel.add(footerPanel, BorderLayout.PAGE_END);

        frame.add(mainPanel);
        frame.pack();
        frame.setTitle("Client StoreView");
        frame.setSize(800, 500);
    }

    /**
     * Displays the GUI
     */
    public static void displayGUI(StoreView storeView) {
        storeView.frame.setVisible(true);
    }

    /**
     * Gives a string containing a product's name, available stock and price
     * @param i int Index of the product we needed in Inventory
     * @return String presenting the needed product information
     */
    public String browseInventory(int i) {
        return storeManager.getInv().getInventory().get(i).getProduct().getName() + " | " + storeManager.getStock(storeManager.getInv().getInventory().get(i).getProduct()) + " | $" + storeManager.getInv().getInventory().get(i).getProduct().getPrice();

    }

    /**
     * Displays a list of commands
     */
    public void help() {
        System.out.println("Possible commands:\n'add to cart' to add an item to cart\n'browse' to see the current items we have in the inventory\n'checkout' to process your transaction\n'help' to see a list of commands\n'remove from cart' to remove an item from cart\n'view cart' to view your cart");
    }

    /**
     * Adds an item to cart
     * @param i int Index of the item in the Inventory to be added to cart
     */
    public void addToCart(int i) {
        storeManager.addToCart(cartID, storeManager.getInv().getInventory().get(i).getProduct(), 1);
    }

    /**
     * Displays the content of a specific item in the cart
     * @param i int Index of the item in the cart
     * @return String presenting the product's name and its quantity in the cart
     */
    public String viewCart(int i) {
        return storeManager.getCarts().get(cartID).getShoppingCart().getsC().get(i).getProduct().getName() + " | Quantity: " + storeManager.getCarts().get(cartID).getShoppingCart().getsC().get(i).stock;
    }

    /**
     * Removes an item in the cart
     * @param j int Index of the item in the cart
     */
    public void removeFromCart(int j) {
        for (int i = 0; i < storeManager.getCarts().get(cartID).getShoppingCart().getsC().size(); i++) {
            if (storeManager.getInv().getInventory().get(j).getProduct().equals(storeManager.getCarts().get(cartID).getShoppingCart().getsC().get(i).getProduct())) {
                storeManager.removeFromCart(cartID, storeManager.getCarts().get(cartID).getShoppingCart().getsC().get(i).getProduct());
            }
        }
    }

    /**
     * Returns the price to pay of a cart
     * @return Double price to pay
     */
    public Double checkout() {
        return storeManager.processTransaction(cartID);
    }

    /**
     * Gives a new random Color object
     * @return random Color
     */
    public static Color getColour() {
        int r = (int)(Math.random()*256);
        int g = (int)(Math.random()*256);
        int b = (int)(Math.random()*256);
        double luma = (0.2126 * r) + (0.7152 * g) + (0.0722 * b);

        while (luma < 75) {
            r = (int)(Math.random()*256);
            g = (int)(Math.random()*256);
            b = (int)(Math.random()*256);
            luma = (0.2126 * r) + (0.7152 * g) + (0.0722 * b);
        }

        return new Color(r, g, b);
    }
}