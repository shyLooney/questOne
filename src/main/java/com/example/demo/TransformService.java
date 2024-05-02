package com.example.demo;

import com.example.demo.entity.postgres.PersonPostgres;
import com.example.demo.repository.mongo.PersonMongoRepository;
import com.example.demo.repository.postgres.PersonPostgresRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.HashSet;

@Slf4j
@Service
@EnableScheduling
public class TransformService {
    private final PersonMongoRepository mongoRepository;
    private final PersonPostgresRepository postgresRepository;
    private final HashSet<Long> hashSet = new HashSet<>();
    private final SimpMessagingTemplate simpMessagingTemplate;


    public TransformService(PersonMongoRepository mongoRepository,
                            PersonPostgresRepository postgresRepository,
                            SimpMessagingTemplate simpMessagingTemplate) {
        this.mongoRepository = mongoRepository;
        this.postgresRepository = postgresRepository;
        this.simpMessagingTemplate = simpMessagingTemplate;

        for (var item : postgresRepository.findAll()) {
            hashSet.add(item.getId());
        }
    }

    @Scheduled(cron = "*/30 * * * * *")
    public void transformData() {
        mongoRepository
                .findAll()
                .stream()
                .filter(elem -> !hashSet.contains(elem.getId()))
                .forEach(elem -> {
                    hashSet.add(elem.getId());

                    var postgres = new PersonPostgres(elem);
                    postgresRepository.save(postgres);
                    log.info("Person '{}' saved to postgres", elem);

                    simpMessagingTemplate.convertAndSend("/topic/person?type=postgres", postgres);
                });

    }

}
