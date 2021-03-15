package com.company;

public class EquilateralTriangle extends IsoscelesTriangle {
    public EquilateralTriangle(double[] array) {
        super(array);
        double [] edges = super.Edges();
        if (edges[0] == edges[1] && edges[1] == edges[2] && edges[0] == edges[2])
            System.out.println("Your triangle is an equilateral triangle");
        else
            System.out.println("Your triangle isn't an equilateral triangle");
    }
}
