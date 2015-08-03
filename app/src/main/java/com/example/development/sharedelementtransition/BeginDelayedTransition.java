package com.example.development.sharedelementtransition;

import android.app.Activity;
import android.os.Bundle;
import android.transition.AutoTransition;
import android.transition.ChangeBounds;
import android.transition.Explode;
import android.transition.Fade;
import android.transition.Scene;
import android.transition.Slide;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.transition.TransitionManager;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import butterknife.Bind;
import butterknife.ButterKnife;

public class BeginDelayedTransition extends Activity {

    @Bind(R.id.layout_root_view)
    ViewGroup mRootView;
    @Bind(R.id.red_box)
    View mRedBox;
    @Bind(R.id.green_box)
    View mGreenBox;
    @Bind(R.id.blue_box)
    View mBlueBox;
    @Bind(R.id.black_box)
    View mBlackBox;
    private Transition mTransitionType;
    private boolean mBoundChanged = false;
    private int mHeight;
    private int mWidth;
    Scene mScene1, mScene2,mScene3;
    TransitionManager mTransitionManager;
    private boolean mIsScene2Visible = false;
    private boolean mIsScene3Visible=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.transitions);
        ButterKnife.bind(this);
        inflateScene();
        inflateTransitionManager();
        setInitialBoxSize();

    }

    private void inflateTransitionManager() {
        TransitionInflater inflater = TransitionInflater.from(this);
        mTransitionManager = inflater.inflateTransitionManager(R.transition.transitions_mgr,
                mRootView);
    }

    private void inflateScene() {
        mScene1 = Scene.getSceneForLayout(mRootView, R.layout.scene1, this);
        mScene2 = Scene.getSceneForLayout(mRootView, R.layout.scene2, this);
        mScene3 = Scene.getSceneForLayout(mRootView, R.layout.scene3, this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        switch (id) {
            case R.id.fade:
                mTransitionType = new Fade();
                mTransitionManager.beginDelayedTransition(mRootView, mTransitionType);
                toggleVisibility(mRedBox, mGreenBox, mBlueBox, mBlackBox);
                break;
            case R.id.slide:
                Slide slide = new Slide();
                slide.setSlideEdge(Gravity.TOP);
                mTransitionType = slide;
                mTransitionManager.beginDelayedTransition(mRootView, mTransitionType);
                toggleVisibility(mRedBox, mGreenBox, mBlueBox, mBlackBox);
                break;
            case R.id.explode:
                mTransitionType = new Explode();
                mTransitionManager.beginDelayedTransition(mRootView, mTransitionType);
                toggleVisibility(mRedBox, mGreenBox, mBlueBox, mBlackBox);
                break;
            case R.id.autoTransition:
                mTransitionType = new AutoTransition();
                mTransitionManager.beginDelayedTransition(mRootView, mTransitionType);
                toggleVisibility(mRedBox, mGreenBox, mBlueBox, mBlackBox);
                break;
            case R.id.changeBounds:
                mTransitionType = new ChangeBounds();
                mTransitionManager.beginDelayedTransition(mRootView, mTransitionType);
                if (mBoundChanged) {
                    setNewSize(R.id.red_box, mWidth, mHeight);
                    setNewSize(R.id.green_box, mWidth, mHeight);
                    setNewSize(R.id.blue_box, mWidth, mHeight);
                    setNewSize(R.id.black_box, mWidth, mHeight);
                    mBoundChanged = false;
                } else {
                    setNewSize(R.id.red_box, 150, 25);
                    setNewSize(R.id.green_box, 150, 25);
                    setNewSize(R.id.blue_box, 150, 25);
                    setNewSize(R.id.black_box, 150, 25);
                    mBoundChanged = true;
                }
                break;
            case R.id.changeBoundsSceneTransition:
                if (mIsScene2Visible) {
                    mIsScene2Visible = false;
                    mTransitionManager.transitionTo(mScene1);
                } else {
                    mIsScene2Visible = true;
                    mTransitionManager.transitionTo(mScene2);
                }
                break;
            case R.id.changeBoundsSceneTransitionFadeIn:
                if (mIsScene3Visible) {
                    mIsScene3Visible = false;
                    mTransitionManager.transitionTo(mScene1);
                } else {
                    mIsScene3Visible = true;
                    mTransitionManager.transitionTo(mScene3);
                }
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setNewSize(int id, int width, int height) {
        View view = findViewById(id);
        ViewGroup.LayoutParams params = view.getLayoutParams();
        params.width = width;
        params.height = height;
        view.setLayoutParams(params);
    }

    private static void toggleVisibility(View... views) {
        for (View view : views) {
            boolean isVisible = view.getVisibility() == View.VISIBLE;
            view.setVisibility(isVisible ? View.INVISIBLE : View.VISIBLE);
        }
    }

    public void setInitialBoxSize() {
        ViewGroup.LayoutParams params = mRedBox.getLayoutParams();
        mWidth = params.width;
        mHeight = params.height;
    }

}
