package com.springbatch.no2;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.builder.FlowBuilder;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@RequiredArgsConstructor
@Configuration
public class BasicFlowConfiguration3 {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job flowJob3() {
        return jobBuilderFactory.get("flowJob3")
                .start(simpleFlow1())
                .next(flow3Step3())
                .end()
                .build();
    }

    @Bean
    public Flow simpleFlow1() {

        FlowBuilder<Flow> builder = new FlowBuilder<>("simpleFlow1");
        builder.start(flow3Step1())
                .next(flow3Step2())
                .end();

        return builder.build();
    }

    @Bean
    public Step flow3Step1() {
        return stepBuilderFactory.get("flow3Step1")
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
                        System.out.println("flow3Step1");

                        if (true) {
                            //throw new RuntimeException("TEST");
                        }

                        return RepeatStatus.FINISHED;
                    }
                }).build();
    }

    @Bean
    public Step flow3Step2() {
        return stepBuilderFactory.get("flow3Step2")
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
                        System.out.println("flow3Step2");

                        if (true) {
                            //throw new RuntimeException("TEST");
                        }

                        return RepeatStatus.FINISHED;
                    }
                }).build();
    }

    @Bean
    public Step flow3Step3() {
        return stepBuilderFactory.get("flow3Step3")
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
                        System.out.println("flow3Step3");

                        if (true) {
                            //throw new RuntimeException("TEST");
                        }

                        return RepeatStatus.FINISHED;
                    }
                }).build();
    }
}
