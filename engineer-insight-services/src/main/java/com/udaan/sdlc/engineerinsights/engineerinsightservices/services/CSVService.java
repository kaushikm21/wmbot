package com.udaan.sdlc.engineerinsights.engineerinsightservices.services;

import java.io.IOException;
import java.util.List;

import com.udaan.sdlc.engineerinsights.engineerinsightservices.engineer.Engineer;
import com.udaan.sdlc.engineerinsights.engineerinsightservices.engineer.EngineerRepository;
import com.udaan.sdlc.engineerinsights.engineerinsightservices.pr.PRRepository;
import com.udaan.sdlc.engineerinsights.engineerinsightservices.pr.PR;
import com.udaan.sdlc.engineerinsights.engineerinsightservices.utils.CSVHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class CSVService {
    public void createEngineers(MultipartFile file, EngineerRepository engineerRepository) {
        try {
            List<Engineer> engineers = CSVHelper.csvToEngineer(file.getInputStream());
            engineerRepository.saveAll(engineers);
        } catch (IOException e) {
            throw new RuntimeException("fail to store csv data: " + e.getMessage());
        }
    }
    public void createPRs(MultipartFile file, PRRepository prRepository) {
        try {
            List<PR> prs = CSVHelper.csvToPR(file.getInputStream());
            prRepository.saveAll(prs);
        } catch (Exception e) {
            System.out.println("There is some issue"+e.getMessage());
            throw new RuntimeException("fail to store csv data: " + e.getMessage());
        }
    }
}
