import HTML.Column;
import HTML.PreparedColumns;
import HTML.Table.InLineTable;
import HTML.Table.InListTable;
import HTML.Table.TableType;

import java.util.ArrayList;

public class ToHTML {

    public void parse(String xml){
        PreparedColumns preparedColumns = new PreparedColumns();
        ArrayList<Column> columns = preparedColumns.prepareColumns(xml);
        int[] tableType = define(columns);

        String html = getHTMLHeader();
        html += tableType[0] == 1 ? createTableInLine(columns) : createTableInList(columns, tableType[1]);
        html += getHTMLFooter();

        System.out.println(html);
    }

    private String getHTMLHeader() {
        String header = "<!DOCTYPE html>\n" +
                "<html>\n" +
                "\t<head>\n" +
                "\t\t<meta charset='UTF-8'>\n" +
                "\t\t\t<title>XML To HTML using best converter:)</title>\n" +
                "\t\t</head>\n" +
                "\t\t<body>\n" +
                "\t\t\t<table border=1>" + "\n";
        return header;
    }

    private String getHTMLFooter(){
        String footer = "\t\t\t</table>\n" +
                        "\t\t</body>\n" +
                        "\t</html>";

        return footer;
    }

    private String createTableInList(ArrayList<Column> columns, int columnsCount){
        InListTable inList = new InListTable();
        return inList.createDoc(columns, columnsCount);
    }

    private String createTableInLine(ArrayList<Column> columns){
        InLineTable inLine = new InLineTable();
        return inLine.createDoc(columns);
    }

    private int[] define(ArrayList<Column> columns){
        TableType type = new TableType();
        return type.define(columns);
    }



}
