import HTML.Column;
import HTML.PreparedColumns;
import HTML.Table.InLineTable;
import HTML.Table.InListTable;
import HTML.Table.TableType;

import java.util.ArrayList;

public class ToHTML {

    private String HTML = "";


    // добавить колонку над всеми с названием первого тэга который не входит никуда
    public void parse(String xml){
        PreparedColumns preparedColumns = new PreparedColumns();
        ArrayList<Column> columns = preparedColumns.prepareColumns(xml);
        TableType type = new TableType();
        int[] tableType = type.define(columns);

        InLineTable inLine = new InLineTable();
        InListTable inList = new InListTable();

        String html = getHTMLHeader();
        if(tableType[0] == 1){
            html += inLine.createDoc(columns);
        }
        else if(tableType[0] == 0){
            int columnsCount = tableType[1];
           html += inList.createDoc(columns, columnsCount);
        }
        html += getHTMLFooter();

        System.out.println(html);
    }


    //подсчет колонок если кратное колво то показать в виде таблицы списка иначе таблица в одну строку
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



}
