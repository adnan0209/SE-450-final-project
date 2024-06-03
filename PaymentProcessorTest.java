package thirdproject;



import org.junit.Test;

import static org.junit.Assert.*;



public class PaymentProcessorTest {



 @Test

 public void testProcessPaymentSuccess() {

 PaymentProcessor paymentProcessor = new PaymentProcessor();

 double amount = 100.0;

 String paymentMethod = "Credit Card";



 assertTrue(paymentProcessor.processPayment(amount, paymentMethod));

 }



 @Test

 public void testProcessPaymentInvalidAmount() {

 PaymentProcessor paymentProcessor = new PaymentProcessor();

 double invalidAmount = -50.0;

 String paymentMethod = "Credit Card";



 assertFalse(paymentProcessor.processPayment(invalidAmount, paymentMethod));

 }



 @Test

 public void testProcessPaymentInvalidMethod() {

 PaymentProcessor paymentProcessor = new PaymentProcessor();

 double amount = 50.0;

 String invalidMethod = "Invalid Method";



 assertFalse(paymentProcessor.processPayment(amount, invalidMethod));

 }



 // Add more tests as needed

}







