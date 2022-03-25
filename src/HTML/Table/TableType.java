package HTML.Table;

import HTML.Column;
import java.util.ArrayList;

public class TableType {


    public int[] define(ArrayList<Column> columns) {
        ArrayList<String> columnNames = getColumnNames(columns);
        return defineType(columnNames);
    }

    private int[] defineType(ArrayList<String> columnNames) {
        int inLine[] = new int[1];
        int inList[] = new int[2];
        ArrayList<Block> tagBlocks = getTagBLocks(columnNames);

        if (isEvenCount(columnNames)) {
            if (tagBlocks.size() >= 1) {
                if (isInList(tagBlocks)) {
                    inList[0] = 0;
                    inList[1] = tagBlocks.get(0).getCells().size();
                    return inList;
                } else {
                    inLine[0] = 1;
                    return inLine;
                }
            }
        } else {
            inLine[0] = 1;
            return inLine;
        }
        return null;
    }

    private boolean isInList(ArrayList<Block> blocks) {
        int blocksCount = blocks.size();
        int cellsCount = blocks.get(0).getCellsCount();
        int counter = 1;

        for (int i = 1; i < blocksCount; i++) {
            int nextCellsInBlock = blocks.get(i).getCellsCount();
            if (cellsCount == nextCellsInBlock) {
                counter++;
                if (counter == blocksCount) {
                    return true;
                }
            }
        }
        return false;
    }

    private ArrayList<Block> getTagBLocks(ArrayList<String> columnNames) {
        String firstName = columnNames.get(0);
        ArrayList<String> oneBlock = new ArrayList<>();
        ArrayList<Block> tagBlocks = new ArrayList<>();

        oneBlock.add(firstName);
        for (int i = 1; i < columnNames.size(); i++) {
            String tmpName = columnNames.get(i);
            if (firstName.equals(tmpName)) {
                tagBlocks.add(new Block(oneBlock, oneBlock.size()));
                oneBlock = new ArrayList<>();
                oneBlock.add(tmpName);
            } else {
                oneBlock.add(tmpName);
            }
        }
        tagBlocks.add(new Block(oneBlock, oneBlock.size()));

        return tagBlocks;
    }

    private boolean isEvenCount(ArrayList<String> columns) {
        return columns.size() % 2 == 0;
    }

    private ArrayList<String> getColumnNames(ArrayList<Column> columns) {
        ArrayList<String> tmp = new ArrayList<>();
        for (Column column : columns) {
            String columnName = column.getColumnName();
            tmp.add(columnName);
        }
        return tmp;
    }
}
