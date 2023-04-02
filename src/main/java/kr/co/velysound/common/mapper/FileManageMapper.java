package kr.co.velysound.common.mapper;

import kr.co.velysound.common.util.MoMap;
import kr.co.velysound.common.vo.FileManageVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FileManageMapper {

	/**
	 * 파일 정보 저장(수정)
	 *
	 * @param vo
	 */
	public void save(FileManageVO vo);

	/**
	 * 파일 정보 삭제
	 *
	 * @param vo
	 * @
	 */
	public void delete(FileManageVO vo);

	/**
	 * 파일 정보 상세 조회
	 *
	 * @param vo
	 * @
	 */
	public FileManageVO select(FileManageVO vo);

	/**
	 * 파일 정보 목록 조회
	 *
	 * @param vo
	 * @
	 */
	public List<FileManageVO> selectList(FileManageVO vo);

	public void saveSvyFile(MoMap param);

	public String getFcltyCdForSvy(MoMap param);

	public MoMap getSvyFileInfo(MoMap param);


}
