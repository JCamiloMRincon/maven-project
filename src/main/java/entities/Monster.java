package entities;

import com.poiji.annotation.ExcelCellName;
import com.poiji.annotation.ExcelSheet;

@ExcelSheet("monstruos")
public class Monster {

    @ExcelCellName("NOMBRE")
    private String name;
    @ExcelCellName("EDAD")
    private int age;
    @ExcelCellName("PESO")
    private double weight;
    @ExcelCellName("GENERO")
    private String gender;
    @ExcelCellName("TIPO")
    private String type;
    @ExcelCellName("NIVEL")
    private int level;

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        final var multiLine = """
                
                {
                    name: %s
                    age: %d
                    weight: %.2f
                    gender: %s
                    type: %s
                    level: %d
                }
                """;
        return String.format(multiLine, name, age, weight, gender, type, level);
    }
}
