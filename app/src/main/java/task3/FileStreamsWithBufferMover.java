package task3;

import java.io.*;

// TODO: how is this different from the FileStreamsMover, except for the buffer size?
public class FileStreamsWithBufferMover implements FastFileMover {

  @Override
  public void moveFile(String sourcePath, String destinationPath) throws IOException {
    File sourceFile = new File(sourcePath);
    File destinationFile = new File(destinationPath);

    FilesChecker.checkTheExistenceOfFiles(destinationFile, sourceFile);
    try (InputStream in = new FileInputStream(sourceFile);
         OutputStream out = new FileOutputStream(destinationFile)) {
      // Create buffer of 100 KB
      byte[] buffer = new byte[102400];
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
