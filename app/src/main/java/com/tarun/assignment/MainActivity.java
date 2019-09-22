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

    MainActivityViewmodel viewmodel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        ((App) getApplication()).getAppComponent().doInjection(this);

        viewmodel = ViewModelProviders.of(this, viewModelFactory).get(MainActivityViewmodel.class);
        viewmodel.loginResponse().observe(this, this::consumeResponse);


        new CountDownTimer(5000, 1000) {

            public void onTick(long millisUntilFinished) {
                if (millisUntilFinished > 3500) {
                    Toast.makeText(MainActivity.this, millisUntilFinished + "", Toast.LENGTH_SHORT).show();
                } else if (millisUntilFinished > 1500) {
                    Toast.makeText(MainActivity.this, millisUntilFinished + "", Toast.LENGTH_SHORT).show();
                }
            }

            public void onFinish() {
                viewmodel.hitApi();
            }

        }.start();
    }

    private void consumeResponse(ApiResponse response) {
        switch (response.status) {
            case LOADING:
                tvStart1.setText(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
                break;

            case SUCCESS:
                tvEnd1.setText(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
                break;

            case ERROR:
                tvEnd1.setText(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
                break;

        }
    }
}
