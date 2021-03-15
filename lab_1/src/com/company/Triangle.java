package com.company;

public class Triangle extends Figure {
    private double [] array;
    private double [] edges;

    public Triangle(double [] array) {
        this.array = array;
        edges = new double[(array.length)/2];
        Edges();
    }
    public double getHeight(){
        return (2*getArea())/edges[2];
    }
    public double circumscribedRadius(){
        return (edges[0]*edges[1]*edges[2])/(4*getArea());
    }
    public double inscribedRadius(){
        return (2*getArea())/(edges[0]+edges[1]+edges[2]);
    }
    @Override
    public double getArea() {
        double p = getPerimeter()/2;
        return Math.sqrt(p*(p-edges[0])*(p-edges[1])*(p-edges[2]));
    }

    @Override
    public double getPerimeter() {
        double perimeter = 0;
        for (double edge : edges) {
            perimeter += edge;
        }
        return perimeter;
    }

    protected double[] Edges(){
        int counter = 0;
        for (int i = 0; i < array.length/2; i++){
            counter+=2;
            edges[i] = Math.sqrt(Math.pow(array[counter % (array.length)]-array[counter-2],2)  +
                    Math.pow(array[(counter+1) % (array.length)] - array[counter-1],2));
        }
        return edges;
    }
    public void getEdges(){
        for (int i = 0; i < array.length/2; i++){
            System.out.println(edges[i]);
        }
    }
    public double median1(){
        double xf, yf;
        xf=(array[0]+array[4])/2;
        yf=(array[1]+array[5])/2;
        return Math.sqrt(Math.pow((xf-array[2]),2)+Math.pow((yf-array[3]),2));
    }

    public double median2(){
        double xf, yf;
        xf=(array[2]+array[4])/2;
        yf=(array[3]+array[5])/2;
        return Math.sqrt(Math.pow((xf-array[0]),2)+Math.pow((yf-array[1]),2));
    }

    public double median3(){
        double xf, yf;
        xf=(array[2]+array[0])/2;
        yf=(array[3]+array[1])/2;
        return Math.sqrt(Math.pow((xf-array[4]),2)+Math.pow((yf-array[5]),2));
    }

    public double bisector1(){
        return (1/(edges[0]+edges[1]))*Math.sqrt(edges[0]*edges[1]*(edges[0]+edges[1]+edges[2])*(edges[0]+edges[1]-edges[2]));
    }

    public double bisector2(){
        return (1/(edges[1]+edges[2]))*Math.sqrt(edges[1]*edges[2]*(edges[0]+edges[1]+edges[2])*(edges[0]+edges[1]-edges[2]));
    }

    public double bisector3(){
        return (1/(edges[2]+edges[0]))*Math.sqrt(edges[2]*edges[0]*(edges[0]+edges[1]+edges[2])*(edges[0]+edges[1]-edges[2]));
    }

}
