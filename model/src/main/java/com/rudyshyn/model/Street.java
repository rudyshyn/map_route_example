package com.rudyshyn.model;

public class Street {
    private Long id; // Является так же значением edge
    private String name;
    private String coordinates;
    private Long source;
    private Long target;
    private Long node; // Идентефикатор узла в пути. Не используется

    public Street() {
    }

    public Street(String coordinates) {
        this.coordinates = coordinates;
    }

    public Street(Long id, Long node) {
        this.id = id;
        this.node = node;
    }

    public Street(Long id, String coordinates) {
        this.id = id;
        this.coordinates = coordinates;
    }

    public Street(Long id, String name, String coordinates, Long source, Long target) {
        this.id = id;
        this.name = name;
        this.coordinates = coordinates;
        this.source = source;
        this.target = target;
    }

    public Street(Long id, String name, String coordinates, Long source, Long target, Long node) {
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

    public Long getSource() {
        return source;
    }

    public void setSource(Long source) {
        this.source = source;
    }

    public Long getTarget() {
        return target;
    }

    public void setTarget(Long target) {
        this.target = target;
    }

    public Long getNode() {
        return node;
    }

    public void setNode(Long node) {
        this.node = node;
    }
}
