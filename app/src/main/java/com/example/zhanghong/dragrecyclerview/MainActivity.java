package com.example.zhanghong.dragrecyclerview;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {

    private RecyclerAdapter mRecyclerAdapter;

    private RecyclerView mRecyclerView;

    private List<String> allFruit = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = (RecyclerView) findViewById(R.id.all_fruit_recycler_view);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setAutoMeasureEnabled(true);
        mRecyclerView.setLayoutManager(layoutManager);

        mRecyclerAdapter = new RecyclerAdapter(this);
        //初始化String列表，作为备选，默认已选为NULL
        initData();
        mRecyclerAdapter.setDatas(null, allFruit);
        mRecyclerView.setAdapter(mRecyclerAdapter);

    }

    private void initData() {
        allFruit.add("苹果");

        allFruit.add("香蕉");

        allFruit.add("雪梨");

        allFruit.add("桃子");

        allFruit.add("火龙果");
    }
}
