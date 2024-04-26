package task3;

import java.io.IOException;

public class Main {

  public static void main(String[] args) {
    if (args.length != 3) {
      System.out.println("Usage: FastFileMover <mover_version> <source_path> <destination_path>");
      System.out.println("Available movers: ");

      for (MoverVersion version : MoverVersion.values()) {
        System.out.println("\t" + version.name());
      }
      return;
    }

    MoverVersion version;
    try {
      version = MoverVersion.valueOf(args[0].toUpperCase());
    } catch (IllegalArgumentException e) {
      System.out.println("Invalid mover version: " + args[0]);
      return;
    }

    String sourcePath = args[1];
    String destinationPath = args[2];

    FastFileMover mover = version.getMover();

    long startTime = System.nanoTime();
    try {
      mover.moveFile(sourcePath, destinationPath);
    } catch (IOException e) {
      System.out.println("Failed to move file: " + e.getMessage());
      return;
    }
    long endTime = System.nanoTime();

    System.out.println("File moved successfully using " + version + "!");
    System.out.println("Time taken: " + (endTime - startTime) + " nanoseconds");
  }

}
