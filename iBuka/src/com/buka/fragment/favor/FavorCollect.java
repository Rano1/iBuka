package com.buka.fragment.favor;

import java.util.ArrayList;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.buka.R;
import com.buka.adapter.FavorAdapter;
import com.buka.base.BaseFragment;
import com.buka.entity.FavorCollectList;
import com.buka.tools.Constants;

public class FavorCollect extends BaseFragment {
	private final static String TAG = "FavorCollect";
	private int favor_type = 0;
	private View parentView;
	private TextView favor_collect_nodata;
	private ListView favor_collect_listview;
	private FavorAdapter mAdapter;
	private ArrayList<FavorCollectList> favorcollectlist;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		Bundle args = getArguments();
		favor_type = (args == null ? Constants.FAVOR_COLLECT: args.getInt("favor_type", Constants.FAVOR_COLLECT));
		favorcollectlist = new ArrayList<FavorCollectList>();
		if(favor_type == Constants.FAVOR_COLLECT){
			for (int i = 0; i < Constants.IMAGES_FAVOR_COLLECT.length; i++) {
				FavorCollectList collect = new FavorCollectList();
				collect.setId(i);
				collect.setTitle("火影忍者" + i);
				collect.setImage(Constants.IMAGES_FAVOR_COLLECT[i]);
				collect.setLast_section(i + "章");
				favorcollectlist.add(collect);
			}
		}else{
			
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		parentView = LayoutInflater.from(activity).inflate(R.layout.frm_favor_collect,null);
		initView();
		Log.d(TAG, "favor_type =" + favor_type);
		return parentView;
	}

	private void initView() {
		favor_collect_nodata = (TextView) parentView
				.findViewById(R.id.favor_collect_nodata);
		favor_collect_listview = (ListView) parentView
				.findViewById(R.id.favor_collect_listview);
		mAdapter = new FavorAdapter(activity, favorcollectlist);
		favor_collect_listview.setAdapter(mAdapter);
		if (favorcollectlist.size() == 0) {
			favor_collect_nodata.setVisibility(View.VISIBLE);
		} else {
			favor_collect_nodata.setVisibility(View.INVISIBLE);
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
