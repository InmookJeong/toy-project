package kr.dev_mook.file_manager.servie.impl;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.Test;

/**
 * FileLocalServiceImpl에 대한 테스트
 * @author Inmook, Jeong
 *
 */
public class FileLocalServiceImplTest {
	
	private String samplePath = "C:\\Users\\jeong\\Desktop\\dev\\000000_workspaces\\100000_project\\10002_file_management\\workspace\\FileManager\\sample\\";

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
