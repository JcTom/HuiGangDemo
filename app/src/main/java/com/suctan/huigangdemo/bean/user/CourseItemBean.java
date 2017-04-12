package com.suctan.huigangdemo.bean.user;

/**
 * Created by Tom on 2017/4/11.
 */

public class CourseItemBean {

    private String id;
    private String title;
    private String label;
    private String introduction;
    private double totalAmount;
    private double monthPay;
    private int payPeriods;
    private String academicYear;
    private String education;
    private String loanConditionImg;
    private String eduLevels;
    private String courseLevelsType;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public double getMonthPay() {
        return monthPay;
    }

    public void setMonthPay(double monthPay) {
        this.monthPay = monthPay;
    }

    public int getPayPeriods() {
        return payPeriods;
    }

    public void setPayPeriods(int payPeriods) {
        this.payPeriods = payPeriods;
    }

    public String getAcademicYear() {
        return academicYear;
    }

    public void setAcademicYear(String academicYear) {
        this.academicYear = academicYear;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getLoanConditionImg() {
        return loanConditionImg;
    }

    public void setLoanConditionImg(String loanConditionImg) {
        this.loanConditionImg = loanConditionImg;
    }

    public String getEduLevels() {
        return eduLevels;
    }

    public void setEduLevels(String eduLevels) {
        this.eduLevels = eduLevels;
    }

    public String getCourseLevelsType() {
        return courseLevelsType;
    }

    public void setCourseLevelsType(String courseLevelsType) {
        this.courseLevelsType = courseLevelsType;
    }
}
