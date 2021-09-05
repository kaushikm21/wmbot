package com.udaan.sdlc.engineerinsights.engineerinsightservices.engineer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DummyRepository extends JpaRepository<Dummy, Integer> {
}

