package lesson1;

import lesson1.fruits.Apple;
import lesson1.fruits.Box;
import lesson1.fruits.Fruit;
import lesson1.fruits.Orange;

import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        // 1
        Integer[] arrInt = {1, 2, 3, 4};
        swapElementsInArray(arrInt,  0 , 1);
        System.out.println(Arrays.toString(arrInt));

        String[] arrStr = {"a", "b", "c", "d"};
        swapElementsInArray(arrStr, 2, 3);
        System.out.println(Arrays.toString(arrStr));

        //2
        ArrayList<Integer> arrListInt = convertToArrayList(arrInt);
        arrListInt.add(5);
        System.out.println(arrListInt);

        ArrayList<String> arrListStr = convertToArrayList(arrStr);
        arrListStr.add("e");
        System.out.println(arrListStr);

        // 3
        Box<Apple> appleBox = new Box();
        for (int i = 0; i < 10; i++) {
            appleBox.add(new Apple());
        }
        Box<Apple> appleBox2 = new Box();
        for (int i = 0; i < 20; i++) {
            appleBox2.add(new Apple());
        }
        System.out.println(String.format("Коробка 1 весит %s", appleBox.getWeight()));

        Box<Orange> orangeBox = new Box();
        for (int i = 0; i < 10; i++) {
            orangeBox.add(new Orange());
        }
        System.out.println(String.format("Коробка 2 весит %s", orangeBox.getWeight()));

        System.out.println("Коробки равны по весу? " + appleBox.compare(orangeBox));

        System.out.println(String.format("Коробка 3 весит %s", appleBox2.getWeight()));
        appleBox.moveTo(appleBox2);
        System.out.println(String.format("Коробка 3 весит %s", appleBox2.getWeight()));
        System.out.println(String.format("Коробка 1 весит %s", appleBox.getWeight()));
        appleBox2.moveTo(appleBox);
        System.out.println(String.format("Коробка 1 весит %s", appleBox.getWeight()));

    }
    public static <T> void swapElementsInArray( T[] arr, int from, int to) throws IndexOutOfBoundsException {
        if (from < 0 || to > arr.length - 1 || to < 0 || to > arr.length - 1) {
            throw new IndexOutOfBoundsException();
        }

        T temp = arr[from];
        arr[from] = arr[to];
        arr[to] = temp;

    }

    public static <T> ArrayList<T> convertToArrayList(T[] arr) {
        return new ArrayList<T>(Arrays.asList(arr));
    }
}
