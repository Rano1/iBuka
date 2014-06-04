package com.buka.adapter;

import java.util.ArrayList;

import com.buka.R;
import com.buka.entity.CateEntity;
import com.buka.tools.Options;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
/**
 * 栏目适配器
 */
public class CateAdapter extends BaseAdapter {
	ArrayList<CateEntity> list;
	Context context;
	LayoutInflater inflater = null;
	protected ImageLoader imageLoader = ImageLoader.getInstance();
	DisplayImageOptions options;
	
	public CateAdapter(Context context, ArrayList<CateEntity> list) {
		this.context = context;
		this.list = list;
		options = new DisplayImageOptions.Builder()
				.showImageOnLoading(R.drawable.ic_launcher)
				.showImageForEmptyUri(R.drawable.ic_launcher)
				.showImageOnFail(R.drawable.ic_launcher).cacheInMemory(true)
				.cacheOnDisc(true).considerExifParams(true)
				.bitmapConfig(Bitmap.Config.RGB_565)
				// .displayer(new RoundedBitmapDisplayer(10))//设置圆角
				.build();
		inflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list == null ? 0 : list.size();
	}

	@Override
	public CateEntity getItem(int position) {
		if (list != null && list.size() != 0) {
			return list.get(position);
		}
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		final ViewHolder mHolder;
		View view = convertView;
		if (view == null) {
			view = inflater.inflate(R.layout.list_cate_item, null);
			mHolder = new ViewHolder();
			mHolder.tv_cate_name = (TextView) view.findViewById(R.id.tv_cate_name);
			mHolder.iv_cate_img = (ImageView) view.findViewById(R.id.iv_cate_img);
			mHolder.iv_cate_progress = (ProgressBar) view.findViewById(R.id.iv_cate_progress);
			view.setTag(mHolder);
		} else {
			mHolder = (ViewHolder) view.getTag();
		}
		CateEntity category = getItem(position);
		mHolder.tv_cate_name.setText(category.getName());
		imageLoader.displayImage(category.getImg_url(), mHolder.iv_cate_img, options, new ImageLoadingListener() {
			
			@Override
			public void onLoadingStarted(String imageUri, View view) {
				mHolder.iv_cate_img.setVisibility(View.INVISIBLE);
				mHolder.iv_cate_progress.setVisibility(View.VISIBLE);
			}
			
			@Override
			public void onLoadingFailed(String imageUri, View view,
					FailReason failReason) {
				mHolder.iv_cate_img.setVisibility(View.INVISIBLE);
				mHolder.iv_cate_progress.setVisibility(View.GONE);
			}
			
			@Override
			public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
				mHolder.iv_cate_img.setVisibility(View.VISIBLE);
				mHolder.iv_cate_progress.setVisibility(View.GONE);
			}
			
			@Override
			public void onLoadingCancelled(String imageUri, View view) {
				
			}
		});
		return view;
	}

	class ViewHolder {
		TextView tv_cate_name;
		ImageView iv_cate_img;
		ProgressBar iv_cate_progress;
	}
}
