package com.zy.aop.field_syn;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

/**
 * 
 * @author XUWENLONG
 * @time 2019年7月31日 上午11:49:04
 *
 */
@Data
public class RpcLg1800Bean {

	/**
	 * 是否存在
	 */
	private String is_exist;
	/**
	 * 身份证号码
	 */
	private String id_card;
	/**
	 * 真实姓名
	 */
	@Description(value = "姓名", cardType = CardTypeEnumDesc.ID)
	private String real_name;
	/**
	 * 性别
	 */
	private String gender;
	/**
	 * 有效期
	 */
	private String valid_date;

	private String id_manual_valid_date;
	private String idcard_issuing_authority;
	private String idcard_address;
	/**
	 * 身份证正面
	 */
	private String font_img_url;
	/**
	 * 身份证反面
	 */
	private String back_img_url;
	/**
	 * 人脸图片
	 */
	private String face_img_url;
	/**
	 * 驾驶证正页图片
	 */
	private String dl_font_img_url;
	/**
	 * 驾驶证副页图片
	 */
	private String dl_back_img_url;
	/**
	 * 准驾车型
	 */
	private String driver_license_type;
	/**
	 * 数据来源,0：PC业务记录
1：微信侧
2：经纪人微信
3：外部接口认证
4：快路宝
5：好运宝
6：网页版管车宝
	 */
	private String data_source;
	/**
	 * 数据级别,10活体   
20ocr  
30实名
	 */
	private String data_level;
	/**
	 * 从业资格证图片
	 */
	private String qual_cert_img_url;
	/**
	 * 从业资格证编号
	 */
	private String qual_cert_no;
	/**
	 * 从业资格证有效期
	 */
	private String qual_cert_valid_date;
	private String qual_manual_valid_date;
	/**
	 * 身份证正面缩略图
	 */
	private String font_small_img_url;
	/**
	 * 身份证反面缩略图
	 */
	private String back_small_img_url;
	/**
	 * 驾驶证正面缩略图
	 */
	private String dl_font_small_img_url;
	/**
	 * 驾驶证反面缩略图
	 */
	private String dl_back_small_img_url;
	/**
	 * 从业资格证缩略图
	 */
	private String qual_cert_small_img_url ;
	/**
	 * 驾驶证有效期
	 */
	private String dl_valid_date;
	private String dl_manual_valid_date;


	/**
	 * 身份证正面的ocrId
	 */
	private String idcard_ocr_front_id;
	/**
	 * 身份证反面的ocrId
	 */
	private String idcard_ocr_back_id;
	/**
	 * 驾驶证正面的ocrId
	 */
	private String driverlic_ocr_front_id;
	/**
	 * 驾驶证反面的ocrId
	 */
	private String driverlic_ocr_back_id;
	/**
	 * 阿里的身份证ocrid
	 */
	private String face_idcard_ocr_id;
	/**
	 * 从业资格证ocrId
	 */
	private String qual_ocr_id;
	private String qual_cert_first_issue_date;
	private String qual_cert_issue_date;
	private String qual_cert_issuing_authority;
	
	private String dl_ele_type;
	private String created_time;
	private String lg_created_time;


	private String tqc_proof_img_url;
	private String qual_cxkh_date;

	public boolean isExist() {
		return StringUtils.equals("0", this.is_exist);
	}

	/**
     * 判断会员体系是否存在
     *
     * @return
     */
    @JsonIgnore
    public boolean isMemberInfoExist() {
        return StringUtils.equals("0", is_exist);
    }

	/**
	 * 身份证证件版本号
	 */
	private String idCardVersionNo;


	/**
	 * 驾驶证证件版本号
	 */
	private String dlCardVersionNo;

	/**
	 * 从业证件版本号
	 */
	private String tqcCardVersionNo;


	private String id_valid_start_date;


	private String dl_valid_start_date;

}
