package com.springbatch.no2;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@RequiredArgsConstructor
@Configuration
public class BasicFlowConfiguration2 {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job flowJob2() {
        return jobBuilderFactory.get("flowJob2")
                .start(flow2Step1())
                    .on("FAILED")
                    .to(flow2Step2())
                    .on("*")
                    .stop()
                .from(flow2Step1())
                    .on("*")
                    .to(flow2Step3())
                    .next(flow2Step4())
                    .end()
                .build();
    }

    @Bean
    public Step flow2Step1() {
        return stepBuilderFactory.get("flow2Step1")
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
                        System.out.println("flow2Step1");

                        if (true) {
                            //throw new RuntimeException("TEST");
                        }

                        return RepeatStatus.FINISHED;
                    }
                }).build();
    }

    @Bean
    public Step flow2Step2() {
        return stepBuilderFactory.get("flow2Step2")
                .tasklet((contribution, chunkContext) -> {
                    System.out.println("flow2Step2");
                    return RepeatStatus.FINISHED;
                }).build();
    }

    @Bean
    public Step flow2Step3() {
        return stepBuilderFactory.get("flow2Step3")
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
                        System.out.println("flow2Step3");
                        return RepeatStatus.FINISHED;
                    }
                }).build();
    }

    @Bean
    public Step flow2Step4() {
        return stepBuilderFactory.get("flow2Step4")
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
                        System.out.println("flow2Step4");
                        return RepeatStatus.FINISHED;
                    }
                }).build();
    }
}
