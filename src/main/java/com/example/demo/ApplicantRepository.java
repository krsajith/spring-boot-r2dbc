package com.example.demo;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface ApplicantRepository extends ReactiveCrudRepository<Applicant, Integer> {
//
//    @Query("select id, name, age from player where name = $1")
//    Flux<Applicant> findAllByName(String name);
//
//    @Query("select * from player where age = $1")
//    Flux<Player> findByAge(int age);
}

//import org.springframework.data.repository.Repository;
//
//public interface ApplicantRepository extends Repository<Applicant, Long> {
//
//}
