package kr.dev_mook.file_manager.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import kr.dev_mook.file_manager.constants.FileConstants;

/**
 * 파일 관리 Util
 * @author Inmook, Jeong
 */
public class MyFileUtil {
	
	private static final Logger _logger = LoggerFactory.getLogger(MyFileUtil.class);
	
	/*
	 * ##########################################################
	 * #####					관리 관련 기능					#####
	 * #####		1. 파일 또는 폴더 생성						#####
	 * #####		2. 파일 또는 폴더 삭제						#####
	 * #####		3. 파일 또는 폴더 이름 변경					#####
	 * #####		4. 파일 또는 폴더 이동						#####
	 * #####		5. 파일 또는 폴더 복사						#####
	 * #####		6. 파일 또는 폴더 정보 조회					#####
	 * #####		7. 파일 또는 폴더 갯수 조회					#####
	 * #####		8. 파일 또는 폴더 목록 조회					#####
	 * #####		9. 파일 내용 읽기							#####
	 * #####		10. 파일 내용 쓰기							#####
	 * #####		11. 파일 업로드								#####
	 * #####		12. 파일 다운로드							#####
	 * #####		13. 파일 또는 폴더 압축						#####
	 * #####		14. 파일 압축해제							#####
	 * ##########################################################
	 */
	
	/*
	 * ######################################
	 * #####	1. 파일 또는 폴더 생성		#####
	 * ######################################
	 */
	
	/**
	 * 파일 생성
	 * @param fileName : 파일 이름
	 * @param targetFolder : 파일을 생성할 폴더 경로
	 * @return : 생성된 파일 Object
	 * @throws Exception : 파일 생성 실패 시 오류 메시지 전송
	 */
	public File createFile(String fileName, String targetFolder) throws Exception {
		File file = new File(targetFolder + FileConstants.FOLDER_SEPARATOR + fileName);
		isFile(file);
		notExistFile(file);
		
		try {
			return _createFileOrFolder(file);
		} catch (IOException e) {
			throw _throwFailedException(file, FileConstants.TYPE_EVENT_CREATE, true);
		}
	}
	
	/**
	 * 폴더 생성
	 * @param folderName : 폴더 이름
	 * @param targetFolder : 폴더를 생성할 폴더 경로
	 * @return : 생성된 폴더 Object
	 * @throws Exception : 폴더 생성 실패 시 오류 메시지 전송
	 */
	public File createFolder(String folderName, String targetFolder) throws Exception {
		File folder = new File(targetFolder + FileConstants.FOLDER_SEPARATOR + folderName);
		isFolder(folder);
		notExistFolder(folder);
		
		try {
			return _createFileOrFolder(folder);
		} catch (IOException e) {
			throw _throwFailedException(folder, FileConstants.TYPE_EVENT_CREATE, false);
		}
	}
	
	/**
	 * 파일 또는 폴더 생성
	 * @param f : 생성할 파일 또는 폴더 Object
	 * @return : 생성된 파일 또는 폴더 Object
	 * @throws IOException : 파일 또는 폴더 생성 실패 시 예외 처리
	 */
	private File _createFileOrFolder(File f) throws IOException {
		FileUtils.touch(f);
		return f;
	}
	
	/*
	 * ######################################
	 * #####	2. 파일 또는 폴더 삭제		#####
	 * ######################################
	 */
	
	/**
	 * 파일 삭제
	 * @param absoluteFilePath : 삭제할 파일 경로
	 * @return : 삭제된 파일 Object
	 * @throws Exception : 파일 삭제 실패 시 오류 메시지 전송
	 */
	public File deleteFile(String absoluteFilePath) throws Exception {
		File file = new File(absoluteFilePath);
		existFile(file);
		isFile(file);
		
		try {
			FileUtils.forceDelete(Paths.get(absoluteFilePath).toFile());
			return file;
		} catch (IOException e) {
			throw _throwFailedException(file, FileConstants.TYPE_EVENT_DELETE, true);
		}
	}
	
