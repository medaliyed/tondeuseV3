/*
package com.batchTondeuse.tondeuse.configuration;

import com.batchTondeuse.tondeuse.config.TondeuseBatchConfig;
import com.batchTondeuse.tondeuse.processor.TondeuseProcessor;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder;
import org.springframework.batch.item.file.mapping.PassThroughLineMapper;
import org.springframework.batch.item.file.transform.PassThroughLineAggregator;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.batch.test.MetaDataInstanceFactory;
import org.springframework.batch.test.context.SpringBatchTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.PlatformTransactionManager;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;


*/
/*
@ExtendWith(SpringExtension.class)
@SpringBootTest
@SpringBatchTest
public class TondeuseBatchConfigTest {


    @Mock
    private TondeuseBatchConfig config;


    @Autowired
    private FlatFileItemReader<String> reader;

    @Test
    public void testReader() {
        
    }
}*//*

@ExtendWith(SpringExtension.class)
@SpringBootTest
@SpringBatchTest
@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(classes = {TondeuseBatchConfig.class, TondeuseBatchConfig.class})
public class TondeuseBatchConfigTest {

    @InjectMocks
    private TondeuseBatchConfig config;

    @Mock
    private PlatformTransactionManager transactionManager;
    @Mock
    private ItemProcessor processor;
    @Mock
    private FlatFileItemWriter writer;
    @Mock
    private FlatFileItemReader reader;

    @Test
    public void testReader() {
        // Test reader configuration
        FlatFileItemReader reader = config.reader();
        assertNotNull(reader);
        verify(reader).setResource(new ClassPathResource("inputFile.txt"));
        verify(reader).setLineMapper(new PassThroughLineMapper());
    }

    @Test
    public void testWriter() {
        // Test writer configuration
        FlatFileItemWriter writer = config.writer();
        assertNotNull(writer);
        verify(writer).setResource(new FileSystemResource("outputFile.txt"));
        verify(writer).setLineAggregator(new PassThroughLineAggregator<>());
    }


    @Test
    public void testProcessor() {
        // Test processor configuration
        TondeuseProcessor processor = config.processor();
        assertNotNull(processor);
        // Add tests for TondeuseProcessor class
    }
}*/
