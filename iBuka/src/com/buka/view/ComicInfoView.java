package com.buka.view;

import android.view.View.OnClickListener;

import com.buka.R;
import com.buka.entity.ComicEntity;
import com.nostra13.universalimageloader.core.ImageLoader;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
/** 
 * 漫画相关信息类
 * */
public class ComicInfoView extends RelativeLayout implements OnClickListener{
	private ImageView detailCover;
	private TextView detailTitle;
	private Button detailFav;
	private Button btn_comic_dir_read;
	private TextView detailPopular;
	private TextView detailAuthor;
	private TextView comic_detail_info_Desc;
	private ImageView comic_detail_info_DescSwitcher;
	LinearLayout layout_comic_detail_info_Desc;
	/** 判断描述信息是否展开 */
	Boolean detail_intr_close_status = true;
	ImageLoader imageLoader = ImageLoader.getInstance();
	
	public ComicInfoView(Context context) {
		super(context);
		init(context);
	}

	public ComicInfoView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}

	public ComicInfoView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init(context);
	}
	
	public void init(Context context){
		View view_info = LayoutInflater.from(context).inflate(R.layout.view_comic_detail_info, null);
		detailCover = (ImageView)view_info.findViewById(R.id.detailCover);
		detailTitle = (TextView)view_info.findViewById(R.id.detailTitle);
		detailFav = (Button)view_info.findViewById(R.id.detailFav);
		btn_comic_dir_read = (Button)view_info.findViewById(R.id.btn_comic_dir_read);
		detailPopular = (TextView)view_info.findViewById(R.id.detailPopular);
		detailAuthor = (TextView)view_info.findViewById(R.id.detailAuthor);
		comic_detail_info_Desc = (TextView)view_info.findViewById(R.id.comic_detail_info_Desc);
		comic_detail_info_DescSwitcher = (ImageView)view_info.findViewById(R.id.comic_detail_info_DescSwitcher);
		layout_comic_detail_info_Desc = (LinearLayout)view_info.findViewById(R.id.layout_comic_detail_info_Desc);
		btn_comic_dir_read.setOnClickListener(this);
		layout_comic_detail_info_Desc.setOnClickListener(this);
		addView(view_info);
	}
	
	/** 设置相关信息 */
	public void setInfo(ComicEntity comic){
		imageLoader.displayImage(comic.getCover_url(), detailCover);
		detailTitle.setText(comic.getCname());
		
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_comic_dir_read:
//			startActivity(new Intent(getApplicationContext(),
//			ComicReadActivity.class));
			break;
		case R.id.layout_comic_detail_info_Desc:
			if (detail_intr_close_status) {
				comic_detail_info_DescSwitcher.setImageResource(R.drawable.detail_intr_close);
				comic_detail_info_Desc.setMaxLines(100);
			} else {
				comic_detail_info_DescSwitcher
						.setImageResource(R.drawable.detail_intr_expand);
				comic_detail_info_Desc.setMaxLines(2);
			}
			detail_intr_close_status = !detail_intr_close_status;
			break;
		default:
			break;
		}
	}
}
