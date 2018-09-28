package de.chrissx.server.file;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class FileLoader {

	public static String getFirstLine(Path path) throws IOException {
		return getLine(path, 0);
	}
	
	public static List<String> getText(Path path) throws IOException {
		return Files.readAllLines(path);
	}
	
	public static String getLine(Path path, int i) throws IOException {
		return getText(path).get(i);
	}
}