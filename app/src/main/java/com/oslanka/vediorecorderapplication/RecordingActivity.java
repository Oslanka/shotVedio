package com.oslanka.vediorecorderapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.SurfaceView;
import android.widget.Toast;

import com.oslanka.videorecordermodel.manage.VideoManage;
import com.oslanka.videorecordermodel.view.CircleButtonView;

public class RecordingActivity extends AppCompatActivity implements CircleButtonView.OnRecordingFinishListener {

    private SurfaceView mSurfaceView;

    private CircleButtonView mRecordButton;

    private VideoManage mVideoManage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recording);
        init();
    }

    private void init() {
        mSurfaceView = (SurfaceView) findViewById(R.id.mySurfaceView);
        mRecordButton = (CircleButtonView) findViewById(R.id.RecorderButton);
        mRecordButton.setRecordingFinishListener(this);
        mVideoManage = VideoManage.getInstance();
        //        mVideoManage.setFileDir(); //这个方法设置录制文件的存放路径
        mVideoManage.setSurfaceHolder(mSurfaceView.getHolder());
    }

    @Override
    public void onLongClick() {

    }

    @Override
    public void onNoMinRecord(int currentTime) {

        Toast.makeText(this, "录制" +
                "时间太短", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRecordingFinish( String fileName) {
        Intent mIntent = new Intent();
        mIntent.putExtra("fileName", fileName);
        setResult(RESULT_OK, mIntent);
        finish();
    }

    /**
     * 获取屏幕分辨率
     *
     * @return
     */
    private DisplayMetrics getWindowDisplay() {
        DisplayMetrics mDisplayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(mDisplayMetrics);
        return mDisplayMetrics;
    }
}
