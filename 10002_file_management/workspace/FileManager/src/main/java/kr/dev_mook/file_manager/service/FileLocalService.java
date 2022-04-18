package kr.dev_mook.file_manager.service;

import java.util.List;

import kr.dev_mook.file_manager.model.File;

public interface FileLocalService {
	
	/**
	 * 파일 생성
	 * @param fileId
	 * @return
	 */
	public File createFile(File file);
	
	/**
	 * 폴더 생성
	 * @param fileId
	 * @return
	 */
	public File createFolder(File folder);
	
	/**
	 * 파일 정보 수정
	 * @param file
	 * @return
	 */
	public File updateFile(File file);
	
	/**
	 * 폴더 정보 수정
	 * @param file
	 * @return
	 */
	public File updateFolder(File file);
	
	/**
	 * 파일의 아이디를 통해 파일 정보 읽기
	 * @param fileId
	 * @return
	 */
	public String readFile(Long fileId);
	
	/**
	 * 파일의 절대 경로를 통해 파일 정보 읽기
	 * @param absoluteFilePath
	 * @return
	 */
	public String readFile(String absoluteFilePath);
	
	/**
	 * 파일을 생성할 폴더 경로와 파일 이름을 통해 이미 파일이 존재하는지 확인
	 * @param targetFolderPath
	 * @param fileName
	 * @return
	 */
	public boolean hasFile(String targetFolderPath, String fileName);
	
	/**
	 * 생성할 파일의 절대 경로를 통해 파일이 이미 존재하는지 확인
	 * @param absoluteFilePath
	 * @return
	 */
	public boolean hasFile(String absoluteFilePath);
	
	/**
	 * 폴더의 아이디를 통해 폴더 정보 읽기
	 * @param fileId
	 * @return
	 */
	public File readFolder(Long fileId);
	
	/**
	 * 폴더의 절대 경로를 통해 폴더 정보 읽기
	 * @param absoluteFolderPath
	 * @return
	 */
	public File readFolder(String absoluteFolderPath);
	
	/**
	 * 폴더를 생성할 폴더 경로와 폴더 이름을 통해 이미 폴더가 존재하는지 확인
	 * @param targetFolderPath
	 * @param folderName
	 * @return
	 */
	public boolean hasFolder(String targetFolderPath, String folderName);
	
	/**
	 * 생성할 폴더의 절대 경로를 통해 폴더가 존재하는지 확인
	 * @param absolteFolderPath
	 * @return
	 */
	public boolean hasFolder(String absolteFolderPath);
	
	/**
	 * 폴더의 아이디를 통해 폴더 안에 있는 파일의 갯수 가져오기
	 * $nbsp; - 하위 폴더 내의 정보는 제외(자식 파일만 계산)
	 * @param fileId
	 * @return
	 */
	public int countFiles(Long folderId);
	
	/**
	 * 폴더의 절대 경로를 통해 폴더 안에 있는 파일의 갯수 가져오기
	 * $nbsp; - 하위 폴더 내의 정보는 제외(자식 파일만 계산)
	 * @param absoluteFolderPath
	 * @return
	 */
	public int countFiles(String absoluteFolderPath);
	
	/**
	 * 폴더의 아이디를 통해 폴더 내 파일 목록 가져오기
	 * @param folderId
	 * @return
	 */
	public List<File> getFiles(Long folderId);
	
	/**
	 * 폴더의 절대 경로를 통해 폴더 내 파일 목록 가져오기
	 * @param absoluteFolderPath
	 * @return
	 */
	public List<File> getFiles(String absoluteFolderPath);
	
	/**
	 * 폴더의 아이디를 통해 폴더 안에 있는 폴더의 갯수 가져오기
	 * $nbsp; - 하위 폴더 내의 정보는 제외(자식 폴더만 계산)
	 * @param folder
	 * @return
	 */
	public int countFolders(Long folderId);
	
	/**
	 * 폴더의 절대 경로를 통해 폴더 안에 있는 폴더의 갯수 가져오기
	 * $nbsp; - 하위 폴더 내의 정보는 제외(자식 폴더만 계산)
	 * @param absoluteFolderPath
	 * @return
	 */
	public int countFolders(String absoluteFolderPath);
	
	/**
	 * 폴더의 아이디를 통해 폴더 내 폴더 목록 가져오기
	 * @param folderId
	 * @return
	 */
	public List<File> getFolders(Long folderId);
	
	/**
	 * 폴더의 절대 경로를 통해 폴더 내 폴더 목록 가져오기
	 * @param absoluteFolderPath
	 * @return
	 */
	public List<File> getFolders(String absoluteFolderPath);
	
	/**
	 * 파일의 아이디를 통해 파일 삭제
	 * @param fileId
	 * @return
	 */
	public File deleteFile(Long fileId);
	
