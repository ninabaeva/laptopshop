package exam.model.dto.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "shops")
@XmlAccessorType(XmlAccessType.FIELD)
public class ShopRootDto {

    @XmlElement(name = "shop")
    private List<ShopDto> shops;

    public ShopRootDto() {
    }

    public List<ShopDto> getShops() {
        return shops;
    }

    public ShopRootDto setShops(List<ShopDto> shops) {
        this.shops = shops;
        return this;
    }
}
