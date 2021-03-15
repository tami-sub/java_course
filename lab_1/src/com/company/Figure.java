package com.company;

public abstract class Figure implements Comparable <Figure> {
    public abstract double getArea();
    public abstract double getPerimeter();

    @Override
    public int compareTo(Figure f) {
        return (int) (this.getArea() - f.getArea());
    }
}
