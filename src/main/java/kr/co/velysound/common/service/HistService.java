package kr.co.velysound.common.service;

import kr.co.velysound.common.mapper.HistMapper;
import kr.co.velysound.common.util.MoMap;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HistService {
	private final HistMapper histMapper;

	/**
	 * 파일 송출 이력 저장
	 *
	 * @param param
	 */
	public void saveExportHist(MoMap param) {
		histMapper.saveExportHist(param);
	}

	/**
	 * 파일 입출력 이력 저장
	 *
	 * @param param
	 */
	public void saveFileIpttHist(MoMap param) {
		histMapper.saveFileIpttHist(param);
	}

	/**
	 * 로그인 성공이력 추가
	 *
	 * @param param
	 */
	public void insertLoginHist(MoMap param) {
		histMapper.insertLoginHist(param);
	}

	/**
	 * 로그인 이력 수정 (로그아웃 및 세션 종료시)
	 *
	 * @param param
	 */
	public void updateLoginHist(Integer histId) {
		histMapper.updateLoginHist(histId);
	}

	/**
	 * 에러 이력 추가
	 *
	 * @param param
	 */
	public void insertErrHist(MoMap param) {
		histMapper.insertErrHist(param);
	}

	/**
	 * 배치 이력 추가
	 *
	 * @param param
	 */
	public void insertBatchHist(MoMap param) {
		histMapper.insertBatchHist(param);
	}

	public void updateBatchHist(MoMap param) {
		histMapper.updateBatchHist(param);
	}

	/**
	 * 메뉴접속 이력 추가
	 *
	 * @param param
	 */
	public void saveMnuConnHist(MoMap param) {
		histMapper.saveMnuConnHist(param);
	}
}
