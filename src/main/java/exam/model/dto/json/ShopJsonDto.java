package exam.model.dto.json;

import com.google.gson.annotations.Expose;

import javax.validation.constraints.NotNull;

public class ShopJsonDto {


    @Expose
    private String name;

    public ShopJsonDto() {
    }

    @NotNull
    public String getName() {
        return name;
    }

    public ShopJsonDto setName(String name) {
        this.name = name;
        return this;
    }
}
