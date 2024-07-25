package com.batchTondeuse.tondeuse.config;

import com.batchTondeuse.tondeuse.processor.TondeuseProcessor;
import org.springframework.batch.core.*;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.file.*;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder;
import org.springframework.batch.item.file.mapping.*;
import org.springframework.batch.item.file.transform.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class TondeuseBatchConfig {

    @Value("${file.input}")
    private String inputSource;
    @Value("${file.output}")
    private String outputSource;



    // reader() pour lire le fichier d'entrée
    @Bean
    public FlatFileItemReader<String> reader() {

        return new FlatFileItemReaderBuilder<String>()
                .name("tondeuseReader")
                .resource(new ClassPathResource(inputSource))
                .lineMapper(new PassThroughLineMapper())
                .build();
    }


    // writer() pour écrire les résultats dans un fichier
    @Bean
    public FlatFileItemWriter<String> writer() {

        return new FlatFileItemWriterBuilder<String>()
                .name("tondeuseWriter")
                .resource(new FileSystemResource(outputSource))
                .lineAggregator(new PassThroughLineAggregator<>())
                .build();
    }

    // Configuration du job Spring Batch
    @Bean
    public Job tondeuseJob(JobRepository jobRepository, @Qualifier("tondeuseStep") Step tondeuseStep, PlatformTransactionManager transactionManager) {

        System.out.println("Here IM JOB!!!!!!!!!!!!!!!!!");
        return new JobBuilder("tondeuseJob", jobRepository)
                .incrementer(new RunIdIncrementer())
                .flow(tondeuseStep)
                .end()
                .build();
    }

    // Configuration de l'étape du job
    @Bean
    public Step tondeuseStep(JobRepository jobRepository, PlatformTransactionManager transactionManager, ItemProcessor<String, String> processor,
                             FlatFileItemWriter<String> writer, FlatFileItemReader<String> reader) {

        return new StepBuilder("tondeuseStep", jobRepository)
                .<String, String>chunk(1, transactionManager)
                .reader(reader())
                .processor(processor())
                .writer(writer())
                .allowStartIfComplete(true)
                .build();
    }

    // Processeur pour traiter les instructions des tondeuses
    @Bean
    public TondeuseProcessor processor() {
        return new TondeuseProcessor();
    }

}