	/**
	 * 폴더 삭제
	 * @param absoluteFolderPath : 삭제할 폴더 경로
	 * @return : 삭제된 폴더 Object
	 * @throws Exception : 폴더 삭제 실패 시 오류 메시지 전송
	 */
	public File deleteFolder(String absoluteFolderPath) throws Exception {
		File folder = new File(absoluteFolderPath);
		existFolder(folder);
		isFolder(folder);
		
		try {
			FileUtils.deleteDirectory(Paths.get(absoluteFolderPath).toFile());
			return folder;
		} catch (IOException e) {
			throw _throwFailedException(folder, FileConstants.TYPE_EVENT_DELETE, false);
		}
	}
	
	/*
	 * ######################################
	 * #####	3. 파일 또는 폴더 이름 변경	#####
	 * ######################################
	 */
	
	/**
	 * 파일 이름 변경
	 * @param renameFilePath : 이름을 변경할 파일의 경로
	 * @param newFileName : 변경할 파일의 이름
	 * @return : 변경된 파일 Object
	 * @throws Exception : 파일 이름 변경 실패 시 오류 메시지 전송
	 */
	public File renameFile(String renameFilePath, String newFileName) throws Exception {
		File oldFile = new File(renameFilePath);
		existFile(oldFile);
		isFile(oldFile);
		
		File newFile = new File(oldFile.getParent() + FileConstants.FOLDER_SEPARATOR + newFileName);
		isFile(newFile);
		notExistFile(newFile);
		
		try {
			FileUtils.moveFile(oldFile, newFile);
			return newFile;
		} catch (IOException e) {
			throw _throwFailedException(newFile, FileConstants.TYPE_EVENT_RENAME, true);
		}
	}
	
	/**
	 * 폴더 이름 변경
	 * @param renameFolderPath : 이름을 변경할 폴더의 경로
	 * @param newFolderName : 변경할 폴더의 이름
	 * @return : 변경된 폴더 Object
	 * @throws Exception : 폴더 이름 변경 실패 시 오류 메시지 전송
	 */
	public File renameFolder(String renameFolderPath, String newFolderName) throws Exception {
		File oldFolder = new File(renameFolderPath);
		existFolder(oldFolder);
		isFolder(oldFolder);
		
		File newFolder = new File(oldFolder.getParent() + FileConstants.FOLDER_SEPARATOR + newFolderName);
		isFolder(newFolder);
		notExistFolder(newFolder);
		
		try {
			FileUtils.moveDirectory(oldFolder, newFolder);
			return newFolder;
		} catch (IOException e) {
			throw _throwFailedException(newFolder, FileConstants.TYPE_EVENT_RENAME, false);
		}
	}
	
	/*
	 * ######################################
	 * #####	4. 파일 또는 폴더 이동		#####
	 * ######################################
	 */
	
	/**
	 * 파일 이동
	 * @param sourceFilePath : 이동시킬 파일의 경로
	 * @param targetFolderPath : 이동할 폴더 경로
	 * @return : 이동된 파일 Object
	 * @throws Exception : 파일 이동 실패 시 오류 메시지 전송
	 */
	public File moveFile(String sourceFilePath, String targetFolderPath) throws Exception {
		File sourceFile = new File(sourceFilePath);
		existFile(sourceFile);
		isFile(sourceFile);
		
		String sourceFileName = sourceFile.getName();
		File movedFile = new File(targetFolderPath + FileConstants.FOLDER_SEPARATOR + sourceFileName); 
		isFile(movedFile);
		notExistFile(movedFile);
		
		try {
			FileUtils.moveFileToDirectory(sourceFile, movedFile, true);
			return movedFile;
		} catch (IOException e) {
			throw _throwFailedException(movedFile, FileConstants.TYPE_EVENT_MOVE, true);
		}
	}
	
	/**
	 * 폴더 이동
	 * @param sourcefolderPath : 이동시킬 폴더의 경로
	 * @param targetFolderPath : 이동할 폴더 경로
	 * @return : 이동된 폴더 Object
	 * @throws Exception : 폴더 이동 실패 시 오류 메시지 전송
	 */
	public File moveFolder(String sourcefolderPath, String targetFolderPath) throws Exception {
		File sourceFolder = new File(sourcefolderPath);
		existFolder(sourceFolder);
		isFolder(sourceFolder);
		
		String sourceFolderName = sourceFolder.getName();
		File movedFolder = new File(targetFolderPath + FileConstants.FOLDER_SEPARATOR + sourceFolderName); 
		isFolder(movedFolder);
		notExistFolder(movedFolder);
		try {
			FileUtils.moveDirectoryToDirectory(sourceFolder, movedFolder, true);
			return movedFolder;
		} catch (IOException e) {
			throw _throwFailedException(movedFolder, FileConstants.TYPE_EVENT_MOVE, false);
		}
	}
	
