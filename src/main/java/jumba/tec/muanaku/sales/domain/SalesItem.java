package jumba.tec.muanaku.sales.domain;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class SalesItem {
    private BigDecimal price;
    private int quantity;
    private boolean plucked;
    private BigDecimal total;

}
