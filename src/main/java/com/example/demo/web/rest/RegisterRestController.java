package com.example.demo.web.rest;

import com.example.demo.entity.mongo.PersonMongo;
import com.example.demo.entity.postgres.PersonPostgres;
import com.example.demo.repository.mongo.PersonMongoRepository;
import com.example.demo.repository.postgres.PersonPostgresRepository;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/person")
@Slf4j
@AllArgsConstructor
public class RegisterRestController {
    private final PersonMongoRepository mongoRepository;
    private final PersonPostgresRepository postgresRepository;
    private final SimpMessagingTemplate simpMessagingTemplate;

    @PostMapping
    public void createPerson(@RequestBody PersonMongo personMongo) {
        mongoRepository.save(personMongo);
        log.info("'{}' saved to mongo ", personMongo);
    }

    @GetMapping("/mongo")
    public List<PersonMongo> getAllPersons() {
        return mongoRepository.findAll();
    }

    @GetMapping("/postgres")
    public Iterable<PersonPostgres> getAllPersonPostgres() {
        return postgresRepository.findAll();
    }
}
