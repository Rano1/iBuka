package com.buka.adapter;

import com.buka.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import android.app.Activity;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

/**
 * 推荐对应的适配器（热门连载、游戏中心）
 */
public class RecomAdapter extends BaseAdapter {
	protected ImageLoader imageLoader = ImageLoader.getInstance();
	public String[] imageUrls;
	public Activity activity;
	private static LayoutInflater inflater = null;
	DisplayImageOptions options;

	public RecomAdapter(Activity activity, String[] imageUrls) {
		this.imageUrls = imageUrls;
		this.activity = activity;
		inflater=LayoutInflater.from(activity);
		options = new DisplayImageOptions.Builder()
				.showImageOnLoading(R.drawable.ic_launcher)
				.showImageForEmptyUri(R.drawable.ic_launcher)
				.showImageOnFail(R.drawable.ic_launcher).cacheInMemory(true)
				.cacheOnDisc(true).considerExifParams(true)
				.bitmapConfig(Bitmap.Config.RGB_565)
				// .displayer(new RoundedBitmapDisplayer(10))//设置圆角
				.build();
	}

	@Override
	public int getCount() {
		return imageUrls == null ? 0 : imageUrls.length;
	}

	@Override
	public Object getItem(int position) {
		if (imageUrls != null && imageUrls.length > position) {
			// return dialect_list.get(position);
		}
		return null;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		final ViewHolder holder;
		View view = convertView;
		if (view == null) {
			view = inflater.inflate(R.layout.list_recom_gamescenter_item, parent,
					false);
			holder = new ViewHolder();
			assert view != null;
			holder.recom_gamescenter_item_image = (ImageView) view
					.findViewById(R.id.recom_gamescenter_item_image);
			holder.recom_gamescenter_item_title = (TextView) view
					.findViewById(R.id.recom_gamescenter_item_title);
			holder.recom_gamescenter_item_progress = (ProgressBar) view
					.findViewById(R.id.recom_gamescenter_item_progress);
			view.setTag(holder);
		} else {
			holder = (ViewHolder) view.getTag();
		}
		imageLoader.displayImage(imageUrls[position],  holder.recom_gamescenter_item_image, options , new ImageLoadingListener() {
			
			@Override
			public void onLoadingStarted(String imageUri, View view) {
				holder.recom_gamescenter_item_progress.setProgress(0);
				holder.recom_gamescenter_item_progress.setVisibility(View.VISIBLE);
				holder.recom_gamescenter_item_image.setVisibility(View.INVISIBLE);
			}
			
			@Override
			public void onLoadingFailed(String imageUri, View view,
					FailReason failReason) {
				holder.recom_gamescenter_item_progress.setVisibility(View.GONE);
				holder.recom_gamescenter_item_image.setVisibility(View.VISIBLE);
			}
			
			@Override
			public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
				holder.recom_gamescenter_item_progress.setVisibility(View.GONE);
				holder.recom_gamescenter_item_image.setVisibility(View.VISIBLE);
			}
			
			@Override
			public void onLoadingCancelled(String imageUri, View view) {
				// TODO Auto-generated method stub
				
			}
		});
		return view;
	}

	class ViewHolder {
		ImageView recom_gamescenter_item_image;
		TextView recom_gamescenter_item_title;
		ProgressBar recom_gamescenter_item_progress;
	}
}
