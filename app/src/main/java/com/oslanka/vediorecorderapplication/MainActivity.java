package com.oslanka.vediorecorderapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.oslanka.videorecordermodel.manage.MediaPlayerManage;
import com.oslanka.videorecordermodel.manage.ScreenRecorder;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final int PLAY_CEDE = 1;


    private Button startRecordView, startRecordScreen;

    private String fileName;

    //存放文件路径
    private List<String> fileData;

    private ListView mListView;

    private MyAdapter myAdapter;

    //屏幕录制工具（android5.0推出的录制屏幕的工具）
//    private MediaProjectionManager mMediaProjectionManager;

    private ScreenRecorder mScreenRecorder;

    private String mCurrentScreenFileName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initRecordView();
        initListView();
    }


    private void initListView() {
        mListView = (ListView) findViewById(R.id.myListView);
        fileData = new ArrayList<>();
        myAdapter = new MyAdapter(fileData, this);
        mListView.setAdapter(myAdapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                MediaPlayerManage mediaPlayerManage = new MediaPlayerManage();
                Bundle bundle = new Bundle();
                bundle.putString("filePath", fileData.get(position));
                mediaPlayerManage.setArguments(bundle);
                mediaPlayerManage.show(getFragmentManager(), "mediaPlayerDialog");
            }
        });
    }

    /**
     * 初始化录制视频按钮
     */
    private void initRecordView() {
        //设置显示视频显示在SurfaceView上
        startRecordView = (Button) findViewById(R.id.press_to_recording);
        startRecordView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RecordingActivity.class);
                startActivityForResult(intent, PLAY_CEDE);
            }
        });
    }


    /**
     * 录制完成的回掉
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && requestCode == PLAY_CEDE) {                  //录制视频完成的回掉
            fileName = data.getStringExtra("fileName");
            fileData.add(fileName);
            myAdapter.notifyDataSetChanged();
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mScreenRecorder != null) {
            mScreenRecorder.quit();
            mScreenRecorder = null;
        }
    }
}
