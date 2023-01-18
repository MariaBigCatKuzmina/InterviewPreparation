package ru.kuzmina.lesson1.oop;

public class Triangle implements GeometryShape{
    private Double side1;
    private Double side2;
    private Double side3;

    @Override
    public Double area() {
        Double p = perimeter() / 2;
        return Math.sqrt(p * (p - side1) * (p - side2) * (p - side3));
    }

    @Override
    public Double perimeter() {
        return side1 + side2 + side3;
    }

    public Triangle(Double side1, Double side2, Double side3) {
        this.side1 = side1;
        this.side2 = side2;
        this.side3 = side3;
    }

    public Double getSide1() {
        return side1;
    }

    public void setSide1(Double side1) {
        this.side1 = side1;
    }

    public Double getSide2() {
        return side2;
    }

    public void setSide2(Double side2) {
        this.side2 = side2;
    }

    public Double getSide3() {
        return side3;
    }

    public void setSide3(Double side3) {
        this.side3 = side3;
    }
}
