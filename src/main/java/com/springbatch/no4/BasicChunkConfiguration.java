package com.springbatch.no4;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.support.ListItemReader;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;

@RequiredArgsConstructor
@Configuration
public class BasicChunkConfiguration {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job chunk1Job1() {
        return jobBuilderFactory.get("chunk1Job1")
                .incrementer(new RunIdIncrementer())
                .start(chunk1Step1())
                .build();
    }

    @Bean
    public Step chunk1Step1() {
        return stepBuilderFactory.get("chunk1Step1")
                .<String, String>chunk(2)
                .reader(new ListItemReader<>(Arrays.asList("data1", "data2", "data3", "data4", "data5", "data6", "data7", "data8", "data9", "data10")))
                .processor(new ItemProcessor<String, String>() {
                    @Override
                    public String process(String data) throws Exception {
                        System.out.println("data = " + data);
                        return data + "_A";
                    }
                })
                .writer(new ItemWriter<String>() {
                    @Override
                    public void write(List<? extends String> items) throws Exception {
                        Thread.sleep(1000);
                        System.out.println(items);
                    }
                })
                .build();
    }
}