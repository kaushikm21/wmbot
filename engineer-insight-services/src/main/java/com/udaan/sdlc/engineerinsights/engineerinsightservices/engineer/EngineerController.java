package com.udaan.sdlc.engineerinsights.engineerinsightservices.engineer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import java.net.URI;
import java.util.List;

@RestController
public class EngineerController {
    @Autowired
    private EngineerDAO engineerDAO;
    @GetMapping("/engineers/demo")
    public List<Engineer> retrieveAllEngineers(){
        return engineerDAO.findAll();
    }
    @GetMapping("/engineers/demo/{id}")
    public EntityModel<Engineer> retrieveEngineer(@PathVariable int id){
        Engineer engineer = engineerDAO.findEngineer(id);
        if(engineer==null){
            throw new EngineerNotFoundException("id---"+id);
        }
        EntityModel<Engineer> model = EntityModel.of(engineer);
        WebMvcLinkBuilder linkToEngineers = linkTo(methodOn(this.getClass()).retrieveAllEngineers());
        model.add(linkToEngineers.withRel("all-engineers"));
        return model;
    }
    @PostMapping ("/demo/engineers")
    public ResponseEntity<Object> createEngineer( @RequestBody Engineer engineer){
        Engineer savedEngineer = engineerDAO.save(engineer);
       URI location= ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedEngineer.getId()).toUri();
        return ResponseEntity.created(location).build();
    }
    @DeleteMapping("/demo/engineers/{id}")
    public void removeEngineer(@PathVariable int id){
        Engineer engineer = engineerDAO.deleteEngineer(id);
        if(engineer==null){
            throw new EngineerNotFoundException("id--"+id);
        }
    }
}
