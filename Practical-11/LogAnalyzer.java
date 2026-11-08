import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.List;

public class LogAnalyzer {
public static void main(String[] args) throws IOException
{
    String keyword = "ERROR";

    Path dir = Files.createTempDirectory("logs");

    Path[] files = {dir.resolve("app1.log"), dir.resolve("app2.log"), dir.resolve("app3.log")};

    Files.write(files[0], List.of("INFO started", "ERROR disk full", "INFO running"));
    Files.write(files[1], List.of("INFO started", "WARN slow response"));
    Files.write(files[2], List.of("ERROR timeout", "ERROR null pointer", "INFO stopped", "INFO bye"));

    int totalLines = 0, matches = 0;

    for(Path p : files){
      try(BufferedReader br = Files.newBufferedReader(p)){
         String line;
         while((line = br.readLine()) != null){
           totalLines++;
           if(line.contains(keyword)){
              matches++;
           }
         }
      }

    BasicFileAttributes attr = Files.readAttributes(p, BasicFileAttributes.class);
    System.out.println(p.getFileName() + " -> " + attr.size() + " bytes");
  }

  System.out.println("Total lines: " + totalLines);
  System.out.println("Lines containing " + keyword + ": " + matches);

  for(Path p : files){
    Files.delete(p);
  }
  Files.delete(dir);
}
}