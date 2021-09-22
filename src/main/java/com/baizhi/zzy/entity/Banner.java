package com.baizhi.zzy.entity;

import java.io.Serializable;

public class Banner implements Serializable {
    private Integer id;
    private Integer isShow;
    private String img;
    private String link;
    private String title;


    @Override
    public String toString() {
        return "Banner{" +
                "id=" + id +
                ", isShow=" + isShow +
                ", img='" + img + '\'' +
                ", link='" + link + '\'' +
                ", title='" + title + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIsShow() {
        return isShow;
    }

    public void setIsShow(Integer isShow) {
        this.isShow = isShow;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Banner(Integer id, Integer isShow, String img, String link, String title) {
        this.id = id;
        this.isShow = isShow;
        this.img = img;
        this.link = link;
        this.title = title;
    }

    public Banner() {
    }
}
