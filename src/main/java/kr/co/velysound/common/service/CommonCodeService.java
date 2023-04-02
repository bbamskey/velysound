package kr.co.velysound.common.service;

import kr.co.velysound.common.mapper.CommonCodeMapper;
import kr.co.velysound.common.util.MoMap;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommonCodeService {

	private final CommonCodeMapper commonCodeMapper;

	public List<MoMap> getGroupCode(MoMap param) {
		return commonCodeMapper.getGroupCode(param);
	}

	public void saveGrpCdInfo(List<MoMap> param, String usrId) {

		for (MoMap moMap : param) {
			moMap.put("usrId", usrId);
			commonCodeMapper.saveGrpCdInfo(moMap);
		}

	}

	public void deleteGrpCdInfo(List<MoMap> param) {

		for (MoMap moMap : param) {
			commonCodeMapper.deleteGrpCdInfo(moMap);
			commonCodeMapper.deleteDtlCdInfo(moMap);
		}

	}

	public List<MoMap> getDetailCode(MoMap param) {
		return commonCodeMapper.getDetailCode(param);
	}

	public List<MoMap> getDtlCdInfo(MoMap param) {
		return commonCodeMapper.getDtlCdInfo(param);
	}

	public void saveDtlCdInfo(List<MoMap> param, String usrId) {

		for (MoMap moMap : param) {
			moMap.put("usrId", usrId);
			commonCodeMapper.saveDtlCdInfo(moMap);
		}

	}

	public void deleteDtlCdInfo(List<MoMap> param) {

		for (MoMap moMap : param) {
			commonCodeMapper.deleteDtlCdInfo(moMap);
		}

	}

	public MoMap idCheck(MoMap param) {
		return commonCodeMapper.idCheck(param);
	}

	public List<MoMap> getLayerCode(MoMap param) {
		return commonCodeMapper.getLayerCode(param);
	}
}
