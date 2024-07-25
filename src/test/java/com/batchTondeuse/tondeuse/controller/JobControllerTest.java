package com.batchTondeuse.tondeuse.controller;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionException;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(JobController.class)
public class JobControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private JobLauncher jobLauncher;

    @MockBean
    @Qualifier("tondeuseJob")
    private Job tondeuseJob;

    @InjectMocks
    private JobController jobController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testProductLoadSuccess() throws Exception {

            JobParameters jobParameters = new JobParametersBuilder().addLong("Start-At", System.currentTimeMillis()).toJobParameters();
            when(jobLauncher.run(eq(tondeuseJob), any(JobParameters.class))).thenReturn(null);

            ResponseEntity<String> response = jobController.productLoad();

            assertEquals(HttpStatus.OK, response.getStatusCode());
            assertEquals("job run", response.getBody());
    }

    @Test
    public void testProductLoad_Failure() throws Exception {

        doThrow(new JobExecutionAlreadyRunningException("Job is already running"))
                .when(jobLauncher).run(any(Job.class), any(JobParameters.class));

        mockMvc.perform(get("/api/v1/load"))
                .andExpect(status().isExpectationFailed());

        ResponseEntity responseEntity = jobController.productLoad();
        assertEquals(HttpStatus.EXPECTATION_FAILED, responseEntity.getStatusCode());
        assertEquals("job error ", responseEntity.getBody().toString());

    }
}