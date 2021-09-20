package kaz.post.crmserver.entity.payment;

import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "categories")
@Data
@Accessors(chain = true)
public class Category implements Serializable {

    @Id
    private String id;

    @Column(name = "enabled")
    private boolean enabled;

    private String description;
    @Column(name = "description_kk")
    private String descriptionKk;
    @Column(name = "description_en")
    private String descriptionEn;
    private Integer priority;
    @Column(name = "img_path")
    private String imgPath;
    @Column(name = "enabled_web")
    private boolean enabledWeb;
    @Column(name = "enabled_mobile")
    private boolean enabledMobile;
}
