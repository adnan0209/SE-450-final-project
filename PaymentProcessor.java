package thirdproject;



class PaymentProcessor {

 public boolean processPayment(double amount, String paymentMethod) {

 // Simulate payment processing using a mock payment gateway



 // Check if the payment method is valid (e.g., Credit Card, PayPal, etc.)

 if (isValidPaymentMethod(paymentMethod)) {

 // Check if the amount is valid

 if (amount > 0) {

 // Simulate a successful payment

 System.out.println("Processing payment of $" + amount + " via " + paymentMethod);

 System.out.println("Payment successful.");

 return true;

 } else {

 System.out.println("Invalid payment amount.");

 }

 } else {

 System.out.println("Invalid payment method.");

 }



 // If payment processing fails, return false

 System.out.println("Payment processing failed.");

 return false;

 }



 private boolean isValidPaymentMethod(String paymentMethod) {

 // Simulate validation of payment method (e.g., check against a list of supported methods)

 // For simplicity, we'll assume all payment methods are valid.

 return !"Invalid Method".equals(paymentMethod);

 }

}