	/*
	 * ######################################
	 * #####	5. 파일 또는 폴더 복사		#####
	 * ######################################
	 */
	
	/**
	 * 파일 복사
	 * @param sourceFilePath : 복사할 파일의 경로
	 * @param targetFolderPath : 복사된 파일을 저장할 폴더 경로
	 * @return : 복사된 파일 Object
	 * @throws Exception : 파일 복사 싪패 시 오류 메시지 전송
	 */
	public File copyFile(String sourceFilePath, String targetFolderPath) throws Exception {
		File sourceFile = new File(sourceFilePath);
		existFile(sourceFile);
		isFile(sourceFile);
		
		String sourceFileName = sourceFile.getName();
		File copiedFile = new File(targetFolderPath + FileConstants.FOLDER_SEPARATOR + sourceFileName); 
		isFile(copiedFile);
		notExistFile(copiedFile);
		
		try {
			FileUtils.copyFile(sourceFile, copiedFile);
			return copiedFile;
		} catch (IOException e) {
			throw _throwFailedException(copiedFile, FileConstants.TYPE_EVENT_COPY, true);
		}
	}
	
	/**
	 * 폴더 복사
	 * @param absolutefolderPath : 복사할 폴더의 경로
	 * @param targetFolderPath : 복사된 폴더를 저장할 폴더 경로
	 * @return : 복사된 폴더 Object
	 * @throws Exception : 폴더 복사 실패 시 오류 메시지 전송
	 */
	public File copyFolder(String absolutefolderPath, String targetFolderPath) throws Exception {
		File sourceFolder = new File(absolutefolderPath);
		existFolder(sourceFolder);
		isFolder(sourceFolder);
		
		String sourceFolderName = sourceFolder.getName();
		File copiedFolder = new File(targetFolderPath + FileConstants.FOLDER_SEPARATOR + sourceFolderName); 
		isFolder(copiedFolder);
		notExistFolder(copiedFolder);
		
		try {
			FileUtils.copyDirectory(sourceFolder, copiedFolder);
			return copiedFolder;
		} catch (IOException e) {
			throw _throwFailedException(copiedFolder, FileConstants.TYPE_EVENT_COPY, false);
		}
	}
	
	/*
	 * ######################################
	 * #####	6. 파일 또는 폴더 정보 조회	#####
	 * ######################################
	 */
	
	/**
	 * 파일 경로를 통해 파일 Object 가져오기
	 * @param filePath : 파일 경로
	 * @return : 조회된 파일 Object
	 * @throws Exception : 파일이 없거나 파일이 아닌 경우 오류 메시지 전송
	 */
	public File getFile(String filePath) throws Exception {
		File file = new File(filePath);
		existFile(file);
		isFile(file);
		return file;
	}
	
	/**
	 * 폴더 경로를 통해 폴더 Object 가져오기
	 * @param folderPath : 폴더 경로
	 * @return : 조회된 폴더 Object
	 * @throws Exception : 폴더가 없거나 폴더가 아닌 경우 오류 메시지 전송
	 */
	public File getFolder(String folderPath) throws Exception {
		File folder = new File(folderPath);
		existFolder(folder);
		isFolder(folder);
		return folder;
	}
	
	/*
	 * ######################################
	 * #####	7. 파일 또는 폴더 갯수 조회	#####
	 * ######################################
	 */
	
	/**
	 * 폴더 경로 내 파일 갯수 조회
	 * @param folderPath : 조회할 폴더 경로
	 * @return : 조회된 파일 갯수
	 * @throws Exception : 폴더 경로 조회 실패 시 오류 메시지 전송
	 */
	public int countFiles(String folderPath) throws Exception {
		File folder = new File(folderPath);
		return countFiles(folder);
	}
	
	/**
	 * 폴더 내 파일 갯수 조회
	 * @param folder : 조회할 폴더 Object
	 * @return : 조회된 파일 갯수
	 * @throws Exception : 폴더 경로 조회 실패 시 오류 메시지 전송
	 */
	public int countFiles(File folder) throws Exception {
		return _countFilesOrFolders(folder, true);
	}
	
