package main.java;

import java.io.File;
import java.net.URISyntaxException;

public class Tester {
	public static void main(String[] args) {
		
		try {
			File root = new File(Thread.currentThread().getContextClassLoader().getResource("").toURI());

			System.out.println(root.getAbsolutePath());
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}

	}
}
