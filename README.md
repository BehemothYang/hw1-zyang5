## Description

This Java command-line program reads state population data from a CSV file and distributes representatives using the Hamilton apportionment method. Results are printed alphabetically by state name.

The program uses 435 representatives by default, or accepts a positive representative count as an optional command-line argument.

## Build

```bash
./gradlew clean build