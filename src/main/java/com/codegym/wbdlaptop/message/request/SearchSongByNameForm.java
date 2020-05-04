package com.codegym.wbdlaptop.message.request;

public class SearchSongByNameForm {
    private String name;

    public SearchSongByNameForm() {
    }

    public SearchSongByNameForm(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
