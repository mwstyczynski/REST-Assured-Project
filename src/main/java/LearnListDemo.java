import java.util.*;

public class LearnListDemo {
    public static void main(String[] args) {
        // Creating a list 
        List<Integer> firstList = new ArrayList<Integer>();

        // Adds 1 at 0 index 
        firstList.add(0, 1);

        // Adds 2 at 1 index 
        firstList.add(1, 2);
        System.out.println("First list: " + firstList);

        // Creating another list 
        List<Integer> secondList = new ArrayList<Integer>();

        secondList.add(1);
        secondList.add(2);
        secondList.add(3);

        // Will add list l2 from 1 index 
        firstList.addAll(1, secondList);
        System.out.println(firstList);

        // Removes element from index 1 
        firstList.remove(1);
        System.out.println(firstList);

        // Prints element at index 3 
        System.out.println("3rd element of 1st list: " + firstList.get(3));

        // Replace 0th element with 5 
        firstList.set(0, 5);
        System.out.println(firstList);
    }
} 