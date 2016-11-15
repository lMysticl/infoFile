package file;

import java.util.stream.Collectors;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FileUtil {

    public static List<String> readFile(String filePath){
        List<String> list = new ArrayList<>();
        try (BufferedReader br = Files.newBufferedReader(Paths.get(filePath))) {
            list = br.lines().collect(Collectors.toList());
        } catch (IOException e) {
            System.err.println("Error readFile " + e);
        }
        return list;
    }

}