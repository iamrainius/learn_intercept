package com.jingz.app.intercept;

import android.content.Context;
import android.support.v4.view.MotionEventCompat;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewConfiguration;
import android.widget.FrameLayout;
import android.widget.Toast;

public class DraggableLayout extends FrameLayout {

	private int mTouchSlop;
	private boolean mIsScrolling;
	private float mDownX;
	private boolean mShown;

	public DraggableLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public DraggableLayout(Context context) {
		super(context);
		init();
	}

	public DraggableLayout(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
	}
	
	private void init() {
		ViewConfiguration vc = ViewConfiguration.get(getContext());
		mTouchSlop = vc.getScaledTouchSlop();
	}

	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev) {
		final int action = MotionEventCompat.getActionMasked(ev);
		
		if (action == MotionEvent.ACTION_CANCEL || action == MotionEvent.ACTION_UP) {
			mIsScrolling = false;
			return false;
		}
		
		switch (action) {
		case MotionEvent.ACTION_DOWN:
			mShown = false;
			mDownX = ev.getRawX();
			return false;
		case MotionEvent.ACTION_MOVE:
			if (mIsScrolling) {
				return true;
			}
			
			final int xDiff = calculateDistanceX(ev);
			if (xDiff > mTouchSlop) {
				mIsScrolling = true;
				return true;
			}
			break;
		}
		
		return false;
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		final int action = MotionEventCompat.getActionMasked(event);
		switch (action) {
		case MotionEvent.ACTION_MOVE:
			if (!mShown) {
				mShown = true;
				Toast.makeText(getContext(), "Moving intercepted in the parent", Toast.LENGTH_SHORT).show();
			}
			return true;
		}
		
		return super.onTouchEvent(event);
	}

	private int calculateDistanceX(MotionEvent ev) {
		return (int) (ev.getRawX() - mDownX);
	}

}
