package com.zy.aop.field_syn;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * T_LG_CARD_VERSION_ID
 *
 * @author
 */
@Data
public class LgCardVersionIdBean implements Serializable {
    private Long lgCardVersionIdId;

    private String versionNo;

    private String versionIdentifierInfo;

    @Description(value = "身份证号码", cardType = CardTypeEnumDesc.ID)
    private String idCard;


    @Description(value = "姓名", cardType = CardTypeEnumDesc.ID)
    private String realName;

    @Description(value = "身份证有效期", cardType = CardTypeEnumDesc.ID, key = "memberInfo.validDate")
    private String validDate;

    private String dataSource;

    @Description(value = "身份证有效期起", cardType = CardTypeEnumDesc.ID, key = "memberInfo.validStartDate")
    private String validStartDate;

    private int sex;

    private String nationality;

    private String birthDate;

    @Description(value = "身份证正本地址", cardType = CardTypeEnumDesc.ID, isImg = true)
    private String fontImgUrl;

    private String frontOcrId;

    @Description(value = "身份证副本地址", cardType = CardTypeEnumDesc.ID, isImg = true)
    private String backImgUrl;

    private String backOcrId;
    @Description(value = "身份证签发机关", cardType = CardTypeEnumDesc.ID, key = "memberInfo.issue")
    private String idcardIssuingAuthority;

    @Description(value = "身份证住址", cardType = CardTypeEnumDesc.ID, key = "memberInfo.address")
    private String idcardAddress;

    private String cartAuditType;

    private String mainReason;

    private String auditUserId;

    private String auditUserName;

    private String subReason;

    private String riskNote;

    private String suggestion;

    private String cardStatus;

    private String isDel;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createdTime;

    private Date lastModifiedTime;

    private String note;

    @Description(value = "身份证认证有效期", cardType = CardTypeEnumDesc.ID, key = "memberInfo.manualValidDate")
    private String idManualValidDate;
    private static final long serialVersionUID = 1L;

    /**
     * 证件废弃原因
     */
    private String discardReason;

}