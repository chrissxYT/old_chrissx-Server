package de.chrissx.server.file;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import de.chrissx.server.shared.P;

public class FileChecker {

	private static File[] files = new File[] {
			P.ANTIGRIEF_TNT_KICK_FILE.toFile(),
			P.ANTIGRIEF_TNT_OUT_FILE.toFile(),
			P.ACTION_FILE
	};
	
	public static void checkFiles() throws IOException {
		for(File f : files) {
			if(!f.exists()) {
				Files.createFile(f.toPath());
			}
		}
	}
}