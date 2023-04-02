package kr.co.velysound.service;

import kr.co.velysound.common.util.MoMap;
import kr.co.velysound.mapper.VideoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VideoService {
	private final VideoMapper videoMapper;

	public List<MoMap> getList(MoMap param) {
		return videoMapper.getList(param);
	}
}
