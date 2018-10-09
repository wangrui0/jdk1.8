package com.entity;

public class Apple {
    private String color;
    private Integer weight;
    private String country;

    public Apple(Integer weight) {
        this.weight = weight;
    }

    public Apple(String color, Integer weight) {
        this.color = color;
        this.weight = weight;
    }

    public Apple(String color,  String country,Integer weight) {
        this.color = color;
        this.weight = weight;
        this.country = country;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public boolean isGreenApple() {
        return "green".equals(this.getColor());
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return "Apple{" +
                "color='" + color + '\'' +
                ", weight=" + weight +
                ", country='" + country + '\'' +
                '}';
    }
}
