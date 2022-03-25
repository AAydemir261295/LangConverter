package HTML.Table;

import HTML.Column;

import java.util.ArrayList;

public class InLineTable {

    public String createDoc(ArrayList<Column> columns) {
        String thead = addTHead(columns);
        String tbody = addTBody(columns);
        return thead + tbody;
    }

    private String addTHead(ArrayList<Column> columns) {
        String html = "";

        html += openTag(4, "<thead>", true);
        html += openTag(5, "<tr>", true);
        for (int i = 0; i < columns.size(); i++) {
            String tmp = openTag(6, "<th>", false) + columns.get(i).getColumnName() + closeTag(0, "</th>", true);
            html += tmp;
        }
        html += closeTag(5, "</tr>", true);
        html += closeTag(4, "</thead>", true);

        return html;
    }

    private String addTBody(ArrayList<Column> columns) {
        String html = "";

        html += openTag(4, "<tbody>", true);
        html += openTag(5, "<tr>", true);
        for (int i = 0; i < columns.size(); i++) {
            String tmp = openTag(6, "<td>", false) + columns.get(i).getCell() + closeTag(0, "</td>", true);
            html += tmp;
        }
        html += closeTag(5, "</tr>", true);
        html += closeTag(4, "</tbody>", true);


        return html;
    }

    private String openTag(int tab, String tag, boolean fromNewLine) {
        if(fromNewLine == true){
            return addTabulation(tab) + tag + "\n";
        }
        else{
            return addTabulation(tab) + tag;
        }

    }

    private String closeTag(int tab, String tag, boolean fromNewLine) {
        if(fromNewLine == true){
            return addTabulation(tab) + tag + "\n";
        }
        else{
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
