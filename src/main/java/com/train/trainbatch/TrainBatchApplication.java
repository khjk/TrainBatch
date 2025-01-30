package com.train.trainbatch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class TrainBatchApplication {

    @Autowired
    private final JobBuilderFactory jobBuilderFactory;

    @Autowired
    private final StepBuilderFactory stepBuilderFactory;

    public TrainBatchApplication(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory) {
        this.jobBuilderFactory = jobBuilderFactory;
        this.stepBuilderFactory = stepBuilderFactory;
    }

    @Bean
    public Step step() {
        return this.stepBuilderFactory.get("step!!!")
                .tasklet((contribution, chunkContext) -> {
                    System.out.println("Step");
                    return RepeatStatus.FINISHED;
                }).build();
    }

    @Bean
    public Job job() {
        return this.jobBuilderFactory.get("job!!!")
                .start(step())
                .build();
    }

    public static void main(String[] args) {
        SpringApplication.run(TrainBatchApplication.class, args);
    }

}
