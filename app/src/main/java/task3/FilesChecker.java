package task3;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class FilesChecker {

  private FilesChecker() {
  }

  public static void checkTheExistenceOfFiles(File destinationFile, File sourceFile) throws IOException {
    File parentDir = destinationFile.getParentFile();
    if (!parentDir.exists() && !parentDir.mkdirs()) {
      throw new IOException("Could not create parent directories for destination file: " + destinationFile.getPath());
    }

    if (!sourceFile.exists()) {
      throw new FileNotFoundException("The source file does not exist: " + sourceFile.getPath());
    }

    if (destinationFile.exists()) {
      throw new IOException("Destination file already exists: " + destinationFile.getPath());
    }

    if (!destinationFile.getParentFile().exists()) {
      throw new FileNotFoundException("Could not find parent directories for destination file: " + destinationFile.getPath());
    }
  }

}
