package com.udaan.sdlc.engineerinsights.engineerinsightservices.pr;



import com.fasterxml.jackson.annotation.JsonIgnore;
import com.udaan.sdlc.engineerinsights.engineerinsightservices.engineer.Engineer;

import javax.persistence.*;

@Entity
public class PR {


    @Id
    @GeneratedValue
    private Integer id;
    private Integer review_duration_min;
    private String review_date;
    private String repo_name;
    private String pr_link;
    private Integer num_comments;
    private Integer lines_changed;
    private Integer misses;
    private String requested_date;
    @ManyToOne(fetch = FetchType.LAZY )
    @JoinColumn(name = "git_id")
    @JsonIgnore
    private Engineer engineer;

public PR(){}
    public PR(Integer id, String repo_name){
    this.id=id;
    this.repo_name =repo_name;
    }
    public PR(Integer id, Integer review_duration_min, String review_date, String repo_name, Integer misses, Integer engineer_id) {

        this.id = id;
        this.review_duration_min = review_duration_min;
        this.review_date = review_date;
        this.repo_name = repo_name;
        this.misses = misses;
       Engineer engineer = new Engineer(engineer_id);
        this.setEngineer(engineer);
    }
    public PR(String git_id, Integer review_duration_min, String review_date, String pr_link, String repo_name, Integer num_comments, Integer lines_changed, String requested_date) {

        this.review_duration_min = review_duration_min;
        this.review_date = review_date;
        this.pr_link=pr_link;
        this.repo_name = repo_name;
        this.num_comments = num_comments;
        this.lines_changed=lines_changed;
        this.requested_date=requested_date;
        Engineer engineer = new Engineer(git_id);
        this.setEngineer(engineer);

    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public Integer getReview_duration_sec() {
        return review_duration_min;
    }

    public void setReview_duration_sec(Integer review_duration_sec) {
        this.review_duration_min = review_duration_sec;
    }

    public String getReview_date() {
        return review_date;
    }

    public void setReview_date(String review_date) {
        this.review_date = review_date;
    }


    public String getRepo_name() {
        return repo_name;
    }

    public void setRepo_name(String repo_name) {
        this.repo_name = repo_name;
    }

    public Integer getMisses() {
        return misses;
    }

    public void setMisses(Integer misses) {
        this.misses = misses;
    }
    public Integer getReview_duration_min() {
        return review_duration_min;
    }

    public void setReview_duration_min(Integer review_duration_min) {
        this.review_duration_min = review_duration_min;
    }

    public String getPr_link() {
        return pr_link;
    }

    public void setPr_link(String pr_link) {
        this.pr_link = pr_link;
    }

    public Integer getNum_comments() {
        return num_comments;
    }

    public void setNum_comments(Integer num_comments) {
        this.num_comments = num_comments;
    }

    public Integer getLines_changed() {
        return lines_changed;
    }

    public void setLines_changed(Integer lines_changed) {
        this.lines_changed = lines_changed;
    }

    public String getRequested_date() {
        return requested_date;
    }

    public void setRequested_date(String requested_date) {
        this.requested_date = requested_date;
    }


    public Engineer getEngineer() {
        return engineer;
    }

    public void setEngineer(Engineer engineer) {
        this.engineer = engineer;
    }



    @Override
    public String toString() {
        return "PRDetails{" +
                ", review_duration_sec=" + review_duration_min +
                ", review_date=" + review_date +
                ", comments='" + repo_name + '\'' +
                ", misses=" + misses +
                '}';
    }
}
