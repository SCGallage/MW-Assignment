package com.demo.delivery.controller;

import com.demo.delivery.bo.DeliveryBO;
import com.demo.delivery.dto.DeliveryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/delivery")
@CrossOrigin("*")
public class DeliveryController {
    private final DeliveryBO deliveryBO;

    @Autowired
    public DeliveryController(DeliveryBO deliveryBO) {
        this.deliveryBO = deliveryBO;
    }

    @PostMapping
    public String addDelivery(@Valid @RequestBody DeliveryDTO deliveryDTO) {
        if (deliveryBO.addDelivery(deliveryDTO)) {
            return "Delivery added successfully.";
        }
        return "Failed to add delivery.";
    }

    @PutMapping
    public String updateDelivery(@Valid @RequestBody DeliveryDTO deliveryDTO) {
        if (deliveryBO.updateDelivery(deliveryDTO)) {
            return "Delivery updated successfully.";
        }
        return "Failed to update delivery.";
    }

    @PutMapping("/state/processing")
    public String setStateProcessing(@Valid @RequestBody DeliveryDTO deliveryDTO) {
        if (deliveryBO.setDeliveryStateProcessing(deliveryDTO)) {
            return "Delivery state updated successfully.";
        }
        return "Failed to update delivery state.";
    }

    @PutMapping("/state/shipped")
    public String setStateShipped(@Valid @RequestBody DeliveryDTO deliveryDTO) {
        if (deliveryBO.setDeliveryStateShipped(deliveryDTO)) {
            return "Delivery state updated successfully.";
        }
        return "Failed to update delivery state.";
    }

    @PutMapping("/state/received")
    public String setStateReceived(@Valid @RequestBody DeliveryDTO deliveryDTO) {
        if (deliveryBO.setDeliveryStateReceived(deliveryDTO)) {
            return "Delivery state updated successfully.";
        }
        return "Failed to update delivery state.";
    }

    @DeleteMapping("/{id}")
    public String deleteDelivery(@Valid @PathVariable Long id) {
        DeliveryDTO deliveryDTO = new DeliveryDTO();
        deliveryDTO.setId(id);

        if (deliveryBO.deleteDelivery(deliveryDTO)) {
            return "Delivery deleted successfully.";
        }
        return "Failed to delete delivery.";
    }

    @GetMapping("/{id}")
    public DeliveryDTO getDelivery(@Valid @PathVariable Long id) {
        DeliveryDTO dto = new DeliveryDTO();
        dto.setId(id);
        return deliveryBO.getDeliveryById(dto);
    }

    @GetMapping
    public List<DeliveryDTO> getDeliveries() {

        return deliveryBO.getAllDeliveries();
    }
}
