package at.models;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Organization {
    @SerializedName("Регистрационный номер")
    private String regNum;
    @SerializedName("ИНН")
    private String inn;
    @SerializedName("ОГРН")
    private String ogrn;
    @SerializedName("Название организации")
    private String name;
    private String regionCD;
    private String actTypeCD;

    public Organization(String regNum, String inn, String ogrn, String name, String regionCD, String actTypeCD) {
        this.regNum = regNum;
        this.inn = inn;
        this.ogrn = ogrn;
        this.name = name;
        this.regionCD = regionCD;
        this.actTypeCD = actTypeCD;
    }

    public Organization() {

    }
}
