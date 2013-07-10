package com.lakehub.beinafu;

import com.lakehub.beinafu.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.view.animation.Animation.AnimationListener;

public class SplashScreen extends Activity {

	/* (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash_screen);		//calling the activity_splash_screen.xml to view
	}
	
	@Override
	protected void onPause() {
		super.onPause();
		// Stop the animation
        TextView logo1 = (TextView) findViewById(R.id.TextViewTopTitle);
        logo1.clearAnimation();
        TextView logo2 = (TextView) findViewById(R.id.TextViewBottomTitle);
        logo2.clearAnimation();
        TableLayout table = (TableLayout) findViewById(R.id.TableLayout01);
        for (int i = 0; i < table.getChildCount(); i++) {
            TableRow row = (TableRow) table.getChildAt(i);
            row.clearAnimation();
        }
	}
	
	//private method to start animating splash screen
	 private void startAnimating() {
		// Fade in top title
        TextView logo1 = (TextView) findViewById(R.id.TextViewTopTitle);
        Animation fade1 = AnimationUtils.loadAnimation(this, R.anim.fade_in);
        logo1.startAnimation(fade1);
        // Fade in bottom title after a built-in delay.
        TextView logo2 = (TextView) findViewById(R.id.TextViewBottomTitle);
        Animation fade2 = AnimationUtils.loadAnimation(this, R.anim.fade_in2);
        logo2.startAnimation(fade2);
        // Transition to Main Menu when bottom title finishes animating
        fade2.setAnimationListener(new AnimationListener() {
            public void onAnimationEnd(Animation animation) {
                // The animation has ended, transition to the Main Menu screen
            	startActivity(new Intent(SplashScreen.this, Main.class));  	//we could type com.lakehub.beinafuu.Main
                SplashScreen.this.finish();
            }

            public void onAnimationRepeat(Animation animation) {
            }

            public void onAnimationStart(Animation animation) {
            }
        });
        // Load animations for all views within the TableLayout
        Animation spinin = AnimationUtils.loadAnimation(this, R.anim.custom_anim);
        LayoutAnimationController controller = new LayoutAnimationController(spinin);
        TableLayout table = (TableLayout) findViewById(R.id.TableLayout01);
        for (int i = 0; i < table.getChildCount(); i++) {
            TableRow row = (TableRow) table.getChildAt(i);
            row.setLayoutAnimation(controller);
        }
	 }
	 
	 @Override
    protected void onResume() {
        super.onResume();

        // Start animating at the beginning so we get the full splash screen experience
        startAnimating();
    }
}
