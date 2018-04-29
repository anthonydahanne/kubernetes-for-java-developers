import java.util.concurrent.TimeUnit;

public class SimpleJavaApplication {
  public static void main(String[] args) throws InterruptedException {
    while(true) {
      System.out.println("Hello Montreal");
      System.out.println("Allo tout le monde === :-)");
      TimeUnit.SECONDS.sleep(1L);
    }

  }


}
