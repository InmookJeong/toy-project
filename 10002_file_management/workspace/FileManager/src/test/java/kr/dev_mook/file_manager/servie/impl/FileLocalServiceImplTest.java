package kr.dev_mook.file_manager.servie.impl;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Test;

/**
 * FileLocalServiceImpl에 대한 테스트
 * @author Inmook, Jeong
 *
 */
public class FileLocalServiceImplTest {
	
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
				Files.createFile(hello2TxtPath);
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
				Files.createDirectory(helloFolderPath);
			}
			
			File file = new File(helloFolderPath.toString());
			assertTrue(file.exists());
			assertTrue(file.isDirectory());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
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
	 * ㅍㄹ더 존재 여부 확인 및 폴더인지 여부 확인
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
}
