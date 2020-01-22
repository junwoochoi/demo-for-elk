package test.elk.demo

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.batch.core.Job
import org.springframework.batch.core.Step
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory
import org.springframework.batch.repeat.RepeatStatus
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class HelloBatchConfig(private val jobBuilderFactory: JobBuilderFactory,
                       private val stepBuilderFactory: StepBuilderFactory) {
    private val log: Logger = LoggerFactory.getLogger(HelloBatchConfig::class.java)

    @Bean
    fun helloJob(step: Step): Job = jobBuilderFactory.get("HelloJob")
            .start(step)
            .build();

    @Bean
    fun step(): Step = stepBuilderFactory.get("HelloJobTaskletStep")
            .tasklet { _, _ ->
                log.info("==== Tasklet called ... ====")

                for (i in 1..10)
                    log.info("Hello Job Batch Log {}", i)
                RepeatStatus.FINISHED
            }.build()
}