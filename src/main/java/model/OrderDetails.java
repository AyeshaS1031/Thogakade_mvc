package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class OrderDetails {
    private String orderId;
    private String  itemCode;
    private String orderQty;
    private String discount;
}
