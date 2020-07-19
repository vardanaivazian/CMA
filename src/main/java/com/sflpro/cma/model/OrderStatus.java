package com.sflpro.cma.model;

import lombok.Getter;

public enum OrderStatus {

    OPEN("Open"), CANCELLED("Cancelled"), CLOSED("Closed");

    @Getter
    private String value;

    OrderStatus(String value) {
        this.value = value;
    }
}

