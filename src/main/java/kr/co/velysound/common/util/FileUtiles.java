package kr.co.velysound.common.util;

import kr.co.velysound.common.vo.AssetsFileManageVO;
import kr.co.velysound.common.vo.FileManageVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@Slf4j
@Component
public class FileUtiles {

	/**
	 * 파일ID 생성
	 *
	 * @return
	 */
	public static String createPrimaryKey() {
		String pk = "CFM" + "_" + StringUtil.getTimeStamp();
		return pk;
	}

	/**
	 * 사진업로드
	 *
	 * @param mf
	 * @param parent
	 * @return
	 * @throws FileNotFoundException
	 */
	public static List<AssetsFileManageVO> photoFileUpload(List<MultipartFile> mf, String fileManageId, String path, String repChgYn) throws FileNotFoundException {
		List<AssetsFileManageVO> fl2 = new ArrayList<AssetsFileManageVO>();
		int cnt = 0;
		for (int i = 0; i < mf.size(); i++) {
			AssetsFileManageVO fl = new AssetsFileManageVO();
			String fileId = null;
			mf.get(i).getOriginalFilename();
			// 파일정보 세팅
			if (fileManageId == null) {
				fileId = FileUtiles.createPrimaryKey();
				fl.setFileId(fileId + cnt);
			} else {
				fl.setFileId(fileManageId);
			}
			fl.setRealFileNm(mf.get(i).getOriginalFilename());
			fl.setFilePth(path);
			String fileNm = mf.get(i).getOriginalFilename();
			String ext = fileNm.substring(fileNm.length() - 4, fileNm.length());
			File file = new File(path, fl.getFileId() + ext);
			fl.setFileNm(fl.getFileId() + ext);
			try {
				if (file.exists()) {
					// 파일이 이미 존재한다면 기존파일을 등록하는 파일로 덮어 씌우기
					File file2 = new File(path, fl.getFileId());
					try {
						mf.get(i).transferTo(file2);
						file.getName();
					} catch (IOException e) {
						throw new IOException(e.toString());
					}
				} else {
					mf.get(i).transferTo(file);
				}
			} catch (IOException e) {
				throw new FileNotFoundException(e.toString());
			}
			cnt++;
			fl2.add(i, fl);
		}
		return fl2;
	}

	/**
	 * 파일업로드
	 *
	 * @param mf
	 * @param parent
	 * @return
	 * @throws IOException
	 */
	public static List<FileManageVO> fileUpload(List<MultipartFile> mf, String uploadPath, String fileManageId) throws FileNotFoundException, InterruptedException, IOException {
		List<FileManageVO> fl2 = new ArrayList<FileManageVO>();
		int cnt = 0;
		for (int i = 0; i < mf.size(); i++) {
			FileManageVO fl = fileUpload(mf.get(i), uploadPath, fileManageId, cnt);
			Thread.sleep(700);
			cnt++;
			fl2.add(i, fl);
		}

		return fl2;
	}

	public static FileManageVO fileUpload(MultipartFile mf, String uploadPath, String fileManageId, int cnt) throws IOException {
		FileManageVO fl = new FileManageVO();
		String fileId = null;
		mf.getOriginalFilename();
		// 파일정보 세팅
		String saveFileNm;
		String fileNm = mf.getOriginalFilename();
		//String ext = fileNm.substring(fileNm.length()-4, fileNm.length());
		String ext = fileNm.substring(fileNm.lastIndexOf(".") + 1);

		if (fileManageId == null) {
			fileId = FileUtiles.createPrimaryKey();
			fl.setFileId(fileId + cnt);
		} else {
			fl.setFileId(fileManageId);
		}
		saveFileNm = fl.getFileId() + cnt + "." + ext;

		fl.setFileSeq(String.valueOf(cnt));
		fl.setRealFileNm(mf.getOriginalFilename());
		fl.setFileNm(saveFileNm);

		File file = new File(uploadPath, saveFileNm);
		try {
			if (file.exists()) {
				// 파일이 이미 존재한다면 기존파일을 등록하는 파일로 덮어 씌우기
				File file2 = new File(uploadPath, saveFileNm);
				try {
					mf.transferTo(file2);
					file.getName();
				} catch (IOException e) {
					throw new IOException(e.toString());
				}
			} else {
				mf.transferTo(file);
			}
		} catch (FileNotFoundException e) {
			if (file.exists()) deleteFile(file);
			throw new FileNotFoundException(e.toString());
		}
		return fl;
	}

