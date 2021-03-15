package com.company;

public class Square extends Rectangle{
    public Square(double[] array) {
        super(array);
    }
    public double getNewArea(){
        double [] edges = super.Edges();
        double area = Math.pow(edges[0],2);
        if(area == super.getNewArea())
            System.out.println("Your parallelogram is a square");
        else System.out.println("Your parallelogram isn't a square");
        return area;
    }
}
