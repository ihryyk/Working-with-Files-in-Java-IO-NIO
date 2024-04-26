package task3;

import java.io.IOException;

@FunctionalInterface
public interface FastFileMover {

  void moveFile(String sourcePath, String destinationPath) throws IOException;

}
