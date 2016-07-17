package org.me.myandroidstuff.Util;

import java.util.List;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.me.myandroidstuff.Data.Item;

//Andrew Muir
//Matric number - S1511342

public class Reader {

    private String rssUrl;

    /**
     * Constructor
     *
     * @param rssUrl
     */
    public Reader(String rssUrl) {
        this.rssUrl = rssUrl;
    }

    /**
     * Get RSS items.
     *
     * @return
     */
    public List<Item> getItems() throws Exception {
        // SAX parse RSS data
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser saxParser = factory.newSAXParser();

        ParseHandler handler = new ParseHandler();

        saxParser.parse(rssUrl, handler);

        return handler.getItems();

    }
}
