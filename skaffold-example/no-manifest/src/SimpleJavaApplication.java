import java.util.concurrent.TimeUnit;

@SuppressWarnings("ALL")
public class SimpleJavaApplication {
  public static void main(String[] args) throws InterruptedException {
    while(true) {
      System.out.println("Bonjour Montreal JUG!");
      TimeUnit.SECONDS.sleep(1L);
    }
  }


}
