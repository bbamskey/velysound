package kr.co.velysound.common.mapper;

import kr.co.velysound.common.util.MoMap;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommonMapper {

	public List<MoMap> getListBkmk(MoMap param);

	public void saveBkmk(MoMap param);

	public void deleteBkmk(MoMap param);
}
