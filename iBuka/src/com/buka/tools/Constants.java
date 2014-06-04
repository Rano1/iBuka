package com.buka.tools;

import java.util.ArrayList;

import com.buka.entity.CateEntity;

public class Constants {
	
	public static ArrayList<CateEntity> getCateList(){
		ArrayList<CateEntity> list = new ArrayList<CateEntity>();
		for(int i =0;i< IMAGES_CATE.length ; i++){
			CateEntity cate1 = new CateEntity("" + i, "title" + i, IMAGES_CATE[i]);
			list.add(cate1);
		}
		return list;
	}
	
	public static final String[] IMAGES_CATE = new String[] {
		"http://img0.imgtn.bdimg.com/it/u=3529685384,1707880994&fm=21&gp=0.jpg",
		"http://img1.imgtn.bdimg.com/it/u=2398049347,2383359376&fm=116&gp=0.jpg",
		"http://img1.imgtn.bdimg.com/it/u=2398049347,2383359376&fm=116&gp=0.jpg",
		"http://img1.imgtn.bdimg.com/it/u=2398049347,2383359376&fm=116&gp=0.jpg",
		"http://img1.imgtn.bdimg.com/it/u=2398049347,2383359376&fm=116&gp=0.jpg",
		"http://img1.imgtn.bdimg.com/it/u=2398049347,2383359376&fm=116&gp=0.jpg",
		"http://img1.imgtn.bdimg.com/it/u=2398049347,2383359376&fm=116&gp=0.jpg",
		"http://img1.imgtn.bdimg.com/it/u=2398049347,2383359376&fm=116&gp=0.jpg",
		"http://img1.imgtn.bdimg.com/it/u=2398049347,2383359376&fm=116&gp=0.jpg",
		"http://img1.imgtn.bdimg.com/it/u=2398049347,2383359376&fm=116&gp=0.jpg"};
}
