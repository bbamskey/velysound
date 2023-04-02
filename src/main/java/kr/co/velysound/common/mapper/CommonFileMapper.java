package kr.co.velysound.common.mapper;

import kr.co.velysound.common.vo.FileManageVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommonFileMapper {

	public void save(FileManageVO vo);

	public int delete(FileManageVO vo);

	public List<FileManageVO> selectList(FileManageVO vo);

	public FileManageVO selectFileId(FileManageVO vo);

	public FileManageVO select(FileManageVO vo);

	public int getMaxSeq(String fileId);
}
