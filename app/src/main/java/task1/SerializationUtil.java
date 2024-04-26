package task1;

import java.io.*;

public class SerializationUtil {

  private SerializationUtil() {
  }

  public static void serializeAnimal(Animal animal, String fileName) {
    try (FileOutputStream fileOutputStream = new FileOutputStream(fileName);
         ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {
      objectOutputStream.writeObject(animal);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  public static <T extends Animal> T deSerializeAnimal(String fileName, Class<T> animalType) {
    try (FileInputStream fileInputStream = new FileInputStream(fileName)) {
      ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
      return animalType.cast(objectInputStream.readObject());
    } catch (IOException | ClassNotFoundException e) {
      throw new RuntimeException(e);
    }
  }
}
