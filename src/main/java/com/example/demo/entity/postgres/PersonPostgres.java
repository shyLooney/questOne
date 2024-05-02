package com.example.demo.entity.postgres;


import com.example.demo.entity.mongo.PersonMongo;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor(access= AccessLevel.PRIVATE, force=true)
public class PersonPostgres {
    @Id
    private Long id;
    private String name;

    public PersonPostgres(PersonMongo personMongo) {
        this.id = personMongo.getId();
        this.name = personMongo.getName();
    }
}
