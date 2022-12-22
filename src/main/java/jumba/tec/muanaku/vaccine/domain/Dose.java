package jumba.tec.muanaku.vaccine.domain;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Data
public class Dose {
    @Min(value = 1)
    @Max(value =4)
    private int order;
    @Min(1)
    @Max(value =30)
    private int startFromDays;

}
