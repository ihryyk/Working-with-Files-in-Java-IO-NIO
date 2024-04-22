package task3;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

public class FileChannelMover implements FastFileMover {

    @Override
    public void moveFile(String sourcePath, String destinationPath) throws IOException {
        File sourceFile = new File(sourcePath);
        File destinationFile = new File(destinationPath);
        FilesChecker.checkTheExistenceOfFiles(destinationFile, sourceFile);
        try (FileChannel sourceChannel = new FileInputStream(sourceFile).getChannel();
             FileChannel destChannel = new FileOutputStream(destinationFile).getChannel()) {
            if (!sourceChannel.isOpen() || !destChannel.isOpen()) {
                throw new IOException("Error opening file channels for source or destination");
            }
            long transferred = destChannel.transferFrom(sourceChannel, 0, sourceChannel.size());
            if (transferred != sourceChannel.size()) {
                throw new IOException(String.format("Failed to transfer all contents from source to destination" +
                        " (source size: %d, transferred: %d)", sourceChannel.size(), transferred));
            }
        } catch (IOException e) {
            throw new RuntimeException("Error occurred while moving file", e);
        }
        if (!sourceFile.delete()) {
            throw new IOException("Failed to delete source file after moving: " + sourcePath);
        }
        System.out.println("File moved successfully from " + sourcePath + " to " + destinationPath);
    }

}
