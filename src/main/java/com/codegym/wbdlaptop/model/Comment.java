package com.codegym.wbdlaptop.model;

import javax.persistence.*;

@Entity
@Table(name = "comment")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    private String content;

    private String date;
    private Boolean isEdit;
    private Long idProduct;

    @ManyToOne
    private Song song;

    @ManyToOne
    private User user;

    public Comment() {
    }

    public Comment(String content, String date, Boolean isEdit, Long idProduct, Song song, User user) {
        this.content = content;
        this.date = date;
        this.isEdit = isEdit;
        this.idProduct = idProduct;
        this.song = song;
        this.user = user;
    }

    public Comment(String content, String date, Boolean isEdit, Long idProduct, Song song) {
        this.content = content;
        this.date = date;
        this.isEdit = isEdit;
        this.idProduct = idProduct;
        this.song = song;
    }

    public Comment(String content, String date, Song song, User user) {
        this.content = content;
        this.date = date;
        this.song = song;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Boolean getEdit() {
        return isEdit;
    }

    public void setEdit(Boolean edit) {
        isEdit = edit;
    }

    public Long getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(Long idProduct) {
        this.idProduct = idProduct;
    }

    public Song getSong() {
        return song;
    }

    public void setSong(Song song) {
        this.song = song;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
