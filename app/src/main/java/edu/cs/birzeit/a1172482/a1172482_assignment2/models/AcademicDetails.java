package edu.cs.birzeit.a1172482.a1172482_assignment2.models;

public class AcademicDetails {
    private String course;
    private String school;
    private String percentage;
    private String mark;
    private String graduated;
    private String yearOfPassing;

    public AcademicDetails() { }

    public AcademicDetails(String course, String school, String percentage, String mark, String graduated, String yearOfPassing) {
        this.course = course;
        this.school = school;
        this.percentage = percentage;
        this.mark = mark;
        this.graduated = graduated;
        this.yearOfPassing = yearOfPassing;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getPercentage() {
        return percentage;
    }

    public void setPercentage(String percentage) {
        this.percentage = percentage;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getGraduated() {
        return graduated;
    }

    public void setGraduated(String graduated) {
        this.graduated = graduated;
    }

    public String getYearOfPassing() {
        return yearOfPassing;
    }

    public void setYearOfPassing(String yearOfPassing) {
        this.yearOfPassing = yearOfPassing;
    }
}
