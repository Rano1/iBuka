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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ComicReadAdapter extends BaseAdapter {
	private LayoutInflater inflater = null;
	private Activity activity;
	private ArrayList<ComicContent> comiccontent;
	protected ImageLoader imageLoader = ImageLoader.getInstance();
	DisplayImageOptions options;

	public ComicReadAdapter(Activity activity,
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

	@Override
	public int getCount() {
		return comiccontent == null ? 0 : comiccontent.size();
	}

	@Override
	public ComicContent getItem(int position) {
		if (comiccontent != null && comiccontent.size() != 0) {
			return comiccontent.get(position);
		}
		return null;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		View view = convertView;
		final ViewHolder holder;
		if (convertView == null) {
			view = inflater.inflate(R.layout.comic_read_item, parent, false);
			holder = new ViewHolder();
			holder.comic_read_item_image = (ImageView) view
					.findViewById(R.id.comic_read_item_image);
			holder.comic_read_item_page = (TextView) view
					.findViewById(R.id.comic_read_item_page);
			view.setTag(holder);
		} else {
			holder = (ViewHolder) view.getTag();
		}
		final ComicContent comic = getItem(position);
		holder.comic_read_item_page.setText(String.valueOf(comic.getPage()));
		imageLoader.displayImage(comic.getImg_url(),
				holder.comic_read_item_image, options,
				new ImageLoadingListener() {

					@Override
					public void onLoadingStarted(String imageUri, View view) {
						// TODO Auto-generated method stub
						holder.comic_read_item_page.setVisibility(View.VISIBLE);
						holder.comic_read_item_image.setVisibility(View.GONE);
					}

					@Override
					public void onLoadingFailed(String imageUri, View view,
							FailReason failReason) {
						// TODO Auto-generated method stub
						holder.comic_read_item_page.setVisibility(View.VISIBLE);
						holder.comic_read_item_image.setVisibility(View.GONE);
					}

					@Override
					public void onLoadingComplete(String imageUri, View view,
							Bitmap loadedImage) {
						// TODO Auto-generated method stub
						holder.comic_read_item_page.setVisibility(View.GONE);
						holder.comic_read_item_image
								.setVisibility(View.VISIBLE);
					}

					@Override
					public void onLoadingCancelled(String imageUri, View view) {
						// TODO Auto-generated method stub

					}
				});
		return view;
	}

	private class ViewHolder {
		public ImageView comic_read_item_image;
		public TextView comic_read_item_page;
	}
}
