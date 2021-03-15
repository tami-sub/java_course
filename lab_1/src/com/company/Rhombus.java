package com.company;

public class Rhombus extends Figure{
    private double [] array;
    private double d1,d2;
    private double [] edges;
    private double [] diagonals = new double[2];

    public Rhombus(double [] array) {
        this.array = array;
        edges = new double[(array.length)/2];
        Edges();
    }

    @Override
    public double getArea() {
        getDiagonals();
        return 0.5*d1*d2;
    }

    @Override
    public double getPerimeter() {
        double perimeter = 0;
        for (double edge : edges) {
            perimeter += edge;
        }
        return perimeter;
    }

    public double getHeight(){
        return getArea()/edges[0];
    }
    public void getDiagonals(){
        for (int i = 0; i < 2; i++){
            diagonals[i] = Math.sqrt(Math.pow(array[i+4] - array[i],2) + Math.pow(array[i+6] - array[i+2],2));
        }
        d1 = diagonals[0];
        d2 = diagonals[1];
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
