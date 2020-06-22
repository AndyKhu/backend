package com.hris.main.model.system;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.hris.main.model.generic.DataTrail;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name = "tmmenu", catalog = "demo", schema = "sys")
public class Tmmenu implements DataTrail {
    @Id
    @Column(name = "id", length = 50)
    @JsonProperty("id")
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "parentid")
    private List<Tmmenu> children;

    @Column(name = "parentid")
    private String parentid;

    @Column(name = "name")
    private String name;

    @Column(name = "type")
    private String type;

    @Column(name = "level")
    private Integer level;

    @Column(name = "status")
    private Boolean status;
}
