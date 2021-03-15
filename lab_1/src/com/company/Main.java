package com.company;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Comparator;
import java.util.Scanner;
public class Main {

    public static void main(String[] args) {
        try(FileWriter writer = new FileWriter("Figures.txt", false))
        {
            Console console = new Console();
        Circle round = new Circle(5);
        System.out.println(round.getArea());
        System.out.println(round.getDiameter());
        System.out.println(round.getPerimeter());
        System.out.println(round.getRadius());

            String text = "Areas: \n";
            writer.write(text);

        Rhombus romb = new Rhombus(console.start(4));
        System.out.println(romb.getPerimeter());
        System.out.println(romb.getHeight());
        System.out.println(romb.getArea());
            writer.write(String.valueOf(romb.getArea()+ "\n"));

        Parallelogram parall = new Parallelogram(console.start(4));
        parall.getEdges();
        System.out.println(parall.getPerimeter());
        System.out.println(parall.getArea());
        System.out.println(parall.getHeight());
            writer.write(String.valueOf(parall.getArea() + "\n"));
        System.out.println(romb.compareTo(parall));
//        Trapeze trap = new Trapeze(console.start(4));
//        System.out.println(trap.getHeight());
//        System.out.println(trap.getArea());
//
//        Triangle triangle = new Triangle(console.start(3));
//        triangle.getEdges();
//        System.out.println(triangle.getHeight());
//        System.out.println(triangle.circumscribedRadius());
//        System.out.println(triangle.inscribedRadius());
//        System.out.println(triangle.median1());
//        System.out.println(triangle.median2());
//        System.out.println(triangle.median3());
//        System.out.println(triangle.bisector1());
//        System.out.println(triangle.bisector2());
//        System.out.println(triangle.bisector3());

//        Rectangle rect = new Rectangle(console.start(4));
//        System.out.println(rect.getNewArea());
//        System.out.println(rect.getArea());

//        Square square = new Square(console.start(4));
//        System.out.println(square.getNewArea());
//        System.out.println(square.getArea());

//        EquilateralTriangle triangle1 = new EquilateralTriangle(console.start(4));
//        IsoscelesTriangle triangle2 = new IsoscelesTriangle(array);


//        RectangularTriangle triangle = new RectangularTriangle(console.start(3));
        CompareFigures comp = new CompareFigures();
        System.out.println(comp.compare(parall,romb));

            writer.flush();
        }
        catch(IOException ex){

            System.out.println(ex.getMessage());
        }
    }

    static class CompareFigures implements Comparator <Figure> {
        @Override
        public int compare(Figure f1, Figure f2) {
            return (int) (f1.getArea()-f2.getArea());
        }
    }
}
