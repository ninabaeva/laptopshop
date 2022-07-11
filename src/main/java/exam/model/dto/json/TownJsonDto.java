package exam.model.dto.json;

import com.google.gson.annotations.Expose;

public class TownJsonDto {

    @Expose
    private String name;

    public TownJsonDto() {
    }

    public String getName() {
        return name;
    }

    public TownJsonDto setName(String name) {
        this.name = name;
        return this;
    }
}
