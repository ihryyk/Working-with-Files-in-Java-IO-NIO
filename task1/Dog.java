package task1;

import java.io.Serial;

public class Dog extends Animal{


    private String shelterName;

    @Serial
    private static final long serialVersionUID = 7182086299230661101L;


    public Dog(String name, boolean isEndangered, int legs, String shelterName) {
        super(name, isEndangered, legs);
        this.shelterName = shelterName;
    }

    public String getShelterName() {
        return shelterName;
    }

    public void setShelterName(String shelterName) {
        this.shelterName = shelterName;
    }

    @Override
    public String toString() {
        return
                "Animal{" +
                        "name='" + super.getName() + '\'' +
                        ", isEndangered=" + super.isEndangered() +
                        ", legs=" + super.getLegs() +
                        '}' +
                "Dog{" +
                "shelterName='" + shelterName + '\'' +
                '}';
    }

}
