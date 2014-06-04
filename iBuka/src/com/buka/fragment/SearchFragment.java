package com.buka.fragment;

import java.util.ArrayList;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;

import com.buka.R;
import com.buka.adapter.CateAdapter;
import com.buka.base.BaseFragment;
import com.buka.entity.CateEntity;
import com.buka.tools.Constants;

public class SearchFragment extends BaseFragment {
	public GridView grid_cate;
	public EditText edt_search;
	public ImageView iv_searchBtn;
	public ImageView iv_searchClearBtn;
	private CateAdapter mCateAdapter;
	private ArrayList<CateEntity> cateList;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = LayoutInflater.from(activity).inflate(R.layout.frm_search,null);
		grid_cate = (GridView)view.findViewById(R.id.grid_cate);
		edt_search = (EditText)view.findViewById(R.id.edt_search);
		iv_searchBtn = (ImageView)view.findViewById(R.id.iv_searchBtn);
		iv_searchClearBtn = (ImageView)view.findViewById(R.id.iv_searchClearBtn);
		new Thread(){

			@Override
			public void run() {
				
				super.run();
			}
		}.start();
		setGridView();
		return view;
	}

	public void setGridView(){
		cateList = Constants.getCateList();
		mCateAdapter = new CateAdapter(activity, cateList);
		grid_cate.setAdapter(mCateAdapter);
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
