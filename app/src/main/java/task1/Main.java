package task1;

public class Main {

  public static void main(String[] args) {
    String currentDir = System.getProperty("user.dir");

    Dog dog = new Dog("labrador", false, 4, "Lviv District Shelter");
    SerializationUtil.serializeAnimal(dog, currentDir + "/task1/SerializableObject/Labrador.bin");
    Dog lablador = SerializationUtil.deSerializeAnimal(currentDir + "/task1/SerializableObject/Labrador.bin", Dog.class);
    System.out.println(lablador);
  }
}
