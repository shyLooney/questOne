package com.example.demo.repository.mongo;

import com.example.demo.entity.mongo.PersonMongo;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PersonMongoRepository extends MongoRepository<PersonMongo, Long> {
}
