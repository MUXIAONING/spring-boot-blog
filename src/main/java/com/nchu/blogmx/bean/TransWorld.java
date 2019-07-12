package com.nchu.blogmx.bean;

public class TransWorld {
    private String word;

    private String result;

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public TransWorld() {

    }

    public TransWorld(String word, String result) {
        this.word = word;
        this.result = result;
    }

    @Override
    public String toString() {
        return "TransWorld{" +
                "word='" + word + '\'' +
                ", result='" + result + '\'' +
                '}';
    }
}