	/**
	 * 폴더 경로 내 폴더 갯수 조회
	 * @param folderPath : 조회할 폴더 경로
	 * @return : 조회된 폴더 갯수
	 * @throws Exception : 폴더 경로 조회 실패 시 오류 메시지 전송
	 */
	public int countFolders(String folderPath) throws Exception {
		File folder = new File(folderPath);
		return countFolders(folder);
	}
	
	/**
	 * 폴더 내 폴더 갯수 조회
	 * @param folder : 조회할 폴더 Object
	 * @return : 조회된 폴더 갯수
	 * @throws Exception : 폴더 경로 조회 실패 시 오류 메시지 전송
	 */
	public int countFolders(File folder) throws Exception {
		return _countFilesOrFolders(folder, false);
	}
	
	/**
	 * 폴더 내 파일 또는 폴더 갯수 조회
	 * @param folder : 조회할 폴더 Object
	 * @param isFile : 
	 * @return
	 * @throws Exception
	 */
	private int _countFilesOrFolders(File folder, boolean isFile) throws Exception {
		existFolder(folder);
		isFolder(folder);
		
		int fileCount = 0, folderCount = 0;
		File[] files = folder.listFiles();
		for(File file : files) {
			if(file.isFile())
				fileCount += 1;
			else
				folderCount += 1;
		}
		return (isFile ? fileCount : folderCount);
	}
	
	/*
	 * ######################################
	 * #####	8. 파일 또는 폴더 목록 조회	#####
	 * ######################################
	 */
	
	/**
	 * 폴더 경로를 통해 하위 파일 목록 가져오기(자식 파일만 대상)
	 * @param folderPath : 폴더 경로
	 * @return : 조회된 파일 목록
	 * @throws Exception : 조회하는 폴더가 없거나 폴더가 아닌 경우 오류 메시지 전송
	 */
	public List<File> getFiles(String folderPath) throws Exception {
		File folder = new File(folderPath);
		return getFiles(folder);
	}
	
	/**
	 * 폴더 Object를 통해 하위 파일 목록 가져오기(자식 파일만 대상)
	 * @param folder : 조회할 폴더 Object
	 * @return
	 * @throws Exception : 조회하는 폴더가 없거나 폴더가 아닌 경우 오류 메시지 전송
	 */
	public List<File> getFiles(File folder) throws Exception {
		return _getFilesOrFolders(folder, true);
	}
	
	/**
	 * 폴더 경로를 통해 하위 폴더 목록 가져오기(자식 폴더만 대상)
	 * @param folderPath : 폴더 경로
	 * @return : 조회된 폴더 Object
	 * @throws Exception : 폴더가 없거나 폴더가 아닌 경우 오류 메시지 전송
	 */
	public List<File> getFolders(String folderPath) throws Exception {
		File folder = new File(folderPath);
		return getFolders(folder);
	}
	
	/**
	 * 폴더 Object를 통해 하위 폴더 목록 가져오기(자식 폴더만 대상)
	 * @param folder : 조회할 폴더 Object
	 * @return
	 * @throws Exception : 폴더가 없거나 폴더가 아닌 경우 오류 메시지 전송
	 */
	public List<File> getFolders(File folder) throws Exception {
		return _getFilesOrFolders(folder, false);
	}
	
	/**
	 * 폴더 Object를 통해 하위 파일 또는 폴더 목록 가져오기
	 * @param folder : 조회할 폴더 Object
	 * @param isFile : 조회할 목록이 파일인지 아닌지 여부
	 * @return : 조회된 하위 파일 또는 폴더 목록
	 * @throws Exception : 조회할 폴더가 없거나 폴더가 아닌 경우 오류 메시지 전송
	 */
	private List<File> _getFilesOrFolders(File folder, boolean isFile) throws Exception {
		existFolder(folder);
		isFolder(folder);
		List<File> files = new ArrayList<File>();
		File[] fileArray = folder.listFiles();
		for(File f : fileArray) {
			if(f.isFile() == isFile) files.add(f);
		}
		return files;
	}
	
	/*
	 * ######################################
	 * #####	9. 파일 내용 읽기			#####
	 * ######################################
	 */
	
