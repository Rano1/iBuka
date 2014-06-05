package com.buka.adapter;

import com.buka.R;
import com.buka.tools.BaseTools;
import com.buka.tools.Constants;
import com.buka.tools.Options;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import android.app.Activity;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.TextView;

/**
 * 游戏中心、热门连载 的适配器
 */
public class RecomAdapter extends BaseAdapter {
	protected ImageLoader imageLoader = ImageLoader.getInstance();
	public String[] imageUrls;
	public Activity activity;
	private static LayoutInflater inflater = null;
	DisplayImageOptions options;
	public int type;
	
	public RecomAdapter(Activity activity, String[] imageUrls  , int type) {
		this.imageUrls = imageUrls;
		this.activity = activity;
		this.type = type;
		inflater=LayoutInflater.from(activity);
		options = Options.getListOptions();
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
			view = inflater.inflate(R.layout.list_recom_item, parent,false);
			holder = new ViewHolder();
			assert view != null;
			holder.recom_item_image = (ImageView) view.findViewById(R.id.recom_item_image);
			holder.recom_item_title = (TextView) view.findViewById(R.id.recom_item_title);
			holder.recom_item_section = (TextView) view.findViewById(R.id.recom_item_section);
			holder.recom_item_progress = (ProgressBar) view.findViewById(R.id.recom_item_progress);
			holder.btn_recom_item = (Button) view.findViewById(R.id.btn_recom_item);
			view.setTag(holder);
		} else {
			holder = (ViewHolder) view.getTag();
		}
		imageLoader.displayImage(imageUrls[position],  holder.recom_item_image, options , new ImageLoadingListener() {
			
			@Override
			public void onLoadingStarted(String imageUri, View view) {
				// 这儿初先初始化出来image所占的位置的大小，先把瀑布流固定住，这样瀑布流就不会因为图片加载出来后大小变化了
				LayoutParams lp = (LayoutParams) holder.recom_item_image.getLayoutParams();
				// 多屏幕适配
				int dWidth = BaseTools.getwidthPixels(activity) - BaseTools.dip2px(activity, 12);
				int dHeight = BaseTools.getheightPixels(activity);
				float wscale = dWidth / 2.0f;
				float hscale = BaseTools.dip2px(activity, 100);
				lp.width = (int) (wscale);
				lp.height = (int) (hscale);
				holder.recom_item_image.setLayoutParams(lp);
				holder.btn_recom_item.setLayoutParams(lp);
				
				
				holder.recom_item_progress.setProgress(0);
				holder.recom_item_progress.setVisibility(View.VISIBLE);
				holder.recom_item_image.setVisibility(View.INVISIBLE);
			}
			
			@Override
			public void onLoadingFailed(String imageUri, View view,
					FailReason failReason) {
				holder.recom_item_progress.setVisibility(View.GONE);
				holder.recom_item_image.setVisibility(View.VISIBLE);
			}
			
			@Override
			public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
				holder.recom_item_progress.setVisibility(View.GONE);
				holder.recom_item_image.setVisibility(View.VISIBLE);
			}
			
			@Override
			public void onLoadingCancelled(String imageUri, View view) {
				// TODO Auto-generated method stub
				
			}
		});
		holder.btn_recom_item.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if(type == Constants.RECOM_HOT){
					
				}else{
					
				}
			}
		});
		if(type == Constants.RECOM_HOT){
			holder.recom_item_section.setVisibility(View.VISIBLE);
		}else{
			holder.recom_item_section.setVisibility(View.GONE);
		}
		return view;
	}

	class ViewHolder {
		ImageView recom_item_image;
		TextView recom_item_title;
		TextView recom_item_section;
		ProgressBar recom_item_progress;
		Button btn_recom_item;
	}
}
