package com.olmez.core.model.enums;

import lombok.Getter;

@Getter
public enum CurrencyCode {
    AUD("AUD", "Australian dollar"),
    CAD("CAD", "Canadian dollar"),
    CHF("CHF", "Swiss franc"),
    EUR("EUR", "Euro"),
    GBP("GBP", "Pound sterling"),
    JPY("JPY", "Japanese yen"),
    RUB("RUB", "Russian ruble"),
    TRY("TRY", "Turkish lira"),
    USD("USD", "United States dollar");

    private String name;
    private String description;

    private CurrencyCode(String name, String description) {
        this.name = name;
        this.description = description;
    }

    @Override
    public String toString() {
        return name;
    }

}
