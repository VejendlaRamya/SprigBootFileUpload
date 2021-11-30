package com.portal.app.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

@Entity
public class FileDao {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column
	private String filename;
	@Column
	private String uploadBy;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCommetns() {
		return commetns;
	}

	public void setCommetns(String commetns) {
		this.commetns = commetns;
	}

	@Column
	private String signedBy;
	@Column
	private Boolean signedStatus;
	@Column
	@Lob
	private byte[] fileData;
	@Column
	private Date uploadDateTime;
	@ManyToOne
	private User user;
	@Column
	private String status;
	@Column
	private String commetns;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Column
	private String fileType;

	public FileDao() {

	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public Integer getId() {
		return id;
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

	public byte[] getFileData() {
		return fileData;
	}

	public void setFileData(byte[] fileData) {
		this.fileData = fileData;
	}

	public Date getUploadDateTime() {
		return uploadDateTime;
	}

	public void setUploadDateTime(Date uploadDateTime) {
		this.uploadDateTime = uploadDateTime;
	}

	public FileDao(String filename, String signedBy, String uploadBy, Boolean signedStatus, byte[] fileData,
			Date uploadDateTime, String fileType, User user, String status, String commetns) {
		super();
		this.filename = filename;
		this.uploadBy = uploadBy;
		this.signedBy = signedBy;
		this.signedStatus = signedStatus;
		this.fileData = fileData;
		this.uploadDateTime = uploadDateTime;
		this.user = user;
		this.status = status;
		this.commetns = commetns;
		this.fileType = fileType;
	}

}
