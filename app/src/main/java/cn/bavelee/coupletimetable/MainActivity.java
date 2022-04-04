package cn.bavelee.coupletimetable;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.GsonBuilder;

import org.json.JSONObject;
import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import cn.bavelee.coupletimetable.adapter.CoupleCourse;
import cn.bavelee.coupletimetable.adapter.CoupleCourseViewBinder;
import cn.bavelee.coupletimetable.adapter.Title;
import cn.bavelee.coupletimetable.adapter.TitleViewBinder;
import cn.bavelee.coupletimetable.bean.ServerData;
import cn.bavelee.coupletimetable.utils.IOUtils;
import cn.bavelee.coupletimetable.utils.TimetableUtils;
import me.drakeet.multitype.MultiTypeAdapter;

public class MainActivity extends AppCompatActivity {

    private ServerData mServerData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // 从assets加载
        try {
            updateServerData(new JSONObject(IOUtils.readStringFromStream(getAssets().open("update.json"))));
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 从数据库中加载
//        mServerData = LitePal.findFirst(ServerData.class);
        if (mServerData != null) {
            showTimetable(mServerData);
        }
        // 从服务器更新
        requestLatestData();
    }

    /**
     * 从服务器获取最新的课程表数据
     */
    private void requestLatestData() {
        // 格式参考 assets/update.json
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(new JsonObjectRequest(Constants.SERVER_DATA_URL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                updateServerData(response);
            }
        }, null));
    }

    private void updateServerData(JSONObject response) {
        ServerData serverData = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create().fromJson(response.toString(), ServerData.class);
        if (mServerData != null && mServerData.getUpdateCode() >= serverData.getUpdateCode()) {
            return;
        }
        serverData.setBoyCourseData(response.optJSONObject("boyCourses").toString());
        serverData.setGirlCourseData(response.optJSONObject("girlCourses").toString());
        LitePal.deleteAll(ServerData.class);
        serverData.save();
        mServerData = serverData;
        showTimetable(mServerData);
        Toast.makeText(MainActivity.this, "更新并显示新数据", Toast.LENGTH_SHORT).show();
    }

    private void showTimetable(ServerData serverData) {
        List<Object> items = new ArrayList<>();
        int weekIndex = TimetableUtils.computeWeekIndex(serverData.getSemesterStartTime(), serverData.getSemesterEndTime());
        setTitle("学期还未开始");
        if (weekIndex == -1)
            setTitle("学期已结束");
        else if (weekIndex != 0) {
            Constants.CURRENT_WEEK = weekIndex;
            setTitle(String.format(Locale.CHINESE, "第%d周", Constants.CURRENT_WEEK));
        }
        Date today = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(today);
        int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
        if (dayOfWeek == Calendar.SUNDAY) {
            dayOfWeek = 6;
        } else {
            dayOfWeek -= 2;
        }
        String[] weeks = getResources().getStringArray(R.array.weeks);
        for (int i = 0; i < weeks.length; i++) {
            items.add(new Title(weeks[i], i == dayOfWeek));
        }
        items.addAll(TimetableUtils.getCourseDataList(serverData));
        setupView(dayOfWeek, items);
    }

    @SuppressLint({"ClickableViewAccessibility"})
    private void setupView(int dayOfWeek, List<Object> items) {
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        MultiTypeAdapter adapter = new MultiTypeAdapter();
        recyclerView.setLayoutManager(new GridLayoutManager(this, 7));
        adapter.register(CoupleCourse.class, new CoupleCourseViewBinder());
        adapter.register(Title.class, new TitleViewBinder());
        adapter.setItems(items);
        recyclerView.setAdapter(adapter);
        final GestureDetector detector = new GestureDetector(this, new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onDoubleTap(MotionEvent e) {
                // 双击跳转到图片校园卡视图
                startActivity(new Intent(MainActivity.this, PassportViewActivity.class));
                return true;
            }
        });
        recyclerView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return detector.onTouchEvent(event);
            }
        });
        // 到了时间显示向右滑动，使周六周日的课表显示出来
        if (dayOfWeek >= Calendar.TUESDAY) {
            final HorizontalScrollView scrollView = (HorizontalScrollView) recyclerView.getParent();
            scrollView.postDelayed(new Runnable() {
                @Override
                public void run() {
                    scrollView.fullScroll(HorizontalScrollView.FOCUS_RIGHT);
                }
            }, 100);
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.love)
            startActivity(new Intent(MainActivity.this, LoveWebsiteActivity.class));
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

}
