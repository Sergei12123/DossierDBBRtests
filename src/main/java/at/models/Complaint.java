package at.models;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Complaint {
    @SerializedName("Номер рекламации")
    private String number;
    @SerializedName("Статус рекламации")
    private String status;
}
