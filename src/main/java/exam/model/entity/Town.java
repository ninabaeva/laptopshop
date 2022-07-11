package exam.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "towns")
public class Town extends BaseEntity{

    private String name;
    private int population;
    private String travelGuide;

    public Town() {
    }

    @Column(nullable = false, unique = true)
    public String getName() {
        return name;
    }

    public Town setName(String name) {
        this.name = name;
        return this;
    }

    @Column(nullable = false)
    public int getPopulation() {
        return population;
    }

    public Town setPopulation(int population) {
        this.population = population;
        return this;
    }

    @Column(name = "travel_guide", nullable = false, columnDefinition = "TEXT")
    public String getTravelGuide() {
        return travelGuide;
    }

    public Town setTravelGuide(String travelGuide) {
        this.travelGuide = travelGuide;
        return this;
    }
}
