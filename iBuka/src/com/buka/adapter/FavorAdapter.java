package com.buka.adapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.zip.Inflater;

import com.buka.R;
import com.buka.entity.FavorCollectList;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

public class FavorAdapter extends BaseAdapter {
	private ArrayList<FavorCollectList> favorcollectlist;
	private Activity activity;
	private LayoutInflater inflater = null;
	protected ImageLoader imageLoader = ImageLoader.getInstance();
	private ImageLoadingListener animateFirstListener = new AnimateFirstDisplayListener();
	DisplayImageOptions options;

	public FavorAdapter(Activity activity,
			ArrayList<FavorCollectList> favorcollectlist) {
		this.activity = activity;
		this.favorcollectlist = favorcollectlist;
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

	@Override
	public int getCount() {
		return favorcollectlist == null ? 0 : favorcollectlist.size();
	}

	@Override
	public FavorCollectList getItem(int position) {
		if (favorcollectlist != null && favorcollectlist.size() != 0) {
			return favorcollectlist.get(position);
		}
		return null;
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
			view = inflater.inflate(R.layout.list_favor_item, parent, false);
			holder = new ViewHolder();
			holder.favor_item_title = (TextView) view
					.findViewById(R.id.favor_item_title);
			holder.favor_item_no = (TextView) view
					.findViewById(R.id.favor_item_no);
			holder.favor_image = (ImageView) view
					.findViewById(R.id.favor_image);
			holder.button_favor_item_more = (ImageView) view
					.findViewById(R.id.button_favor_item_more);
			view.setTag(holder);
		} else {
			holder = (ViewHolder) view.getTag();
		}
		final FavorCollectList favor = getItem(position);
		holder.favor_item_title.setText(favor.getTitle());
		holder.favor_item_no.setText(favor.getLast_section());
		imageLoader.displayImage(favor.getImage(), holder.favor_image, options,
				animateFirstListener);
		holder.button_favor_item_more.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Toast.makeText(activity, "" + favor.getTitle(),
						Toast.LENGTH_SHORT).show();
			}
		});
		return view;
	}

	private class ViewHolder {
		public TextView favor_item_title;
		public TextView favor_item_no;
		public ImageView favor_image;
		public ImageView button_favor_item_more;
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
