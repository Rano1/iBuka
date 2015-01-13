package com.buka.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.widget.ListView;

@SuppressLint("NewApi")
public class ZoomableListView extends ListView {

	private ScaleGestureDetector mScaleDetector;
	private float mScaleFactor = 1.f;
	private boolean isPagerEnabled;
	private static final int INVALID_POINTER_ID = -1;

	// The ‘active pointer’ is the one currently moving our object.
	private int mActivePointerId = INVALID_POINTER_ID;

	private float mPosX;
	private float mPosY;

	private float mLastTouchX;
	private float mLastTouchY;

	// Pivot point for Scaling
	static float gx = 0, gy = 0;

	public ZoomableListView(Context context, AttributeSet attrs) {
		super(context, attrs);
		mScaleDetector = new ScaleGestureDetector(getContext(),
				new ScaleListener());
	}

	public ZoomableListView(Context context) {
		super(context);
		mScaleDetector = new ScaleGestureDetector(getContext(),
				new ScaleListener());
	}

	@Override
	public void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		canvas.save(Canvas.MATRIX_SAVE_FLAG);
		canvas.translate(mPosX, mPosY);
		canvas.scale(mScaleFactor, mScaleFactor);
		canvas.restore();
	}

	@Override
	protected void dispatchDraw(Canvas canvas) {
		// Save the canvas to set the scaling factor returned from detector
		canvas.save(Canvas.MATRIX_SAVE_FLAG);
		if (mScaleFactor == 1.0f) {
			isPagerEnabled = true;
		} else {
			isPagerEnabled = false;
		}

		if (mScaleFactor == 1.0f) {
			mPosX = 0.0f;
			mPosY = 0.0f;
		}
		canvas.translate(mPosX, mPosY);
		canvas.scale(mScaleFactor, mScaleFactor);
		super.dispatchDraw(canvas);
		canvas.restore();
		invalidate();
	}

	@Override
	public boolean onTouchEvent(MotionEvent ev) {
		super.onTouchEvent(ev);
		final int action = ev.getAction();
		mScaleDetector.onTouchEvent(ev);
		switch (action & MotionEvent.ACTION_MASK) {
		case MotionEvent.ACTION_DOWN: {
			final float x = ev.getX();
			final float y = ev.getY();

			mLastTouchX = x;
			mLastTouchY = y;

			// Save the ID of this pointer
			mActivePointerId = ev.getPointerId(0);
			break;
		}

		case MotionEvent.ACTION_MOVE: {
			// Find the index of the active pointer and fetch its position
			final int pointerIndex = ev.findPointerIndex(mActivePointerId);
			final float x = ev.getX(pointerIndex);
			final float y = ev.getY(pointerIndex);

			final float dx = x - mLastTouchX;
			final float dy = y - mLastTouchY;

			mPosX += dx;
			mPosY += dy;

			mLastTouchX = x;
			mLastTouchY = y;

			invalidate();
			break;
		}

		case MotionEvent.ACTION_UP: {
			mActivePointerId = INVALID_POINTER_ID;
			break;
		}

		case MotionEvent.ACTION_CANCEL: {
			mActivePointerId = INVALID_POINTER_ID;
			break;
		}

		case MotionEvent.ACTION_POINTER_UP: {
			// Extract the index of the pointer that left the touch sensor
			final int pointerIndex = (action & MotionEvent.ACTION_POINTER_INDEX_MASK) >> MotionEvent.ACTION_POINTER_INDEX_SHIFT;
			final int pointerId = ev.getPointerId(pointerIndex);
			if (pointerId == mActivePointerId) {
				// This was our active pointer going up. Choose a new
				// active pointer and adjust accordingly.
				final int newPointerIndex = pointerIndex == 0 ? 1 : 0;
				mLastTouchX = ev.getX(newPointerIndex);
				mLastTouchY = ev.getY(newPointerIndex);
				mActivePointerId = ev.getPointerId(newPointerIndex);
			}
			break;
		}
		}

		return true;
	}

	private class ScaleListener extends
			ScaleGestureDetector.SimpleOnScaleGestureListener {
		@Override
		public boolean onScale(ScaleGestureDetector detector) {
			mScaleFactor *= detector.getScaleFactor();

			// Don't let the object get too small or too large.
			mScaleFactor = Math.max(1.0f, Math.min(mScaleFactor, 3.0f));

			invalidate();
			return true;
		}
	}
}