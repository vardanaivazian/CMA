package com.sflpro.cma.model;

import javax.persistence.AttributeConverter;

public class OrderStatusConverter  implements AttributeConverter<OrderStatus, String> {

    @Override
    public String convertToDatabaseColumn(OrderStatus attribute) {
        return attribute.getValue();
    }

    @Override
    public OrderStatus convertToEntityAttribute(String dbData) {
        return OrderStatus.valueOf(dbData.toUpperCase());
    }
}
