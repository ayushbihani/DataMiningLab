import java.util.Arrays;
import java.util.HashSet;
import java.util.*;

public class MainClass {

@SuppressWarnings("unchecked")
  public static void main(String[] a) {
    String elements[] = { "A", "B", "D", "E" };
    HashSet<Character> set = new HashSet(Arrays.asList(elements));

    elements = new String[] { "A", "B", "C" };
    HashSet<Character> set2 = new HashSet(Arrays.asList(elements));

    System.out.println(set.containsAll(set2));
  }
}