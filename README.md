# Progress Button (Demo) for Android
by Yildiz Kabaran

An extended Button that will display animated text colors while in disabled state to indicated an asynchronous process is going on. The given colors will form a linear moving gradient on the button's text. The gradient colors and the movement speed are adjustable through code or xml.

![Preview Image](http://i.imgbox.com/LS4EuFt0.gif)

## Installation

Simply copy the ProgressButton.java and attrs.xml files into your project and use it like you would use any other custom view.

## Usage

You can create in code:
```
// create and customize the view
final ProgressButton button = new ProgressButton(context);
button.setProgressColors(colors); // set an int[] color array of length >= 2
button.setProgressSpeed(-0.02F); // use a range (-1, 1) for best results
								 //positive values move right, negative values move left
button.setOnClickListener(new View.OnClickListener(){
  @Override
  public void onClick(View v){
    button.setEnabled(false); // this will start the animation
						      // you need to keep a reference to the button 
						      // to stop the animation
  }
});
```

or in XML:
```
<com.yildizkabaran.progressbuttondemo.ProgressButton
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:id="@+id/progress_button"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    app:progressColorList="@array/progress_colors"
    app:progressSpeed="-0.02"
    android:text="@string/click_me"/>
```

for a dummy animation, you can simply call:
```
button.setEnabled(false);
button.postDelayed(new Runnable(){
	@Override
	public void run(){
		button.setEnabled(true);
	}
});
```

Please feel free to ask for any fixes/customizations/additions to this button. I would really like to hear some suggestions on what other text effects you would like to see on a progress button so that I (or you) can implement them and post them here for a good collection.

## Notes

- The view has only been tested on HTC One running Android 4.4.2, and therefore needs to be tested on devices with different versions and screen resolutions.
- The color animation spans that entire width of the button, and not only that of the text. It creates little difference in this case but is a problem if the view is to be extended to show progress instead of only showing non-determinted wait.


## Copyright and License

Feel free to use the code in any way you wish without permission.
