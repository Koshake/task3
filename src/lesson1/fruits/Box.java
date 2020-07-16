package lesson1.fruits;

import java.util.ArrayList;

public class Box<T extends Fruit> {
    private ArrayList<T> fruits;

    public Box(ArrayList<T> fruits) {
        this.fruits = fruits;
    }

    public Box() {
        this.fruits = new ArrayList<T>();
    }

    public void add(T fruit) {
        fruits.add(fruit);
    }

    public float getWeight() {
        float weight = 0.0f;
        try {
            for (T fruit : fruits) {
                weight += fruit.getWeight();
            }
        } catch (NullPointerException e) {
            return 0;
        }

        return weight;
    }

    public boolean compare(Box<?> box) {
        float e = 0.00001f;
        if (Math.abs(this.getWeight() - box.getWeight()) < e) {
            return true;
        }
        return false;
    }

    public void moveTo(Box<T> box) {
        for (int i = 0; i < fruits.size(); i++) {
            box.add(fruits.get(i));
        }
        fruits.clear();
    }
}
