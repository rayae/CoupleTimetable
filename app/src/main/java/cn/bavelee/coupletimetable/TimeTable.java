package cn.bavelee.coupletimetable;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import cn.bavelee.coupletimetable.bean.Course;
import cn.bavelee.coupletimetable.bean.CourseList;


public class TimeTable {

    /**
     * name : Timetable of boy
     * courses : [{"name":"Java企业级框架技术应用","location":"主教北楼5机房","teacher":"谢青念","weekIndex":0,"courseIndex":1,"weekStart":1,"weekEnd":16,"isDoubleWeek":false,"isSingleWeek":false},{"name":"操作系统","location":"主教S517","teacher":"李静","weekIndex":0,"courseIndex":2,"weekStart":1,"weekEnd":16,"isDoubleWeek":false,"isSingleWeek":false},{"name":"RFID原理与开发技术","location":"主教S803","teacher":"李静","weekIndex":0,"courseIndex":4,"weekStart":2,"weekEnd":16,"isDoubleWeek":true,"isSingleWeek":false},{"name":"计算机网络","location":"主教S515","teacher":"胡红武","weekIndex":1,"courseIndex":0,"weekStart":1,"weekEnd":16,"isDoubleWeek":false,"isSingleWeek":false},{"name":"计算机组成原理","location":"主教S515","teacher":"王前莉","weekIndex":1,"courseIndex":1,"weekStart":1,"weekEnd":16,"isDoubleWeek":false,"isSingleWeek":false},{"name":"Linux环境编程","location":"主教S517","teacher":"陈军","weekIndex":1,"courseIndex":2,"weekStart":1,"weekEnd":16,"isDoubleWeek":false,"isSingleWeek":false},{"name":"物联网导论","location":"主教S517","teacher":"陈明","weekIndex":1,"courseIndex":3,"weekStart":1,"weekEnd":16,"isDoubleWeek":false,"isSingleWeek":false},{"name":"Java企业级框架技术实验","location":"主教北楼5机房","teacher":"谢青念","weekIndex":2,"courseIndex":0,"weekStart":1,"weekEnd":16,"isDoubleWeek":false,"isSingleWeek":false},{"name":"RFID原理与开发技术","location":"主教S517","teacher":"李静","weekIndex":2,"courseIndex":1,"weekStart":1,"weekEnd":16,"isDoubleWeek":false,"isSingleWeek":false},{"name":"汇编语言与接口技术","location":"主教S518","teacher":"张国林","weekIndex":3,"courseIndex":0,"weekStart":1,"weekEnd":16,"isDoubleWeek":false,"isSingleWeek":false},{"name":"计算机组成原理","location":"主教S517","teacher":"王前莉","weekIndex":3,"courseIndex":1,"weekStart":1,"weekEnd":15,"isDoubleWeek":false,"isSingleWeek":true},{"name":"Linux环境编程","location":"主教北楼5机房","teacher":"陈军","weekIndex":4,"courseIndex":0,"weekStart":1,"weekEnd":16,"isDoubleWeek":false,"isSingleWeek":false},{"name":"汇编语言与接口技术实验","location":"主教S803","teacher":"张国林","weekIndex":4,"courseIndex":1,"weekStart":1,"weekEnd":16,"isDoubleWeek":false,"isSingleWeek":false},{"name":"计算机组成原理","location":"主教S803","teacher":"王前莉","weekIndex":4,"courseIndex":2,"weekStart":9,"weekEnd":16,"isDoubleWeek":false,"isSingleWeek":false},{"name":"web编程技术","location":"主教N409N410","teacher":"张连福","weekIndex":5,"courseIndex":0,"weekStart":9,"weekEnd":16,"isDoubleWeek":false,"isSingleWeek":false},{"name":"web编程技术实验","location":"主教N409N410","teacher":"张连福","weekIndex":5,"courseIndex":1,"weekStart":9,"weekEnd":16,"isDoubleWeek":false,"isSingleWeek":false},{"name":"web编程技术","location":"主教N409N410","teacher":"张连福","weekIndex":6,"courseIndex":0,"weekStart":9,"weekEnd":16,"isDoubleWeek":false,"isSingleWeek":false},{"name":"web编程技术实验","location":"主教N409N410","teacher":"张连福","weekIndex":6,"courseIndex":1,"weekStart":9,"weekEnd":16,"isDoubleWeek":false,"isSingleWeek":false}]
     */

    private String name;
    private List<Course> courses;

    public TimeTable(List<Course> courses) {
        this.courses = courses;
    }

    private int calculateIndex(Course course) {
        return course.getIndexOfDay() * 7 + course.getDayOfWeek();
    }

    public List<Course> asList() {
        List<Course> list = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 5; j++) {
                list.add(Course.empty());
            }
        }
        for (Course course : courses) {
            list.set(calculateIndex(course), course);
        }
        return list;
    }

    public static TimeTable create(String data) {
        CourseList courseList = new Gson().fromJson(data, CourseList.class);
        return new TimeTable(courseList.getCourses());
    }
}
