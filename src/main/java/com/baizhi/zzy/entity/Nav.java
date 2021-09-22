package com.baizhi.zzy.entity;

import java.io.Serializable;

public class Nav implements Serializable {
    private Integer id;
    private String title;
    private String link;
    private Integer position;
    private Integer isSite;

    @Override
    public String toString() {
        return "Nav{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", link='" + link + '\'' +
                ", position=" + position +
                ", isSite=" + isSite +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public Integer getIsSite() {
        return isSite;
    }

    public void setIsSite(Integer isSite) {
        this.isSite = isSite;
    }

    public Nav() {
    }

    public Nav(Integer id, String title, String link, Integer position, Integer isSite) {
        this.id = id;
        this.title = title;
        this.link = link;
        this.position = position;
        this.isSite = isSite;
    }
}
