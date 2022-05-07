package kr.dev_mook.file_manager.servie.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.junit.Test;

import kr.dev_mook.file_manager.util.MyFileUtil;

/**
 * FileLocalServiceImpl에 대한 테스트
 * @author Inmook, Jeong
 *
 */
public class MyFileUtilTest {
	
	private String samplePath = "C:\\Users\\jeong\\Desktop\\dev\\000000_workspaces\\100000_project\\10002_file_management\\workspace\\FileManager\\sample\\";
	
	/**
	 * 파일 생성 테스트
	 * - 같은 이름의 파일이 없는 경우에만 생성
	 */
	@Test
	public void createFileTest() {
		try {
			Path hello2TxtPath = Paths.get(samplePath + "hello2.txt");
			if(!hello2TxtPath.toFile().exists()) {
				FileUtils.touch(hello2TxtPath.toFile());
			}
			
			File file = new File(hello2TxtPath.toString());
			assertTrue(file.exists());
			assertTrue(file.isFile());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 폴더 생성 테스트
	 * - 같은 이름의 폴더가 없는 경우에만 생성
	 */
	@Test
	public void createFolderTest() {
		try {
			Path helloFolderPath = Paths.get(samplePath + "hello_dir");
			if(!helloFolderPath.toFile().exists()) {
				FileUtils.forceMkdir(helloFolderPath.toFile());
			}
			
			File file = new File(helloFolderPath.toString());
			assertTrue(file.exists());
			assertTrue(file.isDirectory());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 절대 경로에 대응하는 파일의 내용 읽기
	 */
	@Test
	public void readFile() {
		try {
			String helloTxtPath = samplePath + "hello.txt";
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(helloTxtPath), "UTF-8"));
			
			String str;
			String resultStr = "";
			while((str = br.readLine()) != null) {
				resultStr = str;
			}
			
			assertTrue(resultStr.equals("Hello World."));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 절대 경로에 대응하는 파일에 내용 작성하기
	 * - 덮어쓰기
	 */
	@Test
	public void writeFile() {
		String helloTxtPath = samplePath + "hello.txt";
		try(BufferedWriter bw = new BufferedWriter(new FileWriter(helloTxtPath, false))) {
			bw.write("Hello World!");
			bw.flush();
			
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(helloTxtPath), "UTF-8"));
			String str;
			String resultStr = "";
			while((str = br.readLine()) != null) {
				resultStr = str;
			}
			assertTrue(resultStr.equals("Hello World!"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 절대 경로에 대응하는 파일에 내용 작성하기
	 * - 내용 추가
	 * - 기존 내용은 유지 : Hello World.
	 */
	@Test
	public void writeFile2() {
		String helloTxtPath = samplePath + "hello.txt";
		try(BufferedWriter bw = new BufferedWriter(new FileWriter(helloTxtPath, true))) {
			bw.newLine();
			bw.write("Hello Hello Hello");
			bw.flush();
			
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(helloTxtPath), "UTF-8"));
			assertTrue(br.readLine().equals("Hello World."));
			assertTrue(br.readLine().equals("Hello Hello Hello"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * JUnit4에는 DisplayName이 없네...
	 * 파일 존재 여부 확인 및 파일인지 여부 확인
	 */
	@Test
	public void hasFileTest() {
		String helloTxtPath = samplePath + "hello.txt";
		File file = new File(helloTxtPath);
		boolean hasFile = file.exists();
		boolean isFile = file.isFile();
		boolean isDirectory = file.isDirectory();
		
		assertTrue(hasFile);
		assertTrue(isFile);
		assertFalse(isDirectory);
	}
	
	/**
	 * 폴더 존재 여부 확인 및 폴더인지 여부 확인
	 */
	@Test
	public void hasFolderTest() {
		File folder = new File(samplePath);
		boolean hasFile = folder.exists();
		boolean isFile = folder.isFile();
		boolean isDirectory = folder.isDirectory();
		
		assertTrue(hasFile);
		assertFalse(isFile);
		assertTrue(isDirectory);
	}
	
	/**
	 * 경로를 통한 파일 삭제
	 */
	@Test
	public void deleteFile() {
		String targetPath = samplePath + "newFolder\\hello.txt";
		try {
			FileUtils.forceDelete(Paths.get(targetPath).toFile());
			assertFalse(Paths.get(targetPath).toFile().exists());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 경로를 통한 폴더 삭제
	 * - 하위 폴더까지 같이 삭제
	 * - FileUtils 사용
	 */
	@Test
	public void deleteFolder() {
		String targetPath = samplePath + "newFolder3";
		try {
			FileUtils.deleteDirectory(Paths.get(targetPath).toFile());
			assertFalse(Paths.get(targetPath).toFile().exists());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 파일 복사 테스트 코드
	 */
	@Test
	public void copyFile() {
		try {
			String helloTxtPath = samplePath + "hello2.txt";
			String targetPath = samplePath + "newFolder\\hello.txt";
			FileUtils.copyFile(Paths.get(helloTxtPath).toFile(), Paths.get(targetPath).toFile());
			
			assertTrue(Paths.get(targetPath).toFile().exists());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void copyFolder() {
		String sourcePath = samplePath + "newFolder";
		String targetPath = samplePath + "newFolder3";
		File sourceF = new File(sourcePath);
		File targetF = new File(targetPath);
		
		try {
			FileUtils.copyDirectory(sourceF, targetF);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		assertTrue(Paths.get(targetPath).toFile().exists());
	}
	
	/**
	 * 경로를 통한 파일 이동 테스트
	 */
	@Test
	public void moveFile() {
		String sourcePath = samplePath + "newFolder\\hello2.txt";
		String targetFolder = samplePath + "newFolder2";
		String targetPath = samplePath + "newFolder2\\hello.txt";
		try {
			FileUtils.moveFileToDirectory(Paths.get(sourcePath).toFile(), Paths.get(targetFolder).toFile(), true);
			assertTrue(Paths.get(targetPath).toFile().exists());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 경로를 통한 폴더 이동 테스트
	 */
	@Test
	public void moveFolder() {
		String sourcePath = samplePath + "newFolder";
		String targetFolder = samplePath + "newFolder2";
		String targetPath = samplePath + "newFolder2\\newFolder";
		try {
			FileUtils.moveDirectoryToDirectory(Paths.get(sourcePath).toFile(), Paths.get(targetFolder).toFile(), true);
			assertTrue(Paths.get(targetPath).toFile().exists());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 경로를 통한 파일 이름 바꾸기
	 */
	@Test
	public void renameFile() {
		String sourcePath = samplePath + "newFolder\\hello2.txt";
		String targetPath = samplePath + "newFolder\\hello.txt";
		try {
			FileUtils.moveFile(Paths.get(sourcePath).toFile(), Paths.get(targetPath).toFile());
			assertTrue(Paths.get(targetPath).toFile().exists());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 경로를 통한 폴더 이름 바꾸기
	 */
	@Test
	public void renameFolder() {
		String sourcePath = samplePath + "newFolder22";
		String targetPath = samplePath + "newFolder3";
		try {
			FileUtils.moveDirectory(Paths.get(sourcePath).toFile(), Paths.get(targetPath).toFile());
			assertTrue(Paths.get(targetPath).toFile().exists());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 파일 또는 폴더 가져오기
	 */
	@Test
	public void getFileOrFolder() {
		MyFileUtil fileUtil = new MyFileUtil();
		String prefix = "C:\\Users\\jeong\\Desktop\\dev\\000000_workspaces\\100000_project\\10002_file_management\\workspace\\FileManager\\sample\\";
		String filePath = prefix + "hello.txt";
		String forderPath = prefix + "newFolder";
		try {
			File file = fileUtil.getFile(filePath);
			File folder = fileUtil.getFolder(forderPath);
			assertTrue(file.exists());
			assertTrue(folder.exists());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 파일 또는 폴더 갯수 가져오기
	 */
	@Test
	public void countFilesAndFolders() {
		MyFileUtil fileUtil = new MyFileUtil();
		try {
			String folderPath = "C:\\Users\\jeong\\Desktop\\dev\\000000_workspaces\\100000_project\\10002_file_management\\workspace\\FileManager\\sample";
			int fileCnt = fileUtil.countFiles(folderPath);
			int folderCnt = fileUtil.countFolders(folderPath);
			assertEquals(fileCnt, 2);
			assertEquals(folderCnt, 4);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// TODO 테스트 코드로 만들기
	@Test
	public void getFilesOrFolder() {
		MyFileUtil fileUtil = new MyFileUtil();
		File folder = new File("C:\\Users\\jeong\\Desktop\\dev\\000000_workspaces\\100000_project\\10002_file_management\\workspace\\FileManager\\sample");
		try {
			List<File> files = fileUtil.getFiles(folder);
			List<File> folders = fileUtil.getFolders(folder);
			System.out.println("fileList ----------- ");
			for(File f : files) {
				System.out.println(f.getName());
			}
			System.out.println("folderList ----------- ");
			for(File f : folders) {
				System.out.println(f.getName());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * 파일 읽기
	 */
	@Test
	public void readContent() {
		File file = new File("C:\\Users\\jeong\\Desktop\\dev\\000000_workspaces\\100000_project\\10002_file_management\\workspace\\FileManager\\sample\\hello.txt");
		try {
			String content = FileUtils.readFileToString(file, "UTF-8");
			assertTrue(content.equals("helloWorld"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * 파일 내용 쓰기
	 */
	@Test
	public void writeContent() {
		MyFileUtil fileUtil = new MyFileUtil();
		File file = new File("C:\\Users\\jeong\\Desktop\\dev\\000000_workspaces\\100000_project\\10002_file_management\\workspace\\FileManager\\sample\\hello.txt");
		String str = "helloWorld";
		try {
			fileUtil.write(file, str, false);
			String content1 = FileUtils.readFileToString(file, "UTF-8");
			String expected = "Hello World!\r\nHello Hello Hello"+str;
			assertTrue(content1.equals(expected));
			
			fileUtil.write(file, str, true);
			String content2 = FileUtils.readFileToString(file, "UTF-8");
			assertTrue(content2.equals(str));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
