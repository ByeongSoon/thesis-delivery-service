package demo.deliveryservice.service;

import demo.deliveryservice.ifs.CrudInterface;
import demo.deliveryservice.model.entity.Delivery;
import demo.deliveryservice.model.network.DeliveryApiRequest;
import demo.deliveryservice.model.network.DeliveryApiResponse;
import demo.deliveryservice.model.network.Header;
import demo.deliveryservice.model.network.Pagination;
import demo.deliveryservice.repository.DeliveryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DeliveryApiLogicService implements CrudInterface<DeliveryApiResponse, DeliveryApiRequest> {

    @Autowired
    private DeliveryRepository deliveryRepository;

    @Override
    @Transactional
    public Header<DeliveryApiResponse> create(Header<DeliveryApiRequest> request) {
        DeliveryApiRequest body = request.getData();

        Delivery delivery = Delivery.builder()
                .status(body.getStatus())
                .revAddress(body.getRevAddress())
                .revName(body.getRevName())
                .arrivalDate(LocalDateTime.now().plusDays(2))
                .build();

        return Header.OK(response(deliveryRepository.save(delivery)));
    }

    @Transactional
    public Long createByOrder(DeliveryApiRequest request) {

        Delivery delivery = Delivery.builder()
                .status(request.getStatus())
                .revAddress(request.getRevAddress())
                .revName(request.getRevName())
                .arrivalDate(LocalDateTime.now().plusDays(2))
                .build();

        return deliveryRepository.save(delivery).getId();
    }

    @Transactional
    public Mono<Long> createByOrderRSocket(DeliveryApiRequest request) {
        Delivery delivery = Delivery.builder()
            .status(request.getStatus())
            .revAddress(request.getRevAddress())
            .revName(request.getRevName())
            .arrivalDate(LocalDateTime.now().plusDays(2))
            .build();

        Delivery newDelivery =  deliveryRepository.save(delivery);

        return Mono.just(
            newDelivery.getId()
        );
    }

    @Override
    @Transactional
    public Header<DeliveryApiResponse> read(Long id) {
        return deliveryRepository.findById(id)
                .map(this::response)
                .map(Header::OK)
                .orElseGet(() -> Header.ERROR("????????? ??????"));
    }

    @Override
    @Transactional
    public Header<DeliveryApiResponse> update(Header<DeliveryApiRequest> request) {
        DeliveryApiRequest body = request.getData();

        return deliveryRepository.findById(body.getId())
                .map(delivery -> delivery
                        .setStatus(body.getStatus())
                        .setRevAddress(body.getRevAddress())
                        .setRevName(body.getRevName())
                )
                .map(delivery ->  deliveryRepository.save(delivery))
                .map(this::response)
                .map(Header::OK)
                .orElseGet(() -> Header.ERROR("???????????? ??????"));
    }

    @Override
    @Transactional
    public Header delete(Long id) {
        return deliveryRepository.findById(id)
                .map(delivery -> {
                    deliveryRepository.delete(delivery);
                    return Header.OK();
                })
                .orElseGet(() -> Header.ERROR("?????? ??????"));
    }

    @Override
    @Transactional
    public Header<List<DeliveryApiResponse>> search(Pageable pageable) {
        Page<Delivery> deliveries = deliveryRepository.findAll(pageable);

        List<DeliveryApiResponse> deliveryApiResponseList = deliveries.stream()
                .map(this::response)
                .collect(Collectors.toList());

        Pagination pagination = Pagination.builder()
                .totalPage(deliveries.getTotalPages())
                .totalElements(deliveries.getTotalElements())
                .currentPage(deliveries.getNumber())
                .currentElements(deliveries.getNumberOfElements())
                .build();

        return Header.OK(deliveryApiResponseList,pagination);
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