	/**
	 * 파일 경로를 통해 파일 내용 읽기
	 * @param filePath : 읽기 대상 파일 경로
	 * @return : 파일에 작성된 내용
	 * @throws Exception : 파일이 없거나 파일이 아닌 경우 오류 메시지 출력
	 */
	public String read(String filePath) throws Exception {
		File file = new File(filePath);
		return read(file);
	}
	
	/**
	 * 파일 Object를 통해 파일 내용 읽기
	 * @param file : 읽기 대상 파일 Object
	 * @return : 파일에 작성된 내용
	 * @throws Exception : 파일이 없거나 파일이 아닌 경우 오류 메시지 출력
	 */
	public String read(File file) throws Exception {
		existFile(file);
		isFile(file);
		return FileUtils.readFileToString(file, "UTF-8");
	}
	
	/*
	 * ######################################
	 * #####	10. 파일 내용 쓰기			#####
	 * ######################################
	 */
	
	/**
	 * 파일 경로를 통해 파일 내용 쓰기
	 * @param filePath : 작성 대상 파일 경로
	 * @param content : 작성할 내용
	 * @param override : 파일 내용 덮어쓰기 여부
	 * @return : 내용이 작성된 파일 Object
	 * @throws Exception : 파일이 없거나 파일이 아닌 경우 오류 메시지 출력
	 */
	public File write(String filePath, String content, boolean override) throws Exception {
		File file = new File(filePath);
		return write(file, content, override);
	}
	
	/**
	 * 파일 Object를 통해 파일 내용 쓰기
	 * @param file : 작성 대상 파일 Object
	 * @param content : 작성할 내용
	 * @param override : 파일 내용 덮어쓰기 여부
	 * @return : 내용이 작성된 파일 Object 
	 * @throws Exception : 파일이 없거나 파일이 아닌 경우 오류 메시지 출력
	 */
	public File write(File file, String content, boolean override) throws Exception {
		existFile(file);
		isFile(file);
		FileWriter fileWriter = new FileWriter(file, !override);
		fileWriter.write(content);
		fileWriter.flush();
		fileWriter.close();
		return file;
	}
	
	/*
	 * ######################################
	 * #####	11. 파일 업로드				#####
	 * ######################################
	 */
	
	public List<File> upload(MultipartHttpServletRequest multipartRequest, String uploadFolderPath, boolean override, boolean unzip) throws Exception {
		existFolder(new File(uploadFolderPath));
		List<File> uploadFiles = new ArrayList<File>();
		Map<String, MultipartFile> multipartFiles = multipartRequest.getFileMap();
		Iterator<Entry<String, MultipartFile>> fileIterator = multipartFiles.entrySet().iterator();
		MultipartFile multiFile;
		
		while(fileIterator.hasNext()) {
			Entry<String, MultipartFile> entry = fileIterator.next();
			multiFile = entry.getValue();
			String fileName = multiFile.getName();
			String fileNameWithoutExt = fileName.substring(0, fileName.lastIndexOf("."));
			String extension = fileName.substring(fileName.lastIndexOf(".")+1);
			// TODO 이름 중복 시 새로운 이름 만들어주기
			String uploadPath = uploadFolderPath + FileConstants.FOLDER_SEPARATOR + fileName;
			File uploadFile = new File(uploadPath);
			notExistFile(uploadFile);
			
			multiFile.transferTo(uploadFile);
			uploadFiles.add(uploadFile);
		}
		
		return uploadFiles;
	}
	
	/*
	 * ######################################
	 * #####	12. 파일 다운로드			#####
	 * ######################################
	 */
	
	/**
	 * 파일 다운로드
	 * @param response : 다운로드를 위한 HttpServletResponse
	 * @param filePath : 다운로드 할 파일의 경로
	 * @throws Exception : 다운로드할 파일이 없거나 다운로드 문제 발생 시 오류 메시지 전송
	 */
	public void download(HttpServletResponse response, String filePath) throws Exception {
		download(response, new File(filePath));
	}
	
	/**
	 * 파일 다운로드
	 * @param response : 다운로드를 위한 HttpServletResponse
	 * @param file : 다운로드 할 파일 Object
	 * @throws Exception : 다운로드할 파일이 없거나 다운로드 문제 발생 시 오류 메시지 전송
	 */
	public void download(HttpServletResponse response, File file) throws Exception {
		existFile(file);
		BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
		
		response.reset();
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/octet-stream");
		response.setContentLength((int) file.length());
		response.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(file.getName(), "UTF-8"));
		OutputStream out = response.getOutputStream();
		int i = 0;
		byte data[] = new byte[1024];
		while((i = bis.read(data)) != -1) {
			out.write(data, 0, i);
		}
		
