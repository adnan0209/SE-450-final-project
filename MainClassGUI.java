package thirdproject;







import javax.swing.*;



import java.awt.FlowLayout;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;

import java.util.List;





public class MainClassGUI extends JFrame {

	private static final long serialVersionUID = 1L;

 private User currentUser;

 private UserAuthentication userAuthentication;

 private ShoppingCart shoppingCart;

 private Catalog catalog;



 public MainClassGUI() {

 super("Online Shopping System");

 setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

 setSize(400, 300);

 setLocationRelativeTo(null);



 userAuthentication = new UserAuthentication();

 shoppingCart = new ShoppingCart();

 catalog = new Catalog();



 // Load products into the catalog

 catalog.addProducts(ProductLoader.loadProducts());



 createGUIComponents();



 setVisible(true);

 }



 private void createGUIComponents() {

 JPanel panel = new JPanel(new FlowLayout());

 



 JButton registerButton = new JButton("Register here");

 registerButton.setBounds(20, 20, 120, 30);

 registerButton.addActionListener(new ActionListener() {

 @Override

 public void actionPerformed(ActionEvent e) {

 showRegistrationDialog();

 }

 });

 panel.add(registerButton);



 JButton loginButton = new JButton("Login");

 loginButton.setBounds(160, 20, 120, 30);

 loginButton.addActionListener(new ActionListener() {

 @Override

 public void actionPerformed(ActionEvent e) {

 showLoginDialog();

 }

 });

 panel.add(loginButton);



 JButton browseProductsButton = new JButton("Browse Products");

 browseProductsButton.setBounds(20, 70, 260, 30);

 browseProductsButton.addActionListener(new ActionListener() {

 @Override

 public void actionPerformed(ActionEvent e) {

 showProductList();

 }

 });

 panel.add(browseProductsButton);



 JButton addToCartButton = new JButton("Add to Cart");

 addToCartButton.setBounds(20, 120, 120, 30);

 addToCartButton.addActionListener(new ActionListener() {

 @Override

 public void actionPerformed(ActionEvent e) {

 showAddToCartDialog();

 }

 });

 panel.add(addToCartButton);



 JButton viewCartButton = new JButton("View Cart");

 viewCartButton.setBounds(160, 120, 120, 30);

 viewCartButton.addActionListener(new ActionListener() {

 @Override

 public void actionPerformed(ActionEvent e) {

 showCart();

 }

 });

 panel.add(viewCartButton);

 

 

 





 JButton placeOrderButton = new JButton("Place Order");

 placeOrderButton.setBounds(20, 170, 260, 30);

 placeOrderButton.addActionListener(new ActionListener() {

 @Override

 public void actionPerformed(ActionEvent e) {

 placeOrder();

 }

 });

 panel.add(placeOrderButton);



 add(panel);

 

 

 JButton removeFromCartButton = new JButton("Remove from Cart");

 removeFromCartButton.setBounds(160, 170, 120, 30);

 removeFromCartButton.addActionListener(new ActionListener() {

 @Override

 public void actionPerformed(ActionEvent e) {

 removeFromCartDialog();

 }

 });

 panel.add(removeFromCartButton);



 add(panel);

 

 



 

}





 private void showRegistrationDialog() {

 JTextField usernameField = new JTextField();

 JPasswordField passwordField = new JPasswordField();

 JTextField nameField = new JTextField();

 JTextField emailField = new JTextField();



 Object[] message = {

 "Username:", usernameField,

 "Password:", passwordField,

 "Name:", nameField,

 "Email:", emailField

 };



 int option = JOptionPane.showConfirmDialog(null, message, "Registration", JOptionPane.OK_CANCEL_OPTION);



 if (option == JOptionPane.OK_OPTION) {

 String username = usernameField.getText();

 String password = new String(passwordField.getPassword());

 String name = nameField.getText();

 String email = emailField.getText();



 // Validate input

 if (!isValidUsername(username)) {

 JOptionPane.showMessageDialog(null, "Username must contain only lowercase letters.");

 } else if (!isValidPassword(password)) {

 JOptionPane.showMessageDialog(null, "Password must contain one uppercase letter, one special character, one number, and at least 8 characters.");

 } else if (!isValidName(name)) {

 JOptionPane.showMessageDialog(null, "Name must be in the format 'First Last' with the first letter of each word capitalized.");

 } else if (!isValidEmail(email)) {

 JOptionPane.showMessageDialog(null, "Email must contain the @ symbol.");

 } else {

 userAuthentication.registerUser(username, password, name, email);

 Logger.log("User registered: " + username);

 JOptionPane.showMessageDialog(null, "User registered successfully!");

 }

 }

 }





