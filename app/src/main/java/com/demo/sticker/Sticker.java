package com.demo.sticker;

public class Sticker {
    private String mURL;

    public Sticker(String url) {
        this.mURL = url;
    }

    public String getURL() {
        return mURL;
    }

    public void setURL(String mURL) {
        this.mURL = mURL;
    }
}