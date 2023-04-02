package kr.co.velysound.common.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * @author 솔루션사업부 BA2
 * @version 1.0
 * @Class Name : FileManageVO.java
 * @Description : FileManageVO Class
 * @Modification Information
 * @
 * @ 수정일      수정자              수정내용
 * @ ---------   ---------   -------------------------------
 * @ 2021.05.02    조용규            최초생성
 * @see
 * @since 2022. 05.02
 */

@Getter
@Setter
@NoArgsConstructor
@ToString
public class FileManageVO implements Serializable {

	private static final long serialVersionUID = 1L;

	private String bsnsId;
	private String fcltyCd;
	private String assetsId;
	private String fileId;
	private String fileSeq;
	private String fileNm;
	private String realFileNm;
	private String regtId;
	private String regtDt;
	private String updtId;
	private String updtDt;
	private String[] fileIds;
	private String[] fileSeqs;
	private String bplcNm;
	private String fcltyDivCd;
	private List<String> AssetsClft;
	private String assetsClftCd;
	private String assetsClftDtlCd;
}
