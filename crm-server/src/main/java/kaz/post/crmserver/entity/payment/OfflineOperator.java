package kaz.post.crmserver.entity.payment;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Entity
@Table(name = "t_offline_operators")
@Data
@Accessors(chain = true)
@RequiredArgsConstructor

public class OfflineOperator {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "mun_dea_code")
    private String munDeaCode;
    @Column(name = "dep_code")
    private String depCode;
    @Column(name = "long_name")
    private String longName;
    @Column(name = "usr_code")
    private String usrCode;
    @Column(name = "contractor_name")
    private String contractorName;
    @Column(name = "name", length = 500)
    private String name;
    @Column(name = "form_code")
    private String formCode;
    @Column(name = "contractor_bin")
    private String contractorBin;
    @Column(name = "group_number")
    private String groupNumber;
    @Column(name = "stamp_name")
    private String stampName;
    @Column(name = "receipt_name")
    private String receiptName;
    @Column(name = "operator_id")
    private String operatorId;
    @Column(name = "p_item_key")
    private String pItemKey;
    @Column(name = "enabled")
    private boolean enabled = false;
    @Column(name = "epd")
    private Boolean epd;
    @Column(name = "opv")
    private Boolean opv;
    @Column(name = "grp_name")
    private String grpName;
    @Column(name = "payment_name")
    private String typePaymentName;
    @Column(name = "type_payment")
    private String typePayment;
    @Column(name = "cnt_usl")
    private String cntUsl;
    @Column(name = "bnfr")
    private String bnfr;
    @Column(name = "code_acc")
    private String codeAcc;
    @Column(name = "code_prpl")
    private String codePrPl;
    @Column(name = "subcategory")
    private String subcategory;
    @Column(name = "category_id")
    private String categoryId;
    @Column(name = "region_id")
    private Integer regionId;
    @Column(name = "img_Path")
    private String imgPath;
    @Column(name = "is_popular")
    private Boolean popular;
    @Column(name = "enabled_web")
    private Boolean enabledWeb;
    @Column(name = "visible_to_mobile")
    private Boolean visibleToMobile;
    @Column(name = "shared")
    private Boolean shared;
    @Column(name = "min_android_version")
    private Integer minAndroidVersion;
    @Column(name = "min_ios_version")
    private Integer minIosVersion;
    @Column(name = "is_online")
    private Boolean online;
    @Column(name = "visible_to_web")
    private Boolean visibleToWeb;

    public OfflineOperator(Long id, String name, String operatorId, String imgPath, String munDeaCode,
                           String contractorName, Boolean enabled, Boolean enabledWeb, Integer minAndroidVersion,
                           Integer minIosVersion, Boolean visibleToMobile, Boolean visibleToWeb, String categoryId) {
        this.id = id;
        this.name = name;
        this.operatorId = operatorId;
        this.imgPath = imgPath;
        this.munDeaCode = munDeaCode;
        this.contractorName = contractorName;
        this.enabled = enabled;
        this.enabledWeb = enabledWeb;
        this.minAndroidVersion = minAndroidVersion;
        this.minIosVersion = minIosVersion;
        this.visibleToMobile = visibleToMobile;
        this.visibleToWeb = visibleToWeb;
        this.categoryId = categoryId;
    }
}