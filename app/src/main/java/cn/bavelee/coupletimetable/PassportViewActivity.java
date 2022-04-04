package cn.bavelee.coupletimetable;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class PassportViewActivity extends AppCompatActivity {

    private ImageView image;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getSupportActionBar() != null)
            getSupportActionBar().hide();
        setContentView(R.layout.activity_img);
        initView();
        final SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
        boolean isBoy = sp.getBoolean("isboy", false);
        image.setImageResource(isBoy ? R.drawable.epassport1 : R.drawable.epassport2);
        image.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                sp.edit().putBoolean("isboy", !sp.getBoolean("isboy", false)).apply();
                boolean isBoy = sp.getBoolean("isboy", false);
                image.setImageResource(isBoy ? R.drawable.epassport1 : R.drawable.epassport2);
                return true;
            }
        });
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initView() {
        image = (ImageView) findViewById(R.id.image);
    }
}
