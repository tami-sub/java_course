package com.company;

public class Circle extends Figure {
    private double radius = 0;

    public Circle(double rad){
        this.radius = rad;
    }

    @Override
    public double getArea() {
        return Math.PI*Math.pow(radius,2);
    }

    @Override
    public double getPerimeter() {
        return 2*Math.PI*radius;
    }

    public double getDiameter() {
        return 2*radius;
    }

    public double getRadius() {
        return radius;
    }
}
