package com.buka.gallery;

import java.util.ArrayList;
import java.util.List;

import com.buka.entity.ComicContent;


import android.content.Context;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

public class BasePagerAdapter extends PagerAdapter {

	private ArrayList<ComicContent> comiccontent;
	protected final Context mContext;
	protected int mCurrentPosition = -1;
	protected OnItemChangeListener mOnItemChangeListener;

	public BasePagerAdapter() {
		comiccontent = null;
		mContext = null;
	}

	public BasePagerAdapter(Context context, ArrayList<ComicContent> comiccontent) {
		this.comiccontent = comiccontent;
		this.mContext = context;
	}

	@Override
	public void setPrimaryItem(ViewGroup container, int position, Object object) {
		super.setPrimaryItem(container, position, object);
		if (mCurrentPosition == position)
			return;
		GalleryViewPager galleryContainer = ((GalleryViewPager) container);
		if (galleryContainer.mCurrentView != null)
			galleryContainer.mCurrentView.resetScale();

		mCurrentPosition = position;
		if (mOnItemChangeListener != null)
			mOnItemChangeListener.onItemChange(mCurrentPosition);
	}

	@Override
	public void destroyItem(ViewGroup collection, int position, Object view) {
		collection.removeView((View) view);
	}

	@Override
	public int getCount() {
		return comiccontent == null ? 0 : comiccontent.size();
	}

	@Override
	public boolean isViewFromObject(View view, Object object) {
		return view.equals(object);
	}

	@Override
	public void finishUpdate(ViewGroup arg0) {
	}

	@Override
	public void restoreState(Parcelable arg0, ClassLoader arg1) {
	}

	@Override
	public Parcelable saveState() {
		return null;
	}

	@Override
	public void startUpdate(ViewGroup arg0) {
	}

	public int getCurrentPosition() {
		return mCurrentPosition;
	}

	public void setOnItemChangeListener(OnItemChangeListener listener) {
		mOnItemChangeListener = listener;
	}

	public static interface OnItemChangeListener {
		public void onItemChange(int currentPosition);
	}
}
