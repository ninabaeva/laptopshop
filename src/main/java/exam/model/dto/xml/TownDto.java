package exam.model.dto.xml;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "town")
@XmlAccessorType(XmlAccessType.FIELD)
public class TownDto {

    @XmlElement
    private String name;
    @XmlElement
    private int population;
    @XmlElement(name = "travel-guide")
    private String travelGuide;

    public TownDto() {
    }

    @NotNull
    @Length(min = 2)
    public String getName() {
        return name;
    }

    public TownDto setName(String name) {
        this.name = name;
        return this;
    }

    @NotNull
    @Min(value = 1)
    public int getPopulation() {
        return population;
    }

    public TownDto setPopulation(int population) {
        this.population = population;
        return this;
    }

    @NotNull
    @Length(min = 10)
    public String getTravelGuide() {
        return travelGuide;
    }

    public TownDto setTravelGuide(String travelGuide) {
        this.travelGuide = travelGuide;
        return this;
    }
}
