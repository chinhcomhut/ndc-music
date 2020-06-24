package com.codegym.wbdlaptop.model;



import javax.persistence.*;
import java.util.List;

@Entity
@Table()
public class Singer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nameSinger;
    @Lob
    private String avatarSinger;
    @Lob
    private String information;


    @OneToMany(targetEntity = Song.class, mappedBy = "singer", cascade = CascadeType.ALL)
    private List<Song> songs;

    public Singer() {
    }

    public Singer(String nameSinger, String avatarSinger, String information, List<Song> songs) {
        this.nameSinger = nameSinger;
        this.avatarSinger = avatarSinger;
        this.information = information;
        this.songs = songs;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameSinger() {
        return nameSinger;
    }

    public void setNameSinger(String nameSinger) {
        this.nameSinger = nameSinger;
    }

    public String getAvatarSinger() {
        return avatarSinger;
    }

    public void setAvatarSinger(String avatarSinger) {
        this.avatarSinger = avatarSinger;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public List<Song> getSongs() {
        return songs;
    }

    public void setSongs(List<Song> songs) {
        this.songs = songs;
    }
}
