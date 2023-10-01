package com.example.Homework72;

public class CreateForm {
    @Length(max = 20, min = 1, message = "文字数異常です")
    private String name;
    @Range(max =150, min = 0, message ="入力範囲を超えています")
    private Integer age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge(String name) {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
