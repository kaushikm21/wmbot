package com.udaan.sdlc.engineerinsights.engineerinsightservices.pr;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PRRepository extends JpaRepository<PR, Integer> {


   @Query("Select NEW  com.udaan.sdlc.engineerinsights.engineerinsightservices.pr.PR(\n" +
           "            p.id, p.comments)  FROM PR p where misses= :missedval AND engineer_id= :enggid")
    List<PR> findMissesByEngineer_ID(Integer missedval, Integer enggid);

}
