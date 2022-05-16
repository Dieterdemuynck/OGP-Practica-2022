package main.java.ingredient;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Objects;
import java.util.Scanner;

public class CNTParser {

    // Conversions under Non-metric Tabularization
    // Extension: .cnt
    // Yes. It's a backronym. Haha. Be glad I didn't make it ".cck" instead.

    public static Scanner getTableScanner() {
        try {
            return new Scanner(new File(String.valueOf(Unit.class.getResource("alchemyUnits.cnt"))));
        } catch (FileNotFoundException e) {
            // I *know* the file will be where I want it to be, I don't need the exception.
            // So... How do I make it ignore the possible exception??
            return null;
        }
    }

    public static Object[][] getTable(State state) {

        // Create scanner
        Scanner scanner = CNTParser.getTableScanner();
        assert scanner != null;

        // Read through the lines until we get to the respective state
        for (int i = 0; i <= state.getID() * 5; i++) {
            scanner.nextLine();
        }

        // Make sure the scanner is at the correct line
        String foundStateName = scanner.nextLine();
        assert (!Objects.equals(foundStateName, state.name()));

        // Split up the unit name line and convert them to units
        String[] unitNames = scanner.nextLine().split(",");
        Unit[] unitList = new Unit[unitNames.length];
        for (int i = 0; i < unitList.length; i++) {
            unitList[i] = Unit.valueOf(unitNames[i]);
        }

        // Split up the value line and convert to ints
        String[] valueStrings = scanner.nextLine().split(",");
        assert valueStrings.length == unitList.length;
        Integer[] valueList = new Integer[valueStrings.length];
        for (int i = 0; i < valueStrings.length; i++) {
            valueList[i] = Integer.parseInt(valueStrings[i]);
        }

        // Split up the isContainer line and convert to booleans
        String[] containerStrings = scanner.nextLine().split(",");
        assert containerStrings.length == unitList.length;
        Boolean[] containerList = new Boolean[containerStrings.length];
        for (int i = 0; i < containerStrings.length; i++) {
            containerList[i] = Integer.parseInt(containerStrings[i]) == 1;
        }

        // Return the three arrays by inserting them into a new Object array array.
        // Note: I could possibly use a record for this? Though at the time of writing, most of my code already uses
        // this Object[][] array...
        return new Object[][]{unitList, valueList, containerList};
    }
}