	/**
	 * 공통파일업로드
	 *
	 * @param mf
	 * @param parent
	 * @return
	 * @throws InterruptedException
	 * @throws IOException
	 */
	public static List<FileManageVO> commonFileUpload(List<MultipartFile> mf, String uploadPath, String fileId, int maxSeq) throws FileNotFoundException, InterruptedException, IOException {
		List<FileManageVO> fl2 = new ArrayList<FileManageVO>();
		int cnt = maxSeq;
		for (int i = 0; i < mf.size(); i++) {
			FileManageVO fl = commonFileUpload(mf.get(i), uploadPath, fileId, cnt);
			Thread.sleep(700);
			cnt++;
			fl2.add(i, fl);
		}

		return fl2;
	}

	public static FileManageVO commonFileUpload(MultipartFile mf, String uploadPath, String fileId, int cnt) throws FileNotFoundException, IOException {

		FileManageVO fileVO = new FileManageVO();
		mf.getOriginalFilename();

		// 파일정보 세팅
		String fileNm = mf.getOriginalFilename();
		String ext = fileNm.substring(fileNm.lastIndexOf(".") + 1);
		String saveFileNm = fileId + cnt + "." + ext;

		fileVO.setFileId(fileId);
		fileVO.setFileSeq(String.valueOf(cnt));
		fileVO.setRealFileNm(mf.getOriginalFilename());
		fileVO.setFileNm(saveFileNm);

		File file = new File(uploadPath, saveFileNm);

		try {

			if (file.exists()) {
				// 파일이 이미 존재한다면 기존파일을 등록하는 파일로 덮어 씌우기
				File file2 = new File(uploadPath, saveFileNm);

				try {
					mf.transferTo(file2);
				} catch (IOException e) {
					throw new IOException(e.toString());
				}

			} else {
				mf.transferTo(file);
			}
		} catch (FileNotFoundException e) {
			if (file.exists()) deleteFile(file);
			throw new FileNotFoundException(e.toString());
		}

		return fileVO;
	}

	/**
	 * 조사파일업로드
	 *
	 * @param mf
	 * @param parent
	 * @return
	 * @throws InterruptedException
	 * @throws IOException
	 */
	public static FileManageVO svyFileUpload(MultipartFile mf, String uploadPath, String fcltyCd) throws FileNotFoundException, IOException {

		FileManageVO fileVO = new FileManageVO();
		mf.getOriginalFilename();

		// 파일정보 세팅
		String fileNm = mf.getOriginalFilename();
		String ext = fileNm.substring(fileNm.lastIndexOf(".") + 1);
		String saveFileNm = FileUtiles.createPrimaryKey() + "." + ext;

		fileVO.setFcltyCd(fcltyCd);
		fileVO.setRealFileNm(mf.getOriginalFilename());
		fileVO.setFileNm(saveFileNm);

		File file = new File(uploadPath, saveFileNm);

		try {

			if (file.exists()) {
				// 파일이 이미 존재한다면 기존파일을 등록하는 파일로 덮어 씌우기
				File file2 = new File(uploadPath, saveFileNm);

				try {
					mf.transferTo(file2);
				} catch (IOException e) {
					throw new IOException(e.toString());
				}

			} else {
				mf.transferTo(file);
			}
		} catch (FileNotFoundException e) {
			if (file.exists()) deleteFile(file);
			throw new FileNotFoundException(e.toString());
		}

		return fileVO;
	}

