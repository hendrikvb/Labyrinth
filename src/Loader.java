import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Loader {

  private static boolean[][] allocateBoolArrayForChars(List<String> strings){
    int height = strings.size();
    int width = strings.get(0).length();
    return new boolean[height][width];
  }

  private static boolean[][] parseMAZEStrings(List<String> strings){
    boolean[][] res = allocateBoolArrayForChars(strings);
    int x = 0; 
    for(String line : strings){
      int y = 0;
      for(char c : line.toCharArray()){
        res[x][y] = !(c == 'X');
        y++;
      }
      x++;
    }
    return res;
  }

  public static boolean[][] load(String path) 
  {
  try{
    List<String> strings = Files.readAllLines(Paths.get(path),Charset.defaultCharset());
    return parseMAZEStrings(strings);
  } catch (Exception e) {
    System.out.println("failed to open load file: "+path);
    System.out.println(e.getMessage());
    e.printStackTrace();
  }
    return null;
  }
}
