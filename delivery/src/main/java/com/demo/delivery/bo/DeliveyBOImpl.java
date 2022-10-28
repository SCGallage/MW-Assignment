package com.demo.delivery.bo;

import com.demo.delivery.dao.DeliveryDAO;
import com.demo.delivery.dto.DeliveryDTO;
import com.demo.delivery.entity.Delivery;
import com.demo.delivery.util.Globals;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DeliveyBOImpl implements DeliveryBO {
    private final DeliveryDAO deliveryDAO;

    @Autowired
    public DeliveyBOImpl(DeliveryDAO deliveryDAO) {
        this.deliveryDAO = deliveryDAO;
    }

    private Delivery getEntity(DeliveryDTO deliveryDTO) {
        Delivery delivery = new Delivery();
        if (deliveryDTO.getId() != null) delivery.setId(deliveryDTO.getId());
        if (deliveryDTO.getAddress() != null) delivery.setAddress(deliveryDTO.getAddress());
        if (deliveryDTO.getDate() != null) delivery.setDate(deliveryDTO.getDate());
        if (deliveryDTO.getReceiverName() != null) delivery.setReceiverName(deliveryDTO.getReceiverName());

        return delivery;
    }

    private DeliveryDTO getDto(Delivery delivery) {
        DeliveryDTO deliveryDTO = new DeliveryDTO();
        if (delivery.getId() != null) deliveryDTO.setId(delivery.getId());
        if (delivery.getAddress() != null) deliveryDTO.setAddress(delivery.getAddress());
        if (delivery.getDate() != null) deliveryDTO.setDate(delivery.getDate());
        if (delivery.getReceiverName() != null) deliveryDTO.setReceiverName(delivery.getReceiverName());
        if (delivery.getState() != null) deliveryDTO.setState(delivery.getState());

        return deliveryDTO;
    }

    private Date getDateToday() {
        LocalDate localDate = LocalDate.now();
        return Date.valueOf(localDate);
    }

    @Override
    public boolean addDelivery(DeliveryDTO deliveryDTO) {
        Delivery delivery = getEntity(deliveryDTO);
        //set date
        delivery.setDate(getDateToday());
        delivery.setState(Globals.DELIVERY_STATE_PROCESSING);
        try {
            deliveryDAO.save(delivery);
            return true;
        } catch (Exception e) {
            System.out.println("Failed to add delivery. " + e);
        }
        return false;
    }

    @Override
    public boolean updateDelivery(DeliveryDTO deliveryDTO) {
        //check existence
        if (deliveryDAO.existsById(deliveryDTO.getId())) {

            Optional<Delivery> deliveryOpt = deliveryDAO.findById(deliveryDTO.getId());

            if (deliveryOpt.isPresent()) {
                Delivery delivery = deliveryOpt.get();

                //only address and receiver can be updated
                delivery.setAddress(deliveryDTO.getAddress());
                delivery.setReceiverName(deliveryDTO.getReceiverName());

                try {
                    deliveryDAO.save(delivery);
                    return true;
                } catch (Exception e) {
                    System.out.println("Failed to update delivery. " + e);
                }
            }
        }
        return false;
    }

    @Override
    public boolean deleteDelivery(DeliveryDTO deliveryDTO) {
        //check existence
        if (deliveryDAO.existsById(deliveryDTO.getId())) {

            Optional<Delivery> deliveryOpt = deliveryDAO.findById(deliveryDTO.getId());

            if (deliveryOpt.isPresent()) {
                Delivery delivery = deliveryOpt.get();

                try {
                    deliveryDAO.delete(delivery);
                    return true;
                } catch (Exception e) {
                    System.out.println("Failed to delete delivery. " + e);
                }
            }
        }
        return false;
    }

    @Override
    public List<DeliveryDTO> getAllDeliveries() {
        List<Delivery> deliveries = deliveryDAO.findAll();
        if (!deliveries.isEmpty()) {
            List<DeliveryDTO> deliveryDTOS = new ArrayList<>();
            deliveries.forEach(delivery -> deliveryDTOS.add(getDto(delivery)));

            return deliveryDTOS;
        }
        return null;
    }

    @Override
    public DeliveryDTO getDeliveryById(DeliveryDTO deliveryDTO) {
        //check existence
        if (deliveryDAO.existsById(deliveryDTO.getId())) {
            Optional<Delivery> deliveryOpt = deliveryDAO.findById(deliveryDTO.getId());
            if (deliveryOpt.isPresent()) {
                return getDto(deliveryOpt.get());
            }

        }
        return null;
    }

    private boolean setDeliveryState(String state, Long id) {
        if (deliveryDAO.existsById(id)) {
            Optional<Delivery> deliveryOpt = deliveryDAO.findById(id);
            if (deliveryOpt.isPresent()) {
                Delivery delivery = deliveryOpt.get();
                delivery.setState(state);

                try {
                    deliveryDAO.save(delivery);
                    return true;
                } catch (Exception e) {
                    System.out.println("Failed to update delivery. " + e);
                }
            }

        }
        return false;
    }

    @Override
    public boolean setDeliveryStateProcessing(DeliveryDTO deliveryDTO) {
        return setDeliveryState(Globals.DELIVERY_STATE_PROCESSING, deliveryDTO.getId());
    }

    @Override
    public boolean setDeliveryStateShipped(DeliveryDTO deliveryDTO) {
        return setDeliveryState(Globals.DELIVERY_STATE_SHIPPED, deliveryDTO.getId());
    }

    @Override
    public boolean setDeliveryStateReceived(DeliveryDTO deliveryDTO) {
        return setDeliveryState(Globals.DELIVERY_STATE_RECEIVED, deliveryDTO.getId());
    }
}
