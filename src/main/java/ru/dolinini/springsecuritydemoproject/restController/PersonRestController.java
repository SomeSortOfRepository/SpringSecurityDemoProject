package ru.dolinini.springsecuritydemoproject.restController;

import lombok.Getter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.dolinini.springsecuritydemoproject.model.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("/api/v1/persons")
public class PersonRestController {
    private List<Person> personsList= Stream.of(new Person(1L,"Asd","Qwe"),
                                                new Person(2L,"Sdf","Wer"),
                                                new Person(3L,"Dfg","Ert"))
                                            .collect(Collectors.toList());


    @GetMapping
    public List<Person> findAll() {

        return personsList;
    }

    @GetMapping("/{id}")
    public Person getById(@PathVariable(name="id") Long id) {

        return personsList.stream()
                          .filter(person->person.getId().equals(id))
                          .findFirst()
                          .orElseThrow();
    }


}
