package kr.co.velysound.common.mapper;

import kr.co.velysound.common.util.MoMap;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommonCodeMapper {

	public List<MoMap> getGroupCode(MoMap param);

	public void saveGrpCdInfo(MoMap param);

	public void deleteGrpCdInfo(MoMap param);

	public List<MoMap> getDetailCode(MoMap param);

	public List<MoMap> getDtlCdInfo(MoMap param);

	public void saveDtlCdInfo(MoMap param);

	public void deleteDtlCdInfo(MoMap param);

	public MoMap idCheck(MoMap param);

	public List<MoMap> getLayerCode(MoMap param);
}
