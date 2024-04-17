package task1;

import java.io.Serial;
import java.io.Serializable;

public class Animal implements Serializable {

    @Serial
    private static final long serialVersionUID = 8466985796531575340L;

    private String name;
    private boolean endangered;
    private transient int legs;

    public Animal(String name, boolean endangered, int legs) {
        this.name = name;
        this.endangered = endangered;
        this.legs = legs;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isEndangered() {
        return endangered;
    }

    public void setEndangered(boolean endangered) {
        this.endangered = endangered;
    }

    public int getLegs() {
        return legs;
    }

    public void setLegs(int legs) {
        this.legs = legs;
    }

    @Override
    public String toString() {
        return "Animal{" +
                "name='" + name + '\'' +
                ", isEndangered=" + endangered +
                ", legs=" + legs +
                '}';
    }

}
