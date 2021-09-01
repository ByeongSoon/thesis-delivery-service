package demo.deliveryservice.controller;

import demo.deliveryservice.ifs.CrudInterface;
import demo.deliveryservice.model.network.DeliveryApiRequest;
import demo.deliveryservice.model.network.DeliveryApiResponse;
import demo.deliveryservice.model.network.Header;
import demo.deliveryservice.service.DeliveryApiLogicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/delivery")
public class DeliveryApiController implements CrudInterface<DeliveryApiResponse, DeliveryApiRequest> {

    @Autowired
    private DeliveryApiLogicService deliveryApiLogicService;

    @Override
    @PostMapping("")
    public Header<DeliveryApiResponse> create(@RequestBody Header<DeliveryApiRequest> request) {
        return deliveryApiLogicService.create(request);
    }

    @PostMapping("/byOrder")
    public Long createByOrder(@RequestBody DeliveryApiRequest request) {
        return deliveryApiLogicService.createByOrder(request);
    }

    @Override
    @GetMapping("{id}")
    public Header<DeliveryApiResponse> read(@PathVariable Long id) {
        return deliveryApiLogicService.read(id);
    }

    @Override
    @PutMapping("")
    public Header<DeliveryApiResponse> update(@RequestBody Header<DeliveryApiRequest> request) {
        return deliveryApiLogicService.update(request);
    }

    @Override
    @DeleteMapping("{id}")
    public Header delete(@PathVariable Long id) {
        return deliveryApiLogicService.delete(id);
    }

    @Override
    @GetMapping("")
    public Header<List<DeliveryApiResponse>> search(@PageableDefault(sort = "id", direction = Sort.Direction.ASC, size = 10) Pageable pageable) {
        return deliveryApiLogicService.search(pageable);
    }

}
