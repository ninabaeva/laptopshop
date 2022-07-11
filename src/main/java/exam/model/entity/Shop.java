package exam.model.entity;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "shops")
public class Shop extends BaseEntity{




    private String name;
    private BigDecimal income;
    private String address;
    private int employeeCount;
    private int shopArea;
    private Town town;

    public Shop() {
    }

    @Column(unique = true, nullable = false)
    public String getName() {
        return name;
    }

    public Shop setName(String name) {
        this.name = name;
        return this;
    }

    @Column(nullable = false)
    public BigDecimal getIncome() {
        return income;
    }

    public Shop setIncome(BigDecimal income) {
        this.income = income;
        return this;
    }

    @Column(nullable = false)
    public String getAddress() {
        return address;
    }

    public Shop setAddress(String address) {
        this.address = address;
        return this;
    }

    @Column(name = "employee_count", nullable = false)
    public int getEmployeeCount() {
        return employeeCount;
    }

    public Shop setEmployeeCount(int employeeCount) {
        this.employeeCount = employeeCount;
        return this;
    }

    @Column(name = "shop_area", nullable = false)
    public int getShopArea() {
        return shopArea;
    }

    public Shop setShopArea(int shopArea) {
        this.shopArea = shopArea;
        return this;
    }

    @ManyToOne
    @JoinColumn(name = "town_id")
    public Town getTown() {
        return town;
    }

    public Shop setTown(Town town) {
        this.town = town;
        return this;
    }
}
