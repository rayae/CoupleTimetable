package cn.bavelee.coupletimetable.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CourseList {

    /**
     * name : Timetable of boy
     * courses : [{"name":"Java企业级框架技术应用","location":"主教北楼5机房","teacher":"谢青念","weekIndex":0,"courseIndex":1,"weekStart":1,"weekEnd":16,"isDoubleWeek":false,"isSingleWeek":false},{"name":"操作系统","location":"主教S517","teacher":"李静","weekIndex":0,"courseIndex":2,"weekStart":1,"weekEnd":16,"isDoubleWeek":false,"isSingleWeek":false},{"name":"RFID原理与开发技术","location":"主教S803","teacher":"李静","weekIndex":0,"courseIndex":4,"weekStart":2,"weekEnd":16,"isDoubleWeek":true,"isSingleWeek":false},{"name":"计算机网络","location":"主教S515","teacher":"胡红武","weekIndex":1,"courseIndex":0,"weekStart":1,"weekEnd":16,"isDoubleWeek":false,"isSingleWeek":false},{"name":"计算机组成原理","location":"主教S515","teacher":"王前莉","weekIndex":1,"courseIndex":1,"weekStart":1,"weekEnd":16,"isDoubleWeek":false,"isSingleWeek":false},{"name":"Linux环境编程","location":"主教S517","teacher":"陈军","weekIndex":1,"courseIndex":2,"weekStart":1,"weekEnd":16,"isDoubleWeek":false,"isSingleWeek":false},{"name":"物联网导论","location":"主教S517","teacher":"陈明","weekIndex":1,"courseIndex":3,"weekStart":1,"weekEnd":16,"isDoubleWeek":false,"isSingleWeek":false},{"name":"Java企业级框架技术实验","location":"主教北楼5机房","teacher":"谢青念","weekIndex":2,"courseIndex":0,"weekStart":1,"weekEnd":16,"isDoubleWeek":false,"isSingleWeek":false},{"name":"RFID原理与开发技术","location":"主教S517","teacher":"李静","weekIndex":2,"courseIndex":1,"weekStart":1,"weekEnd":16,"isDoubleWeek":false,"isSingleWeek":false},{"name":"汇编语言与接口技术","location":"主教S518","teacher":"张国林","weekIndex":3,"courseIndex":0,"weekStart":1,"weekEnd":16,"isDoubleWeek":false,"isSingleWeek":false},{"name":"计算机组成原理","location":"主教S517","teacher":"王前莉","weekIndex":3,"courseIndex":1,"weekStart":1,"weekEnd":15,"isDoubleWeek":false,"isSingleWeek":true},{"name":"Linux环境编程","location":"主教北楼5机房","teacher":"陈军","weekIndex":4,"courseIndex":0,"weekStart":1,"weekEnd":16,"isDoubleWeek":false,"isSingleWeek":false},{"name":"汇编语言与接口技术实验","location":"主教S803","teacher":"张国林","weekIndex":4,"courseIndex":1,"weekStart":1,"weekEnd":16,"isDoubleWeek":false,"isSingleWeek":false},{"name":"计算机组成原理","location":"主教S803","teacher":"王前莉","weekIndex":4,"courseIndex":2,"weekStart":9,"weekEnd":16,"isDoubleWeek":false,"isSingleWeek":false},{"name":"web编程技术","location":"主教N409N410","teacher":"张连福","weekIndex":5,"courseIndex":0,"weekStart":9,"weekEnd":16,"isDoubleWeek":false,"isSingleWeek":false},{"name":"web编程技术实验","location":"主教N409N410","teacher":"张连福","weekIndex":5,"courseIndex":1,"weekStart":9,"weekEnd":16,"isDoubleWeek":false,"isSingleWeek":false},{"name":"web编程技术","location":"主教N409N410","teacher":"张连福","weekIndex":6,"courseIndex":0,"weekStart":9,"weekEnd":16,"isDoubleWeek":false,"isSingleWeek":false},{"name":"web编程技术实验","location":"主教N409N410","teacher":"张连福","weekIndex":6,"courseIndex":1,"weekStart":9,"weekEnd":16,"isDoubleWeek":false,"isSingleWeek":false}]
     */

    @SerializedName("name")
    private String name;
    @SerializedName("courses")
    private List<Course> courses;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }
}
