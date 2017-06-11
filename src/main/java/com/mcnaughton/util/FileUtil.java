package com.mcnaughton.util;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileUtil {

    public static String getFileContents(String location){
        try(Stream<String> stream = Files.lines(Paths.get(location))){
            return stream.collect(Collectors.joining("\n"));
        } catch (IOException e) {
            return "";
        }
    }

    public static void writeToFile(String location, String value){
        try{
            Files.write(Paths.get(location), value.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
