package com.olmez.core.currency;

import java.io.IOException;
import java.time.LocalDate;

import com.olmez.core.model.CurrencyRate;

public interface CurrencyAPIService {

    CurrencyRate update() throws IOException, InterruptedException;

    CurrencyRate update(LocalDate date) throws IOException, InterruptedException;

}
