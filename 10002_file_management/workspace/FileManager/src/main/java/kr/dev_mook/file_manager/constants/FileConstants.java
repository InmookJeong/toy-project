package kr.dev_mook.file_manager.constants;

import java.io.File;

/**
 * 파일 관련 Constants
 * @author Inmook, Jeong
 *
 */
public class FileConstants {
	
	// Root Folder
	public static final String WINDOWS_ROOT_FOLDER = "D:\\";
	public static final String LINUX_ROOT_FOLDER = "/";
	
	// 폴더 구분자
	public static final String FOLDER_SEPARATOR = File.pathSeparator;
	
	// 객체 타입
	public static final String TYPE_OBJECT_FILE = "File";
	public static final String TYPE_OBJECT_FOLDER = "Folder";
	
	// 이벤트 타입
	public static final String TYPE_EVENT_CREATE = "Create";
	public static final String TYPE_EVENT_DELETE = "Delete";
	public static final String TYPE_EVENT_RENAME = "Rename";
	public static final String TYPE_EVENT_MOVE = "Move";
	public static final String TYPE_EVENT_COPY = "Copy";
	public static final String TYPE_EVENT_UPLOAD = "Upload";
	public static final String TYPE_EVENT_DOWNLOAD = "Download";
	public static final String TYPE_EVENT_ZIP = "Zip";
	public static final String TYPE_EVENT_UNZIP = "Unzip";
	

}
