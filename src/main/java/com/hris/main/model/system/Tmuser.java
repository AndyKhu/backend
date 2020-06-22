package com.hris.main.model.system;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hris.main.helper.staticdata.RoleName;
import com.hris.main.model.generic.DataTrail;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name = "tmuser", catalog = "demo", schema = "sys")
public class Tmuser implements DataTrail {
    @Id
    @Column(name = "id", length = 50)
    @JsonProperty("id")
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "username")
    private String username;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "password")
    private String password;

    @Basic(optional = false)
    @NotNull
    @Column(name = "status")
    private boolean status;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "userid")
    private List<Tmuser_oto> TmuserOto;

    @NotNull
    @Column(name="role")
    private String role;

    public Tmuser(String username,String password,String role) {
        this.username = username;
        this.password = password;
        switch(role) {
	    		case "admin":
                    this.role = RoleName.ROLE_ADMIN.name();
	    			break;
	    		case "pm":
                    this.role = RoleName.ROLE_PM.name();
	    			break;
	    		default:
	    		    this.role = RoleName.ROLE_USER.name();
        	}
    }
}
