package com.buka.gallery;

import java.util.ArrayList;
import java.util.List;

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
import android.widget.ImageView;

public class UrlPagerAdapter extends BasePagerAdapter {
	private LayoutInflater inflater = null;
	private Context context;
	private ArrayList<ComicContent> comiccontent;
	protected ImageLoader imageLoader = ImageLoader.getInstance();
	DisplayImageOptions options;

	public UrlPagerAdapter(Context context, ArrayList<ComicContent> comiccontent) {
		this.context = context;
		this.comiccontent = comiccontent;
		inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		options = new DisplayImageOptions.Builder()
				.showImageOnLoading(R.drawable.ic_launcher)
				.showImageForEmptyUri(R.drawable.ic_launcher)
				.showImageOnFail(R.drawable.ic_launcher).cacheInMemory(true)
				.cacheOnDisc(true).considerExifParams(true)
				.bitmapConfig(Bitmap.Config.RGB_565)
				// .displayer(new RoundedBitmapDisplayer(20))
				.build();
	}

	@Override
	public void setPrimaryItem(ViewGroup container, int position, Object object) {
		super.setPrimaryItem(container, position, object);
		((GalleryViewPager) container).mCurrentView = ((TouchImageView) object);
	}

	@Override
	public Object instantiateItem(ViewGroup collection, int position) {
		final TouchImageView iv = new TouchImageView(mContext);
		iv.setLayoutParams(new ViewGroup.LayoutParams(
				ViewGroup.LayoutParams.MATCH_PARENT,
				ViewGroup.LayoutParams.MATCH_PARENT));
		imageLoader.displayImage(comiccontent.get(position).getImg_url(), iv,
				options, new ImageLoadingListener() {

					@Override
					public void onLoadingStarted(String imageUri, View view) {
						// TODO Auto-generated method stub
						// holder.comic_read_item_page.setVisibility(View.VISIBLE);
						iv.setVisibility(View.GONE);
					}

					@Override
					public void onLoadingFailed(String imageUri, View view,
							FailReason failReason) {
						// TODO Auto-generated method stub
						// holder.comic_read_item_page.setVisibility(View.VISIBLE);
						iv.setVisibility(View.GONE);
					}

					@Override
					public void onLoadingComplete(String imageUri, View view,
							Bitmap loadedImage) {
						// TODO Auto-generated method stub
						// holder.comic_read_item_page.setVisibility(View.GONE);
						iv.setVisibility(View.VISIBLE);
					}

					@Override
					public void onLoadingCancelled(String imageUri, View view) {
						// TODO Auto-generated method stub

					}
				});
		collection.addView(iv, 0);
		return iv;
	}
}
