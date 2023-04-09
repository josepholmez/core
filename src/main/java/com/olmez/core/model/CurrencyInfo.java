package com.olmez.core.model;

import java.time.LocalDate;
import java.util.Objects;

import org.springframework.lang.Nullable;

import com.olmez.core.model.enums.CurrencyCode;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class CurrencyInfo extends BaseObject {

    private LocalDate date;
    private double amount = 1.0;
    private CurrencyCode baseCode = CurrencyCode.USD;
    private @Nullable Double aUD;
    private @Nullable Double cAD;
    private @Nullable Double cHF;
    private @Nullable Double eUR;
    private @Nullable Double gBP;
    private @Nullable Double uSD;
    private @Nullable Double jPY;
    private @Nullable Double rUB;
    private @Nullable Double tRY;

    public CurrencyInfo(LocalDate date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return String.format("Currency Info on %s", date);
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, date);
    }
}
