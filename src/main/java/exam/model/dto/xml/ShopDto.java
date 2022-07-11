package exam.model.dto.xml;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigDecimal;

@XmlRootElement(name = "shop")
@XmlAccessorType(XmlAccessType.FIELD)
public class ShopDto {


    @XmlElement
    private String name;
    @XmlElement
    private BigDecimal income;
    @XmlElement
    private String address;
    @XmlElement(name = "employee-count")
    private int employeeCount;
    @XmlElement(name = "shop-area")
    private int shopArea;
    @XmlElement
    private TownXmlDto town;

    public ShopDto() {
    }

    @NotNull
    @Length(min = 4)
    public String getName() {
        return name;
    }

    public ShopDto setName(String name) {
        this.name = name;
        return this;
    }

    @NotNull
    @Min(value = 20000)
    public BigDecimal getIncome() {
        return income;
    }


    public ShopDto setIncome(BigDecimal income) {
        this.income = income;
        return this;
    }

    @NotNull
    @Length(min = 4)
    public String getAddress() {
        return address;
    }

    public ShopDto setAddress(String address) {
        this.address = address;
        return this;
    }

    @NotNull
    @Min(value = 1)
    @Max(value = 50)
    public int getEmployeeCount() {
        return employeeCount;
    }

    public ShopDto setEmployeeCount(int employeeCount) {
        this.employeeCount = employeeCount;
        return this;
    }

    @NotNull
    @Min(value = 150)
    public int getShopArea() {
        return shopArea;
    }

    public ShopDto setShopArea(int shopArea) {
        this.shopArea = shopArea;
        return this;
    }

    public TownXmlDto getTown() {
        return town;
    }

    public ShopDto setTown(TownXmlDto town) {
        this.town = town;
        return this;
    }
}
