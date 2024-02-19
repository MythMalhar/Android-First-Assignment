package com.example.assignment1;

public class model {
    private  String name;
    private String img;
    private String type;
    private String hitpoints;
    private String location;

    public model(String name,String img, String type, String hitpoints, String location) {
        this.name = name;
        this.img = img;
        this.type = type;
        this.hitpoints = hitpoints;
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getHitpoints() {
        return hitpoints;
    }

    public void setHitpoints(String hitpoints) {
        this.hitpoints = hitpoints;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
