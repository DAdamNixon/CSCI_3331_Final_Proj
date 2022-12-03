package main.java;

// Resources is a pipeline for using resource files
// Static class which appends filepaths
public class Resources {
	private static String resourcePath = "./main/resources/";
	private static String dataPath = "./src/main/resources/";

	// Gets the filename specified image file from the resource directory
	static String imagePath(String path) {
		return Resources.resourcePath + path;
	}

	// Gets the filename specified data file from the resource directory
	static String dataPath(String path) {
		return Resources.dataPath + path;
	}
}
