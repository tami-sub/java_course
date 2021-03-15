package com.company;

public class Rectangle extends Parallelogram {
    public Rectangle(double[] array) {
        super(array);
    }
    public double getNewArea(){
        double [] edges = super.Edges();
        double area = edges[0]*edges[1];
        if(area == super.getArea())
            System.out.println("Your parallelogram is a rectangle");
        else System.out.println("Your parallelogram isn't a rectangle");
        return area;
    }
}