 private void showLoginDialog() {

 JTextField usernameField = new JTextField();

 JPasswordField passwordField = new JPasswordField();



 Object[] message = {

 "Username:", usernameField,

 "Password:", passwordField

 };



 int option = JOptionPane.showConfirmDialog(null, message, "Login", JOptionPane.OK_CANCEL_OPTION);



 if (option == JOptionPane.OK_OPTION) {

 String loginUsername = usernameField.getText();

 String loginPassword = new String(passwordField.getPassword());



 if (currentUser != null) {

 JOptionPane.showMessageDialog(null, "You are already logged in.");

 } else {

 currentUser = userAuthentication.login(loginUsername, loginPassword);



 if (currentUser != null) {

 Logger.log("User logged in: " + currentUser.getUsername());

 JOptionPane.showMessageDialog(null, "Login successful. Welcome, " + currentUser.getUsername() + "!");

 } else {

 Logger.log("Login failed for user: " + loginUsername);

 JOptionPane.showMessageDialog(null, "Login failed. Please check your credentials.");

 }

 }

 }

 }



 private void showProductList() {

 List<Product> productList = catalog.getAllProducts();



 if (!productList.isEmpty()) {

 DefaultListModel<String> listModel = new DefaultListModel<>();

 

 for (Product product : productList) {

 String productInfo = product.getName() + " - $" + product.getPrice()

 + "\n Description: " + product.getDescription()

 + "\n Quantity in Stock: " + product.getQuantityInStock() + "\n";

 listModel.addElement(productInfo);

 }



 JList<String> productListJList = new JList<>(listModel);

 JScrollPane scrollPane = new JScrollPane(productListJList);



 JOptionPane.showMessageDialog(null, scrollPane, "Product List", JOptionPane.PLAIN_MESSAGE);

 } else {

 JOptionPane.showMessageDialog(null, "No products available in the catalog.", "Product List", JOptionPane.INFORMATION_MESSAGE);

 }

 }





 private void showAddToCartDialog() {

 if (currentUser != null) {

 JTextField productNameField = new JTextField();



 Object[] message = {

 "Enter the product name to add to the cart:", productNameField

 };



 int option = JOptionPane.showConfirmDialog(null, message, "Add to Cart", JOptionPane.OK_CANCEL_OPTION);



 if (option == JOptionPane.OK_OPTION) {

 String productName = productNameField.getText();

 Product selectedProduct = findProductByName(catalog.getAllProducts(), productName);



 if (selectedProduct != null) {

 shoppingCart.addItem(selectedProduct);

 JOptionPane.showMessageDialog(null, "Product added to the cart.");

 } else {

 JOptionPane.showMessageDialog(null, "Product not found in the catalog.");

 }

 }

 } else {

 JOptionPane.showMessageDialog(null, "You need to log in to add products to the cart.");

 }

 }





 

 

