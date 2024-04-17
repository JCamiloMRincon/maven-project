package utilities;

import com.poiji.bind.Poiji;
import entities.Monster;
import entities.Product;

import java.io.File;
import java.util.List;

public class ExcelReader {
    private static final String excelPath = "src/main/resources/data/dataExcel.xlsx";

    public static List<Monster> getMonstersList() {
        Logs.debug("Getting the monsters list from Excel");
        return Poiji.fromExcel(new File(excelPath), Monster.class);
    }

    public static List<Product> getProductsList() {
        Logs.debug("Getting the products list from Excel");
        return Poiji.fromExcel(new File(excelPath), Product.class);
    }
}
