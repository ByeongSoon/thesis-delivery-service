package demo.deliveryservice.controller;

import demo.deliveryservice.model.network.DeliveryApiRequest;
import demo.deliveryservice.service.DeliveryApiLogicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Mono;

@Controller
public class DeliveryRSocketMapping {

  @Autowired
  private DeliveryApiLogicService deliveryApiLogicService;

  @MessageMapping("delivery-id")
  Mono<Long> getDeliveryId(DeliveryApiRequest request) {
    return deliveryApiLogicService.createByOrderRSocket(request);
  }

}
