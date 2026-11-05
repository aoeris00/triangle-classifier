package com.triangle;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TriangleClassifierTest {
    private final TriangleClassifier classifier = new TriangleClassifier();

    @ParameterizedTest
    @CsvSource({
            "3, 3, 3, EQUILATERAL",
            "2.5, 2.5, 2.5, EQUILATERAL"
    })
    void classifiesEquilateralTriangles(double sideA, double sideB, double sideC, TriangleType expected) {
        assertEquals(expected, classifier.classify(new Triangle(sideA, sideB, sideC)));
    }

    @ParameterizedTest
    @CsvSource({
            "3, 3, 5, ISOSCELES",
            "3, 5, 3, ISOSCELES",
            "5, 3, 3, ISOSCELES",
            "2.5, 2.5, 3.5, ISOSCELES"
    })
    void classifiesIsoscelesTrianglesRegardlessOfEqualSidePositions(
            double sideA, double sideB, double sideC, TriangleType expected) {
        assertEquals(expected, classifier.classify(new Triangle(sideA, sideB, sideC)));
    }

    @ParameterizedTest
    @CsvSource({
            "3, 4, 5, SCALENE",
            "2.5, 3.5, 4.5, SCALENE"
    })
    void classifiesScaleneTriangles(double sideA, double sideB, double sideC, TriangleType expected) {
        assertEquals(expected, classifier.classify(new Triangle(sideA, sideB, sideC)));
    }
}
