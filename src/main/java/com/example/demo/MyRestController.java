package com.example.demo;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/applicants")
public class MyRestController {

    private final ApplicantRepository applicantRepository;

    public MyRestController(ApplicantRepository applicantRepository) {
        this.applicantRepository = applicantRepository;
    }

    @GetMapping(produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Applicant> getUserCustomers() {
        return this.applicantRepository.findAll();
//                .onBackpressureBuffer(10);
    }
}
