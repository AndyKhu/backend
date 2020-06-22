package com.hris.main.model.system;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Data
@NoArgsConstructor
@Table(name = "tmuser_oto", catalog = "demo", schema = "sys")
public class Tmuser_oto implements Serializable {

    @Id
    @Column(name = "id", length = 50)
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userid", referencedColumnName = "id")
    private Tmuser userid;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "authid", referencedColumnName = "id")
    private Tmauth authid;

    @Basic(optional = false)
    @NotNull
    @Column(name = "status")
    private boolean status;
}
