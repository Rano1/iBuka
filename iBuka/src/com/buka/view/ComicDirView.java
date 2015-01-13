package com.buka.view;

import java.util.ArrayList;

import com.buka.ComicReadActivity;
import com.buka.R;
import com.buka.adapter.ComicDirAdapter;
import com.buka.entity.ComicChapter;
import com.buka.tools.Constants;

import android.content.Context;
import android.content.Intent;
import android.renderscript.Sampler;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.SimpleAdapter;
/** 
 * 漫画详情目录列表View
 * */
public class ComicDirView extends LinearLayout {
	ArrayList<ComicChapter> chapterList;
	GridView grid_dir;
	private ComicDirAdapter mAdapter;
	Context context;
	
	public ComicDirView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}

	public ComicDirView(Context context) {
		super(context);
		init(context);
	}

	private void init(Context context) {
		this.context = context;
		View view_dir = LayoutInflater.from(context).inflate(R.layout.view_comic_detail_dir, null);
		grid_dir = (GridView)view_dir.findViewById(R.id.grid_dir);
		LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		addView(view_dir , params);
	}

	public void setInfo(int mid){
		chapterList = new ArrayList<ComicChapter>();
		chapterList = Constants.getChapter(getContext(), mid);
		if(chapterList != null && chapterList.size() != 0){
			mAdapter = new ComicDirAdapter(getContext(), chapterList);
			grid_dir.setAdapter(mAdapter);
			grid_dir.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> parent, View view,int position, long id) {
					ComicChapter chapter = mAdapter.getItem(position);
					Intent intent = new Intent(context,ComicReadActivity.class);
					intent.putExtra("cid", chapter.getCid());
					context.startActivity(intent);
				}
			});
		}
	}
}
