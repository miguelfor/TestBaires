package com.google.testapplication.ui.Models;

import java.io.Serializable;

public class Item implements Serializable {

    private String name;
    private String path;
    private String html_url;

    public Item(String name, String path, String html_url) {
        this.name = name;
        this.path = path;
        this.html_url = html_url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getHtml_url() {
        return html_url;
    }

    public void setHtml_url(String html_url) {
        this.html_url = html_url;
    }
}
