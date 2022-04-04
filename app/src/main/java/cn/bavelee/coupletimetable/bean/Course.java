package cn.bavelee.coupletimetable.bean;

import com.google.gson.annotations.SerializedName;

import org.litepal.annotation.Column;

import cn.bavelee.coupletimetable.Constants;

public class Course{
    /**
     * name : GJava企业级框架技术应用
     * location : 主教北楼5机房
     * teacher : 谢青念
     * dayOfWeek : 0  星期几
     * indexOfDay : 1 第几节课
     * weekStart : 1
     * weekEnd : 16
     * isDoubleWeek : false
     * isSingleWeek : false
     */
    public static final int NO_COURSE = -1;
    @Column(ignore = true)
    private int id;
    @SerializedName("name")
    private String name;
    @SerializedName("location")
    private String location;
    @SerializedName("teacher")
    private String teacher;
    @SerializedName("dayOfWeek")
    private Integer dayOfWeek;
    @SerializedName("indexOfDay")
    private Integer indexOfDay;
    @SerializedName("weekStart")
    private Integer weekStart;
    @SerializedName("weekEnd")
    private Integer weekEnd;
    @SerializedName("isDoubleWeek")
    private Boolean isDoubleWeek;
    @SerializedName("isSingleWeek")
    private Boolean isSingleWeek;

    public Course() {
    }

    public Course(int id) {
        this.id = id;
    }

    public static Course empty() {
        return new Course(NO_COURSE);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getTeacher() {
        if (isDoubleWeek) return teacher + "(双周)";
        if (isSingleWeek) return teacher + "(单周)";
        if (Constants.CURRENT_WEEK < weekStart){
            return teacher + "(从第" + weekStart + "周开始)";
        }
        return teacher;
    }

    public boolean isEmptyCourse() {
        return id == NO_COURSE;
    }

    public boolean isAvailable() {
        if (Constants.CURRENT_WEEK < weekStart) return false;
        if (isDoubleWeek)
            return Constants.CURRENT_WEEK % 2 == 0;
        if (isSingleWeek)
            return Constants.CURRENT_WEEK % 2 != 0;
        return true;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public Integer getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(Integer dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public Integer getIndexOfDay() {
        return indexOfDay;
    }

    public void setIndexOfDay(Integer indexOfDay) {
        this.indexOfDay = indexOfDay;
    }

    public Integer getWeekStart() {
        return weekStart;
    }

    public void setWeekStart(Integer weekStart) {
        this.weekStart = weekStart;
    }

    public Integer getWeekEnd() {
        return weekEnd;
    }

    public void setWeekEnd(Integer weekEnd) {
        this.weekEnd = weekEnd;
    }

    public Boolean getIsDoubleWeek() {
        return isDoubleWeek;
    }

    public void setIsDoubleWeek(Boolean isDoubleWeek) {
        this.isDoubleWeek = isDoubleWeek;
    }

    public Boolean getIsSingleWeek() {
        return isSingleWeek;
    }

    public void setIsSingleWeek(Boolean isSingleWeek) {
        this.isSingleWeek = isSingleWeek;
    }
}