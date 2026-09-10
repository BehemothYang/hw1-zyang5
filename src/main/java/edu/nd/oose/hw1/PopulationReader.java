package edu.nd.oose.hw1;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public final class PopulationReader {

    private PopulationReader() {
        // Prevent creation of PopulationReader objects.
    }

    public static List<State> read(String filename) {
        Path filePath = validateInputPath(filename);
        List<State> states = readStates(filePath);

        if (states.isEmpty()) {
            throw new IllegalArgumentException(
                    "The input file contains no valid state and population records."
            );
        }

        return states;
    }

    private static Path validateInputPath(String filename) {
        if (filename == null || filename.isBlank()) {
            throw new IllegalArgumentException(
                    "Missing input filename. Provide the path to a population CSV file."
            );
        }

        Path filePath = Path.of(filename);

        if (!Files.exists(filePath)) {
            throw new IllegalArgumentException(
                    "Input file does not exist: " + filename
                            + ". Check the filename or file path and try again."
            );
        }

        if (!Files.isRegularFile(filePath) || !Files.isReadable(filePath)) {
            throw new IllegalArgumentException(
                    "Input file cannot be read: " + filename
                            + ". Check the file permissions and try again."
            );
        }

        return filePath;
    }

    private static List<State> readStates(Path filePath) {
        try (BufferedReader reader = Files.newBufferedReader(filePath)) {
            reader.readLine();
            return readDataLines(reader);
        } catch (IOException exception) {
            throw new IllegalArgumentException(
                    "Unable to read input file: " + filePath
                            + ". Check the file and try again.",
                    exception
            );
        }
    }

    private static List<State> readDataLines(BufferedReader reader)
            throws IOException {
        List<State> states = new ArrayList<>();
        String line;
        int lineNumber = 1;

        while ((line = reader.readLine()) != null) {
            lineNumber++;
            State state = parseState(lineNumber, line);

            if (state != null) {
                states.add(state);
            }
        }

        return states;
    }

    private static State parseState(int lineNumber, String line) {
        String[] columns = line.split(",", -1);

        if (columns.length < 2) {
            printWarning(lineNumber, line);
            return null;
        }

        String stateName = columns[0].trim();
        String populationText = columns[1].trim();

        try {
            if (stateName.isEmpty()) {
                throw new IllegalArgumentException();
            }

            long population = Long.parseLong(populationText);
            return new State(stateName, population);
        } catch (IllegalArgumentException exception) {
            printWarning(lineNumber, line);
            return null;
        }
    }

    private static void printWarning(int lineNumber, String line) {
        System.err.println(
                "Line " + lineNumber + " - Bad format - " + line
        );
    }
}