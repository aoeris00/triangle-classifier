package com.triangle;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MainTest {
    @ParameterizedTest
    @CsvSource({
            "'3 3 3', 'Triangle type: Equilateral'",
            "'3 3 5', 'Triangle type: Isosceles'",
            "'3 4 5', 'Triangle type: Scalene'",
            "'2.5 2.5 3.5', 'Triangle type: Isosceles'"
    })
    void displaysClassificationForValidInput(String input, String expectedOutput) {
        CliResult result = runMain(input + System.lineSeparator());

        assertTrue(result.output().contains(expectedOutput));
        assertFalse(result.error().contains("Error:"));
    }

    @ParameterizedTest
    @ValueSource(strings = {"3 4", "3 4 5 6", ""})
    void reportsInputWhenItDoesNotContainExactlyThreeSides(String input) {
        CliResult result = runMain(input + System.lineSeparator());

        assertTrue(result.error().contains("Please enter exactly three side lengths."));
    }

    @ParameterizedTest
    @ValueSource(strings = {"abc 4 5", "1,2 3 4"})
    void reportsNonNumericSideLengths(String input) {
        CliResult result = runMain(input + System.lineSeparator());

        assertTrue(result.error().contains("Side lengths must be valid numbers."));
    }

    @ParameterizedTest
    @ValueSource(strings = {"0 2 2", "-1 2 2", "1 2 10"})
    void reportsInvalidTriangleGeometry(String input) {
        CliResult result = runMain(input + System.lineSeparator());

        assertTrue(result.error().contains("Error:"));
        assertFalse(result.output().contains("Triangle type:"));
    }

    @Test
    void continuesPromptingAfterInvalidInputAndClassifiesNextTriangle() {
        CliResult result = runMain("abc 4 5" + System.lineSeparator() + "3 4 5" + System.lineSeparator());

        assertTrue(result.error().contains("Side lengths must be valid numbers."));
        assertTrue(result.output().contains("Triangle type: Scalene"));
    }

    private static CliResult runMain(String input) {
        var originalIn = System.in;
        var originalOut = System.out;
        var originalErr = System.err;
        var output = new ByteArrayOutputStream();
        var error = new ByteArrayOutputStream();

        try {
            System.setIn(new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8)));
            System.setOut(new PrintStream(output, true, StandardCharsets.UTF_8));
            System.setErr(new PrintStream(error, true, StandardCharsets.UTF_8));
            Main.main(new String[0]);
        } finally {
            System.setIn(originalIn);
            System.setOut(originalOut);
            System.setErr(originalErr);
        }

        return new CliResult(output.toString(StandardCharsets.UTF_8), error.toString(StandardCharsets.UTF_8));
    }

    private record CliResult(String output, String error) {
    }
}
