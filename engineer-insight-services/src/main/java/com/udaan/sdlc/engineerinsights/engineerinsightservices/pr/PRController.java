package com.udaan.sdlc.engineerinsights.engineerinsightservices.pr;

import com.udaan.sdlc.engineerinsights.engineerinsightservices.engineer.Engineer;
import com.udaan.sdlc.engineerinsights.engineerinsightservices.engineer.EngineerNotFoundException;
import com.udaan.sdlc.engineerinsights.engineerinsightservices.engineer.EngineerRepository;
import com.udaan.sdlc.engineerinsights.engineerinsightservices.services.CSVService;
import com.udaan.sdlc.engineerinsights.engineerinsightservices.utils.CSVHelper;
import com.udaan.sdlc.engineerinsights.engineerinsightservices.utils.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RestController
public class PRController {
    @Autowired
    private EngineerRepository engineerRepository;
    @Autowired
    private PRRepository prRepository;
    @Autowired
    CSVService fileService;

   @RequestMapping(value = "/prs/bulk_upload", method=RequestMethod.POST)
   public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file) {
       String message = "";
       if (CSVHelper.hasCSVFormat(file)) {
           try {
               fileService.createPRs(file, prRepository);
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
  @GetMapping(value = "/prs/misses/{missedval}/{enggid}")
    public List<PR> retrieveMissesByEngineer(@PathVariable int missedval, @PathVariable int enggid){
       System.out.println("coming here");
        return prRepository.findMissesByEngineer_ID(missedval,enggid);
    }


}
