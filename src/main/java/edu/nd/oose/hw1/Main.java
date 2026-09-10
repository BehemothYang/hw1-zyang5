package edu.nd.oose.hw1;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

public final class Main {

    private static final int DEFAULT_REPRESENTATIVE_COUNT = 435;

    private Main() {
        // Prevent creation of Main objects.
    }

    public static void main(String[] args) {
        validateCommandLineArguments(args);

        String filename = args[0];
        int representativeCount = getRepresentativeCount(args);

        List<State> states = PopulationReader.read(filename);
        Map<State, Integer> apportionment =
                HamiltonApportionment.apportion(
                        states,
                        representativeCount
                );

        printApportionment(states, apportionment);
    }

    private static void validateCommandLineArguments(String[] args) {
        if (args.length < 1 || args.length > 2) {
            throw new IllegalArgumentException(
                    "Incorrect arguments. Use: java -jar "
                            + "Apportionment.jar <population.csv> "
                            + "[number of representatives]"
            );
        }
    }

    private static int getRepresentativeCount(String[] args) {
        if (args.length == 1) {
            return DEFAULT_REPRESENTATIVE_COUNT;
        }

        try {
            int representativeCount = Integer.parseInt(args[1]);

            if (representativeCount <= 0) {
                throw new IllegalArgumentException(
                        "The number of representatives must be greater than zero."
                );
            }

            return representativeCount;
        } catch (NumberFormatException exception) {
            throw new IllegalArgumentException(
                    "The number of representatives must be a positive integer.",
                    exception
            );
        }
    }

    private static void printApportionment(
            List<State> states,
            Map<State, Integer> apportionment
    ) {
        List<State> statesAlphabetically = new ArrayList<>(states);
        statesAlphabetically.sort(
                Comparator.comparing(State::getName)
        );

        for (State state : statesAlphabetically) {
            System.out.println(
                    state.getName() + " - " + apportionment.get(state)
            );
        }
    }
}