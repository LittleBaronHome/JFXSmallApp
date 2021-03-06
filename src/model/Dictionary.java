package model;

public class Dictionary<T> {
    private Long id;
    private String value;
    private String text;
    private T data;

    public Dictionary() {
    }

    public Dictionary(Long id, String value, String text) {
        this.id = id;
        this.value = value;
        this.text = text;
    }

    public Dictionary(Long id, String value, String text, T data) {
        this.id = id;
        this.value = value;
        this.text = text;
        this.data = data;
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

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return text;
    }
}
