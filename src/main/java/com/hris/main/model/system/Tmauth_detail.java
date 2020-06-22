package com.hris.main.model.system;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@NoArgsConstructor
@Table(name = "tmauth_detail", catalog = "demo", schema = "sys")
public class Tmauth_detail implements Serializable {
    @Id
    @Column(name = "id", length = 50)
    @JsonProperty("id")
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "authid", referencedColumnName = "id")
    private Tmauth authid;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "menuid", referencedColumnName = "id")
    private Tmmenu menuid;
}
