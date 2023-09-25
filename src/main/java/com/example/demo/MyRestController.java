package com.example.demo;

import io.r2dbc.spi.Row;
import io.r2dbc.spi.RowMetadata;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.http.MediaType;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;
import java.util.List;
import java.util.function.BiFunction;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/applicants")
public class MyRestController {

    private final ApplicantRepository applicantRepository;

    private final DatabaseClient databaseClient;

    public static final BiFunction<Row, RowMetadata, Applicant> MAPPING_FUNCTION = (row, rowMetaData) -> Applicant.builder()
            .applicantId(row.get("applicant_id", Integer.class))
            .firstName(row.get("first_name", String.class))
            .build();

    public MyRestController(ApplicantRepository applicantRepository, DatabaseClient databaseClient) {
        this.applicantRepository = applicantRepository;
        this.databaseClient = databaseClient;
    }

    @GetMapping("/non-reactive")
    public Flux<Applicant> getAllApplicantsNonReactive() {
        return getCountWithCount(1000);
    }

    @QueryMapping
    public Flux<Applicant> applicants() {
        return this.applicantRepository.findAll();
//                .buffer(100);
    }

    @MutationMapping
    public Mono<Applicant> updateApplicant(@RequestBody Applicant applicant){
        return this.applicantRepository.save(applicant);
    }


    @GetMapping(produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Applicant> getAllApplicants() {
        return this.applicantRepository.findAll();
//                .buffer(100);

    }


    /**
     * Batch
     * @param count
     * @return
     */
    @GetMapping( value = {"/batch/{count}"},produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<List<Applicant>> getApplicants(@PathVariable("count") Integer count) {
        return getCountWithCount(count)     .subscribeOn(Schedulers.boundedElastic())
                .bufferTimeout(100, Duration.ofMillis(500));
    }

    /**
     * Without batch
     * @param count
     * @return
     */
    @GetMapping( value = {"/{count}"},produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Applicant> getApplicantsBatch(@PathVariable("count") Integer count) {
        return getCountWithCount(count);
    }

    private Flux<Applicant> getCountWithCount(Integer count) {
        return this.databaseClient.sql("select * from applicant limit :count").bind("count", count).map(MAPPING_FUNCTION).all();
    }
}
