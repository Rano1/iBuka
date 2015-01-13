package com.buka.view;

import android.annotation.SuppressLint;
import android.view.ScaleGestureDetector;
import android.view.ScaleGestureDetector.OnScaleGestureListener;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;

//缩放view
@SuppressLint("NewApi")
public class ScaleGesture implements OnScaleGestureListener {

	private float beforeFactor;
	private float mPivotX;
	private float mPivotY;
	private View mVSouce;
	private boolean isFillAfter;

	public void setSourceView(View destinyView) {
		mVSouce = destinyView;
	}

	@Override
	public boolean onScale(ScaleGestureDetector detector) {
		if (checkIsNull()) {
			return false;
		}
		final float factor = detector.getScaleFactor();
		Animation animation = new ScaleAnimation(beforeFactor, factor,
				beforeFactor, factor, mPivotX, mPivotY);
		animation.setFillAfter(true);
		mVSouce.startAnimation(animation);
		beforeFactor = factor;
		return false;
	}

	@Override
	public boolean onScaleBegin(ScaleGestureDetector detector) {
		if (checkIsNull()) {
			return false;
		}
		beforeFactor = 1f;
		mPivotX = detector.getFocusX() - mVSouce.getLeft();
		mPivotY = mVSouce.getTop() + (mVSouce.getHeight() >> 1);
		return true;
	}

	@Override
	public void onScaleEnd(ScaleGestureDetector detector) {
		if (checkIsNull()) {
			return;
		}
		final float factor = detector.getScaleFactor();
		final int nWidth = (int) (mVSouce.getWidth() * factor);
		final int nHeight = (int) mVSouce.getHeight();
		final int nLeft = (int) (mVSouce.getLeft() - ((nWidth - mVSouce
				.getWidth()) * (mPivotX / mVSouce.getWidth())));
		final int nTop = (int) mVSouce.getTop();
		if (isFillAfter) {
			mVSouce.layout(nLeft, nTop, nLeft + nWidth, nTop + nHeight);
		}
		// MUST BE CLEAR ANIMATION. OTHERWISE WILL BE FLICKER
		// if can not clear animation the layout will keep the size
		// mVSouce.clearAnimation();
	}

	public boolean checkIsNull() {
		return mVSouce == null ? true : false;
	}

	/**
	 * if parameter is true that keeping same scale when next scaling.
	 * 
	 * @param isFill
	 */
	public void setFillAfter(boolean isFill) {
		isFillAfter = isFill;
	}
}
