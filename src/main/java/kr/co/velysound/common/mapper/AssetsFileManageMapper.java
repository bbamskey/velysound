package kr.co.velysound.common.mapper;

import kr.co.velysound.common.util.MoMap;
import kr.co.velysound.common.vo.AssetsFileManageVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AssetsFileManageMapper {

	/**
	 * 파일 정보 저장(수정)
	 *
	 * @param vo
	 */
	public void save(AssetsFileManageVO vo);

	/**
	 * 파일 정보 삭제
	 *
	 * @param vo
	 * @
	 */
	public int delete(AssetsFileManageVO vo);

	/**
	 * 파일 정보 상세 조회
	 *
	 * @param vo
	 * @
	 */
	public MoMap select(MoMap vo);

	/**
	 * 파일 정보 목록 조회
	 *
	 * @param vo
	 * @
	 */
	public List<MoMap> selectList(MoMap vo);

	/**
	 * 파일 정보 저장(수정)
	 *
	 * @param vo
	 */
	public void saveNoAssetsId(AssetsFileManageVO vo);

	public List<MoMap> selectListByAssetsId(MoMap param);

	public List<MoMap> getSvylistByFcltyCd(MoMap param);
}
