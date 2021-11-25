package com.portal.app.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
@Entity
public class FileDao {
	 @Id
	    @GeneratedValue(strategy= GenerationType.IDENTITY)
	private Integer id;
	 @Column
	private String filename;
	 @Column
	private String uploadBy;
	 @Column
	private String signedBy;
	 @Column
	private Boolean signedStatus;
	 @Column
	 @Lob
	private byte[] fileData;
	 @Column
	private Date uploadDateTime;
	 @Column
		private String fileType;
	 public FileDao(Integer id,String filename, String signedBy, Boolean signedStatus, byte[] fileData, Date uploadDateTime,
			String fileType) {
		super();
		this.id=id;
		this.filename = filename;
		this.signedBy = signedBy;
		this.signedStatus = signedStatus;
		this.fileData = fileData;
		this.uploadDateTime = uploadDateTime;
		this.fileType = fileType;
	}
	 public FileDao(String filename, String signedBy, Boolean signedStatus, byte[] fileData, Date uploadDateTime,
				String fileType) {
			super();
		
			this.filename = filename;
			this.signedBy = signedBy;
			this.signedStatus = signedStatus;
			this.fileData = fileData;
			this.uploadDateTime = uploadDateTime;
			this.fileType = fileType;
		}
	public FileDao(){
		 
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
	public FileDao(String filename,  String signedBy,String uploadBy, Boolean signedStatus, byte[] fileData,
			Date uploadDateTime, String fileType) {
		super();
		this.filename = filename;
		this.uploadBy = uploadBy;
		this.signedBy = signedBy;
		this.signedStatus = signedStatus;
		this.fileData = fileData;
		this.uploadDateTime = uploadDateTime;
		this.fileType = fileType;
	}
	public FileDao(Integer id, String filename, String uploadBy, String signedBy, Boolean signedStatus, byte[] fileData,
			Date uploadDateTime, String fileType) {
		super();
		this.id = id;
		this.filename = filename;
		this.uploadBy = uploadBy;
		this.signedBy = signedBy;
		this.signedStatus = signedStatus;
		this.fileData = fileData;
		this.uploadDateTime = uploadDateTime;
		this.fileType = fileType;
	}
	

	
}
