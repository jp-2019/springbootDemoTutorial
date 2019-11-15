package com.example.demo.api;

import com.example.demo.model.Person;
import com.example.demo.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

@RequestMapping("api/v1/person")
@RestController
public class PersonController {

    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    // POST request to db
    @PostMapping
    public void addPerson(@Valid @NotNull @RequestBody Person person){ // request body from Postman
        personService.addPerson(person);
    }

    //GET request
    @GetMapping
    public List<Person> getAllPeople(){
        return personService.getAllPeople();
    }

    //GET request by json id
    @GetMapping(path = "{id}")
    public Person getPersonById(@PathVariable("id") UUID id){ //path variable is JSON id from postman
        return personService.getPersonById(id)
                .orElse(null); // throw 404 error message optional here
    }

    //DELETE request
    @DeleteMapping(path = "{id}")
    public void deletePersonById(@PathVariable("id") UUID id){
        personService.deletePerson(id);
    }

    //UPDATE request
    @PutMapping(path = "{id}")
    public void updatePerson(@PathVariable("id") UUID id, @Valid @NotNull @RequestBody Person personToUpdate){
        personService.updatePerson(id, personToUpdate);
    }
}
