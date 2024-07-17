package com.zy.aop.field_syn;

import lombok.Data;

/**
 * @Author XUWENLONG
 * @Date 2022/3/28 19:01
 * @Version 1.0.1
 */
@Data
public class RpcBase2225Bean {
	private String stateCode;
	private String stateDesc;
	private String ocrIdcardFaceId;
	@Description(value = "姓名", cardType = CardTypeEnumDesc.ID)
	private String name;
	private String sex;
	private String idcard;
	private String birth;
	private String nationality;
	private String address;
}