	/**
	 * 파일의 절대 경로를 통해 파일 삭제
	 * @param absoluteFilePath
	 * @return
	 */
	public File deleteFile(String absoluteFilePath);
	
	/**
	 * 폴더의 아이디를 통해 폴더 삭제
	 * $nbsp; - 폴더 삭제 시 하위에 생성된 폴더와 파일도 모두 삭제
	 * @param folderId
	 * @return
	 */
	public File deleteFolder(Long folderId);
	
	/**
	 * 폴더의 절대 경로를 통해 폴더 삭제
	 * $nbsp; - 폴더 삭제 시 하위에 생성된 폴더와 파일도 모두 삭제
	 * @param absoluteFolderPath
	 * @return
	 */
	public File deleteFolder(String absoluteFolderPath);
	
	/**
	 * 파일 객체를 통한 파일 업르도
	 * @param file
	 * @return
	 */
	public File uploadFile(File file);
	
	/**
	 * 파일 아이디를 통한 파일 다운로드
	 * @param fileId
	 * @return
	 */
	public File downloadFile(Long fileId);
	
	/**
	 * 파일의 절대 경로를 통한 파일 다운로드
	 * @param absoluteFilePath
	 * @return
	 */
	public File downloadFile(String absoluteFilePath);
	
	/**
	 * 폴더를 압축 파일로 만들기
	 * @param folder
	 * @return
	 */
	public File zipFile(File folder);
	
	/**
	 * 절대 경로에 있는 폴더를 압축 파일로 만들기
	 * @param absoluteFolderPath
	 * @return
	 */
	public File zipFile(String absoluteFolderPath);
	
	/**
	 * 파일 객체를 통한 압축파일 해제
	 * @param file
	 * @return
	 */
	public File unzipFile(File file);
	
	/**
	 * 파일 아이디에 대응하는 파일을 타깃 폴더에 복사
	 * @param fileId
	 * @param targetFolder
	 * @return
	 */
	public File copyFile(Long fileId, String targetFolder);
	
	/**
	 * 파일의 절대 경로에 대응하는 파일을 타깃 폴더에 복사
	 * @param absoluteFilePath
	 * @param targetFolder
	 * @return
	 */
	public File copyFile(String absoluteFilePath, String targetFolder);
	
	/**
	 * 폴더 아이디에 대응하는 폴더를 타깃 폴더에 복사
	 * &nbsp; - 하위 폴더의 모든 데이터를 같이 복사
	 * @param folderId
	 * @param targetFolder
	 * @return
	 */
	public File copyFolder(Long folderId, String targetFolder);
	
	/**
	 * 폴더의 절대 경로에 대응하는 폴더를 타깃 폴더에 복사
	 * &nbsp; - 하위 폴더의 모든 데이터를 같이 복사
	 * @param absolutefolderPath
	 * @param targetFolder
	 * @return
	 */
	public File copyFolder(String absolutefolderPath, String targetFolder);
	
	/**
	 * 폴더의 아이디에 대응하는 폴더를 타깃 폴더로 이동
	 * @param fileId
	 * @param targetFolder
	 * @return
	 */
	public File moveFile(Long fileId, String targetFolder);
	
	/**
	 * 폴더의 절대 경로에 대응하는 폴더를 타깃 폴더로 이동
	 * @param absoluteFilePath
	 * @param targetFolder
	 * @return
	 */
	public File moveFile(String absoluteFilePath, String targetFolder);
	
	/**
	 * 폴더의 아이데에 대응하는 폴더를 타깃 폴더로 이동
	 * &nbsp; - 하위 폴더의 모든 데이터를 같이 이동
	 * @param folderId
	 * @param targetFolder
	 * @return
	 */
	public File moveFolder(Long folderId, String targetFolder);
	
	/**
	 * 폴더의 절대 경로에 대응하는 폴더를 타깃 폴더로 이동
	 * &nbsp; - 하위 폴더의 모든 데이터를 같이 이동
	 * @param absolutefolderPath
	 * @param targetFolder
	 * @return
	 */
	public File moveFolder(String absolutefolderPath, String targetFolder);
	
	/**
	 * 파일 아이디에 대응하는 파일의 이름 수정
	 * @param fileId
	 * @param newName
	 * @return
	 */
	public File renameFile(Long fileId, String newName);
	
	/**
	 * 파일의 절대 경로에 대응하는 파일의 이름 수정
	 * @param absoluteFilePath
	 * @param newName
	 * @return
	 */
	public File renameFile(String absoluteFilePath, String newName);
	
	/**
	 * 폴더 아이디에 대응하는 폴더의 이름 수정
	 * @param folderId
	 * @param newName
	 * @return
	 */
	public File renameFolder(Long folderId, String newName);
	
	/**
	 * 폴더의 절대 경로에 대응하는 폴더의 이름 수정
	 * @param absoluteFolderPath
	 * @param newName
	 * @return
	 */
	public File renameFolder(String absoluteFolderPath, String newName);

}
