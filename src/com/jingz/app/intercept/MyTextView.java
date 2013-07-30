package com.jingz.app.intercept;

import android.content.Context;
import android.support.v4.view.MotionEventCompat;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.TextView;
import android.widget.Toast;

public class MyTextView extends TextView {

	private boolean mShown = false;

	public MyTextView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	public MyTextView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	public MyTextView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		final int action = MotionEventCompat.getActionMasked(event);
		
		if (action == MotionEvent.ACTION_DOWN) {
			mShown = false;
			return true;
		}
		if (action == MotionEvent.ACTION_MOVE) {
			if (!mShown ) {
				mShown = true;
				Toast.makeText(getContext(), "Moving intercepted in the child", Toast.LENGTH_SHORT).show();
			}
			return true;
		}
		
		return super.onTouchEvent(event);
	}
	
}
