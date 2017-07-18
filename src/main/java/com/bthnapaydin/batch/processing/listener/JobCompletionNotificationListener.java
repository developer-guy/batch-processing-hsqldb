package com.bthnapaydin.batch.processing.listener;

import com.bthnapaydin.batch.processing.bdomain.Person;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.List;

@Component
@Slf4j
@RequiredArgsConstructor
public class JobCompletionNotificationListener extends JobExecutionListenerSupport {
    private final JdbcTemplate jdbcTemplate;

    @Override
    public void afterJob(JobExecution jobExecution) {
        if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
            log.info("!!! JOB FINISHED! Time to verify the results");
            List<Person> persons = jdbcTemplate.query("SELECT first_name, last_name FROM people",
                    (resultSet, i) -> new Person(resultSet.getString(1), resultSet.getString(2)));

            for (Person person : persons) {
                log.info("Found <" + person + "> in the database.");
            }
        }
    }
}
