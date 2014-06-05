package com.buka.entity;

public class RecomComic {
	public Integer id;
	public String title;
	public String section;
	public String img_url;
	public Integer img_width;
	public Integer img_height;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSection() {
		return section;
	}

	public void setSection(String section) {
		this.section = section;
	}

	public String getImg_url() {
		return img_url;
	}

	public void setImg_url(String img_url) {
		this.img_url = img_url;
	}

	public Integer getImg_width() {
		return img_width;
	}

	public void setImg_width(Integer img_width) {
		this.img_width = img_width;
	}

	public Integer getImg_height() {
		return img_height;
	}

	public void setImg_height(Integer img_height) {
		this.img_height = img_height;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

}
