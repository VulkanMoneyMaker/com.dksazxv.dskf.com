package com.dksazxv.dskf.com;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;


import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

public class Splash_Activity extends AppCompatActivity {
    private static final String TAG = Splash_Activity.class.getSimpleName();

    private static final int TIME_SPLASH_SEC = 3;

    private Disposable mDisposable;

    private final float TOUCH_SCALE_FACTOR = 180.0f / 320.0f;
    private float previousX;
    private float previousY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate");
        setContentView(R.layout.activity_splash);

        mDisposable = Observable.timer(TIME_SPLASH_SEC, TimeUnit.SECONDS)
                .subscribe(__ -> openChoiceActivity(), Throwable::printStackTrace);
    }

    @Override
    public void onDestroy() {
        if (mDisposable != null) mDisposable.dispose();
        super.onDestroy();
    }

    public boolean onTouchEvent(final MotionEvent evt) {
        float currentX = evt.getX();
        float currentY = evt.getY();
        float deltaX, deltaY;
        switch (evt.getAction()) {
            case MotionEvent.ACTION_MOVE:
                // Modify rotational angles according to movement
                deltaX = currentX - previousX;
                deltaY = currentY - previousY;
        }
        // Save current x, y
        previousX = currentX;
        previousY = currentY;
        return true;  // Event handled
    }

    private void openChoiceActivity() {
        Log.d(TAG, "openChoiceActivity");
        startActivity(First_Single_Activity.getChoiceActivityIntent(this));
        finish();
    }
}
