package com.hris.main.model.system;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hris.main.helper.staticdata.DataAuditTrailType;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@Table(name = "tb_data_audit_trail", catalog = "demo", schema = "sys")
public class TbDataAuditTrail {

    @Id
    @Column(name = "id", length = 50)
    @JsonProperty("id")
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;

    @Column(name = "transaction_id")
    private String transactionId;

    @Enumerated(EnumType.STRING)
    @Column(name = "event_type")
    private DataAuditTrailType eventType;
    
    @Column(name = "event_time")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date eventTime;

    @Column(name = "event_user")
//     @JsonSerialize(using = EventUserSerializer.class)
    private String eventUser;
    
    public TbDataAuditTrail(String transactionId, DataAuditTrailType eventType,
                            String eventUser) {
        this.transactionId = transactionId;
        this.eventType = eventType;
        this.eventUser = eventUser;
        this.eventTime = new Date();
    }            
}
