package com.batchTondeuse.tondeuse;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TondeuseApplication {

	@Qualifier("tondeuseJob")
	private Job tondeuseJob;

	public static void main(String[] args) {
		SpringApplication.run(TondeuseApplication.class, args);

		JobParameters parameters = new JobParametersBuilder().addLong("Start-At" ,System.currentTimeMillis())
				.toJobParameters();

	}

}
