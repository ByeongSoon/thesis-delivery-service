package demo.deliveryservice.service;

import demo.deliveryservice.ifs.CrudInterface;
import demo.deliveryservice.model.entity.Delivery;
import demo.deliveryservice.model.network.DeliveryApiRequest;
import demo.deliveryservice.model.network.DeliveryApiResponse;
import demo.deliveryservice.model.network.Header;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeliveryApiLogicService implements CrudInterface<DeliveryApiResponse, DeliveryApiRequest> {

    @Override
    public Header<DeliveryApiResponse> create(Header<DeliveryApiRequest> request) {
        return null;
    }

    @Override
    public Header<DeliveryApiResponse> read(Long id) {
        return null;
    }

    @Override
    public Header<DeliveryApiResponse> update(Header<DeliveryApiRequest> request) {
        return null;
    }

    @Override
    public Header<DeliveryApiResponse> delete(Long id) {
        return null;
    }

    @Override
    public Header<List<DeliveryApiResponse>> search(Pageable pageable) {
        return null;
    }

    private DeliveryApiResponse response(Delivery delivery){
        return DeliveryApiResponse.builder()
                .id(delivery.getId())
                .status(delivery.getStatus())
                .revAddress(delivery.getRevAddress())
                .revName(delivery.getRevName())
                .arrivalDate(delivery.getArrivalDate())
                .createdAt(delivery.getCreatedAt())
                .updatedAt(delivery.getUpdatedAt())
                .build();
    }

}
