package kr.co.velysound.mapper;

import kr.co.velysound.common.util.MoMap;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SheetMapper {

	public List<MoMap> getList(MoMap param);
}
