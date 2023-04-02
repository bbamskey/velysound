package kr.co.velysound.common.service;

import kr.co.velysound.common.mapper.CommonMapper;
import kr.co.velysound.common.util.MoMap;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommonService {

	private final CommonMapper commonMapper;

	public List<MoMap> getListBkmk(MoMap moMap) {
		return commonMapper.getListBkmk(moMap);
	}

	public void saveBkmk(MoMap moMap) {
		commonMapper.saveBkmk(moMap);
	}

	public void deleteBkmk(MoMap param) {
		commonMapper.deleteBkmk(param);
	}
}
