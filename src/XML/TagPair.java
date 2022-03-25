package XML;

import java.util.ArrayList;

public class TagPair {

    private String open;
    private String close;
    private ArrayList<Attribute> attributes = new ArrayList<>();
    private String element;
    private boolean isBlock;
    private int openPosition;
    private int closePosition;

    public TagPair(String open, int openPosition) {
        this.open = open;
        this.openPosition = openPosition;
    }

    public void setBlock(boolean block) {
        isBlock = block;
    }

    public String getElement() {
        return element;
    }

    public void setElement(String element) {
        this.element = element;
    }

    public int getOpenPosition() {
        return openPosition;
    }

    public ArrayList<Attribute> getAttributes() {
        return attributes;
    }

    public boolean elementNotEmpty() {
        return element != null;
    }


    public int getClosePosition() {
        return closePosition;
    }

    public String getOpen() {
        return open;
    }

    public void setClose(String close, int position) {
        this.close = close;
        this.closePosition = position;
    }

    public boolean tagIsClosed() {
        // Проверяем есть ли закрывающий тег
        if (open.equals(close)) {
            return true;
        } else {
            return false;
        }
    }

    public void addAttributes(Attribute attribute) {
        this.attributes.add(attribute);
    }
}