	/**
	 * 파일을 삭제.
	 *
	 * @param targetFile
	 * @throws Exception
	 */
	public static void deleteFile(File targetFile) {
		if (targetFile.exists()) {
			targetFile.delete();
		}
		;
	}

	/**
	 * 파일 다운로드
	 *
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	public static void download(String fileNameOrg, String realFileNm, String uploadPath, HttpServletRequest request,
	                            HttpServletResponse response) throws IOException {
		FileInputStream fileInputStream = null;
		ServletOutputStream servletOutputStream = null;
		try {
			File file = new File(uploadPath, fileNameOrg);
			String downName = null;
			String browser = request.getHeader("User-Agent");

			realFileNm = StringUtils.isNotEmpty(realFileNm) ? realFileNm : fileNameOrg;

			// 파일 인코딩
			if (browser.contains("MSIE") || browser.contains("Trident") || browser.contains("Chrome")) {// 브라우저 확인 파일명
				// encode
				downName = URLEncoder.encode(realFileNm, "UTF-8").replaceAll("\\+", "%20");
			} else {
				downName = new String(realFileNm.getBytes("UTF-8"), "ISO-8859-1");
			}

			response.setHeader("Content-Disposition", "attachment;filename=\"" + downName + "\"");
			response.setContentType("application/octer-stream");
			response.setHeader("Content-Transfer-Encoding", "binary;");
			fileInputStream = new FileInputStream(file);
			servletOutputStream = response.getOutputStream();

			byte b[] = new byte[1024];
			int data = 0;

			while ((data = (fileInputStream.read(b, 0, b.length))) != -1) {
				servletOutputStream.write(b, 0, data);
			}
			servletOutputStream.flush();// 출력
		} catch (IOException e) {
			WAMSUtil.getErrorModelAndView(e);
		} finally {
			if (servletOutputStream != null) {
				try {
					servletOutputStream.close();
				} catch (IOException e) {
					WAMSUtil.getErrorModelAndView(e);
				}
			}
			if (fileInputStream != null) {
				try {
					fileInputStream.close();
				} catch (IOException e) {
					WAMSUtil.getErrorModelAndView(e);
				}
			}
		}
	}

	/**
	 * 다중파일 zip으로 압축후 다운로드
	 *
	 * @param list
	 * @param mgcNm
	 * @param infoNm
	 * @param tagSn
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	public static void downloadZip(List<FileManageVO> list, String fileRealNm, String uploadPath,
	                               HttpServletRequest request, HttpServletResponse response) throws IOException {
		FileInputStream fileInputStream = null;
		ServletOutputStream servletOutputStream = null;
		String zipName = null;
		FileInputStream in = null;
		ZipOutputStream zout = null;

		FileInputStream fis = null;
		BufferedOutputStream bos = null;

		try {
			// ======================================================================================
			// zip파일 이름설정
			//zn = new String(zn.getBytes("UTF-8"), "UTF-8");
			String browser = request.getHeader("User-Agent");
			if (browser.contains("MSIE") || browser.contains("Trident") || browser.contains("Chrome")) {
				fileRealNm = URLEncoder.encode(fileRealNm, "UTF-8").replaceAll("\\+", "%20");
			} else {
				fileRealNm = new String(fileRealNm.getBytes("UTF-8"), "ISO-8859-1");
			}
			zipName = fileRealNm + "_" + StringUtil.getTimeStamp() + ".zip";
			//zipPath = list.get(0).getFilePth();
			// zip파일 압축 start
			zout = new ZipOutputStream(new FileOutputStream(uploadPath + File.separator + zipName));
			byte[] buffer = new byte[1024];

			int cnt = 0;
			for (FileManageVO fileVo : list) {
				String realName = fileVo.getRealFileNm().substring(0, fileVo.getRealFileNm().lastIndexOf("."));
				String ext = fileVo.getRealFileNm().substring(fileVo.getRealFileNm().lastIndexOf("."), fileVo.getRealFileNm().length());
				String fileName = fileVo.getFileNm();
				// 압축대상 파일
				in = new FileInputStream(uploadPath + File.separator + fileName);
				// 압축에 저장될 파일명
				zout.putNextEntry(new ZipEntry(realName + "_" + StringUtil.getTimeStamp() + cnt + ext));
				int len;
				while ((len = in.read(buffer)) > 0) {
					// 읽은 파일 zipOutputStream에 write
					zout.write(buffer, 0, len);
				} // while end

				zout.closeEntry();
				in.close();
			}
			zout.close();// zip파일 압축 end

			// 파일 다운로드 start
			response.setContentType("application/zip; charset=UTF-8");
			response.addHeader("Content-Disposition", "attachment; filename=" + zipName);
			response.setHeader("Content-Transfer-Encoding", "binary");

			fis = new FileInputStream(uploadPath + File.separator + zipName);
			BufferedInputStream bis = new BufferedInputStream(fis);
			ServletOutputStream sos = response.getOutputStream();
			bos = new BufferedOutputStream(sos);

			int n = 0;
			while ((n = bis.read(buffer)) > 0) {
				bos.write(buffer, 0, n);
				bos.flush();
			}

			if (bos != null)
				bos.close();
			if (bis != null)
				bis.close();
			if (sos != null)
				sos.close();
			if (fis != null)
				fis.close();
			// 파일 다운로드 end

		} catch (IOException e) {
			WAMSUtil.getErrorModelAndView(e);
		} finally {
			if (servletOutputStream != null) {
				try {
					servletOutputStream.close();
				} catch (IOException e) {
					WAMSUtil.getErrorModelAndView(e);
				}
			}
			if (fileInputStream != null) {
				try {
					fileInputStream.close();
				} catch (IOException e) {
					WAMSUtil.getErrorModelAndView(e);
				}
			}
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					WAMSUtil.getErrorModelAndView(e);
				}
			}
			if (zout != null) {
				try {
					zout.close();
				} catch (IOException e) {
					WAMSUtil.getErrorModelAndView(e);
				}
			}
			if (fis != null) {
				try {
					fis.close();
				} catch (IOException e) {
					WAMSUtil.getErrorModelAndView(e);
				}
			}
			if (bos != null) {
				try {
					bos.close();
				} catch (IOException e) {
					WAMSUtil.getErrorModelAndView(e);
				}
			}
			File zipFile = new File(uploadPath + File.separator + zipName);
			deleteFile(zipFile);
		}
	}

	/**
	 * 파일 삭제
	 *
	 * @param list
	 * @throws IOException
	 */
	public static void delete(List<FileManageVO> list, String uploadPath) throws IOException {
		for (FileManageVO fileVo : list) {
			String ext = fileVo.getRealFileNm().substring(fileVo.getRealFileNm().indexOf("."), fileVo.getRealFileNm().length());
			String fileName = fileVo.getFileNm();
			File zipFile = new File(uploadPath + File.separator + fileName);
			deleteFile(zipFile);
		}
	}

	/**
	 * multipartFile을 File로 변환한다.
	 *
	 * @param MultipartFile file 멀티파트 파일
	 * @return File 변환된 파일을 반환한다.
	 * @throws IOException
	 */
	public static File multipartFileToFile(String key, MultipartFile file, String uploadPath) throws IOException {
		File convFile = new File(uploadPath + File.separator + key + file.getOriginalFilename());
		convFile.createNewFile();
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(convFile);
			fos.write(file.getBytes());
			fos.close();
		} catch (IOException e) {
			WAMSUtil.getErrorModelAndView(e);
		} finally {
			if (fos != null) {
				try {
					fos.close();
				} catch (IOException e) {
					WAMSUtil.getErrorModelAndView(e);
				}
			}
		}

		return convFile;
	}
}