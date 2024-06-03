package thirdproject;



import java.util.ArrayList;

import java.util.List;



public class UserAuthentication {

 private List<User> users;



 public UserAuthentication() {

 this.users = new ArrayList<>();

 }



 public void registerUser(String username, String password, String name, String email) {

 // Check if the username is already in use

 if (isUsernameTaken(username)) {

 System.out.println("Username is already taken. Please choose a different one.");

 return;

 }



 // Create a new user and add them to the list of users

 User newUser = new User(username, password, name, email);

 users.add(newUser);

 System.out.println("User registered successfully!");

 }



 public User login(String username, String password) {

 for (User user : users) {

 if (user.getUsername().equals(username) && user.getPassword().equals(password)) {

 return user; // User found, return the user object

 }

 }

 return null; // User not found

 }



 private boolean isUsernameTaken(String username) {

 for (User user : users) {

 if (user.getUsername().equals(username)) {

 return true; // Username is already in use

 }

 }

 return false; // Username is available

 }



	public boolean isValidPassword(String password) {

	// TODO Auto-generated method stub

	return false;

	}



	public boolean isValidUsername(String username) {

	// TODO Auto-generated method stub

	return false;

	}

}




