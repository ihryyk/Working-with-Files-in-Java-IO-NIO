package task3;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class FileStreamsMover implements FastFileMover {

    @Override
    public void moveFile(String sourcePath, String destinationPath) throws IOException {
        File sourceFile = new File(sourcePath);
        File destinationFile = new File(destinationPath);

        FilesChecker.checkTheExistenceOfFiles(destinationFile, sourceFile);

        try (InputStream in = new FileInputStream(sourceFile);
             OutputStream out = new FileOutputStream(destinationFile)) {

            byte[] buffer = new byte[1024];
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
