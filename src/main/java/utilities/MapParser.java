package utilities;

import entities.Product;

import java.util.HashMap;
import java.util.Map;

public class MapParser {

    public static Map<String, Product> getProductMap() {
        final var map = new HashMap<String, Product>();
        final var productsList = ExcelReader.getProductsList();

        for(var product : productsList) {
            map.put(product.getName(), product);
        }

        return map;
    }
}
