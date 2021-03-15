package com.company;


public class RectangularTriangle extends Triangle {
    public RectangularTriangle(double[] array) {
        super(array);
        double [] edges = super.Edges();
        if (Math.pow(edges[0],2)+Math.pow(edges[1],2)==Math.pow(edges[2],2) ||
                Math.pow(edges[2],2)+Math.pow(edges[0],2)==Math.pow(edges[1],2) ||
                Math.pow(edges[1],2)+Math.pow(edges[2],2)==Math.pow(edges[0],2))
            System.out.println("Your triangle is a rectangular triangle");
        else
            System.out.println("Your triangle isn't a rectangular triangle");
    }
}
