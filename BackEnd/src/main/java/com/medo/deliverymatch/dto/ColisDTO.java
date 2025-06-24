package com.medo.deliverymatch.dto;

public class ColisDTO {
    private Long id;
    private String description;
    private double poids;
    private double volume;
    private String typeColis;
    private String dimensions;

    public ColisDTO() {
    }

    public ColisDTO(Long id, String description, double poids, double volume, String typeColis, String dimensions) {
        this.id = id;
        this.description = description;
        this.poids = poids;
        this.volume = volume;
        this.typeColis = typeColis;
        this.dimensions = dimensions;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPoids() {
        return poids;
    }

    public void setPoids(double poids) {
        this.poids = poids;
    }

    public double getVolume() {
        return volume;
    }

    public void setVolume(double volume) {
        this.volume = volume;
    }

    public String getTypeColis() {
        return typeColis;
    }

    public void setTypeColis(String typeColis) {
        this.typeColis = typeColis;
    }

    public String getDimensions() {
        return dimensions;
    }

    public void setDimensions(String dimensions) {
        this.dimensions = dimensions;
    }


}
