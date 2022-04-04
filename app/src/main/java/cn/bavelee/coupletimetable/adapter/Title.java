package cn.bavelee.coupletimetable.adapter;

public class Title {
    private String title;
    private boolean isToday;

    public Title(String title, boolean isToday) {
        this.title = title;
        this.isToday = isToday;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isToday() {
        return isToday;
    }

    public void setToday(boolean today) {
        isToday = today;
    }
}
