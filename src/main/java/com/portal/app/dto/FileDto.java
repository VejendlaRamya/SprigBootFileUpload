package com.portal.app.dto;

import java.util.Date;

import com.portal.app.model.User;

public class FileDto {
	private Integer id;
	private String filename;
	private String uploadBy;
	private String signedBy;
	private Boolean signedStatus;
	private Date uploadDateTime;
	private User user;
	private String status;
	private String comments;

	public Integer getId() {
		return id;
	}

	public FileDto(Integer id, String filename, String uploadBy, String signedBy, Boolean signedStatus,
			Date uploadDateTime, User user, String status, String comments) {
		super();
		this.id = id;
		this.filename = filename;
		this.uploadBy = uploadBy;
		this.signedBy = signedBy;
		this.signedStatus = signedStatus;
		this.uploadDateTime = uploadDateTime;
		this.user = user;
		this.status = status;
		this.comments = comments;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getUploadBy() {
		return uploadBy;
	}

	public void setUploadBy(String uploadBy) {
		this.uploadBy = uploadBy;
	}

	public String getSignedBy() {
		return signedBy;
	}

	public void setSignedBy(String signedBy) {
		this.signedBy = signedBy;
	}

	public Boolean getSignedStatus() {
		return signedStatus;
	}

	public void setSignedStatus(Boolean signedStatus) {
		this.signedStatus = signedStatus;
	}

	public Date getUploadDateTime() {
		return uploadDateTime;
	}

	public void setUploadDateTime(Date uploadDateTime) {
		this.uploadDateTime = uploadDateTime;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

}
