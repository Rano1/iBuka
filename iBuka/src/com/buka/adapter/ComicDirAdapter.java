package com.buka.adapter;

import java.util.ArrayList;

import com.buka.R;
import com.buka.entity.ComicChapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;

public class ComicDirAdapter extends BaseAdapter {
	private LayoutInflater inflater = null;
	ArrayList<ComicChapter> chapterList;

	public ComicDirAdapter(Context context, ArrayList<ComicChapter> chapterList) {
		inflater = LayoutInflater.from(context);
		this.chapterList = chapterList;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return chapterList == null ? 0 : chapterList.size();
	}

	@Override
	public ComicChapter getItem(int position) {
		if(chapterList !=null && chapterList.size() != 0){
			return chapterList.get(position);
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
		View view = convertView;
		ViewHolder holder;
		if(view ==null){
			holder = new ViewHolder();
			view = inflater.inflate(R.layout.view_comic_detail_dir_item, null);
			holder.btn_comic_detail_dir = (Button)view.findViewById(R.id.btn_comic_detail_dir);
			view.setTag(holder);
		}else{
			holder = (ViewHolder) view.getTag();
		}
		ComicChapter chapter = getItem(position);
		holder.btn_comic_detail_dir.setText(chapter.getCname());
		return view;
	}

	class ViewHolder{
		public Button btn_comic_detail_dir;
	}
}
