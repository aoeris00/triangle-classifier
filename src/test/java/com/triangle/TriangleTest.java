package com.triangle;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TriangleTest {
    @Test
    void constructsTriangleWithValidIntegerSides() {
        assertDoesNotThrow(() -> new Triangle(3, 4, 5));
    }

    @Test
    void constructsTriangleWithValidDecimalSides() {
        assertDoesNotThrow(() -> new Triangle(2.5, 2.5, 3.5));
    }

    @Test
    void rejectsZeroLengthSide() {
        assertThrows(IllegalArgumentException.class, () -> new Triangle(0, 2, 2));
    }

    @Test
    void rejectsNegativeSideLength() {
        assertThrows(IllegalArgumentException.class, () -> new Triangle(-1, 2, 2));
    }

    @ParameterizedTest
    @CsvSource({
            "8, 2, 3",
            "2, 8, 3",
            "2, 3, 8"
    })
    void rejectsSidesThatViolateTriangleInequality(double sideA, double sideB, double sideC) {
        assertThrows(IllegalArgumentException.class, () -> new Triangle(sideA, sideB, sideC));
    }

    @ParameterizedTest
    @CsvSource({
            "5, 2, 3",
            "2, 6, 4",
            "3, 4, 7"
    })
    void rejectsDegenerateTriangles(double sideA, double sideB, double sideC) {
        assertThrows(IllegalArgumentException.class, () -> new Triangle(sideA, sideB, sideC));
    }
}
