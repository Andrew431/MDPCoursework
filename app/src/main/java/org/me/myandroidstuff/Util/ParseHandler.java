package org.me.myandroidstuff.Util;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import org.me.myandroidstuff.Data.Item;

//Andrew Muir
//Matric number - S1511342

public class ParseHandler extends DefaultHandler{

    private List<Item> rssItems;

    // Used to reference item while parsing
    private Item currentItem;

    // Parsing title indicator
    private boolean parsingTitle;
    // Parsing link indicator
    private boolean parsingLink;
    // Parsing description indicator
    private boolean parsingDescription;


    public ParseHandler() {
        rssItems = new ArrayList<Item>();
    }

    public List<Item> getItems() {
        return rssItems;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if ("item".equals(qName)) {
            currentItem = new Item();
        } else if ("title".equals(qName)) {
            parsingTitle = true;
        } else if ("link".equals(qName)) {
            parsingLink = true;
        }
        else if ("description".equals(qName)){
            parsingDescription = true;
        }

    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if ("item".equals(qName)) {
            rssItems.add(currentItem);
            currentItem = null;
        } else if ("title".equals(qName)) {
            parsingTitle = false;
        } else if ("link".equals(qName)) {
            parsingLink = false;
        }
        else if ("description".equals(qName)){
            parsingDescription = false;
        }

    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        if (parsingTitle) {
            if (currentItem != null)
                currentItem.setTitle(new String(ch, start, length));
        } else if (parsingLink) {
            if (currentItem != null) {
                currentItem.setLink(new String(ch, start, length));
                parsingLink = false;
            }
        }
        else if (parsingDescription){
            if (currentItem != null){
                currentItem.setDescription(new String(ch, start, length));
                parsingDescription = false;
            }
        }


    }
}
