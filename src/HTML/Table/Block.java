package HTML.Table;

import java.util.ArrayList;

public class Block {

    private final ArrayList<String> cells;
    private final int cellsCount;

    public Block(ArrayList<String> cells, int cellsCount) {
        this.cells = cells;
        this.cellsCount = cellsCount;
    }

    public ArrayList<String> getCells() {
        return cells;
    }

    public int getCellsCount() {
        return cellsCount;
    }
}
