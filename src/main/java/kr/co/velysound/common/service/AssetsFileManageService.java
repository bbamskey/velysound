package kr.co.velysound.common.service;

import kr.co.velysound.common.mapper.AssetsFileManageMapper;
import kr.co.velysound.common.util.MoMap;
import kr.co.velysound.common.vo.AssetsFileManageVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AssetsFileManageService {

	private final AssetsFileManageMapper assetsFileManagerMapper;

	public void insert(AssetsFileManageVO param) {
		assetsFileManagerMapper.save(param);
	}

	public int delete(AssetsFileManageVO param) {
		return assetsFileManagerMapper.delete(param);
	}

	public MoMap select(MoMap param) {
		return assetsFileManagerMapper.select(param);
	}

	public List<MoMap> selectList(MoMap param) {
		return assetsFileManagerMapper.selectList(param);
	}

	public void saveNoAssetsId(AssetsFileManageVO param) {
		assetsFileManagerMapper.saveNoAssetsId(param);
	}

	public List<MoMap> selectListByAssetsId(List<MoMap> paramList) {

		String[] assetsIds = new String[paramList.size()];

		MoMap param = new MoMap();

		for (int i = 0; i < paramList.size(); i++) {
			param = paramList.get(i);
			assetsIds[i] = param.getString("assetsId");
		}

		param.put("assetsIdArr", assetsIds);

		return assetsFileManagerMapper.selectListByAssetsId(param);
	}

	public List<MoMap> getSvylistByFcltyCd(MoMap param) {
		return assetsFileManagerMapper.getSvylistByFcltyCd(param);
	}
}
