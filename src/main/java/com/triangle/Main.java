package com.triangle;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.regex.Pattern;

public final class Main {
    private Main() {
    }

    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        TriangleClassifier classifier = new TriangleClassifier();
        while (true) {
            System.out.println("Enter three side lengths separated by spaces:");

            String input;
            try {
                input = reader.readLine();
            } catch (IOException exception) {
                System.err.println("Error: Unable to read input.");
                return;
            }

            if (input == null) {
                return;
            }

            List<String> values = Pattern.compile("\\s+").splitAsStream(input.trim()).toList();
            if (values.size() != 3) {
                System.err.println("Error: Please enter exactly three side lengths.");
                continue;
            }

            List<Double> sides;
            try {
                sides = values.stream().map(Double::parseDouble).toList();
            } catch (NumberFormatException exception) {
                System.err.println("Error: Side lengths must be valid numbers.");
                continue;
            }

            try {
                var triangle = new Triangle(sides.get(0), sides.get(1), sides.get(2));
                var type = classifier.classify(triangle);
                System.out.println();
                System.out.println("Triangle type: " + displayName(type));
                System.out.println();
            } catch (IllegalArgumentException exception) {
                System.err.println("Error: " + exception.getMessage());
            }
        }
    }

    private static String displayName(TriangleType type) {
        return switch (type) {
            case EQUILATERAL -> "Equilateral";
            case ISOSCELES -> "Isosceles";
            case SCALENE -> "Scalene";
        };
    }
}
