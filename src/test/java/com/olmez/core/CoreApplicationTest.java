package com.olmez.core;

import lombok.extern.slf4j.Slf4j;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.olmez.core.utility.TestUtility;

@SpringBootApplication
@Slf4j
public class CoreApplicationTest implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(CoreApplicationTest.class, args);
    }


    @Override
    public void run(String... args) throws Exception {
        log.info("Test app has started!");
    }

    @Test
    void testBasic(){
        String profile = TestUtility.PROFILE;
        assertThat(profile).isEqualTo("test");
    }
}
