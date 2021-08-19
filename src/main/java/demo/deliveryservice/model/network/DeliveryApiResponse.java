package demo.deliveryservice.model.network;

import demo.deliveryservice.model.enumclass.DeliveryStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DeliveryApiResponse {

    private Long id;

    private DeliveryStatus status;

    private String revAddress;

    private String revName;

    private LocalDateTime arrivalDate;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

}
