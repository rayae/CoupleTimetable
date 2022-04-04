package cn.bavelee.coupletimetable.adapter;

import cn.bavelee.coupletimetable.bean.Course;

public class CoupleCourse {
    private int type;
    private Course boyCourseData;
    private Course girlCourseData;

    public CoupleCourse(int type, Course boyCourseData, Course girlCourseData) {
        this.type = type;
        this.boyCourseData = boyCourseData;
        this.girlCourseData = girlCourseData;
    }

    public Course getBoyCourseData() {
        return boyCourseData;
    }

    public Course getGirlCourseData() {
        return girlCourseData;
    }

    public boolean onlyGirl() {
        return type == 1;
    }

    public boolean onlyBoy() {
        return type == 2;
    }

    public boolean boyAndGirl() {
        return type == 3;
    }

    public boolean empty() {
        return type == 0;
    }
}