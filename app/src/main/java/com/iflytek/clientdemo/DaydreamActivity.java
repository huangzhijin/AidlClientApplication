package com.iflytek.clientdemo;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.iflytek.clientdemo.com.util.DictionaryBean;
import com.iflytek.clientdemo.com.util.DictionaryViewUtil;

import java.util.ArrayList;
import java.util.List;

public class DaydreamActivity extends AppCompatActivity {
    private LinearLayout linearLayout;
    private RelativeLayout relativeLayout;
    private Button button,button2,button3;
    private Context context;
    private DictionaryViewUtil viewUtil;
    private DictionaryBean bean;
    private int height = 0, width = 0, margin = 0, padding = 0, screenW = 0,screenH=0;
    private List<DictionaryBean> data=new ArrayList<DictionaryBean>();
    String TAG="DaydreamActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daydream);
        Log.i(TAG,"-------onCreate----------"+BuildConfig.PROXY);
        context=DaydreamActivity.this;
        linearLayout= (LinearLayout) findViewById(R.id.container);
        relativeLayout=(RelativeLayout)findViewById(R.id.day_container);
       button=(Button)findViewById(R.id.button);
       button2=(Button)findViewById(R.id.button2);
       button3=(Button)findViewById(R.id.button3);
        button.setText("展开");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if("展开".equals(button.getText().toString())){
                    button.setText("折叠");
                    for(int i=0;i<3;i++){
                        View v=  LayoutInflater.from(context).inflate(R.layout.add_view,null);
                        linearLayout.addView(v);
                    }
                }else{
                    linearLayout.removeAllViews();
                    button.setText("展开");
                }
            }
        });
        initViewData();



        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addChildView();
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewUtil.getDictionaryInfo(data, bean);//选中的
                String   id = bean.getId();
                String value = bean.getValue();
                Log.i(TAG,id+"---id------addChildView------value---"+value);
            }
        });
    }




    /**
     * 初始化数据字典视图显示的度量数据
     */
    private void initViewData() {
        height = (int) getResources() .getDimension(R.dimen.dictionary_tv_height);
        margin = (int) getResources().getDimension(R.dimen.top_popup_marginTop);

        padding = 4 * margin;
        DisplayMetrics metrics = new DisplayMetrics();
        // 获取屏幕信息
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        screenW = metrics.widthPixels;
         Log.i(TAG,"----------------initViewData-----------"+screenW);
        screenH = metrics.heightPixels;

        width = (screenW - (3 * margin + 2 * padding)) / 4;

//        saveBtn.setPadding(0, 0, 2 * margin, 0);

        bean = new DictionaryBean();

        //添加数据
        for(int i=0;i<15;i++){
            DictionaryBean bean=new DictionaryBean();
            bean.setId(i+"");
            bean.setName("button"+i);
            bean.setValue("button"+i);
            data.add(bean);
        }

    }

    private void addChildView(){
        viewUtil = new DictionaryViewUtil(this, width, height, margin);

        viewUtil.showDictionaryView(relativeLayout, data, 1);
    }

}
