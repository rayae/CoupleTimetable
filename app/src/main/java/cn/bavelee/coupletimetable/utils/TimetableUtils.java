package cn.bavelee.coupletimetable.utils;

import android.annotation.SuppressLint;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.bavelee.coupletimetable.TimeTable;
import cn.bavelee.coupletimetable.adapter.CoupleCourse;
import cn.bavelee.coupletimetable.bean.Course;
import cn.bavelee.coupletimetable.bean.ServerData;

public class TimetableUtils {
    private final static int DAY = (1000 * 3600 * 24);
    @SuppressLint("SimpleDateFormat")
    private final static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    /**
     * 根据学期开始时间和结束时间计算当前为第几周
     *
     * @param d1 学期开始时间
     * @param d2 学期结束时间
     * @return 当前为第几周
     */
    @SuppressLint("SimpleDateFormat")
    public static int computeWeekIndex(String d1, String d2) {
        try {
            Date today = new Date();
            Date date2 = sdf.parse(d2);
            Date date1 = sdf.parse(d1);
            if (date1 == null || date2 == null) {
                return 0;
            }
            int max = (int) ((date2.getTime() - date1.getTime()) / DAY) / 7 + 1;
            int days = (int) ((today.getTime() - date1.getTime()) / DAY) / 7 + 1;
            if (days >= max) return -1;
            if (days < 1) return 0;
            return days;
        } catch (Exception ignore) {
            return 0;
        }
    }

    /**
     * @return 返回解析后的课程列表
     */
    public static List<CoupleCourse> getCourseDataList(ServerData serverData) {
        List<CoupleCourse> courses = new ArrayList<>();
        TimeTable boyTimeTable = TimeTable.create(serverData.getBoyCourseData());
        TimeTable girlTimeTable = TimeTable.create(serverData.getGirlCourseData());
        List<Course> boyList = boyTimeTable.asList();
        List<Course> girlList = girlTimeTable.asList();
        for (int i = 0; i < boyList.size(); i++) {
            Course bc = boyList.get(i);
            Course gc = girlList.get(i);
            int type = 0;
            type += bc.isEmptyCourse() ? 0 : 2;
            type += gc.isEmptyCourse() ? 0 : 1;
            CoupleCourse course = new CoupleCourse(type, bc, gc);
            courses.add(course);
        }
        return courses;
    }
}
