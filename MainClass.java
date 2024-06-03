package thirdproject;

import java.util.Scanner;
import java.util.List;
import java.util.regex.Pattern;

public class MainClass {

    private static Scanner scanner = new Scanner(System.in);
    private static User currentUser = null;
    private static UserAuthentication userAuthentication = new UserAuthentication();
    private static ShoppingCart shoppingCart = new ShoppingCart();
    private static Catalog catalog = new Catalog();
    private static PaymentProcessor paymentProcessor = new PaymentProcessor();

    public static void main(String[] args) {
        List<Product> products = ProductLoader.loadProducts();
        catalog.addProducts(products);

        while (true) {
            System.out.println("\nAvailable Options:");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Browse Products");
            System.out.println("4. Add Product to Cart");
            System.out.println("5. View Cart");
            System.out.println("6. Place the Order");
            System.out.println("7. Process a Payment");
            System.out.println("8. Exit");

            if (currentUser != null) {
                System.out.println("Logged in as: " + currentUser.getUsername());
            }

            System.out.print("Please select an option (1-8): ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    registerUser();
                    break;
                case 2:
                    loginUser();
                    break;
                case 3:
                    displayProductList();
                    break;
                case 4:
                    addProductToCart();
                    break;
                case 5:
                    viewCart();
                    break;
                case 6:
                    placeOrder();
                    break;
                case 7:
                    processPayment();
                    break;
                case 8:
                    System.out.println("Exiting the program...");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid option selected. Please try again.");
            }
        }
    }

    private static void registerUser() {
        System.out.print("Enter your username: ");
        String username = scanner.nextLine();
        System.out.print("Enter your password (Password must contain at least one uppercase letter, one special character, one number, and be at least 8 characters long): ");
        String password = scanner.nextLine();
        System.out.print("Enter your full name (Note: name must be in the format 'First Last' with the first letter of each word capitalized): ");
        String name = scanner.nextLine();
        System.out.print("Enter your email address: ");
        String email = scanner.nextLine();

        if (!isValidUsername(username) || !isValidPassword(password) || !isValidName(name) || !isValidEmail(email)) {
            System.out.println("Invalid input, registration failed. Please check your inputs and try again.");
        } else {
            userAuthentication.registerUser(username, password, name, email);
            Logger.log("User registered: " + username);
            System.out.println("Registration successful. Welcome, " + name + "!");
        }
    }

    private static void loginUser() {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        currentUser = userAuthentication.login(username, password);
        if (currentUser != null) {
            Logger.log("User logged in: " + currentUser.getUsername());
            System.out.println("Login successful. Welcome back, " + currentUser.getName() + "!");
        } else {
            Logger.log("Login failed for user: " + username);
            System.out.println("Login failed. Please check your credentials and try again.");
        }
    }

    private static void displayProductList() {
        List<Product> productList = catalog.getAllProducts();
        System.out.println("Product List:");
        for (Product product : productList) {
            System.out.println(product.getName() + " - $" + product.getPrice() + " Description: " + product.getDescription());
        }
    }

    private static void addProductToCart() {
        System.out.print("Enter the product name to add to the cart: ");
        String productName = scanner.nextLine();
        Product selectedProduct = findProductByName(productName);

        if (selectedProduct != null) {
            shoppingCart.addItem(selectedProduct);
            System.out.println("Product added to the cart successfully.");
        } else {
            System.out.println("Product not found in the catalog.");
        }
    }

    private static void viewCart() {
        if (currentUser != null) {
            List<Product> items = shoppingCart.getItems();
            System.out.println("Cart for " + currentUser.getUsername() + ":");
            for (Product item : items) {
                System.out.println(item.getName() + " - $" + item.getPrice());
            }
            double totalPrice = calculateTotalPrice(shoppingCart);
            System.out.println("Total Price: $" + totalPrice);
        } else {
            System.out.println("You need to log in to view your cart.");
        }
    }

    private static void placeOrder() {
        if (currentUser != null) {
            System.out.println("Placing the order...");
            double totalPrice = calculateTotalPrice(shoppingCart);
            boolean paymentSuccess = paymentProcessor.processPayment(totalPrice, "Credit Card");
            if (paymentSuccess) {
                Logger.log("Order placed for user: " + currentUser.getUsername());
                System.out.println("Order placed successfully.");
            } else {
                Logger.log("Payment failed for user: " + currentUser.getUsername());
                System.out.println("Payment failed. Please try again.");
            }
        } else {
            System.out.println("You need to log in to place an order.");
        }
    }

    private static void processPayment() {
        System.out.print("Enter the payment amount: $");
        double amount = scanner.nextDouble();
        scanner.nextLine();  // Consume newline left-over

        System.out.print("Enter the payment method (e.g., Credit Card, PayPal): ");
        String paymentMethod = scanner.nextLine();

        boolean success = paymentProcessor.processPayment(amount, paymentMethod);
        if (success) {
            System.out.println("Payment processed successfully!");
        } else {
            System.out.println("Payment processing failed.");
        }
    }

    // Validation and helper methods
    private static boolean isValidUsername(String username) {
        return Pattern.matches("^[a-z]+$", username);
    }

    private static boolean isValidPassword(String password) {
        return Pattern.matches("^(?=.*[A-Z])(?=.*[!@#$%^&*()])(?=.*[0-9])(?=.*[a-z]).{8,}$", password);
    }

    private static boolean isValidName(String name) {
        return Pattern.matches("^[A-Z][a-z]* [A-Z][a-z]*$", name);
    }

    private static boolean isValidEmail(String email) {
        return email.contains("@");
    }

    private static Product findProductByName(String productName) {
        return catalog.getAllProducts().stream()
                .filter(p -> p.getName().equalsIgnoreCase(productName))
                .findFirst()
                .orElse(null);
    }

    private static double calculateTotalPrice(ShoppingCart shoppingCart) {
        return shoppingCart.getItems().stream()
                .mapToDouble(Product::getPrice)
                .sum();
    }
}
