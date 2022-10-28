package com.demo.delivery.bo;

import com.demo.delivery.dto.DeliveryDTO;

import java.util.List;

public interface DeliveryBO {
    public boolean addDelivery(DeliveryDTO deliveryDTO);
    public boolean updateDelivery(DeliveryDTO deliveryDTO);
    public boolean deleteDelivery(DeliveryDTO deliveryDTO);
    public List<DeliveryDTO> getAllDeliveries();
    public DeliveryDTO getDeliveryById(DeliveryDTO deliveryDTO);
    public boolean setDeliveryStateProcessing(DeliveryDTO deliveryDTO);
    public boolean setDeliveryStateShipped(DeliveryDTO deliveryDTO);
    public boolean setDeliveryStateReceived(DeliveryDTO deliveryDTO);


}
