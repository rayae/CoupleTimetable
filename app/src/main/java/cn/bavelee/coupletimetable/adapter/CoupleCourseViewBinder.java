package cn.bavelee.coupletimetable.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Locale;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import cn.bavelee.coupletimetable.MainActivity;
import cn.bavelee.coupletimetable.R;
import cn.bavelee.coupletimetable.bean.Course;

public class CoupleCourseViewBinder extends me.drakeet.multitype.ItemViewBinder<CoupleCourse, CoupleCourseViewBinder.Holder> {

    @NonNull
    @Override
    protected Holder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        return new Holder(inflater.inflate(R.layout.item_couple, parent, false));
    }

    @Override
    protected void onBindViewHolder(@NonNull Holder h, @NonNull CoupleCourse course) {
        Course gc = course.getGirlCourseData();
        Course bc = course.getBoyCourseData();
        h.boyLayout.setBackgroundResource(R.drawable.bg_none);
        h.girlLayout.setBackgroundResource(R.drawable.bg_none);
        if (course.boyAndGirl()) {
            //都有课
            h.boyLayout.setBackgroundResource(bc.isAvailable() ? R.drawable.bg_boy_down : R.drawable.bg_boy_down_disabled);
            h.girlLayout.setBackgroundResource(gc.isAvailable() ? R.drawable.bg_girl_up : R.drawable.bg_girl_up_disabled);
            h.boyLayout.setVisibility(View.VISIBLE);
            h.girlLayout.setVisibility(View.VISIBLE);
            h.tvGirlTitle.setText(gc.getName());
            h.tvGirlSummary.setText(String.format(Locale.CHINA, "@%s\n%s", gc.getLocation(), gc.getTeacher()));
            h.tvBoyTitle.setText(bc.getName());
            h.tvBoySummary.setText(String.format(Locale.CHINA, "@%s\n%s", bc.getLocation(), bc.getTeacher()));
        } else if (course.onlyBoy()) {
            //boy课
            h.boyLayout.setVisibility(View.VISIBLE);
            h.girlLayout.setVisibility(View.GONE);
            h.boyLayout.setBackgroundResource(bc.isAvailable() ? R.drawable.bg_boy_only : R.drawable.bg_boy_only_disabled);
            h.tvGirlTitle.setText("");
            h.tvGirlSummary.setText("");
            h.tvBoyTitle.setText(bc.getName());
            h.tvBoySummary.setText(String.format(Locale.CHINA, "@%s\n%s", bc.getLocation(), bc.getTeacher()));
        } else if (course.onlyGirl()) {
            //girl课
            h.boyLayout.setVisibility(View.GONE);
            h.girlLayout.setVisibility(View.VISIBLE);
            h.girlLayout.setBackgroundResource(gc.isAvailable() ? R.drawable.bg_girl_only : R.drawable.bg_girl_only_disabled);
            h.tvBoyTitle.setText("");
            h.tvBoySummary.setText("");
            h.tvGirlTitle.setText(gc.getName());
            h.tvGirlSummary.setText(String.format(Locale.CHINA, "@%s\n%s", gc.getLocation(), gc.getTeacher()));
        } else {
            //都没课
            h.boyLayout.setVisibility(View.VISIBLE);
            h.girlLayout.setVisibility(View.VISIBLE);
            h.tvGirlTitle.setText("");
            h.tvGirlSummary.setText("");
            h.tvBoyTitle.setText("");
            h.tvBoySummary.setText("");
            h.layout.setBackgroundResource(R.drawable.bg_none);
        }
    }

    public static class Holder extends RecyclerView.ViewHolder {
        TextView tvGirlTitle;
        TextView tvGirlSummary;
        TextView tvBoyTitle;
        TextView tvBoySummary;
        LinearLayout layout;
        LinearLayout girlLayout;
        LinearLayout boyLayout;

        public Holder(@NonNull View itemView) {
            super(itemView);
            layout = (LinearLayout) ((ViewGroup) itemView).getChildAt(0);
            girlLayout = itemView.findViewById(R.id.gl);
            boyLayout = itemView.findViewById(R.id.bl);
            tvGirlTitle = itemView.findViewById(R.id.tv1);
            tvGirlSummary = itemView.findViewById(R.id.tv2);
            tvBoyTitle = itemView.findViewById(R.id.tv3);
            tvBoySummary = itemView.findViewById(R.id.tv4);
        }
    }

    public static enum Mode {
        DISPLAY,
        EDITOR
    }
}
