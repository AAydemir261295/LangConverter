package HTML;

public class ColumnName {

    String name;
    int closePosition;

    public ColumnName(String name, int closePosition) {
        this.name = name;
        this.closePosition = closePosition;
    }

    public ColumnName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getClosePosition() {
        return closePosition;
    }
}
