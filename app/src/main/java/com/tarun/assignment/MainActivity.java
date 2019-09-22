package com.tarun.assignment;

import android.arch.lifecycle.ViewModelProviders;
import android.os.CountDownTimer;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.tarun.assignment.model.ApiResponse;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @Inject
    ViewModelFactory viewModelFactory;

    @BindView(R.id.tv_start1)
    TextView tvStart1;

    @BindView(R.id.tv_end1)
    TextView tvEnd1;

    @BindView(R.id.tv_start2)
    TextView tvStart2;

    @BindView(R.id.tv_end2)
    TextView tvEnd2;

    @BindView(R.id.tv_start3)
    TextView tvStart3;

    @BindView(R.id.tv_end3)
    TextView tvEnd3;

    @BindView(R.id.tv_start4)
    TextView tvStart4;

    @BindView(R.id.tv_end4)
    TextView tvEnd4;

    MainActivityViewmodel viewmodel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        ((App) getApplication()).getAppComponent().doInjection(this);

        viewmodel = ViewModelProviders.of(this, viewModelFactory).get(MainActivityViewmodel.class);
        viewmodel.loginResponse1().observe(this, this::consumeResponse1);
        viewmodel.loginResponse2().observe(this, this::consumeResponse2);
        viewmodel.loginResponse3().observe(this, this::consumeResponse3);
        viewmodel.loginResponse4().observe(this, this::consumeResponse4);


        new CountDownTimer(5000, 1000) {

            public void onTick(long millisUntilFinished) {
                if (millisUntilFinished > 3500) {
                    Toast.makeText(MainActivity.this, millisUntilFinished + "", Toast.LENGTH_SHORT).show();
                } else if (millisUntilFinished > 1500) {
                    Toast.makeText(MainActivity.this, millisUntilFinished + "", Toast.LENGTH_SHORT).show();
                }
            }

            public void onFinish() {
                viewmodel.hitAllApi();
            }

        }.start();
    }

    private void consumeResponse1(ApiResponse response) {
        switch (response.status) {
            case LOADING:
                tvStart1.setText(Calendar.getInstance().getTimeInMillis() + "");
                break;

            case SUCCESS:
                tvEnd1.setText(Calendar.getInstance().getTimeInMillis() + "");
                break;

            case ERROR:
                tvEnd1.setText("Error: " + Calendar.getInstance().getTimeInMillis() + "");
                break;

        }
    }

    private void consumeResponse4(ApiResponse response) {
        switch (response.status) {
            case LOADING:
                tvStart4.setText(Calendar.getInstance().getTimeInMillis() + "");
                break;

            case SUCCESS:
                tvEnd4.setText(Calendar.getInstance().getTimeInMillis() + "");
                break;

            case ERROR:
                tvEnd4.setText("Error: " + Calendar.getInstance().getTimeInMillis() + "");
                break;

        }
    }

    private void consumeResponse2(ApiResponse response) {
        switch (response.status) {
            case LOADING:
                tvStart2.setText(Calendar.getInstance().getTimeInMillis() + "");
                break;

            case SUCCESS:
                tvEnd2.setText(Calendar.getInstance().getTimeInMillis() + "");
                break;

            case ERROR:
                tvEnd2.setText("Error: " + Calendar.getInstance().getTimeInMillis() + "");
                break;

        }
    }

    private void consumeResponse3(ApiResponse response) {
        switch (response.status) {
            case LOADING:
                tvStart3.setText(Calendar.getInstance().getTimeInMillis() + "");
                break;

            case SUCCESS:
                tvEnd3.setText(Calendar.getInstance().getTimeInMillis() + "");
                break;

            case ERROR:
                tvEnd3.setText("Error: " + Calendar.getInstance().getTimeInMillis() + "");
                break;

        }
    }
}
