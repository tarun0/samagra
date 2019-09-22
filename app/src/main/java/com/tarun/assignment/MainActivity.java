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

    @BindView(R.id.tv_start_comments)
    TextView tvStartComments;

    @BindView(R.id.tv_end_comments)
    TextView tvEndComments;

    @BindView(R.id.tv_start_photos)
    TextView tvStartPhotos;

    @BindView(R.id.tv_end_photos)
    TextView tvEndPhotos;

    @BindView(R.id.tv_start_todo)
    TextView tvStartTodos;

    @BindView(R.id.tv_end_todo)
    TextView tvEndTodos;

    @BindView(R.id.tv_start_posts)
    TextView tvStartPosts;

    @BindView(R.id.tv_end_posts)
    TextView tvEndPosts;

    MainActivityViewmodel viewmodel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        ((App) getApplication()).getAppComponent().doInjection(this);

        viewmodel = ViewModelProviders.of(this, viewModelFactory).get(MainActivityViewmodel.class);
        viewmodel.loginResponseComments().observe(this, this::consumeResponseComments);
        viewmodel.loginResponsePhotos().observe(this, this::consumeResponsePhotos);
        viewmodel.loginResponseTodo().observe(this, this::consumeResponseTodos);
        viewmodel.loginResponsePosts().observe(this, this::consumeResponsePosts);


        new CountDownTimer(5000, 1000) {

            public void onTick(long millisUntilFinished) {
                if (millisUntilFinished > 1500) {
                    Toast.makeText(MainActivity.this, millisUntilFinished + "", Toast.LENGTH_SHORT).show();
                }
            }

            public void onFinish() {
                viewmodel.hitAllApi();
            }

        }.start();
    }

    private void consumeResponseComments(ApiResponse response) {
        switch (response.status) {
            case LOADING:
                tvStartComments.setText("Start: " + Calendar.getInstance().getTimeInMillis() + "");
                break;

            case SUCCESS:
                tvEndComments.setText("End: " +Calendar.getInstance().getTimeInMillis() + "");
                break;

            case ERROR:
                tvEndComments.setText("Error: " + Calendar.getInstance().getTimeInMillis() + "");
                break;

        }
    }

    private void consumeResponsePosts(ApiResponse response) {
        switch (response.status) {
            case LOADING:
                tvStartPosts.setText("Start: " +Calendar.getInstance().getTimeInMillis() + "");
                break;

            case SUCCESS:
                tvEndPosts.setText("End: " +Calendar.getInstance().getTimeInMillis() + "");
                break;

            case ERROR:
                tvEndPosts.setText("Error: " + Calendar.getInstance().getTimeInMillis() + "");
                break;

        }
    }

    private void consumeResponsePhotos(ApiResponse response) {
        switch (response.status) {
            case LOADING:
                tvStartPhotos.setText("Start: " +Calendar.getInstance().getTimeInMillis() + "");
                break;

            case SUCCESS:
                tvEndPhotos.setText("End: " +Calendar.getInstance().getTimeInMillis() + "");
                break;

            case ERROR:
                tvEndPhotos.setText("Error: " + Calendar.getInstance().getTimeInMillis() + "");
                break;

        }
    }

    private void consumeResponseTodos(ApiResponse response) {
        switch (response.status) {
            case LOADING:
                tvStartTodos.setText("Start: " +Calendar.getInstance().getTimeInMillis() + "");
                break;

            case SUCCESS:
                tvEndTodos.setText("End: " + Calendar.getInstance().getTimeInMillis() + "");
                break;

            case ERROR:
                tvEndTodos.setText("Error: " + Calendar.getInstance().getTimeInMillis() + "");
                break;

        }
    }
}
