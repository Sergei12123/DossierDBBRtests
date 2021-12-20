package at.models;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Opty {
    @SerializedName("Тип заявки")
    private String type;
    @SerializedName("Номер заявки")
    private String number;
    @SerializedName("Статус заявки")
    private String status;
}
