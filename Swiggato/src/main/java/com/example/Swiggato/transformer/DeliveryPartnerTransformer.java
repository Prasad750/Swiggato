package com.example.Swiggato.transformer;

import com.example.Swiggato.dto.request.DeliveryPartnerRequest;
import com.example.Swiggato.model.Cart;
import com.example.Swiggato.model.DeliveryPartner;
import com.example.Swiggato.model.OrderEntity;

import java.util.ArrayList;

public class DeliveryPartnerTransformer {

    public static DeliveryPartner DeliveryPartnerRequestToDeliveryPartner(DeliveryPartnerRequest deliveryPartnerRequest)
    {
        return DeliveryPartner.builder()
                .name(deliveryPartnerRequest.getName())
                .mobileNo(deliveryPartnerRequest.getMobileNo())
                .gender(deliveryPartnerRequest.getGender())
                .orderEntities(new ArrayList<>())
                .build();
    }
}
