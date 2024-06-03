package thirdproject;



import java.util.ArrayList;

import java.util.List;



class ShoppingCart {

 private List<Product> items = new ArrayList<>();



 public void addItem(Product product) {

 items.add(product);

 }



 public void removeItem(Product product) {

 items.remove(product);

 }



 public List<Product> getItems() {

 return items;

 }



 public double getTotalAmount() {

 double total = 0.0;

 for (Product item : items) {

 total += item.getPrice();

 }

 return total;

 }



	public void addToCart(Product product1) {

	// TODO Auto-generated method stub

	

	}



	public void removeFromCart(Product product1) {

	// TODO Auto-generated method stub

	

	}

}









