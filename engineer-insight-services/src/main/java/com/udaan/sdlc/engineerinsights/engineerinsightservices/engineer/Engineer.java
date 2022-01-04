package com.udaan.sdlc.engineerinsights.engineerinsightservices.engineer;

import com.udaan.sdlc.engineerinsights.engineerinsightservices.pr.PR;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.util.List;
@EnableAutoConfiguration
@Entity

public class Engineer {
    @Id
    private String git_id;
    private int bravo;
    private String joined;
    private String name;
    private Integer id;
    private Double average_review_time;
    private int total_pr_reviews;

    @OneToMany(mappedBy = "engineer")

    private List <PR> prs;

    public Engineer(){}
    public Engineer(Integer id){
        this.id=id;
    }
    public Engineer(String git_id){
        this.git_id=git_id;
    }
    public Engineer(Integer id, int bravo, String joined, String name, String git_id) {
        this.id = id;
        this.bravo = bravo;
        this.joined = joined;
        this.name = name;
        this.git_id=git_id;
    }
    public Double getAverage_review_time() {
        return average_review_time;
    }

    public void setAverage_review_time(Double average_review_time) {
        this.average_review_time = average_review_time;
    }

    public int getTotal_pr_reviews() {
        return total_pr_reviews;
    }

    public void setTotal_pr_reviews(int total_pr_reviews) {
        this.total_pr_reviews = total_pr_reviews;
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

    public String getJoined() {
        return joined;
    }
    public String getGit_id() {return git_id;}

    public void setGit_id(String git_id) {this.git_id = git_id;}

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBravo(int bravo) {
        this.bravo = bravo;
    }

    public void setJoined(String joined) {
        this.joined = joined;
    }

    public List<PR> getPrs() {return prs;}

    public void setPrs(List<PR> prs) {this.prs = prs;}

    @Override
    public String toString() {
        return "Engineer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", bravo=" + bravo +
                ", git_id=" + git_id +
                ", joined=" + joined +
                '}';
    }
}
