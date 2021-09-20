package kaz.post.crmserver.entity.payment;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "operators")
@Data
@Accessors(chain = true)
public class Operators implements Serializable {

    @Id
    private String id;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @Column(name = "is_topical")
    private boolean topical;
    @Column(name = "is_popular")
    private Boolean popular;
    @Column(name = "partner_id")
    private String partnerId;

    @Column(name = "enabled_web")
    private boolean enabledWeb;

    @Column(name = "visible_to_mobile")
    private boolean isVisibleToMobile;

    @Column(name = "enabled")
    private boolean enabled;

    @Column(name = "shared")
    private boolean shared;

    private String description;
    private String contract;
    @Column(name = "min_android_version")
    private Integer minAndroidVersion;
    @Column(name = "min_ios_version")
    private Integer minIosVersion;

    @ManyToOne
    @JoinColumn(name = "region_id")
    private Region region;

    @ManyToOne
    @JoinColumn(name = "new_category_id")
    private Category newCategory;

    @Column(name = "img_path")
    private String imgPath;


}

