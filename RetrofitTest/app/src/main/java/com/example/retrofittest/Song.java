package com.example.retrofittest;

public class Song {
    private String name;
    private String url;
    private String picurl;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPicurl() {
        return picurl;
    }

    public void setPicurl(String picurl) {
        this.picurl = picurl;
    }

    public String getArtistsname() {
        return artistsname;
    }

    public void setArtistsname(String artistsname) {
        this.artistsname = artistsname;
    }

    private String artistsname;


}
