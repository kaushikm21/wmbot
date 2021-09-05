package com.udaan.sdlc.engineerinsights.engineerinsightservices.engineer;

import com.udaan.sdlc.engineerinsights.engineerinsightservices.pr.PRRepository;
import com.udaan.sdlc.engineerinsights.engineerinsightservices.pr.PR;
import com.udaan.sdlc.engineerinsights.engineerinsightservices.services.CSVService;
import com.udaan.sdlc.engineerinsights.engineerinsightservices.utils.CSVHelper;
import com.udaan.sdlc.engineerinsights.engineerinsightservices.utils.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class EngineerRestController {
    @Autowired
    private EngineerRepository engineerRepository;
    @Autowired
    private PRRepository prRepository;
    @Autowired
    CSVService fileService;
    @GetMapping("/engineers")
    public List<Engineer> retrieveAllEngineers(){
        return engineerRepository.findAll();
    }
    @GetMapping("/engineers/{id}")
    public EntityModel<Engineer> retrieveEngineer(@PathVariable int id){
        Optional<Engineer> engineer = engineerRepository.findById(id);
        if(!engineer.isPresent())
            throw new EngineerNotFoundException("id---"+id);
        EntityModel<Engineer> model = EntityModel.of(engineer.get());
        WebMvcLinkBuilder linkToEngineers = linkTo(methodOn(this.getClass()).retrieveAllEngineers());
        model.add(linkToEngineers.withRel("all-engineers"));
        return model;
    }
    @PostMapping ("/engineers")
    public ResponseEntity<Object> createEngineer( @RequestBody Engineer engineer){
        Engineer savedEngineer = engineerRepository.save(engineer);
       URI location= ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedEngineer.getId()).toUri();
        return ResponseEntity.created(location).build();
    }
   @RequestMapping(value = "/engineers/bulk_upload", method=RequestMethod.POST)
   public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file) {
       String message = "";
       if (CSVHelper.hasCSVFormat(file)) {
           try {
               fileService.createEngineers(file, engineerRepository);
               message = "Uploaded the file successfully: " + file.getOriginalFilename();
               return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
           } catch (Exception e) {
               message = "Could not upload the file: " + file.getOriginalFilename() + "!";
               return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
           }
       }
       message = "Please upload a csv file!";
       return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(message));
   }
    @DeleteMapping("/engineers/{id}")
    public void removeEngineer(@PathVariable int id){
     engineerRepository.deleteById(id);
    }
    @GetMapping("/engineers/{id}/prs")
    public List<PR> retrieveAllPRs(@PathVariable int id){
        Optional <Engineer> engineerOptional = engineerRepository.findById(id);
        if(!engineerOptional.isPresent())
            throw new EngineerNotFoundException("id---"+id);

        return engineerOptional.get().getPrs();
    }
    @PostMapping ("/engineers/{id}/prs")
    public ResponseEntity<Object> createPRs(@PathVariable int id, @RequestBody PR prs){
        Optional <Engineer> engineerOptional = engineerRepository.findById(id);
        if(!engineerOptional.isPresent())
            throw new EngineerNotFoundException("id---"+id);
        prRepository.save(prs);
      URI location= ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(prs.getId()).toUri();
        return ResponseEntity.created(location).build();

    }

}