		out.flush();
		out.close();
		bis.close();
	}
	
	/*
	 * ######################################
	 * #####	13. 파일 또는 폴더 압축		#####
	 * ######################################
	 */
	
	/**
	 * 파일 압축하기
	 * @param files : 압축할 파일 및 폴더 목록
	 * @param zipFileName : 압축 파일 이름
	 * @param zipFolderPath : 압축 파일이 저장될 폴더 경로
	 * @return : 압축된 파일 Object
	 * @throws Exception : 파일 및 폴더 압축 실패 시 오류 메시지 전송
	 */
	public File zip(List<File> files, String zipFileName, String zipFolderPath) throws Exception {
		File zipFile = new File(zipFolderPath + FileConstants.FOLDER_SEPARATOR + zipFileName + ".zip");
		ZipOutputStream zout = new ZipOutputStream(new FileOutputStream(zipFile));
		for(File file : files) {
			ZipEntry zipEntry = new ZipEntry(file.getName());
			zout.putNextEntry(zipEntry);
			
			FileInputStream fis = new FileInputStream(file);
			byte[] buffer = new byte[1024];
			int length;
			while((length = fis.read(buffer)) > 0) {
				zout.write(buffer, 0, length);
			}
			zout.closeEntry();
			fis.close();
		}
		zout.close();
		return zipFile;
	}
	
	/*
	 * ######################################
	 * #####	14. 파일 압축해제			#####
	 * ######################################
	 */
	
	/**
	 * 압축 파일 해제
	 * @param filePath : 압축 파일 경로
	 * @param unzipFolderPath : 압축 해제할 폴더 경로
	 * @throws Exception : 압축 해제할 폴더가 없거나 압축 해제할 파일이 이미 존재하는 경우 오류 메시지 전송
	 */
	public void unzip(String filePath, String unzipFolderPath) throws Exception {
		// TODO unzip 파일 중복 체크 필요
		File unzipFolder = new File(unzipFolderPath);
		File unzipFile = new File(filePath);
		String unzipFileName = unzipFile.getName();
		existFolder(unzipFolder);
		notExistFile(unzipFile);
		
		ZipInputStream zis = new ZipInputStream(new FileInputStream(unzipFile), Charset.forName("utf-8"));
		ZipEntry zipEntry = zis.getNextEntry();
		while(zipEntry != null) {
			String entryName = zipEntry.getName();
			File entryFile = Paths.get(unzipFolderPath, entryName).toFile();
			entryFile.getParentFile().mkdir();
			FileOutputStream fos = new FileOutputStream(entryFile);
			int length;
			byte[] buffer = new byte[1024];
			while ((length = zis.read(buffer)) > 0) {
				fos.write(buffer, 0, length);
			}
			fos.close();
			
			zipEntry = zis.getNextEntry();
		}
		zis.closeEntry();
		zis.close();
	}
	
	/*
	 * ##########################################################
	 * #####					검증 관련 기능					#####
	 * #####		1. 파일 또는 폴더가 존재하는지 검증				#####
	 * #####		2. 파일 또는 폴더가 존재하지 않는지 검증			#####
	 * #####		3. 파일인지 검증							#####
	 * #####		4. 폴더인지 검증							#####
	 * #####		5. 이벤트 실패 시 에러 메시지 전송				#####
	 * ##########################################################
	 */
	
	/**
	 * 파일이 존재하는지 검증
	 * @param file : 검증할 파일 Object
	 * @throws Exception : 파일이 존재하지 않는 경우 예외 메시지 전송
	 */
	public void existFile(File file) throws Exception {
		_checkExistFileOrFolder(file, true);
	}
	
	/**
	 * 폴더가 존재하는지 검증
	 * @param folder : 검증할 폴더 Object
	 * @throws Exception : 폴더가 존재하지 않는 경우 예외 메시지 전송
	 */
	public void existFolder(File folder) throws Exception {
		_checkExistFileOrFolder(folder, false);
	}
	
	/**
	 * 파일 또는 폴더가 존재하는지 검증
	 * @param f : 검증할 파일 또는 폴더 Object
	 * @param isFile : 검증할 객체가 파일인지 여부 확인
	 * @throws Exception : 파일 또는 폴더가 존재하지 않는 경우 예외 메시지 전송
	 */
	private void _checkExistFileOrFolder(File f, boolean isFile) throws Exception {
		String fPath = f.getAbsolutePath();
		String objectType = FileConstants.TYPE_OBJECT_FOLDER;
		if(isFile) objectType = FileConstants.TYPE_OBJECT_FILE;
		if(!f.exists()) {
			_logger.error("##### This " + objectType + " not exist. - Path : " + fPath);
			throw new Exception("##### This " + objectType + " not exist. - Path : " + fPath);
		}
	}
	
	/**
	 * 파일이 존재하지 않는지 검증
	 * @param file : 검증할 파일 Object
	 * @throws Exception : 파일이 존재하면 예외 메시지 전송
	 */
	public void notExistFile(File file) throws Exception {
		_checkNotExistFileOrFolder(file, true);
	}
	
	/**
	 * 폴더가 존재하지 않는지 검증
	 * @param folder : 검증할 폴더 Object
	 * @throws Exception : 폴더가 존재하면 예외 메시지 전송
	 */
	public void notExistFolder(File folder) throws Exception {
		_checkNotExistFileOrFolder(folder, false);
	}
	
	/**
	 * 파일 또는 폴더가 존재하지 않는지 검증
	 * @param f : 검증할 파일 또는 폴더 Object
	 * @param isFile : 검증할 객체가 파일인지 여부 확인
	 * @throws Exception : 파일 또는 펄도가 존재하는 경우 예외 메시지 전송
	 */
	private void _checkNotExistFileOrFolder(File f, boolean isFile) throws Exception {
		String fPath = f.getAbsolutePath();
		String objectType = FileConstants.TYPE_OBJECT_FOLDER;
		if(isFile) objectType = FileConstants.TYPE_OBJECT_FILE;
		if(f.exists()) {
			_logger.error("##### Already " + objectType + " exist. - Path : " + fPath);
			throw new Exception("##### Already " + objectType + " exist. - Path : " + fPath);
		}
	}
	
	/**
	 * 객체가 파일인지 검증
	 * @param file : 검증할 파일 Object
	 * @throws Exception : 파일이 아닌 경우 예외 메시지 전송
	 */
	public void isFile(File file) throws Exception {
		if(!file.isFile()) {
			String filePath = file.getAbsolutePath();
			_logger.error("##### This is not " + FileConstants.TYPE_OBJECT_FILE + ". - Path : " + filePath);
			throw new Exception("##### This is not " + FileConstants.TYPE_OBJECT_FILE + ". - Path : " + filePath);
		}
	}
	
	/**
	 * 객체가 폴더인지 검증
	 * @param folder : 검증할 폴더 Object
	 * @throws Exception : 폴더가 아닌 경우 예외 메시지 전송
	 */
	public void isFolder(File folder) throws Exception {
		if(!folder.isDirectory()) {
			String folderPath = folder.getAbsolutePath();
			_logger.error("##### This is not " + FileConstants.TYPE_OBJECT_FOLDER + ". - Path : " + folderPath);
			throw new Exception("##### This is not " + FileConstants.TYPE_OBJECT_FOLDER + ". - Path : " + folderPath);
		}
	}
	
	/**
	 * 파일 또는 폴더 관리와 관련된 이벤트 실패 메시지 전송
	 * @param f : 이벤트에 실패한 파일 또는 폴더 Object
	 * @param eventType : 실패한 이벤트 타입(Create, Delete, Rename, Move, Copy, Upload, Download, Zip, Unzip)
	 * @param isFile : 이벤트에 실패한 객체가 파일인지 여부 확인
	 * @return : 이벤트 실패 오류 메시지 전송
	 */
	private Exception _throwFailedException(File f, String eventType, boolean isFile) {
		String fPath = f.getAbsolutePath();
		String objectType = FileConstants.TYPE_OBJECT_FOLDER;
		if(isFile) objectType = FileConstants.TYPE_OBJECT_FILE;
		
		_logger.error("##### Failed "+ eventType + " " + objectType + ". - path : " + fPath);
		return new Exception("##### Failed " + eventType + " " + objectType + ". - path : " + fPath);
	}
}
