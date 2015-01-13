package com.buka.adapter;

import java.util.ArrayList;

import com.buka.R;
import com.buka.entity.ComicContent;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.LayoutParams;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class ComicReadViewAdapter extends PagerAdapter {

	// 界面列表
	// private ArrayList<View> views;

	private LayoutInflater inflater = null;
	private Activity activity;
	private ArrayList<ComicContent> comiccontent;
	protected ImageLoader imageLoader = ImageLoader.getInstance();
	DisplayImageOptions options;

	public ComicReadViewAdapter(Activity activity,
			ArrayList<ComicContent> comiccontent) {
		this.activity = activity;
		this.comiccontent = comiccontent;
		inflater = (LayoutInflater) activity
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		options = new DisplayImageOptions.Builder()
				.showImageOnLoading(R.drawable.ic_launcher)
				.showImageForEmptyUri(R.drawable.ic_launcher)
				.showImageOnFail(R.drawable.ic_launcher).cacheInMemory(true)
				.cacheOnDisc(true).considerExifParams(true)
				// .displayer(new RoundedBitmapDisplayer(20))
				.build();

	}

	/**
	 * 获得当前界面数
	 */
	@Override
	public int getCount() {
		Log.d("ComicReadViewAdapter", comiccontent.size() + "");
		return comiccontent == null ? 0 : comiccontent.size();
	}

	/**
	 * 初始化position位置的界面 //初始化一个item
	 */
	ImageView comic_read_item_image;
	TextView comic_read_item_page;

	@Override
	public Object instantiateItem(ViewGroup view, int position) {
		// ((ViewPager) view).addView(views.get(position), 0);
		View view_item = inflater.inflate(R.layout.comic_read_item, null);
		comic_read_item_image = (ImageView) view_item
				.findViewById(R.id.comic_read_item_image);
		comic_read_item_page = (TextView) view_item
				.findViewById(R.id.comic_read_item_page);
		final ComicContent comic = comiccontent.get(position);
		imageLoader.displayImage(comic.getImg_url(), comic_read_item_image,
				options, new ImageLoadingListener() {

					@Override
					public void onLoadingStarted(String imageUri, View view) {
						// TODO Auto-generated method stub
						comic_read_item_page.setVisibility(View.VISIBLE);
						comic_read_item_image.setVisibility(View.GONE);
					}

					@Override
					public void onLoadingFailed(String imageUri, View view,
							FailReason failReason) {
						// TODO Auto-generated method stub
						comic_read_item_page.setVisibility(View.VISIBLE);
						comic_read_item_image.setVisibility(View.GONE);
					}

					@Override
					public void onLoadingComplete(String imageUri, View view,
							Bitmap loadedImage) {
						// TODO Auto-generated method stub
						comic_read_item_page.setVisibility(View.GONE);
						comic_read_item_image.setVisibility(View.VISIBLE);
					}

					@Override
					public void onLoadingCancelled(String imageUri, View view) {
						// TODO Auto-generated method stub

					}
				});
		((ViewPager) view).addView(view_item);
		return view;
		// return views.get(position);
	}

	/**
	 * 判断是否由对象生成界面
	 */
	@Override
	public boolean isViewFromObject(View view, Object arg1) {
		return (view == arg1);
	}

	/**
	 * 销毁position位置的界面//pageAdatper与BaseAdapter有很大区别，现在我理解为ViewPager释放有adapter处理
	 */
	@Override
	public void destroyItem(ViewGroup view, int position, Object arg2) {
		((ViewPager) view).removeView((View) arg2);
	}

	@Override
	public void setPrimaryItem(ViewGroup container, int position, Object object) {
		// TODO Auto-generated method stub
		super.setPrimaryItem(container, position, object);
	}

	@Override
	public void finishUpdate(ViewGroup container) {
		// TODO Auto-generated method stub
		super.finishUpdate(container);
	}
}