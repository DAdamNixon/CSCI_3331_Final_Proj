package main.java;

public class Resources {
	private static String resourcePath = "./main/resources/";
	private static String dataPath = "./bin/main/resources/";

	static String imagePath(String path) {
		return Resources.resourcePath + path;
	}

	static String dataPath(String path) {
		return Resources.dataPath + path;
	}
}
