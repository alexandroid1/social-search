package nl.codeimpact.facebook.DTO;

import lombok.Data;

import java.util.ArrayList;


@Data
public class Person {
    private String name;
    private String profilePictureUrl;
    private ArrayList<Work> work = new ArrayList<Work>();
    private ArrayList<Study> study = new ArrayList<Study>();

    public void addWork(Work work) {
        this.work.add(work);
    }

    public void addStudy(Study study) {
        this.study.add(study);
    }
}
