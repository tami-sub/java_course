package com.company;

public class Trapeze extends Figure {
    private double [] array;
    double a,b,c,d;
    private double [] edges;

    public Trapeze(double [] array) {
        this.array = array;
        edges = new double[(array.length)/2];
        Edges();
    }

    public double getHeight(){
        a=edges[0];b=edges[1];c=edges[2];d=edges[3];
        return Math.sqrt(a*a-Math.pow(((d-b)*(d-b)+a*a-c*c)/(2*(d-b)),2));
    }

    @Override
    public double getArea() {
        return ((b+d)/2)*getHeight();
    }

    @Override
    public double getPerimeter() {
        double perimeter = 0;
        for (double edge : edges) {
            perimeter += edge;
        }
        return perimeter;
    }


    protected void Edges(){
        int counter = 0;
        for (int i = 0; i < array.length/2; i++){
            counter+=2;
            edges[i] = Math.sqrt(Math.pow(array[counter % (array.length)]-array[counter-2],2)  +
                    Math.pow(array[(counter+1) % (array.length)] - array[counter-1],2));
        }
    }
    public void getEdges(){
        for (int i = 0; i < array.length/2; i++){
            System.out.println(edges[i]);
        }
    }
}
