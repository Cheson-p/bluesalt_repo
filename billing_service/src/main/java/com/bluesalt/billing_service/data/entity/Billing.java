package com.bluesalt.billing_service.data.entity;

import com.bluesalt.billing_service.data.entity.types.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Data
@ToString
public class Billing implements Serializable {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", insertable=true, updatable = true, unique=true, nullable = false)
    private String id;
    @Column
    private String customerId;
    @Column
    @Enumerated(EnumType.STRING)
    private Status status;
    @Column
    private BigDecimal amount;
    @Column
    private String transactionId;
}
