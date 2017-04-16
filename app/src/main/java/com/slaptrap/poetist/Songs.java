package com.slaptrap.poetist;

/**
 * Created by Ivan on 4/15/2017.
 */

public class Songs {

    private String author,title,lines;

    public Songs(String author, String title, String lines) {

        this.setAuthor(author);
        this.setTitle(title);
        this.setLines(lines);
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLines() {
        return lines;
    }

    public void setLines(String lines) {
        this.lines = lines;
    }
}
