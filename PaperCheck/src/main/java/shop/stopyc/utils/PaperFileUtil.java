package shop.stopyc.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 * @author stopyc
 * （论文）文件处理工厂
 */
public class PaperFileUtil {
    public static String getStringFromFile(String path) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(path));
        //合成为一个String
        return String.join("\n", lines);
    }
    public static void write(String path, List<String> str) throws IOException {
        Files.write(Paths.get(path), str);
    }
}
