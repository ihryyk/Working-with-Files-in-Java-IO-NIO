package task3;

import java.io.*;

public class FileStreamsMover implements FastFileMover {

  private static final int DEFAULT_BUFFER_SIZE = 1024;

  private final int bufferSize;

  public FileStreamsMover() {
    this(DEFAULT_BUFFER_SIZE);
  }

  public FileStreamsMover(int bufferSize) {
    this.bufferSize = bufferSize;
  }

  @Override
  public void moveFile(String sourcePath, String destinationPath) throws IOException {
    File sourceFile = new File(sourcePath);
    File destinationFile = new File(destinationPath);

    FilesChecker.checkTheExistenceOfFiles(destinationFile, sourceFile);
    try (InputStream in = new FileInputStream(sourceFile);
         OutputStream out = new FileOutputStream(destinationFile)) {
      byte[] buffer = new byte[this.bufferSize];
      int bytesRead;
      while ((bytesRead = in.read(buffer)) > 0) {
        out.write(buffer, 0, bytesRead);
      }
    }
    if (!sourceFile.delete()) {
      throw new IOException("Failed to delete source file after moving: " + sourcePath);
    }
    System.out.println("File moved successfully from " + sourcePath + " to " + destinationPath);
  }

}
