package com.udaan.sdlc.engineerinsights.engineerinsightservices.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import com.udaan.sdlc.engineerinsights.engineerinsightservices.engineer.Engineer;
import com.udaan.sdlc.engineerinsights.engineerinsightservices.pr.PR;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.web.multipart.MultipartFile;
public class CSVHelper {
    public static String TYPE = "text/csv";
    static String[] HEADERs = { "Id", "Bravo", "Joined", "Name", "Git_Id" };
    static String[] PR_HEADERs = {"Id", "Review_Duration_Sec", "Review_Date", "Comments", "Misses", "Engineer_Id"};

    public static boolean hasCSVFormat(MultipartFile file) {

        if (!TYPE.equals(file.getContentType())) {
            return false;
        }

        return true;
    }

    public static List<Engineer> csvToEngineer(InputStream is) {
        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
             CSVParser csvParser = new CSVParser(fileReader,
                     CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {
            List<Engineer> engineers = new ArrayList<>();

            Iterable<CSVRecord> csvRecords = csvParser.getRecords();
            for (CSVRecord csvRecord : csvRecords) {
                Engineer engineer = new Engineer( Integer.parseInt(csvRecord.get("Id")),Integer.parseInt(csvRecord.get("Bravo")),
                        csvRecord.get("Joined"), csvRecord.get("Name"),csvRecord.get("Git_Id"));
                engineers.add(engineer);
            }
            return engineers;
        } catch (IOException e) {
            System.out.println("There is some issue"+e.getMessage());
            throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
        }
    }
    public static List<PR> csvToPR(InputStream is) {
        CSVFormat csvFormat = CSVFormat.DEFAULT;
        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
             CSVParser csvParser = new CSVParser(fileReader,
                     CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {
            List<PR> prs = new ArrayList<>();
            Iterable<CSVRecord> csvRecords = csvParser.getRecords();
            System.out.println("parsing csvParser");
            for (CSVRecord csvRecord : csvRecords) {
                PR pr = new PR( Integer.parseInt(csvRecord.get("Id")),Integer.parseInt(csvRecord.get("Review_Duration_Sec")),
                        csvRecord.get("Review_Date"), csvRecord.get("Comments"),Integer.parseInt(csvRecord.get("Misses")),Integer.parseInt(csvRecord.get("Engineer_Id")));
                prs.add(pr);
            }
            return prs;
        } catch (Exception e) {
            System.out.println("There is some issue"+e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
        }
    }

}