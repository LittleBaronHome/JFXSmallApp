package com.java.fx.model;

public class Dictionary {
    private Long id;
    private String value;
    private String text;

    public Dictionary() {
    }

    public Dictionary(Long id, String value, String text) {
        this.id = id;
        this.value = value;
        this.text = text;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}
