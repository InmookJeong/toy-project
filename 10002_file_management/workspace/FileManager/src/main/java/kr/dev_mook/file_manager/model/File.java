package kr.dev_mook.file_manager.model;

import java.time.LocalDate;

/**
 * File 관리를 위한 Model Object
 * @author Inmook, Jeong
 *
 */
public class File {
	
	private Long fileId;
	private String name;
	private boolean isFile;
	private Long fileSize;
	private String extension;
	private String mimeType;
	private String path;
	private String parentPath;
	private Long parentFileId;
	private String userId;
	private LocalDate createDate;
	private LocalDate modifiedDate;
	private Long readCount;
	private String repositoryType;
	private String status;
	
	private File() {}

	public File(Long fileId, String name, boolean isFile, Long fileSize, String extension, String mimeType, String path,
			String parentPath, Long parentFileId, String userId, LocalDate createDate, LocalDate modifiedDate,
			Long readCount, String repositoryType, String status) {
		super();
		this.fileId = fileId;
		this.name = name;
		this.isFile = isFile;
		this.fileSize = fileSize;
		this.extension = extension;
		this.mimeType = mimeType;
		this.path = path;
		this.parentPath = parentPath;
		this.parentFileId = parentFileId;
		this.userId = userId;
		this.createDate = createDate;
		this.modifiedDate = modifiedDate;
		this.readCount = readCount;
		this.repositoryType = repositoryType;
		this.status = status;
	}

	public Long getFileId() {
		return fileId;
	}

	public void setFileId(Long fileId) {
		this.fileId = fileId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isFile() {
		return isFile;
	}

	public void setFile(boolean isFile) {
		this.isFile = isFile;
	}

	public Long getFileSize() {
		return fileSize;
	}

	public void setFileSize(Long fileSize) {
		this.fileSize = fileSize;
	}

	public String getExtension() {
		return extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}

	public String getMimeType() {
		return mimeType;
	}

	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getParentPath() {
		return parentPath;
	}

	public void setParentPath(String parentPath) {
		this.parentPath = parentPath;
	}

	public Long getParentFileId() {
		return parentFileId;
	}

	public void setParentFileId(Long parentFileId) {
		this.parentFileId = parentFileId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public LocalDate getCreateDate() {
		return createDate;
	}

	public void setCreateDate(LocalDate createDate) {
		this.createDate = createDate;
	}

	public LocalDate getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(LocalDate modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public Long getReadCount() {
		return readCount;
	}

	public void setReadCount(Long readCount) {
		this.readCount = readCount;
	}

	public String getRepositoryType() {
		return repositoryType;
	}

	public void setRepositoryType(String repositoryType) {
		this.repositoryType = repositoryType;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "File [fileId=" + fileId + ", name=" + name + ", isFile=" + isFile + ", fileSize=" + fileSize
				+ ", extension=" + extension + ", mimeType=" + mimeType + ", path=" + path + ", parentPath="
				+ parentPath + ", parentFileId=" + parentFileId + ", userId=" + userId + ", createDate=" + createDate
				+ ", modifiedDate=" + modifiedDate + ", readCount=" + readCount + ", repositoryType=" + repositoryType
				+ ", status=" + status + "]";
	}
	
	
}
