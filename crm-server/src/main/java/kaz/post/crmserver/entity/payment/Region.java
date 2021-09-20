package kaz.post.crmserver.entity.payment;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "regions")
@Data
@Accessors(chain = true)
public class Region {

    @Id
    private Long id;
    @Column(name = "name_ru")
    private String nameRu;
    @Column(name = "name_kk")
    private String nameKk;
    @Column(name = "name_en")
    private String nameEn;
    private String index;
    private Integer priority;
    @Column(name = "dep_index")
    private String depIndex;


}
