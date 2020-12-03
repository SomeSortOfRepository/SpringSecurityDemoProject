package ru.dolinini.springsecuritydemoproject.restController;



import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.dolinini.springsecuritydemoproject.model.Person;


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
    @PreAuthorize("hasAuthority('permission:read')")
    public Person getPersonById(@PathVariable(name="id") Long id) {

        return personsList.stream()
                          .filter(person->person.getId().equals(id))
                          .findFirst()
                          .orElseThrow();
    }
    @PostMapping
    @PreAuthorize("hasAuthority('permission:write')")
    public Person createAndAddNewPerson(@RequestBody Person person){
        if(personsList.stream().anyMatch(p->p.getId().equals(person.getId()))) {
            throw new RuntimeException("Such person already exists");
        }
        else {
            personsList.add(person);
        }
        return person;
    }
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('permission:write')")
    public void removePersonById(@PathVariable(name = "id") Long id){
        personsList.removeIf(person->person.getId().equals(id));
    }




}
