package main.java.ingredient;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Objects;
import java.util.Scanner;

public class CuNTParser {

    // Conversions under Non-metric Tabularization

    static Scanner getTableScanner() throws FileNotFoundException {
        return new Scanner(new File(String.valueOf(Unit.class.getResource("alchemyUnits.cnt"))));
    }

    private static Object[][] getTable(State state) throws FileNotFoundException, IncorrectStateIDException {
        Scanner scanner = getTableScanner();
        // TODO: make state ID
        for (int i = 0; i <= state.getID() * 5; i++) {
            scanner.nextLine();
        }
        if (!Objects.equals(scanner.nextLine(), state.name())) {
            // TODO: make exception
            throw new IncorrectStateIDException(state);
        }

        String[] unitNames = scanner.nextLine().split(",");
        Unit[] unitList = new Unit[unitNames.length];
        for (int i = 0; i < unitList.length; i++) {
            unitList[i] = Unit.valueOf(unitNames[i]);
        }

        String[] valueStrings = scanner.nextLine().split(",");
        Integer[] valueList = new Integer[valueStrings.length];
        for (int i = 0; i < valueStrings.length; i++) {
            valueList[i] = Integer.parseInt(valueStrings[i]);
        }

        String[] containerStrings = scanner.nextLine().split(",");
        Boolean[] containerList = new Boolean[containerStrings.length];
        for (int i = 0; i < containerStrings.length; i++) {
            containerList[i] = Integer.parseInt(containerStrings[i]) == 1;
        }

        return new Object[][]{unitList, valueList, containerList};
    }
}
