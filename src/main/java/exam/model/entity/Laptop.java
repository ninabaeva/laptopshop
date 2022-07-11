package exam.model.entity;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "laptops")
public class Laptop extends BaseEntity{

    private String macAddress;
    private double cpuSpeed;
    private int ram;
    private int storage;
    private String description;
    private BigDecimal price;
    private WarrantyType warrantyType;
    private Shop shop;

    public Laptop() {
    }

    @Column(nullable = false, unique = true)
    public String getMacAddress() {
        return macAddress;
    }

    public Laptop setMacAddress(String macAddress) {
        this.macAddress = macAddress;
        return this;
    }

    @Column(nullable = false)
    public double getCpuSpeed() {
        return cpuSpeed;
    }

    public Laptop setCpuSpeed(double cpuSpeed) {
        this.cpuSpeed = cpuSpeed;
        return this;
    }

    @Column(nullable = false)
    public int getRam() {
        return ram;
    }

    public Laptop setRam(int ram) {
        this.ram = ram;
        return this;
    }

    @Column(nullable = false)
    public int getStorage() {
        return storage;
    }

    public Laptop setStorage(int storage) {
        this.storage = storage;
        return this;
    }

    @Column(nullable = false, columnDefinition = "TEXT")
    public String getDescription() {
        return description;
    }

    public Laptop setDescription(String description) {
        this.description = description;
        return this;
    }

    @Column(nullable = false)
    public BigDecimal getPrice() {
        return price;
    }

    public Laptop setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    @Column(nullable = false)
    public WarrantyType getWarrantyType() {
        return warrantyType;
    }

    public Laptop setWarrantyType(WarrantyType warrantyType) {
        this.warrantyType = warrantyType;
        return this;
    }

    @ManyToOne
    @JoinColumn(name = "shop_id")
    public Shop getShop() {
        return shop;
    }

    public Laptop setShop(Shop shop) {
        this.shop = shop;
        return this;
    }
}
