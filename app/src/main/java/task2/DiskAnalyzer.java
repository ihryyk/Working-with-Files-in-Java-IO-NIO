package task2;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DiskAnalyzer {

  public static void main(String[] args) throws IOException {
    Scanner scanner = new Scanner(System.in);

    System.out.println("Please enter the path:");
    String path = scanner.nextLine();

    System.out.println("Please select the function to execute:");
    System.out.println("1: Find the file with maximum 's' characters in its name");
    System.out.println("2: Find the top 5 largest files");
    System.out.println("3: Calculate the average file size");
    System.out.println("4: Count  the number of files and folders starting with each letter");

    int functionNumber;
    try {
      functionNumber = Integer.parseInt(scanner.nextLine());
    } catch (NumberFormatException e) {
      System.out.println("Error: Function number must be an integer.");
      return;
    }

    switch (functionNumber) {
      case 1:
        findFileWithMaxSChars(path);
        break;
      case 2:
        findTop5LargestFiles(path);
        break;
      case 3:
        findAverageFileSize(path);
        break;
      case 4:
        findNoOfFilesStartingWithEachLetter(path);
        break;
      default:
        System.out.println("Invalid function number. Available function numbers are 1 to 4.");
        break;
    }
  }

  private static void findFileWithMaxSChars(String path) throws IOException {
    try (Stream<Path> paths = Files.walk(Paths.get(path))) {
      Comparator<Path> sLettersCountFilenameComparator = Comparator.comparingLong(p -> p.getFileName()
        .toString().toLowerCase().chars()
        .filter(ch -> ch == 's').count());

      Path fileWithMaxS = paths
        .filter(Files::isRegularFile)
        .max(sLettersCountFilenameComparator)
        .orElseThrow(() -> new FileNotFoundException("No files found in specified directory."));

      if (fileWithMaxS.getFileName().toString().contains("s")) {
        System.out.println("The file with the maximum number of letters 's' is " + fileWithMaxS);
      } else {
        System.out.println("No files found with the letter 's' in their names.");
      }
    }
  }

  private static void findTop5LargestFiles(String path) throws IOException {
    try (Stream<Path> paths = Files.walk(Paths.get(path))) {
      Comparator<Path> fileSizeComparator = Comparator.comparingLong(DiskAnalyzer::getFileSize);

      List<Path> largestFiles = paths
        .filter(Files::isRegularFile)
        .sorted(fileSizeComparator.reversed())
        .limit(5)
        .toList();

      System.out.println("Top 5 largest files:");
      for (Path file : largestFiles) {
        System.out.println(file + " - size: " + Files.size(file) + " bytes");
      }
    }
  }

  private static void findAverageFileSize(String path) throws IOException {
    try (Stream<Path> paths = Files.walk(Paths.get(path))) {
      OptionalDouble avgSize = paths
        .filter(Files::isRegularFile)
        .mapToLong(DiskAnalyzer::getFileSize)
        .average();

      if (avgSize.isPresent()) {
        System.out.println("The average file size is " + avgSize.getAsDouble() + " bytes");
      } else {
        System.out.println("No files found in specified directory.");
      }
    }
  }

  private static long getFileSize(Path file) {
    try {
      return Files.size(file);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  private static void findNoOfFilesStartingWithEachLetter(String path) throws IOException {
    try (Stream<Path> paths = Files.walk(Paths.get(path))) {
      Map<Character, Long> fileCountByLetter = paths
        .filter(Files::isRegularFile)
        .map(filePath -> filePath.getFileName().toString().toLowerCase().charAt(0))
        .filter(Character::isLetter)
        .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

      System.out.println("Number of files starting with each letter:");
      fileCountByLetter.forEach((letter, count) -> System.out.println(letter + ": " + count));
    }
  }
}

