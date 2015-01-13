package com.buka.entity;

public class ComicContent {
	/** 漫画ID */
	public Integer mid;
	/** 章节ID */
	public Integer cid;
	/** 页码 */
	public Integer page;
	/** 总页码 */
	public Integer totalpages;
	/** 漫画URL */
	public String img_url;

	public Integer getMid() {
		return mid;
	}

	public void setMid(Integer mid) {
		this.mid = mid;
	}

	public Integer getCid() {
		return cid;
	}

	public void setCid(Integer cid) {
		this.cid = cid;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getTotalpages() {
		return totalpages;
	}

	public void setTotalpages(Integer totalpages) {
		this.totalpages = totalpages;
	}

	public String getImg_url() {
		return img_url;
	}

	public void setImg_url(String img_url) {
		this.img_url = img_url;
	}

}
