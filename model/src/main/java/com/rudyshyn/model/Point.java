package com.rudyshyn.model;

public class Point {
    private Long id;
    private Long streetID;
    private String name;
    private String coordinates;
    private String tag;

    public Point() {
    }

    public Point(Long id, String name, String coordinates) {
        this.id = id;
        this.name = name;
        this.coordinates = parseCoordinates(coordinates);
    }

    public Point(Long id, String name, String coordinates, String tag) {
        this.id = id;
        this.name = name;
        this.coordinates = parseCoordinates(coordinates);
        this.tag = tag;
    }

    public Point(Long id, Long streetID, String name, String coordinates, String tag) {
        this.id = id;
        this.streetID = streetID;
        this.name = name;
        this.coordinates = parseCoordinates(coordinates);
        this.tag = tag;
    }

    private String parseCoordinates(String coordinates){
        String buf = coordinates.split("\\(")[1];
        buf = buf.split("\\)")[0];
        return buf.split(" ")[1] + ", " + buf.split(" ")[0];
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getStreetID() {
        return streetID;
    }

    public void setStreetID(Long streetID) {
        this.streetID = streetID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(String coordinates) {
        this.coordinates = coordinates;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}
