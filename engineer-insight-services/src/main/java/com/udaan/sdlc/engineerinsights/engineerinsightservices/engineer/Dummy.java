package com.udaan.sdlc.engineerinsights.engineerinsightservices.engineer;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

import javax.persistence.*;


@EnableAutoConfiguration
@Entity
@Table (name="dummy")
public class Dummy {
    @Id
    @GeneratedValue
    @Column(name="id")
    private Integer id;
    @Column(name="bravo")
    private int bravo;
    @Column(name="joined")
    private String joined;
    @Column(name="name")
    private String name;
    @Column(name="requested")
    private int requested;


    public Dummy(){}

    public Dummy(Integer id, int bravo, String joined, String name, int requested) {
        this.id = id;
        this.bravo = bravo;
        this.joined = joined;
        this.name = name;
        this.requested = requested;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getBravo() {
        return bravo;
    }

    public int getRequested() {
        return requested;
    }

    public String getJoined() {
        return joined;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBravo(int bravo) {
        this.bravo = bravo;
    }

    public void setRequested(int requested) {
        this.requested = requested;
    }

    public void setJoined(String joined) {
        this.joined = joined;
    }


    @Override
    public String toString() {
        return "Engineer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", bravo=" + bravo +
                ", requested=" + requested +
                ", joined=" + joined +
                '}';
    }
}
