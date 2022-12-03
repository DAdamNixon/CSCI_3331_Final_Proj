package main.java;
import java.util.HashMap;
import java.util.LinkedList;

public abstract class User {
	String username;
	
	HashMap<String, LinkedList<Merchandise>> wishlists;
}
