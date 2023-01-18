package ru.kuzmina.lesson1.oop;

public class Square implements GeometryShape{
    private Double sideLength;

    public Square(Double sideLength) {
        this.sideLength = sideLength;
    }

    public Double getSideLength() {
        return sideLength;
    }

    public void setSideLength(Double sideLength) {
        this.sideLength = sideLength;
    }

    @Override
    public Double area() {
        return sideLength * sideLength;
    }

    @Override
    public Double perimeter() {
        return sideLength * 4.0;
    }
}
