package principal;

import data.CustomData;
import entities.Monster;
import io.qameta.allure.Severity;
import io.qameta.allure.Description;
import io.qameta.allure.SeverityLevel;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utilities.BaseTest;
import utilities.ExcelReader;
import utilities.Logs;

import java.util.List;

public class ExcelMonsterTests extends BaseTest {

    private List<Monster> monstersList;

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        monstersList = ExcelReader.getMonstersList();
    }

    @Test(groups = { regression, smoke })
    @Description("Verifying the name of the first monster")
    @Severity(SeverityLevel.CRITICAL)
    public void firstTest() {
        Logs.info("Verifying the name of the first monster...");
        Assert.assertEquals(monstersList.get(0).getName(),"TOLOSA",
                    "The name is not TOLOSA");
    }

    @Test(groups = { smoke })
    @Description("Verifying the list size")
    @Severity(SeverityLevel.MINOR)
    public void secondTest() {
        Logs.info("Verifying the list size...");
        Assert.assertEquals(monstersList.size(),14,
                    "The size is not 14");
    }

    @Test(groups = { regression, smoke })
    @Description("Verifying the level of the third monster")
    @Severity(SeverityLevel.BLOCKER)
    public void thirdTest() {
        Logs.info("Verifying the level of the third monster...");
        Assert.assertEquals(monstersList.get(2).getLevel(), 22,
                    "The level is not 22");
    }

    @Test(groups = { regression })
    @Description("Verifying all the information of the last monster")
    @Severity(SeverityLevel.MINOR)
    public void fourthTest() {
        Logs.info("Verifying all the information of the last monster...");
        final var lastMonster = monstersList.get(monstersList.size() - 1);

        softAssert.assertEquals(lastMonster.getName(), "LUCENA");
        softAssert.assertEquals(lastMonster.getAge(), 3);
        softAssert.assertEquals(lastMonster.getWeight(), 8.57);
        softAssert.assertEquals(lastMonster.getGender(), "MACHO");
        softAssert.assertEquals(lastMonster.getType(), "PLANTA");
        softAssert.assertEquals(lastMonster.getLevel(), 36);
        softAssert.assertAll();
    }

    @Test(dataProviderClass = CustomData.class, dataProvider = CustomData.DPNAME, groups = { regression })
    @Description("Verifying the data provider")
    @Severity(SeverityLevel.CRITICAL)
    public void fifthTest(Monster monster) {
        softAssert.assertTrue(monster.getAge() > 0);
        softAssert.assertTrue(monster.getLevel() < 100);
        softAssert.assertAll();
    }
}
