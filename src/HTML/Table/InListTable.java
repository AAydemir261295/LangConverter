package HTML.Table;

import HTML.Column;

import java.util.ArrayList;

public class InListTable {

    public String createDoc(ArrayList<Column> columns, int columnsCount) {

        return addTHead(columns, columnsCount) + addTBody(columns, columnsCount);
    }

    private String addTHead(ArrayList<Column> columns, int columnsCount) {
        String html = "";

        html += openTag(4, "<thead>", true);
        html += openTag(5, "<tr>", true);
        for (int i = 0; i < columnsCount; i++) {
            html += openTag(6, "<th>", false)  + columns.get(i).getColumnName() + closeTag(0, "</th>", true);
        }
        html += openTag(5, "</tr>", true) ;
        html += openTag(4, "</thead>", true);

        return html;
    }

    private String addTBody(ArrayList<Column> columns, int columnsCount) {
        int counter = 0;
        String html = "";

        html += openTag(4, "<tbody>", true);
        for (int i = 0; i <= columnsCount; i++) {
            int nextCells = counter + columnsCount;

            html += openTag(5, "<tr>", true);
            html += addCells(columns, counter, nextCells);
            html += closeTag(5, "</tr>", true);

            counter += columnsCount;
        }
        html += closeTag(4, "</tbody>", true);
        return html;
    }

    private String addCells(ArrayList<Column> columns,
                            int counter, int cellsCount) {
        String tmp = "";
        for (int i = counter; i < cellsCount; i++) {
            tmp += openTag(6, "<td>", false) + columns.get(i).getCell() + closeTag(0, "</td>", true);
        }
        return tmp;
    }

    private String openTag(int tab, String tag, boolean fromNewLine) {
        if (fromNewLine == true) {
            return addTabulation(tab) + tag + "\n";
        } else {
            return addTabulation(tab) + tag;
        }
    }

    private String closeTag(int tab, String tag, boolean fromNewLine) {
        if (fromNewLine == true) {
            return addTabulation(tab) + tag + "\n";
        } else {
            return addTabulation(tab) + tag;
        }
    }

    private static String addTabulation(int a) {

        String tmp = "";
        for (int i = 0; i < a; i++) {
            tmp += "\t";
        }
        return tmp;
    }
}
