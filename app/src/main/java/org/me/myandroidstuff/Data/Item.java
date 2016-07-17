package org.me.myandroidstuff.Data;

//Andrew Muir
//Matric number - S1511342

public class Item {
    // item title
    private String title;
    // item link
    private String link;
    //item description
    private String description;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getDescription() {return description;}

    public void setDescription(String description) {this.description = description;}

    @Override
    public String toString() {
        return title;
    }
}
