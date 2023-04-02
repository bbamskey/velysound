package kr.co.velysound.common.mapper;

import kr.co.velysound.common.util.MoMap;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface HistMapper {
	public void saveExportHist(MoMap param);

	public void saveFileIpttHist(MoMap param);

	public void insertLoginHist(MoMap param);

	public void updateLoginHist(Integer histId);

	public void insertErrHist(MoMap param);

	public void insertBatchHist(MoMap param);

	public void updateBatchHist(MoMap param);

	public void insertGisIfHist(MoMap param);

	public void updateGisIfHist(MoMap param);

	public void updateShapeHist(MoMap gisHistParam);

	public void saveMnuConnHist(MoMap gisHistParam);
}
