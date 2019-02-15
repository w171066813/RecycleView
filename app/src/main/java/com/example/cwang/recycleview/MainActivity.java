package com.example.cwang.recycleview;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    MyAdapter myAdapter ;
    ArrayList<String> arrayList  = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.rv);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        myAdapter = new MyAdapter(this);
        myAdapter.setListener(new MyAdapter.itemShortClickListener() {
            @Override
            public void shortClick(MyAdapter.ViewHolder holder) {
                Log.d("demo", "shortClick: "+holder.tv.getText());
            }
        });
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(myAdapter);
        initData();
        RecycleViewHelp recycleViewHelp = new RecycleViewHelp(myAdapter);
        recycleViewHelp.attacthRecycle(recyclerView);
    }

    private void initData(){
        for(int a = 0; a < 12; a ++){
            arrayList.add("菜单"+"==="+a);
        }
        myAdapter.setData(arrayList);
    }

}
