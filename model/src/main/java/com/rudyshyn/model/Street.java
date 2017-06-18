package com.rudyshyn.model;

public class Street {
    private Long id; // Является так же значением edge
    private String name;
    private String coordinates;
    private int source;
    private int target;
    private int node; // Идентефикатор узла в пути

    public Street() {
    }

    public Street(Long id, int node) {
        this.id = id;
        this.node = node;
    }

    public Street(Long id, String coordinates) {
        this.id = id;
        this.coordinates = coordinates;
    }

    public Street(Long id, String name, String coordinates, int source, int target) {
        this.id = id;
        this.name = name;
        this.coordinates = coordinates;
        this.source = source;
        this.target = target;
    }

    public Street(Long id, String name, String coordinates, int source, int target, int node) {
        this.id = id;
        this.name = name;
        this.coordinates = coordinates;
        this.source = source;
        this.target = target;
        this.node = node;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public int getSource() {
        return source;
    }

    public void setSource(int source) {
        this.source = source;
    }

    public int getTarget() {
        return target;
    }

    public void setTarget(int target) {
        this.target = target;
    }

    public int getNode() {
        return node;
    }

    public void setNode(int node) {
        this.node = node;
    }
}
