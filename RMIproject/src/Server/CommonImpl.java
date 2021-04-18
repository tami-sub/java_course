package Server;

import Common.Common;
import java.util.ArrayList;

public class CommonImpl implements Common {
    @Override
    public ArrayList<Double> squares(ArrayList<Double> arr) {
        System.out.println("Input:");
        arr.forEach((element) -> { System.out.print("  " + element); });
        ArrayList<Double> squares = new ArrayList<Double>();
        arr.forEach((element) -> { squares.add(Math.pow(element, 2)); });
        return squares;
    }

    @Override
    public ArrayList<Double> roots(ArrayList<Double> arr) {
        System.out.println("\nInput:");
        arr.forEach((element) -> { System.out.print("  " + element); });
        ArrayList<Double> roots = new ArrayList<>();
        arr.forEach((element) -> { roots.add(Math.sqrt(element)); });
        return roots;
    }
}
