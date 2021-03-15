package com.company;

public class Parallelogram extends Figure{
    private double [] array;
    double x1,y1,x2,y2,a,b;
    private double [] edges;

    public Parallelogram(double [] array) {
        this.array = array;
        edges = new double[(array.length)/2];
        Edges();
    }
    public double getHeight(){
        return getArea()/edges[3];
    }
    @Override
    public double getArea() {
        return edges[0] * edges[3] * Math.sin(getAngle());
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

    public double getAngle()
    {
//        double angle = Math.toDegrees(Math.atan2(array[2] - array[0], array[3] - array[1]));
//        angle = angle + Math.ceil( -angle / 360 ) * 360;
        x1 = Math.abs(array[2] - array[0]);
        y1 = Math.abs(array[3] - array[1]);
        x2 = Math.abs(array[0] - array[6]);
        y2 = Math.abs(array[1] - array[7]);

        double cos = ((x1*x2+y1*y2)/(Math.sqrt(Math.pow(x1,2)+Math.pow(y1,2)) * Math.sqrt(Math.pow(x2,2)+Math.pow(y2,2))));
        return Math.acos(cos);
    }
}