package de.chrissx.util.thirdparty.lzma;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.util.List;

public class LzmaCompressor
{

    public static void compress(List<Path> rawFilePaths, Path compressedFilePath) throws IOException {
        LzmaOutputStream outputStream = new LzmaOutputStream.Builder(
                new BufferedOutputStream(new FileOutputStream(compressedFilePath.toFile())))
                .useMaximalDictionarySize()
                .useMaximalFastBytes()
                .build();
        for(Path rawFilePath : rawFilePaths) {
        	InputStream inputStream = new BufferedInputStream(new FileInputStream(rawFilePath.toFile()));
            IOUtils.copy(inputStream, outputStream);
        }
    }
    
    public static void compress(Path rawFilePath, Path compressedFilePath) throws IOException {
    	LzmaOutputStream outputStream = new LzmaOutputStream.Builder(
                new BufferedOutputStream(new FileOutputStream(compressedFilePath.toFile())))
                .useMaximalDictionarySize()
                .useMaximalFastBytes()
                .build();
    	InputStream inStr = new BufferedInputStream(new FileInputStream(rawFilePath.toFile()));
    	IOUtils.copy(inStr, outputStream);
    }
}