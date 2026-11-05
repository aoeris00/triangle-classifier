package com.triangle;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
    @MethodSource("nonFiniteSideLengths")
    void rejectsNonFiniteSideLengths(double sideA, double sideB, double sideC) {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Triangle(sideA, sideB, sideC)
        );

        assertEquals("Side lengths must be finite numbers.", exception.getMessage());
    }

    private static Stream<Arguments> nonFiniteSideLengths() {
        return Stream.of(
                Arguments.of(Double.NaN, 2, 2),
                Arguments.of(Double.POSITIVE_INFINITY, 2, 2),
                Arguments.of(Double.NEGATIVE_INFINITY,2, 2),
                Arguments.of(2, Double.NaN, 2),
                Arguments.of(2, Double.POSITIVE_INFINITY, 2),
                Arguments.of(2, Double.NEGATIVE_INFINITY, 2),
                Arguments.of(2, 2, Double.NaN),
                Arguments.of(2, 2, Double.POSITIVE_INFINITY),
                Arguments.of(2, 2, Double.NEGATIVE_INFINITY)
        );
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
