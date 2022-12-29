package com.olmez.core.currency;

import java.io.IOException;
import java.time.LocalDate;

import com.olmez.core.model.CurrencyInfo;

public interface CurrencyAPIService {

    CurrencyInfo update() throws IOException, InterruptedException;

    CurrencyInfo update(LocalDate date) throws IOException, InterruptedException;

}
