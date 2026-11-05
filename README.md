# Triangle Classifier

A small Java application that validates three side lengths, classifies the triangle, and displays its type.

## Prerequisites

- Java 21
- Maven

## Build and test

Run the test suite:

```shell
mvn clean test
```

Build the executable JAR:

```shell
mvn clean package
```

## Run

Run the packaged application:

```shell
java -jar target/triangle-classifier-1.0-SNAPSHOT.jar
```

Enter three side lengths on one line. For example:

```text
Enter three side lengths separated by spaces:
3 3 3

Triangle type: Equilateral
```

Other examples:

- Input `3 3 5` displays `Triangle type: Isosceles`.
- Input `3 4 5` displays `Triangle type: Scalene`.
- Invalid input displays a clear error and exits.

## Project structure

- `Triangle` is the immutable domain model and validates side lengths at construction.
- `TriangleType` defines the three classification results.
- `TriangleClassifier` contains only the classification rules.
- `Main` parses CLI input, handles user-facing errors, and formats the result.
- `src/test/java` contains domain validation and parameterized classifier tests.
