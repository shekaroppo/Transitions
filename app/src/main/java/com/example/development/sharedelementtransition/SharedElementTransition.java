package com.example.development.sharedelementtransition;

import android.app.Activity;
import android.os.Bundle;
import android.transition.AutoTransition;
import android.transition.TransitionManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

public class SharedElementTransition extends Activity implements View.OnClickListener {

    ViewGroup mRootView;
    private View mRedBox, mGreenBox, mBlueBox, mBlackBox;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared_element_transition);
        mRootView = (ViewGroup) findViewById(R.id.layout_root_view);
        mRootView.setOnClickListener(this);
        mRedBox = findViewById(R.id.red_box);
        mGreenBox = findViewById(R.id.green_box);
        mBlueBox = findViewById(R.id.blue_box);
        mBlackBox = findViewById(R.id.black_purple);
    }
    @Override
    public void onClick(View v) {
        TransitionManager.beginDelayedTransition(mRootView, new AutoTransition());
        toggleVisibility(mRedBox, mGreenBox, mBlueBox, mBlackBox);
    }
    private static void toggleVisibility(View... views) {
        for (View view : views) {
            boolean isVisible = view.getVisibility() == View.VISIBLE;
            view.setVisibility(isVisible ? View.INVISIBLE : View.VISIBLE);
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_shared_element_transition, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
