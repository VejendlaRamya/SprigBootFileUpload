package com.portal.app.dto;

import java.util.Date;

public class FileDto {
private Integer id;
	private String name;
	private String url;
	private String type;
	private long size;
	
	public FileDto(String name,String type, long size,Integer id) {
		super();
		this.name = name;
	
		this.type = type;
		this.size = size;
		this.id=id;
	}
	public FileDto(String filename) {
		this.name=filename;
		// TODO Auto-generated constructor stub
	}
	public FileDto(String filename, Integer id2,String fileSignedBy,String updateOn) {
		this.name=filename;
		this.size=id2;
		this.url=fileSignedBy;
		this.type=updateOn;
		// TODO Auto-generated constructor stub
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public long getSize() {
		return size;
	}
	public void setSize(long size) {
		this.size = size;
	}
	
}
