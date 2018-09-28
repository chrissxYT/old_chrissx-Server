package de.chrissx.server.file;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import de.chrissx.util.thirdparty.lzma.LzmaCompressor;

public class FileCompressor {

	public static void checkFiles(File dir) throws IOException {
		List<Path> files = new ArrayList<Path>();
		for(File f : dir.listFiles()) {
			files.add(f.toPath());
		}
		if(files.size() != 0) {
			if(files.get(0).toString().split(".").length != 0) {
				LzmaCompressor.compress(files, Paths.get(files.get(0).toString().replace("."+files.get(0).toString().split(".")[files.get(0).toString().split(".").length-1], ".7z")));
			}
		}
		for(Path f : files) {
			Files.delete(f);
		}
	}
	
	public static void compressFile(File f) throws IOException {
		Path source = f.toPath();
		Path target = Paths.get(f.toString().replace(f.toString().split(".")[f.toString().split(".").length-1], ".7z"));
		LzmaCompressor.compress(source, target);
		Files.delete(source);
	}
}