 private void removeFromCartDialog() {

 if (currentUser != null) {

 List<Product> cartItems = shoppingCart.getItems();



 if (!cartItems.isEmpty()) {

 JTextField productNameField = new JTextField();



 Object[] message = {

 "Enter the product name to remove from the cart:", productNameField

 };



 int option = JOptionPane.showConfirmDialog(null, message, "Remove from Cart", JOptionPane.OK_CANCEL_OPTION);



 if (option == JOptionPane.OK_OPTION) {

 String productName = productNameField.getText();

 Product productToRemove = findProductByName(cartItems, productName);



 if (productToRemove != null) {

 shoppingCart.removeItem(productToRemove);

 JOptionPane.showMessageDialog(null, "Product removed from the cart.");

 } else {

 JOptionPane.showMessageDialog(null, "Product not found in the cart.");

 }

 }

 } else {

 JOptionPane.showMessageDialog(null, "Your shopping cart is empty.", "Remove from Cart", JOptionPane.INFORMATION_MESSAGE);

 }

 } else {

 JOptionPane.showMessageDialog(null, "You need to log in to remove products from the cart.");

 }

 }

 private Product findProductByName(List<Product> products, String productName) {

 for (Product product : products) {

 if (product.getName().equalsIgnoreCase(productName)) {

 return product;

 }

 }

 return null;

 }





	private void showCart() {

 java.util.List<Product> cartItems = shoppingCart.getItems();



 if (!cartItems.isEmpty()) {

 DefaultListModel<String> listModel = new DefaultListModel<>();



 for (Product item : cartItems) {

 String itemInfo = item.getName() + " - $" + item.getPrice();

 listModel.addElement(itemInfo);

 }



 JList<String> cartItemList = new JList<>(listModel);

 JScrollPane scrollPane = new JScrollPane(cartItemList);



 double totalPrice = calculateTotalPrice(shoppingCart);

 String totalPriceMessage = "Total Price: $" + totalPrice;



 JOptionPane.showMessageDialog(null, new Object[]{scrollPane, totalPriceMessage}, "Shopping Cart", JOptionPane.PLAIN_MESSAGE);

 } else {

 JOptionPane.showMessageDialog(null, "Your shopping cart is empty.", "Shopping Cart", JOptionPane.INFORMATION_MESSAGE);

 }

 }

	

	private void placeOrder() {

	if (currentUser != null) {

	System.out.println("Placing an order...");



	// Placeholder for calculating total price

	double totalPrice = calculateTotalPrice(shoppingCart);



	// Placeholder for processing payment (replace with your payment processing logic)

	boolean paymentSuccess = new PaymentProcessor().processPayment(totalPrice, "Credit Card");



	if (paymentSuccess) {

	Logger.log("Order placed for user: " + currentUser.getUsername());

	JOptionPane.showMessageDialog(null, "Order placed successfully. Payment received.");

	// Clear the cart after successful payment

	} else {

	Logger.log("Payment failed for user: " + currentUser.getUsername());

	JOptionPane.showMessageDialog(null, "Payment failed. Please try again.");

	}

	} else {

	JOptionPane.showMessageDialog(null, "You need to log in to place an order.");

	}

	}







	private double calculateTotalPrice(ShoppingCart shoppingCart) {

 double total = 0.0;

 for (Product product : shoppingCart.getItems()) {

 total += product.getPrice();

 }

 return total;

 }

	private boolean isValidUsername(String username) {

	// Username must contain only lowercase letters.

	return username.matches("^[a-z]+$");

	}



	private boolean isValidPassword(String password) {

	// Password must contain one uppercase letter, one special character, one number, and at least 8 characters.

	return password.matches("^(?=.*[A-Z])(?=.*[!@#$%^&*()])(?=.*[0-9])(?=.*[a-z]).{8,}$");

	}



	private boolean isValidName(String name) {

	// Name must be in the format 'First Last' with the first letter of each word capitalized.

	return name.matches("^[A-Z][a-z]*\\s[A-Z][a-z]*$");

	}



	private boolean isValidEmail(String email) {

	// Email must contain the @ symbol.

	return email.contains("@");

	}





	



 public static void main(String[] args) {

 SwingUtilities.invokeLater(new Runnable() {

 @Override

 public void run() {

 new MainClassGUI();

 }

 });

 }

}







