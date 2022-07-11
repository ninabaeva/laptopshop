package exam.model.dto.json;

import com.google.gson.annotations.Expose;
import exam.model.entity.Shop;
import exam.model.entity.WarrantyType;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class LaptopDto {

    @Expose
    private String macAddress;
    @Expose
    private double cpuSpeed;
    @Expose
    private int ram;
    @Expose
    private int storage;
    @Expose
    private String description;
    @Expose
    private BigDecimal price;
    @Expose
    private WarrantyType warrantyType;
    @Expose
    private ShopJsonDto shop;

    public LaptopDto() {
    }

    @NotNull
    @Length(min = 9)
    public String getMacAddress() {
        return macAddress;
    }

    public LaptopDto setMacAddress(String macAddress) {
        this.macAddress = macAddress;
        return this;
    }

    @NotNull
    @Min(value = 1)
    public double getCpuSpeed() {
        return cpuSpeed;
    }

    public LaptopDto setCpuSpeed(double cpuSpeed) {
        this.cpuSpeed = cpuSpeed;
        return this;
    }

    @NotNull
    @Min(value = 8)
    @Max(value = 128)
    public int getRam() {
        return ram;
    }

    public LaptopDto setRam(int ram) {
        this.ram = ram;
        return this;
    }

    @NotNull
    @Min(value = 128)
    @Max(value = 1024)
    public int getStorage() {
        return storage;
    }

    public LaptopDto setStorage(int storage) {
        this.storage = storage;
        return this;
    }

    @NotNull
    @Length(min = 10)
    public String getDescription() {
        return description;
    }

    public LaptopDto setDescription(String description) {
        this.description = description;
        return this;
    }

    @NotNull
    @Min(value = 1)
    public BigDecimal getPrice() {
        return price;
    }

    public LaptopDto setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    @NotNull
    public WarrantyType getWarrantyType() {
        return warrantyType;
    }

    public LaptopDto setWarrantyType(WarrantyType warrantyType) {
        this.warrantyType = warrantyType;
        return this;
    }





    @NotNull
    public ShopJsonDto getShop() {
        return shop;
    }

    public LaptopDto setShop(ShopJsonDto shop) {
        this.shop = shop;
        return this;
    }
}
