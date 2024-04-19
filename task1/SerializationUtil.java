package task1;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

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

    public static Animal deSerializeAnimal(String fileName) {
        try (FileInputStream fileInputStream = new FileInputStream(fileName)) {
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            return (Animal) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

}
