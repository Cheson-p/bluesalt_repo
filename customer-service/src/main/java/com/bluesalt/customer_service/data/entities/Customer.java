package com.bluesalt.customer_service.data.entities;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Data
public class Customer implements Serializable {
   @Id
   @GeneratedValue(generator = "uuid")
   @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
   @Column(name = "id", insertable=true, updatable = true, unique=true, nullable = false)
   private String id;
   @Column
   private String firstname;
   @Column
   private String lastname;
   @Column
   private BigDecimal balance;
}
