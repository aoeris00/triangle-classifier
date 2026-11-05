package com.triangle;

public class TriangleClassifier {
    public TriangleType classify(Triangle triangle) {
        double sideA = triangle.getSideA();
        double sideB = triangle.getSideB();
        double sideC = triangle.getSideC();

        if (sideA == sideB && sideB == sideC) {
            return TriangleType.EQUILATERAL;
        }
        if (sideA == sideB || sideA == sideC || sideB == sideC) {
            return TriangleType.ISOSCELES;
        }
        return TriangleType.SCALENE;
    }
}
