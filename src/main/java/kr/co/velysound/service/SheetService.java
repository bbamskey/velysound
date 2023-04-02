package kr.co.velysound.service;

import kr.co.velysound.common.util.MoMap;
import kr.co.velysound.mapper.SheetMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SheetService {
	private final SheetMapper sheetMapper;

	public List<MoMap> getList(MoMap param) {
		return sheetMapper.getList(param);
	}
}
