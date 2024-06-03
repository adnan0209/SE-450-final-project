package thirdproject;



class Order {

 private User user;

 private ShoppingCart cart;

 private String orderStatus;



 public Order(User user, ShoppingCart cart) {

 this.user = user;

 this.cart = cart;

 this.orderStatus = "Pending";

 }



 public void placeOrder() {

 if (orderStatus.equals("Pending")) {

 // Simulate order placement logic

 System.out.println("Placing the order for User: " + user.getUsername());



 System.out.println("Order Details:");

 for (Product product : cart.getItems()) {

 System.out.println(product.getName() + " - $" + product.getPrice());

 }



 System.out.println("Total Amount: $" + cart.getTotalAmount());



 // Implement additional order placement logic here, such as recording order details, updating inventory, etc.

 // For this example, we'll keep it simple by updating the order status.

 this.orderStatus = "Placed";



 System.out.println("Order placed successfully.");

 } else {

 System.out.println("Order has already been placed.");

 }

 }



 public User getUser() {

 return user;

 }



 public ShoppingCart getCart() {

 return cart;

 }



 public String getOrderStatus() {

 return orderStatus;

 }

}



// You can use the Order class in your ECommerceSystem or wherever needed.





