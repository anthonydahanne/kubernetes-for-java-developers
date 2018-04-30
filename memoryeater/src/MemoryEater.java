//  The following is inspired by :
//  http://alvinalexander.com/blog/post/java/java-program-consume-all-memory-ram-on-computer

import java.util.Vector;

public class MemoryEater {


  private static final int MB = 1024 *1024;

  public static void main(String[] args) {

    System.out.println("Howdy Java developer !");


    Runtime rt = Runtime.getRuntime();

    long maxMemoryBytes = rt.maxMemory();
    System.out.println("Maximum amount of memory the JVM will use : " + toMB(maxMemoryBytes));

    long usedMemoryBytes = rt.totalMemory() - rt.freeMemory();
    System.out.println("Current amount of memory the JVM is using : " + toMB(usedMemoryBytes));

    long freeMemoryBytes = rt.maxMemory() - usedMemoryBytes;
    System.out.println("Current amount of memory free to use : " + toMB(freeMemoryBytes));

    System.out.println("Allocating chunks of 8 MB until 70% of the max");


    Vector<byte[]> bytes =  new Vector<>();
    while (usedMemoryBytes < maxMemoryBytes * 0.7) {
      bytes.add(new byte[8 * MB]);
      usedMemoryBytes = rt.totalMemory() - rt.freeMemory();
      freeMemoryBytes = rt.maxMemory() - usedMemoryBytes;
      System.out.println("Current amount of memory the JVM is using | free to use " + toMB(usedMemoryBytes) + "|" + toMB(freeMemoryBytes));
    }

    System.out.println("Done !");

  }

  private static String toMB(long bytes) {
    return String.valueOf(bytes / MB) + " MB";
  }
}