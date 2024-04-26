package task3;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileNioMover implements FastFileMover {

  @Override
  public void moveFile(String sourcePath, String destinationPath) throws IOException {
    Path source = Paths.get(sourcePath);
    Path destination = Paths.get(destinationPath);
    if (!Files.exists(source) || !Files.isRegularFile(source)) {
      throw new FileNotFoundException("Source file does not exist: " + sourcePath);
    }
    if (Files.exists(destination)) {
      throw new IOException("Destination file already exists: " + destinationPath);
    }
    if (destination.getParent() != null && !Files.exists(destination.getParent())) {
      Files.createDirectories(destination.getParent());
    }
    Files.move(source, destination);
    System.out.println("File moved successfully from " + sourcePath + " to " + destinationPath);
  }

}
