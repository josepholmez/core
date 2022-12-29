package com.olmez.core.services.impl;

import org.springframework.stereotype.Service;

import com.olmez.core.repositories.CurrencyInfoRepository;
import com.olmez.core.repositories.EmployeeRepository;
import com.olmez.core.repositories.LocationRepository;
import com.olmez.core.repositories.UserRepository;
import com.olmez.core.services.TestRepoCleanerService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class TestRepoCleanerServiceImpl implements TestRepoCleanerService {

    private final UserRepository userRepository;
    private final LocationRepository locationRepository;
    private final EmployeeRepository employeeRepository;
    private final CurrencyInfoRepository curInfoRepository;

    public void clear() {
        locationRepository.deleteAll();
        userRepository.deleteAll();
        employeeRepository.deleteAll();
        curInfoRepository.deleteAll();
        log.info("All test repositories has cleaned!");
    }

}
