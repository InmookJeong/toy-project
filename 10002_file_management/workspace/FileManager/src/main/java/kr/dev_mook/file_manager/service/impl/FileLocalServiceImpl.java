package kr.dev_mook.file_manager.service.impl;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import kr.dev_mook.file_manager.model.File;
import kr.dev_mook.file_manager.service.FileLocalService;

public class FileLocalServiceImpl implements FileLocalService {
	
	private static final Logger _logger = LoggerFactory.getLogger(FileLocalServiceImpl.class);

	@Override
	public File createFile(File file) {
		File returnFile = null;
		Path path = Paths.get(file.getPath());
		try {
			if(!path.toFile().exists() && path.toFile().isFile()) {
				FileUtils.touch(path.toFile());
				_logger.info("##### Success creation file.");
				_logger.debug("##### Creation File Information");
				_logger.debug("##### - Name : " + file.getName());
				_logger.debug("##### - Path : " + file.getPath());
				_logger.debug("##### - Extention : " + file.getExtension());
				_logger.debug("##### - Size : " + file.getFileSize());
				_logger.debug("##### - Owner : " + file.getUserId());
			}
			returnFile = file;
		} catch (IOException e) {
			_logger.error("##### Failed creation file.");
			e.printStackTrace();
		}
		
		return returnFile;
	}

	@Override
	public File createFolder(File folder) {
		File returnFolder = null;
		Path path = Paths.get(folder.getPath());
		try {
			if(!path.toFile().exists() && path.toFile().isDirectory()) {
				FileUtils.touch(path.toFile());
				_logger.info("##### Success creation folder.");
				_logger.debug("##### Creation Folder Information");
				_logger.debug("##### - Name : " + folder.getName());
				_logger.debug("##### - Path : " + folder.getPath());
				_logger.debug("##### - Extention : " + folder.getExtension());
				_logger.debug("##### - Size : " + folder.getFileSize());
				_logger.debug("##### - Owner : " + folder.getUserId());
			}
			returnFolder = folder;
		} catch (IOException e) {
			_logger.error("##### Failed creation folder.");
			e.printStackTrace();
		}
		
		return returnFolder;
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
	public File getFile(Long fileId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public File getFile(String absoluteFilePath) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String readFile(Long fileId) {
		// TODO Auto-generated method stub
		return readFile("");
	}

	@Override
	public String readFile(String absoluteFilePath) {
		StringBuilder sb = new StringBuilder();
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(absoluteFilePath), "UTF-8"));
			
			String str;
			String resultStr = "";
			while((str = br.readLine()) != null) {
				sb.append(str);
			}
			
			assertTrue(resultStr.equals("Hello World."));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sb.toString();
	}
	
	@Override
	public File writeFile(Long fileId, String content, boolean override) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public File writeFile(String absoluteFilePath, String content, boolean override) {
		try(BufferedWriter bw = new BufferedWriter(new FileWriter(absoluteFilePath, override))) {
			bw.write(content);
			bw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		// TODO File Return
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
	public File getFolder(Long fileId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public File getFolder(String absoluteFolderPath) {
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
		try {
			FileUtils.forceDelete(Paths.get(absoluteFilePath).toFile());
		} catch (IOException e) {
			e.printStackTrace();
		}
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
		try {
			FileUtils.deleteDirectory(Paths.get(absoluteFolderPath).toFile());
			assertFalse(Paths.get(absoluteFolderPath).toFile().exists());
		} catch (IOException e) {
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
		return null;
	}
	
	// TODO
	public void upload2(MultipartFile[] multipartFiles, String uploadFolder) {
		for(MultipartFile multipartFile : multipartFiles) {
			java.io.File file = new java.io.File(uploadFolder, multipartFile.getOriginalFilename());
			try {
				multipartFile.transferTo(file);
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public File upload(File file) {
		
		try {
			InputStream is = new FileInputStream(new java.io.File(file.getPath()));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
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
		try {
			String name = Paths.get(absoluteFilePath).toFile().getName();
			FileUtils.copyFile(Paths.get(absoluteFilePath).toFile(), Paths.get(targetFolder+"\\"+name).toFile());
		} catch (IOException e) {
			e.printStackTrace();
		}
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
		try {
			String name = Paths.get(absolutefolderPath).toFile().getName();
			FileUtils.copyDirectory(Paths.get(absolutefolderPath).toFile(), Paths.get(absolutefolderPath+"\\"+name).toFile());
		} catch (IOException e) {
			e.printStackTrace();
		}
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
		try {
			String name = Paths.get(absoluteFilePath).toFile().getName();
			FileUtils.moveFileToDirectory(Paths.get(absoluteFilePath).toFile(), Paths.get(targetFolder+"\\"+name).toFile(), true);
		} catch (IOException e) {
			e.printStackTrace();
		}
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
		try {
			String name = Paths.get(absolutefolderPath).toFile().getName();
			FileUtils.moveDirectoryToDirectory(Paths.get(absolutefolderPath).toFile(), Paths.get(targetFolder+"\\"+name).toFile(), true);
		} catch (IOException e) {
			e.printStackTrace();
		}
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
		try {
			String name = Paths.get(absoluteFilePath).toFile().getName();
			String folderPath = Paths.get(absoluteFilePath).toFile().getParent();
			FileUtils.moveFile(Paths.get(absoluteFilePath).toFile(), Paths.get(folderPath+"\\"+name).toFile());
		} catch (IOException e) {
			e.printStackTrace();
		}
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
		try {
			String name = Paths.get(absoluteFolderPath).toFile().getName();
			String folderPath = Paths.get(absoluteFolderPath).toFile().getParent();
			FileUtils.moveDirectory(Paths.get(absoluteFolderPath).toFile(), Paths.get(folderPath+"\\"+name).toFile());
		} catch (IOException e) {
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
		return null;
	}
}
