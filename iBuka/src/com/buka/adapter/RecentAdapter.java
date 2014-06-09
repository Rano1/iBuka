package com.buka.adapter;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import com.buka.R;
import com.buka.tools.Options;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

/**
 * 最近更新列表的适配器
 */
public class RecentAdapter extends BaseAdapter {
	protected ImageLoader imageLoader = ImageLoader.getInstance();
	private ImageLoadingListener animateFirstListener = new AnimateFirstDisplayListener();
	Context context;
	String[] imageUrls;
	public LayoutInflater inflater = null;
	DisplayImageOptions options;
	
	public RecentAdapter(Context context , String[] imageUrls) {
		this.context = context;
		this.imageUrls = imageUrls;
		inflater = LayoutInflater.from(context);
		options = Options.getListOptions();
	}

	private class ViewHolder {
		public TextView recom_recent_item_title;
		public TextView recom_recent_item_writer;
		public TextView recom_recent_item_no;
		public ImageView recom_recent_item_image;
		public RatingBar recom_recent_item_ratingBar;
	}

	@Override
	public int getCount() {
		return imageUrls.length;
	}

	@Override
	public Object getItem(int position) {
		return position;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		View view = convertView;
		final ViewHolder holder;
		if (convertView == null) {
			view = inflater.inflate(R.layout.list_recom_recent_item, parent, false);
			holder = new ViewHolder();
			holder.recom_recent_item_title = (TextView) view.findViewById(R.id.recom_recent_item_title);
			holder.recom_recent_item_writer = (TextView) view.findViewById(R.id.recom_recent_item_writer);
			holder.recom_recent_item_no = (TextView) view.findViewById(R.id.recom_recent_item_no);
			holder.recom_recent_item_image = (ImageView) view.findViewById(R.id.recom_recent_item_image);
			holder.recom_recent_item_ratingBar = (RatingBar) view.findViewById(R.id.recom_recent_item_ratingBar);
			view.setTag(holder);
		} else {
			holder = (ViewHolder) view.getTag();
		}
		holder.recom_recent_item_title.setText("火影忍者" + (position + 1));
		holder.recom_recent_item_ratingBar.setProgress((position + 1) / 10);
		imageLoader.displayImage(imageUrls[position],holder.recom_recent_item_image, options, animateFirstListener);
		return view;
	}

	private static class AnimateFirstDisplayListener extends
			SimpleImageLoadingListener {

		static final List<String> displayedImages = Collections
				.synchronizedList(new LinkedList<String>());

		@Override
		public void onLoadingComplete(String imageUri, View view,
				Bitmap loadedImage) {
			if (loadedImage != null) {
				ImageView imageView = (ImageView) view;
				boolean firstDisplay = !displayedImages.contains(imageUri);
				if (firstDisplay) {
					FadeInBitmapDisplayer.animate(imageView, 500);
					displayedImages.add(imageUri);
				}
			}
		}
	}
}
