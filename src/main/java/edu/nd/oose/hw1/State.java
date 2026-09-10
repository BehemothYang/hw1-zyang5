package edu.nd.oose.hw1;

public final class State {
    private final String name;
    private final long population;

    public State(String name, long population) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("State name cannot be blank.");
        }

        if (population < 0) {
            throw new IllegalArgumentException("Population cannot be negative.");
        }

        this.name = name.trim();
        this.population = population;
    }

    public String getName() {
        return name;
    }

    public long getPopulation() {
        return population;
    }
}