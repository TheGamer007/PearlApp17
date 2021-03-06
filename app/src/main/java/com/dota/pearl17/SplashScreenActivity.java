package com.dota.pearl17;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.ViewTreeObserver;
import android.view.animation.AccelerateInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.plattysoft.leonids.ParticleSystem;
import com.squareup.picasso.Picasso;

/**
 * Created by Shri Akhil on 09-03-2017.
 */

public class SplashScreenActivity extends AppCompatActivity {

    RelativeLayout container;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        container = (RelativeLayout) findViewById(R.id.container_splash);

        startService(new Intent(SplashScreenActivity.this, ScheduleUpdateIntentService.class));


        Picasso.with(this)
                .load(R.drawable.splash_screen_bg)
                .fit()
                .into((ImageView) findViewById(R.id.bg_splash));

        Picasso.with(this)
                .load(R.drawable.splash_logo)
                .fit()
                .centerInside()
                .into((ImageView) findViewById(R.id.logo_splash));

        Picasso.with(this)
                .load(R.drawable.splash_dates)
                .fit()
                .centerInside()
                .into((ImageView) findViewById(R.id.dates_splash));

        ViewTreeObserver viewTreeObserver = container.getViewTreeObserver();
        if (viewTreeObserver.isAlive()) {
            viewTreeObserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                @Override
                public void onGlobalLayout() {
                    container.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    emit();
                }
            });
        }
    }

    public void emit() {
        int num = 130;
        final int dur = 4300;
        float accel = 0.00015f;
        float vel_y = 0.03f;
        float max_vel_y = 0.09f;

        new ParticleSystem(this, num, R.drawable.confetti_red, dur, R.id.bg_hook)
                .setScaleRange(0.2f,0.4f)
                .setAcceleration(accel, 90)
                .setInitialRotationRange(-45, 45)
                .setSpeedByComponentsRange(0f, 0f, vel_y, max_vel_y)
                .setFadeOut(200, new AccelerateInterpolator())
                .emitWithGravity(container, Gravity.TOP, 30, dur);

        new ParticleSystem(this, num, R.drawable.confetti_blue, dur, R.id.bg_hook)
                .setScaleRange(0.2f,0.4f)
                .setAcceleration(accel, 90)
                .setInitialRotationRange(-45, 45)
                .setSpeedByComponentsRange(0f, 0f, vel_y, max_vel_y)
                .setFadeOut(200, new AccelerateInterpolator())
                .emitWithGravity(container, Gravity.TOP, 30, dur);

        new ParticleSystem(this, num, R.drawable.confetti_green, dur, R.id.bg_hook)
                .setScaleRange(0.2f,0.4f)
                .setAcceleration(accel, 90)
                .setInitialRotationRange(-45, 45)
                .setSpeedByComponentsRange(0f, 0f, vel_y, max_vel_y)
                .setFadeOut(200, new AccelerateInterpolator())
                .emitWithGravity(container, Gravity.TOP, 30, dur);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(SplashScreenActivity.this, MainActivity.class);
                i.putExtra("fromSplash", 1);
                startActivity(i);
                finish();
            }
        }, dur);
    }
}
