package cn.edu.gdmec.android.boxuegu.activity;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.content.pm.ProviderInfo;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cn.edu.gdmec.android.boxuegu.R;
import cn.edu.gdmec.android.boxuegu.adapter.PlayHistoryAdapter;
import cn.edu.gdmec.android.boxuegu.bean.VideoBean;
import cn.edu.gdmec.android.boxuegu.utils.AnalysisUtils;
import cn.edu.gdmec.android.boxuegu.utils.DBUtils;


public class PlayHistoryActivity extends Activity{

    private DBUtils db;
    private List<VideoBean> vbl;
    private TextView tv_main_title;
    private TextView tv_save;
    private TextView tv_back;
    private TextView tv_none;
    private RelativeLayout rl_title_bar;
    private ListView lv_list;
    private PlayHistoryAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_history);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        db = DBUtils.getInstance(this);
        vbl = new ArrayList<VideoBean>();
        vbl = db.getVideoHistory(AnalysisUtils.readLoginUserName(this));
        init();
    }

    /**
     * 初始化UI控件
     */
    private void init() {
        tv_main_title = (TextView) findViewById(R.id.tv_main_title);
        tv_main_title.setText("播放记录");
        rl_title_bar = (RelativeLayout) findViewById(R.id.rl_title_bar);
        rl_title_bar.setBackgroundColor(Color.parseColor("#30B4FF"));
        tv_back = (TextView) findViewById(R.id.tv_back);
        lv_list = (ListView) findViewById(R.id.lv_list);
        tv_none = (TextView) findViewById(R.id.tv_none);
        if (vbl.size() == 0){
            tv_none.setVisibility(View.VISIBLE);
        }

        //后退键的点击事件
        tv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                PlayHistoryActivity.this.finish();
            }
        });
        adapter = new PlayHistoryAdapter(this);
        adapter.setData(vbl);
        lv_list.setAdapter(adapter);
    }
}