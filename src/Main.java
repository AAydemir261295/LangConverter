import java.util.ArrayList;
import java.util.Stack;

public class Main {

    public static void main(String[] args) {

//
//        String xml = "<breakfast_menu>\n" +
//                "<food>\n" +
//                "<name>Belgian Waffles</name>\n" +
//                "<price>$5.95</price>\n" +
//                "<description>Two of our famous Belgian Waffles with plenty of real maple syrup</description>\n" +
//                "<calories>650</calories>\n" +
//                "</food>\n" +
//                "<food>\n" +
//                "<name>Strawberry Belgian Waffles</name>\n" +
//                "<price>$7.95</price>\n" +
//                "<description>Light Belgian waffles covered with strawberries and whipped cream</description>\n" +
//                "<calories>900</calories>\n" +
//                "</food>\n" +
//                "<food>\n" +
//                "<name>Berry-Berry Belgian Waffles</name>\n" +
//                "<price>$8.95</price>\n" +
//                "<description>Light Belgian waffles covered with an assortment of fresh berries and whipped cream</description>\n" +
//                "<calories>900</calories>\n" +
//                "</food>\n" +
//                "<food>\n" +
//                "<name>French Toast</name>\n" +
//                "<price>$4.50</price>\n" +
//                "<description>Thick slices made from our homemade sourdough bread</description>\n" +
//                "<calories>600</calories>\n" +
//                "</food>\n" +
//                "<food>\n" +
//                "<name>Homestyle Breakfast</name>\n" +
//                "<price>$6.95</price>\n" +
//                "<description>Two eggs, bacon or sausage, toast, and our ever-popular hash browns</description>\n" +
//                "<calories>950</calories>\n" +
//                "</food>\n" +
//                "</breakfast_menu>";


//        String xml = "<country id=\"Russia\"> \n" +
//                "<cities-list>\n" +
//                "<city>\n" +
//                "<title>Новосибирск</title>\n" +
//                "<universities-list>\n" +
//                "<university id=\"1\">\n" +
//                "<title>Сибирский Государственный Университет Телекоммуникаций и Информатики</title>\n" +
//                "<address URL=\"www.neic.nsk.su\"/>\n" +
//                "</university> \n" +
//                "</universities-list>\n" +
//                "</city>\n" +
//                "</cities-list>\n" +
//                "</country>";

        String xml = "<country id=\"Russia\">\n" +
                "<cities-list>\n" +
                "<city>\n" +
                "<title>Новосибирск</title>\n" +
                "<universities-list>\n" +
                "<university id=\"1\">\n" +
                "<title>Сибирский Государственный Университет Телекоммуникаций и Информатики</title>\n" +
                "<address URL=\"www.neic.nsk.su\"/>\n" +
                "</university>  \n" +
                "<university id=\"2\">\n" +
                "<title>Новосибирский Государственный Университет</title>\n" +
                "<address URL=\"www.nsu.ru\"/>\n" +
                "</university>  \n" +
                "</universities-list>\n" +
                "</city>\n" +
                "<city>\n" +
                "<title>Москва</title>\n" +
                "<universities-list>\n" +
                "<university id=\"1\">\n" +
                "<title>Московский Государственный Университет</title>\n" +
                "<address URL=\"www.msu.ru\"/>\n" +
                "</university>  \n" +
                "</universities-list>\n" +
                "</city>\n" +
                "</cities-list>\n" +
                "</country>";


//        String xml = "<note>\n" +
//                "  <to>Tove</to>\n" +
//                "  <from>Jani</from>\n" +
//                "  <heading>Reminder</heading>\n" +
//                "  <body>Don't forget me this weekend!</body>\n" +
//                "</note>";


        String tmp = xml.replaceAll("\n", "").trim();
        ToHTML toHTML = new ToHTML();
        toHTML.parse(tmp);

    }


}
