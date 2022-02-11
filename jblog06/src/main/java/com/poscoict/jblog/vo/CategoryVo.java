package com.poscoict.jblog.vo;

public class CategoryVo {

	private Long no;
	private String name;
	private String description;
	private String blog_id;
	private int post_cnt;
	
	public int getPost_cnt() {
		return post_cnt;
	}
	public void setPost_cnt(int post_cnt) {
		this.post_cnt = post_cnt;
	}
	public Long getNo() {
		return no;
	}
	public void setNo(Long no) {
		this.no = no;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getBlog_id() {
		return blog_id;
	}
	public void setBlog_id(String blog_id) {
		this.blog_id = blog_id;
	}
	
	@Override
	public String toString() {
		return "CategoryVo [no=" + no + ", name=" + name + ", description=" + description + ", blog_id=" + blog_id
				+ ", post_cnt=" + post_cnt + "]";
	}
	
	

}
