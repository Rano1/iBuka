package com.buka.adapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.buka.R;
import com.buka.entity.RecomComic;
import com.buka.tools.BaseTools;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

public class WaterfallAdapter extends BaseAdapter {

	ArrayList<RecomComic> comicList;
	Context context;
	private Drawable drawable;
	private int width_interval = (6 + 1) * 2;
	DisplayImageOptions options;

	public WaterfallAdapter(ArrayList<RecomComic> comicList, Context context) {
		this.comicList = comicList;
		this.context = context;
		drawable = context.getResources().getDrawable(R.drawable.ic_launcher);
		options = new DisplayImageOptions.Builder()
				// .showImageOnLoading(R.drawable.ic_launcher)
				.showImageForEmptyUri(R.drawable.ic_launcher)
				.showImageOnFail(R.drawable.ic_launcher).cacheInMemory(true)
				.cacheOnDisc(true).considerExifParams(true)
				// .displayer(new RoundedBitmapDisplayer(20))
				.build();
	}

	@Override
	public int getCount() {
		return comicList != null ? comicList.size() : 0;
	}

	@Override
	public RecomComic getItem(int arg0) {
		if (comicList != null && comicList.size() != 0) {
			return comicList.get(arg0);
		}
		return null;
	}

	@Override
	public long getItemId(int arg0) {
		return arg0;
	}

	@Override
	public View getView(final int position, View view, ViewGroup group) {
		final Holder holder;
		// 得到View
		if (view == null) {
			holder = new Holder();
			LayoutInflater inflater = LayoutInflater.from(context);
			view = inflater.inflate(R.layout.list_recom_wonderful_item, null);
			holder.ivIcon = (ImageView) view.findViewById(R.id.row_icon);
			holder.pbLoad = (ProgressBar) view.findViewById(R.id.pb_load);
			holder.recom_recommend_item_title = (TextView) view
					.findViewById(R.id.recom_recommend_item_title);
			holder.recom_recommend_item_section = (TextView) view
					.findViewById(R.id.recom_recommend_item_section);
			holder.button_item_img = (Button) view
					.findViewById(R.id.button_item_img);

			view.setTag(holder);
		} else {
			holder = (Holder) view.getTag();
		}
		final RecomComic recomcomic = getItem(position);
		String url = recomcomic.getImg_url();
		ImageLoader.getInstance().displayImage(url, holder.ivIcon, options,
				new ImageLoadingListener() {

					final List<String> displayedImages = Collections
							.synchronizedList(new LinkedList<String>());

					@Override
					public void onLoadingStarted(String imageUri, View view) {

						// 这儿初先初始化出来image所占的位置的大小，先把瀑布流固定住，这样瀑布流就不会因为图片加载出来后大小变化了
						LayoutParams lp = (LayoutParams) holder.ivIcon
								.getLayoutParams();
						// 多屏幕适配
						int dWidth = BaseTools.getwidthPixels(context)
								- BaseTools.dip2px(context, width_interval);
						int dHeight = BaseTools.getheightPixels(context);
						float wscale = dWidth / 2.0f;
						float hscale = dHeight / 2.0f;
						lp.width = (int) (wscale);
						lp.height = (int) (Float.valueOf(recomcomic
								.getImg_height()) * wscale / Float
								.valueOf(recomcomic.getImg_width()));
						// lp.height = (int) (wscale);
						// lp.width = (int) (hscale);
						holder.ivIcon.setLayoutParams(lp);
						holder.button_item_img.setLayoutParams(lp);
						// holder.ivIcon.setImageDrawable(drawable);
						holder.ivIcon.setVisibility(View.INVISIBLE);
						holder.pbLoad.setVisibility(View.VISIBLE);
					}

					@Override
					public void onLoadingFailed(String imageUri, View view,
							FailReason failReason) {
						String message = null;
						switch (failReason.getType()) {
						case IO_ERROR:
							message = "Input/Output error";
							break;
						case DECODING_ERROR:
							message = "can not be decoding";
							break;
						case NETWORK_DENIED:
							message = "Downloads are denied";
							break;
						case OUT_OF_MEMORY:
							message = "内存不足";
							Toast.makeText(context, message, Toast.LENGTH_SHORT)
									.show();
							break;
						case UNKNOWN:
							message = "Unknown error";
							Toast.makeText(context, message, Toast.LENGTH_SHORT)
									.show();
							break;
						}
						holder.pbLoad.setVisibility(View.GONE);
					}

					@Override
					public void onLoadingComplete(String imageUri, View view,
							Bitmap loadedImage) {
						holder.ivIcon.setVisibility(View.VISIBLE);
						holder.pbLoad.setVisibility(View.GONE);
						if (loadedImage != null) {
							ImageView imageView = (ImageView) view;
							boolean firstDisplay = !displayedImages
									.contains(imageUri);
							if (firstDisplay) {
								FadeInBitmapDisplayer.animate(imageView, 500);
								displayedImages.add(imageUri);
							}
						}
					}

					@Override
					public void onLoadingCancelled(String paramString,
							View paramView) {
					}
				});

		holder.recom_recommend_item_title.setText(recomcomic.getTitle());
		holder.recom_recommend_item_section.setText(recomcomic.getSection());
		holder.ivIcon.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				// TODO Auto-generated method stub
				Log.i("TAG", "image click");
			}
		});

		holder.button_item_img.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
//				Intent intent = new Intent(context, ComicDirActivity.class);
//				intent.putExtra("comic_id", recomcomic.getId());
//				context.startActivity(intent);
			}
		});
		return view;
	}

}

class Holder {
	public ImageView ivIcon;
	public ProgressBar pbLoad;
	public TextView recom_recommend_item_title;
	public TextView recom_recommend_item_section;
	public Button button_item_img;
}