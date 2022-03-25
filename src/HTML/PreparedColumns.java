package HTML;

import XML.Attribute;
import XML.ReadXML;
import XML.TagPair;


import java.util.ArrayList;

public class PreparedColumns {

    private ArrayList<ColumnName> columnName = new ArrayList<>();

    public ArrayList<Column> prepareColumns(String xml) {
        ReadXML readXML = new ReadXML();
        ArrayList<TagPair> tags = readXML.XMLToObject(xml);
        ArrayList<Column> columns = new ArrayList<>();

        for (int i = 1; i < tags.size(); i++) {
            TagPair tmp = tags.get(i);
            lastBlockClosed(tmp);
            columns.addAll(extractColumnAndCell(tmp));
        }
        return columns;
    }

    private ArrayList<Column> extractColumnAndCell(TagPair tag) {
        ArrayList<Attribute> attrs = tag.getAttributes();
        addNameToNextColumn(tag.getOpen(), tag.getClosePosition());
        ArrayList<Column> tmpColumns = new ArrayList<>();

        if (itWithoutInnerTags(tag)) {
            tmpColumns.addAll(addAttributesAndElement(attrs, tag));
            columnName.remove(columnName.size() - 1);
        } else if(itSelfClosedTag(tag)) {
            tmpColumns.addAll(addAttributes(attrs));
            columnName.remove(columnName.size() - 1);
        } else {
            tmpColumns.addAll(addAttributesAndElement(attrs, tag));
        }
        return tmpColumns;
    }

    private void lastBlockClosed(TagPair tag){
        int currentOpenPosition = tag.getOpenPosition();
        columnName.removeIf(col -> currentOpenPosition > col.getClosePosition() && col.getClosePosition() != -1);
    }

    private ArrayList<Column> addAttributes(ArrayList<Attribute> attrs) {
        String column = getColumnName(columnName);
        return parseAttrToColumns(column, attrs);
    }

    private Column addElement(TagPair tag) {
        String column = getColumnName(columnName);
        String cell = tag.getElement();
        return new Column(column, cell);
    }

    private ArrayList<Column> addAttributesAndElement(ArrayList<Attribute> attrs, TagPair tag) {
        ArrayList<Column> tmpColumns = new ArrayList<>();
        if(tag.elementNotEmpty()){
            tmpColumns.add(addElement(tag));
            if(attrs.size() != 0){
                tmpColumns.addAll(parseAttrToColumns(tmpColumns.get(0).getColumnName(), attrs));
            }
        }
        else{
            tmpColumns.addAll(parseAttrToColumns(getColumnName(columnName), attrs));
        }
        return tmpColumns;
    }

    private ArrayList<Column> parseAttrToColumns(String col, ArrayList<Attribute> attrs) {
        ArrayList<ColumnName> tHeadColName = new ArrayList<>();
        tHeadColName.add(new ColumnName(col));
        String cell;
        ArrayList<Column> tempColumns = new ArrayList<>();
        for (Attribute attr : attrs) {
            tHeadColName.add(new ColumnName("." + attr.getName()));
            cell = attr.getValue();
            Column tmp = new Column(getColumnName(tHeadColName), cell);
            tHeadColName.remove(tHeadColName.size() - 1);
            tempColumns.add(tmp);
        }
        return tempColumns;
    }

    private String getColumnName(ArrayList<ColumnName> columnName) {
        String column = "";
        for (ColumnName s : columnName) {
            column += s.getName();
        }
        return column;
    }

    private void addNameToNextColumn(String str, int closePosition) {
        if (columnName.size() == 0) {
            columnName.add(new ColumnName(str, closePosition));
        } else {
            columnName.add(new ColumnName("." + str, closePosition));
        }
    }

    private boolean itWithoutInnerTags(TagPair tag) {
        return tag.getOpenPosition() == (tag.getClosePosition() - 1) ||
                tag.getOpenPosition() == tag.getClosePosition();
    }

    private boolean itSelfClosedTag(TagPair tag) {
        return tag.getClosePosition() == -1;
    }
}
