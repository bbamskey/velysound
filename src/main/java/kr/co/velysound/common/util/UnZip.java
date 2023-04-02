package kr.co.velysound.common.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class UnZip {
	/**
	 * 압축풀기 메소드
	 *
	 * @param zipFileName 압축파일
	 * @param directory   압축 풀 폴더
	 */
	public static boolean run(String zipFileName, String directory) throws Throwable {

		FileInputStream fis = null;
		ZipInputStream zis = null;
		ZipEntry zipentry = null;
		try {
			File zipFile = new File(zipFileName);
			fis = new FileInputStream(zipFile);
			zis = new ZipInputStream(fis);
			while ((zipentry = zis.getNextEntry()) != null) {
				String filename = zipentry.getName();
				File file = new File(directory, filename);
				//entiry가 폴더면 폴더 생성
				if (zipentry.isDirectory()) {
					file.mkdirs();
				} else {
					//파일이면 파일 만들기
					createFile(file, zis);
				}
			}
		} catch (Throwable e) {
			throw e;
		} finally {
			if (zis != null)
				zis.close();
			if (fis != null)
				fis.close();
		}
		return true;
	}

	/**
	 * 파일 만들기 메소드
	 *
	 * @param file 파일
	 * @param zis  Zip스트림
	 */
	private static void createFile(File file, ZipInputStream zis) throws Throwable {
		File parentDir = new File(file.getParent());
		if (!parentDir.exists()) {
			parentDir.mkdirs();
		}
		try (FileOutputStream fos = new FileOutputStream(file)) {
			byte[] buffer = new byte[256];
			int size = 0;
			while ((size = zis.read(buffer)) > 0) {
				fos.write(buffer, 0, size);
			}
		} catch (Throwable e) {
			throw e;
		}
	}

}
