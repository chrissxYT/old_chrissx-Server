package de.chrissx.server.file;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class FileWriter {
	
	public static void write(File f, String s, boolean append) throws IOException {
		BufferedWriter writer = new BufferedWriter(new java.io.FileWriter(f, append));
		String[] strs = s.split("\n");
		writer.write(strs[0]);
		writer.flush();
		for(int i = 1; i < strs.length; i++) {
			writer.newLine();
			writer.flush();
			writer.write(strs[i]);
			writer.flush();
		}
		writer.close();
	}
	
	public static void write(File f, String[] s, boolean append) throws IOException {
		BufferedWriter writer = new BufferedWriter(new java.io.FileWriter(f, append));
		writer.write(s[0]);
		writer.flush();
		for(int i = 1; i < s.length; i++) {
			writer.newLine();
			writer.flush();
			writer.write(s[i]);
			writer.flush();
		}
		writer.close();
	}
	
	public static void write(File f, List<String> s, boolean append) throws IOException {
		if(!f.exists()) {
			Files.createFile(f.toPath());
		}
		if(s.size() == 0) {
			return;
		}
		BufferedWriter writer = new BufferedWriter(new java.io.FileWriter(f, append));
		writer.write(s.get(0));
		writer.flush();
		for(int i = 1; i < s.size(); i++) {
			writer.newLine();
			writer.flush();
			writer.write(s.get(i));
			writer.flush();
		}
		writer.close();
	}
	
	public static void write(File f, List<String> s) throws IOException {
		write(f, s, false);
	}
	
	public static void write(File f, String[] s) throws IOException {
		write(f, s, false);
	}

	public static void write(File f, String s) throws IOException {
		write(f, s, false);
	}
}