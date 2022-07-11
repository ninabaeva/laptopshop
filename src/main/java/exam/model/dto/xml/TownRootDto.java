package exam.model.dto.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "towns")
@XmlAccessorType(XmlAccessType.FIELD)
public class TownRootDto {

    @XmlElement(name = "town")
    private List<TownDto> towns;

    public TownRootDto() {
    }

    public List<TownDto> getTowns() {
        return towns;
    }

    public TownRootDto setTowns(List<TownDto> towns) {
        this.towns = towns;
        return this;
    }
}
