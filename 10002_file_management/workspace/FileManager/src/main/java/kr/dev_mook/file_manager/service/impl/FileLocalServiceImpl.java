package kr.dev_mook.file_manager.service.impl;

import java.nio.file.Paths;
import java.util.List;

import kr.dev_mook.file_manager.model.File;
import kr.dev_mook.file_manager.service.FileLocalService;

public class FileLocalServiceImpl implements FileLocalService {

	@Override
	public File createFile(final Long fileId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public File createFolder(Long fileId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public File updateFile(File file) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public File updateFolder(File file) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public File readFile(Long fileId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public File readFile(String absoluteFilePath) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean hasFile(String targetFolderPath, String fileName) {
		String filePath = Paths.get(targetFolderPath, fileName).toString();
		return hasFile(filePath);
	}

	@Override
	public boolean hasFile(String absoluteFilePath) {
		java.io.File file = Paths.get(absoluteFilePath).toFile();
		boolean exist = file.exists();
		if(exist) exist = file.isFile();
		return exist;
	}

	@Override
	public File readFolder(Long fileId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public File readFolder(String absoluteFolderPath) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean hasFolder(String targetFolderPath, String folderName) {
		String folderPath = Paths.get(targetFolderPath, folderName).toString();
		return hasFolder(folderPath);
	}

	@Override
	public boolean hasFolder(String absolteFolderPath) {
		java.io.File folder = Paths.get(absolteFolderPath).toFile();
		boolean exist = folder.exists();
		if(exist) exist = folder.isDirectory();
		return exist;
	}

	@Override
	public int countFiles(Long folderId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int countFiles(String absoluteFolderPath) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<File> getFiles(Long folderId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<File> getFiles(String absoluteFolderPath) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int countFolders(Long folderId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int countFolders(String absoluteFolderPath) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<File> getFolders(Long folderId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<File> getFolders(String absoluteFolderPath) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public File deleteFile(Long fileId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public File deleteFile(String absoluteFilePath) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public File deleteFolder(Long folderId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public File deleteFolder(String absoluteFolderPath) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public File uploadFile(File file) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public File downloadFile(Long fileId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public File downloadFile(String absoluteFilePath) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public File zipFile(File folder) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public File zipFile(String absoluteFolderPath) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public File unzipFile(File file) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public File copyFile(Long fileId, String targetFolder) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public File copyFile(String absoluteFilePath, String targetFolder) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public File copyFolder(Long folderId, String targetFolder) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public File copyFolder(String absolutefolderPath, String targetFolder) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public File moveFile(Long fileId, String targetFolder) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public File moveFile(String absoluteFilePath, String targetFolder) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public File moveFolder(Long folderId, String targetFolder) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public File moveFolder(String absolutefolderPath, String targetFolder) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public File renameFile(Long fileId, String newName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public File renameFile(String absoluteFilePath, String newName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public File renameFolder(Long folderId, String newName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public File renameFolder(String absoluteFolderPath, String newName) {
		// TODO Auto-generated method stub
		return null;
	}

}
