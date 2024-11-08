package org.example.api.models;

public class Unicorn {
    private String name;
    private String tailcolor;

    public Unicorn(String name, String tailcolor) {
        this.name = name;
        this.tailcolor = tailcolor;
    }

    public String getName() {
        return name;
    }

    public String getTailcolor() {
        return tailcolor;
    }

    @Override
    public String toString() {
        return "Unicorn{" +
                "name='" + name + '\'' +
                ", tailcolor='" + tailcolor + '\'' +
                '}';
    }

    public String toJson() {
        return "{\"name\":\"" + name + "\",\"tailcolor\":\"" + tailcolor + "\"}";
    }
}
