package com.buka.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.GestureDetector.OnDoubleTapListener;
import android.view.GestureDetector.OnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ListView;

public class SlideImageView extends View implements OnTouchListener {

	GestureDetector mGesture = null;
	Context context;
	private double Scale = 1;

	public SlideImageView(Context context) {
		super(context);
		this.context = context;
		init(context);
	}

	public SlideImageView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		this.context = context;
		init(context);
	}

	public SlideImageView(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.context = context;
		init(context);
	}

	private void init(final Context context) {
		if (mGesture == null)
			mGesture = new GestureDetector(context, gestureListener);
		mGesture.setOnDoubleTapListener(new OnDoubleTapListener() {

			@Override
			public boolean onSingleTapConfirmed(MotionEvent e) {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public boolean onDoubleTapEvent(MotionEvent e) {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public boolean onDoubleTap(MotionEvent e) {
				// TODO Auto-generated method stub
				// 双击时产生一次

				return true;
			}
		});
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		// TODO Auto-generated method stub
		return false;
	}

	// 接下来是重点，让我们的View接受触控，需要使用下面两个方法让GestureDetector类去处理onTouchEvent和onInterceptTouchEvent方法
	public boolean onTouchEvent(MotionEvent event) {
		mGesture.onTouchEvent(event);
		return true;
	};

	OnGestureListener gestureListener = new OnGestureListener() {
		// 用户（轻触触摸屏后）松开，由一个1个MotionEvent ACTION_UP触发
		@Override
		public boolean onSingleTapUp(MotionEvent e) {
			// TODO Auto-generated method stub
			Log.i("MyGesture", "onSingleTapUp");
			return true;
		}

		@Override
		public void onShowPress(MotionEvent e) {
			// TODO Auto-generated method stub

		}

		// 用户按下触摸屏，并拖动，由1个MotionEvent ACTION_DOWN, 多个ACTION_MOVE触发
		@Override
		public boolean onScroll(MotionEvent e1, MotionEvent e2,
				float distanceX, float distanceY) {
			// TODO Auto-generated method stub
			return false;
		}

		// 用户长按触摸屏，由多个MotionEvent ACTION_DOWN触发
		@Override
		public void onLongPress(MotionEvent e) {
			// TODO Auto-generated method stub

		}

		// 用户按下触摸屏、快速移动后松开，由1个MotionEvent ACTION_DOWN, 多个ACTION_MOVE,
		// 1个ACTION_UP触发
		@Override
		public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
				float velocityY) {
			// TODO Auto-generated method stub
			int dx = (int) (e2.getX() - e1.getX()); // 计算滑动的距离
			if (Math.abs(dx) > 1 && Math.abs(velocityX) > Math.abs(velocityY)) { // 降噪处理，必须有较大的动作才识别
				if (velocityX > 0) {
					// 向右边
				} else {// 向左边}
				}
				return true;
			} else {
				return false;// 当然可以处理velocityY处理向上和向下的动作

			}
		}

		// 用户轻触触摸屏，由1个MotionEvent ACTION_DOWN触发
		@Override
		public boolean onDown(MotionEvent e) {
			// TODO Auto-generated method stub
			Log.i("MyGesture", "onDown");
			return true;
		}
	};
}