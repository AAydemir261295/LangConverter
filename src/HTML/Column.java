package HTML;

public class Column {

    private String columnName;
    private String cell;

    public Column(String columnName, String cell) {
        this.columnName = columnName;
        this.cell = cell;

    }

    public String getColumnName() {
        return columnName;
    }

    public String getCell() {
        return cell;
    }

}
