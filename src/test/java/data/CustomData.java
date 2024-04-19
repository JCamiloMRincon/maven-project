package data;

import org.testng.annotations.DataProvider;
import utilities.ExcelReader;

public class CustomData {
    public static final String DPNAME = "Data Monsters";

    @DataProvider(name = DPNAME)
    public Object[][] monsterDataProvider() {
        final var monstersList = ExcelReader.getMonstersList();
        final var listSize = monstersList.size();
        final var object = new Object[listSize][];

        for (var i = 0; i < listSize; i++) {
            // The set only contains the monster
            object[i] = new Object[]{ monstersList.get(i) };
        }

        return object;
    }
}
