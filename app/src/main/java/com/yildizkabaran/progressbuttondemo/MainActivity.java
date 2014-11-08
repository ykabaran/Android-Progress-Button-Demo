package com.yildizkabaran.progressbuttondemo;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;

import com.yildizkabaran.texteffectsdemo.R;

import java.util.Random;


public class MainActivity extends Activity implements View.OnClickListener {

  private static final String TAG = MainActivity.class.getSimpleName();

  private Button button;
  private Handler handler;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    setContentView(R.layout.activity_main);

    handler = new Handler();

    button = (Button) findViewById(R.id.rainbow_button);
    button.setOnClickListener(this);
  }

  @Override
  protected void onDestroy() {
    button = null;
    handler = null;

    super.onDestroy();
  }

  @Override
  public void onClick(View v) {
    int id = v.getId();
    switch (id) {
      case R.id.rainbow_button:
        onRainbowButtonClicked();
        break;
    }
  }

  private void onRainbowButtonClicked() {
    button.setEnabled(false);

    // simulate some async work
    handler.postDelayed(new Runnable() {
      @Override
      public void run() {
        if (button != null) {
          button.setEnabled(true);
        }
      }
    }, new Random().nextInt(1000) + 5000);
  }
}
