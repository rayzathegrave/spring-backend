package com.example.springbackend.controller;

import com.example.springbackend.model.Reading;
import com.example.springbackend.repository.ReadingRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class TemperatureControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ReadingRepository mockRepository;

    @Test
    void shouldSaveAndReturnReading() throws Exception {
        Reading reading = new Reading(LocalDateTime.now(), "23.5");

        when(mockRepository.save(any(Reading.class))).thenReturn(reading);

        mockMvc.perform(post("/api/temperature")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(reading)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.temperature").value("23.5"));
    }

    @Test
    void shouldReturnAllReadings() throws Exception {
        List<Reading> readings = List.of(
                new Reading(LocalDateTime.now(), "21.1"),
                new Reading(LocalDateTime.now().minusMinutes(1), "22.0")
        );

        when(mockRepository.findAll()).thenReturn(readings);

        mockMvc.perform(get("/api/temperature"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
    }

    @TestConfiguration
    static class MockConfig {
        @Bean
        @Primary
        public ReadingRepository mockRepo() {
            return mock(ReadingRepository.class);
        }
    }
}
