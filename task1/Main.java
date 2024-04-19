package task1;

public class Main {

    public static void main(String[] args) {
        Dog dog = new Dog("labrador", false, 4,"Lviv District Shelter");

        SerializationUtil.serializeAnimal(dog,"./task1/SerializableObject/Labrador.bin");
        Dog lablador = (Dog) SerializationUtil.deSerializeAnimal("./task1/SerializableObject/Labrador.bin");
        System.out.println(lablador);
    }

}
