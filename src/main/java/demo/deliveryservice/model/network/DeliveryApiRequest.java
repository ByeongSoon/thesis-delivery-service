package demo.deliveryservice.model.network;

import demo.deliveryservice.model.enumclass.DeliveryStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DeliveryApiRequest {

    private Long id;

    private DeliveryStatus status;

    private String revAddress;

    private String revName;

}
