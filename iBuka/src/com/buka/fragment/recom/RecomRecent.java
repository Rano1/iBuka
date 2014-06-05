package com.buka.fragment.recom;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import android.graphics.Bitmap;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.buka.R;
import com.buka.base.BaseFragment;
import com.buka.tools.Constants;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;

/**
 * 最近更新
 */
public class RecomRecent extends BaseFragment {
	View view;
	private ListView recom_recent_listview;
	DisplayImageOptions options;
	String[] imageUrls;
	private static String TAG = "RecomRecent";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		imageUrls = Constants.IMAGES_COMIC_RECENT;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = LayoutInflater.from(activity).inflate(R.layout.frm_recom_recent,
				null);
		initView();
		return view;
	}

	private void initView() {
		recom_recent_listview = (ListView) view.findViewById(R.id.recom_recent_listview);
		recom_recent_listview.setAdapter(new ItemAdapter());

	}

	class ItemAdapter extends BaseAdapter {

		private ImageLoadingListener animateFirstListener = new AnimateFirstDisplayListener();

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
		public View getView(final int position, View convertView,
				ViewGroup parent) {
			View view = convertView;
			final ViewHolder holder;
			if (convertView == null) {
				view = getActivity().getLayoutInflater().inflate(R.layout.list_recom_recent_item, parent, false);
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
			imageLoader.displayImage(imageUrls[position],
					holder.recom_recent_item_image, options,
					animateFirstListener);

			return view;
		}
	}
	protected ImageLoader imageLoader = ImageLoader.getInstance();
	private static class AnimateFirstDisplayListener extends SimpleImageLoadingListener {

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

	@Override
	public void onSaveInstanceState(Bundle outState) {
		// TODO Auto-generated method stub
		super.onSaveInstanceState(outState);
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}
}
