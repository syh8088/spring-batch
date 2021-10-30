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
public class BasicFlowConfiguration {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job flowJob() {
        return jobBuilderFactory.get("flowJob")
                .start(flowStep1())
                .on("COMPLETED").to(flowStep2())
                .from(flowStep1())
                .on("FAILED").to(flowStep3())
                .end()
                .build();
    }

    @Bean
    public Step flowStep1() {
        return stepBuilderFactory.get("flowStep1")
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
                        System.out.println("flowStep1");

                        if (true) {
                            throw new RuntimeException("TEST");
                        }

                        return RepeatStatus.FINISHED;
                    }
                }).build();
    }

    @Bean
    public Step flowStep2() {
        return stepBuilderFactory.get("flowStep2")
                .tasklet((contribution, chunkContext) -> {
                    System.out.println("flowStep2");
                    return RepeatStatus.FINISHED;
                }).build();
    }

    @Bean
    public Step flowStep3() {
        return stepBuilderFactory.get("flowStep3")
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
                        System.out.println("flowStep3");
                        return RepeatStatus.FINISHED;
                    }
                }).build();
    }
}
