package exam.model.dto.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "town")
@XmlAccessorType(XmlAccessType.FIELD)
public class TownXmlDto {


    @XmlElement
    private String name;

    public TownXmlDto() {
    }


    public String getName() {
        return name;
    }

    public TownXmlDto setName(String name) {
        this.name = name;
        return this;
    }
}
