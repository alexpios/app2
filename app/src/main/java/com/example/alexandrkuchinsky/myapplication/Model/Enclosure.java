package com.example.alexandrkuchinsky.myapplication.Model;

/**
 * Created by Alexandr Kuchinsky on 08.02.2018.
 */

public class Enclosure
{
    public String link;
    public String type;
    public int length;

    public Enclosure(String link, String type, int length) {
        this.link = link;
        this.type = type;
        this.length = length;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }
}