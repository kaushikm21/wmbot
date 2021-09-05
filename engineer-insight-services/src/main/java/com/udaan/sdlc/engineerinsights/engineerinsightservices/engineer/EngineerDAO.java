package com.udaan.sdlc.engineerinsights.engineerinsightservices.engineer;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Component
public class EngineerDAO {
    private static List<Engineer> engineers = new ArrayList<>();
    private static int engineerCount = 3;



    static {
        engineers.add(new Engineer(1, 10, new String(), "Kaushik","blah"));
        engineers.add(new Engineer(2, 20, new String(), "Priyesh","blah1"));
        engineers.add(new Engineer(3, 30, new String(),"XYZ","blah2"));
    }
    public List<Engineer> findAll(){
        return engineers;
    }
    public Engineer save(Engineer engineer){
        if(engineer.getId()==null){
            engineer.setId(++engineerCount);
        }
        engineers.add(engineer);
        return engineer;
    }

    public Engineer findEngineer(int id){
        for (Engineer engineer:engineers){
            if(engineer.getId()==id){
                return engineer;
            }
        }
        return null;
    }

    public Engineer deleteEngineer(int id){
        Iterator<Engineer> iterator = engineers.iterator();
        while(iterator.hasNext()){
            Engineer engineer = iterator.next();
            if(engineer.getId()==id){
                iterator.remove();
                return engineer;
            }
        }
        return null;
    }
}
