package com.buka.entity;

import java.io.Serializable;

/**
 * 漫画列表属性
 */
public class ComicEntity implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** 　漫画ID */
	public Integer mid;
	/** 　漫画NAME */
	public String mname;
	/** 　章节ID */
	public Integer cid;
	/** 　章节NAME */
	public String cname;
	/** 　章节号 */
	public Integer chapter;
	/** 　是否是推荐的 */
	public Boolean isrecom;
	/** 　是否是热门的 */
	public Boolean ishot;
	/** 　漫画封面对应的URL */
	public String cover_url;
	/** 　最后更新时间 */
	public String lastuptime;
	/** 　大分类 */
	public Integer cate;
	/** 　阅读时间 */
	public String readtime;
	/** 　作者 */
	public String author;
	/** 　描述 */
	public String describe;
	/** 　战斗力 */
	public Integer fighting;
	/** 　评分 */
	public Float score;
	/** 　吐槽数量 */
	public Integer comments;
	/** 　封面宽度 */
	public Integer cover_width;
	/** 　封面高度 */
	public Integer cover_height;

	public Integer getMid() {
		return mid;
	}

	public void setMid(Integer mid) {
		this.mid = mid;
	}

	public String getMname() {
		return mname;
	}

	public void setMname(String mname) {
		this.mname = mname;
	}

	public Integer getCid() {
		return cid;
	}

	public void setCid(Integer cid) {
		this.cid = cid;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public Integer getChapter() {
		return chapter;
	}

	public void setChapter(Integer chapter) {
		this.chapter = chapter;
	}

	public Boolean getIsrecom() {
		return isrecom;
	}

	public void setIsrecom(Boolean isrecom) {
		this.isrecom = isrecom;
	}

	public Boolean getIshot() {
		return ishot;
	}

	public void setIshot(Boolean ishot) {
		this.ishot = ishot;
	}

	public String getCover_url() {
		return cover_url;
	}

	public void setCover_url(String cover_url) {
		this.cover_url = cover_url;
	}

	public String getLastuptime() {
		return lastuptime;
	}

	public void setLastuptime(String lastuptime) {
		this.lastuptime = lastuptime;
	}

	public Integer getCate() {
		return cate;
	}

	public void setCate(Integer cate) {
		this.cate = cate;
	}

	public String getReadtime() {
		return readtime;
	}

	public void setReadtime(String readtime) {
		this.readtime = readtime;
	}

	public Integer getCover_width() {
		return cover_width;
	}

	public void setCover_width(Integer cover_width) {
		this.cover_width = cover_width;
	}

	public Integer getCover_height() {
		return cover_height;
	}

	public void setCover_height(Integer cover_height) {
		this.cover_height = cover_height;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getDescribe() {
		return describe;
	}

	public void setDescribe(String describe) {
		this.describe = describe;
	}

	public Integer getFighting() {
		return fighting;
	}

	public void setFighting(Integer fighting) {
		this.fighting = fighting;
	}

	public Float getScore() {
		return score;
	}

	public void setScore(Float score) {
		this.score = score;
	}

	public Integer getComments() {
		return comments;
	}

	public void setComments(Integer comments) {
		this.comments = comments;
	}

}
