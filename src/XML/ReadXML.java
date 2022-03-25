package XML;

import XML.Attribute;
import XML.TagPair;

import java.util.ArrayList;

public class ReadXML {

    public ArrayList<TagPair> XMLToObject(String XMLString) {
        ArrayList<TagPair> tags = new ArrayList<>();
        //Где используется двойные могут быть и одинарные кавычки(обработать)!!!!!!!

        int position = 0;
        String[] str = XMLString.split("[<]");
        trimIt(str);
        for (String s : str) {
            if (itOpenTag(s)) {
                addOpen(s, position, tags);
                position += 1;
                addAttrAndElement(s, tags);
            } else if (itSelfClosedTag(s)) {
                addOpen(s, position, tags);
                position += 1;
                tags.get(tags.size() - 1).setClose(tags.get(tags.size() - 1).getOpen(), -1);
                addAttrAndElement(s, tags);
            } else {
                addCloseTag(s, position, tags);
                position += 1;
            }
        }
        markBlocks(tags);
        return tags;
    }

    private void addAttrAndElement(String str, ArrayList<TagPair> tags){
        if(itWithAttr(str)){
           addAttributes(str, tags);
        } if(itWithElement(str)){
            addElement(str, tags);
        }
    }

    private boolean itWithElement(String s) {
        String[] tmp = s.split(">");
        trimIt(tmp);
        return tmp.length > 1;
    }

    private boolean itWithAttr(String s) {
        String tag = s.split(">")[0];
        for (int i = 0; i < tag.length(); i++) {
            if (Character.toString(tag.charAt(i)).matches("=")) {
                return true;
            }
        }
        return false;
    }

    private boolean itOpenTag(String str) {
        if (!(str.startsWith("/")) && !(str.endsWith("/>"))) {
            return true;
        } else if (str.endsWith("/>") || str.startsWith("/")) {
            return false;
        }
        return false;
    }

    private boolean itSelfClosedTag(String str) {
        return str.endsWith("/>");
    }

    private void addElement(String str, ArrayList<TagPair> tags) {
        String[] tmp = str.split(">");
        tags.get(tags.size() - 1).setElement(tmp[1]);
    }

    private void addOpen(String str, int position, ArrayList<TagPair> tags) {
        String openTag = str.split(">")[0].split(" ")[0];
        tags.add(new TagPair(openTag, position));
    }

    private void addCloseTag(String str, int position, ArrayList<TagPair> tags) {
        String closeTag = getCloseTag(str);
        for (int i = tags.size() - 1; i >= 0; i--) {
            if (openTagEqualsClose(closeTag, i, tags) && !(tags.get(i).tagIsClosed())) {
                closeIt(closeTag, i, position, tags);
                return;
            }
        }
    }

    private boolean openTagEqualsClose(String closeTag, int j, ArrayList<TagPair> tags) {
        return tags.get(j).getOpen() != null && tags.get(j).getOpen().equals(closeTag);
    }

    private void closeIt(String closeTag, int j, int position, ArrayList<TagPair> tags) {
        tags.get(j).setClose(closeTag, position);
    }

    private void trimIt(String[] strings) {
        for (int i = 0; i < strings.length; i++) {
            String tmp = strings[i].trim();
            strings[i] = tmp;
        }
    }

    private String getCloseTag(String str) {
        return str.replaceAll("[</\\>]", "");
    }

    private void addAttributes(String str, ArrayList<TagPair> tags) {
        String[] tmp = str.split("\" ");
        String attrName;
        String attrValue;
        for (int i = 0; i < tmp.length; i++) {
            if (i == tmp.length - 1 && tmp.length > 1) {
                attrName = tmp[i].split("=\"")[0];
                attrValue = tmp[i].split(">")[0].split("=\"")[1].replaceAll("[\"<>/]", "");
                tags.get(tags.size() - 1).addAttributes(new Attribute(attrName, attrValue));
                continue;
            }
            if (i == 0) {
                attrName = tmp[i].split("=\"")[0].split(" ")[1];
                attrValue = tmp[i].split(">")[0].split("=\"")[1].replaceAll("[\"<>]", "");
                tags.get(tags.size() - 1).addAttributes(new Attribute(attrName, attrValue));
                continue;
            }
            attrName = tmp[i].split("=\"")[0];
            attrValue = tmp[i].split("=\"")[1].replaceAll("[\"<>]", "");
            tags.get(tags.size() - 1).addAttributes(new Attribute(attrName, attrValue));
        }
    }

    private void markBlocks(ArrayList<TagPair> tags){
        for (TagPair tag :
                tags) {
            int openPos = tag.getOpenPosition();
            int closePos = tag.getClosePosition();

            tag.setBlock((openPos + 1) != closePos && closePos != -1);
        }
    }
}
