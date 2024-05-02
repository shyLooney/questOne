package com.example.demo.repository.postgres;

import com.example.demo.entity.postgres.PersonPostgres;
import org.springframework.data.repository.CrudRepository;

public interface PersonPostgresRepository extends CrudRepository<PersonPostgres, Long> {
}
