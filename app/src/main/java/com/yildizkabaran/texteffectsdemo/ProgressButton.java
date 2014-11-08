package com.yildizkabaran.texteffectsdemo;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.widget.Button;

/**
 * Created by yildizkabaran on 8.11.2014.
 */
public class ProgressButton extends Button {

  private static final String TAG = ProgressButton.class.getSimpleName();

  public ProgressButton(Context context) {
    super(context);
  }

  public ProgressButton(Context context, AttributeSet attrs) {
    super(context, attrs);
    setupAttributes(attrs);
  }

  public ProgressButton(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    setupAttributes(attrs);
  }

  private static final int ANIMATION_DELAY = 20;
  private static final float DEFAULT_COLOR_SPEED = 0.01F;

  private int[] progressColors;
  private float progressSpeed = DEFAULT_COLOR_SPEED;

  private float currentOffset = 0F;
  private int width;

  private Runnable textColorAnimator = new Runnable() {
    @Override
    public void run() {
      if(progressColors != null && progressColors.length >= 2) {
        float startX = currentOffset * width;
        final Shader shader = new LinearGradient(startX, 0, startX + width, 0, progressColors, null, Shader.TileMode.REPEAT);
        getPaint().setShader(shader);
        invalidate();
      }

      currentOffset += progressSpeed;
      currentOffset %= 1F;

      postDelayed(this, ANIMATION_DELAY);
    }
  };

  private void setupAttributes(AttributeSet attrs){
    TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.ProgressButton);

    final int numAttrs = a.getIndexCount();
    for (int i = 0; i < numAttrs; ++i) {
      final int attr = a.getIndex(i);
      switch (attr) {
        case R.styleable.ProgressButton_progressColorList:
          int arrayId = a.getResourceId(i, -1);
          if(arrayId >= 0){
            // TypedArray does not provide a method for obtaining integer arrays so using resources instead
            if(!isInEditMode()) {
              int[] colors = getResources().getIntArray(arrayId);
              if (colors != null) {
                setProgressColors(colors);
              }
            }
          }
          break;
        case R.styleable.ProgressButton_progressSpeed:
          float speed = a.getFloat(i, DEFAULT_COLOR_SPEED);
          setProgressSpeed(speed);
          break;
      }
    }
    a.recycle();
  }

  public void setProgressColors(int[] colors){
    this.progressColors = colors;
  }

  public void setProgressSpeed(float speed){
    this.progressSpeed = speed;
  }

  @Override
  protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
    super.onMeasure(widthMeasureSpec, heightMeasureSpec);

    width = getMeasuredWidth();
  }

  @Override
  public void setEnabled(boolean enabled) {
    super.setEnabled(enabled);
    if(!enabled){
      startTextColorAnimation();
    } else {
      stopTextColorAnimation();
    }
  }

  private void startTextColorAnimation(){
    post(textColorAnimator);
  }

  private void stopTextColorAnimation(){
    removeCallbacks(textColorAnimator);

    getPaint().setShader(null);
    invalidate();
  }
}
