package gh2;


import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class ReRe {

    public static void main(String[] args) throws IOException {
       GuitarPlayer player = new GuitarPlayer(new File("D:\\28596571465-1-192.mid"));
       player.play();
    }
}