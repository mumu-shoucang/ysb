package com.n.ysb.service.system.vo;

import java.util.ArrayList;
import java.util.List;

public class Menu {

	private String code, name, url;
	private List<Menu> subMenus;
	
	public Menu() {
	}
	
	public Menu(String code, String name, String url) {
		this.code = code;
		this.name = name;
		this.url = url;
		this.subMenus = new ArrayList<>();
	}

	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Menu> getSubMenus() {
		return subMenus;
	}
	public void setSubMenus(List<Menu> subMenus) {
		this.subMenus = subMenus;
	}
	
}
