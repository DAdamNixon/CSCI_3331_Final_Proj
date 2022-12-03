package main.java;
import java.util.HashMap;
import java.util.LinkedList;

// Abstract base class for users
public abstract class User {
	String username;
	
	HashMap<String, LinkedList<Merchandise>> wishlists;
}
