package com.olmez.core.temp;

import com.olmez.core.utility.DateTimeUtility;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Temp {

    public static void main(String[] args) {

        log.info("1- {}", DateTimeUtility.showMemoryUsageBYTE());
        log.info("2- {}", DateTimeUtility.showMemoryUsageKB());
        log.info("3- {}", DateTimeUtility.showMemoryUsageMB());
    }

}
