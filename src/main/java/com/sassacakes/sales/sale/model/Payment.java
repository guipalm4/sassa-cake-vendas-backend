package com.sassacakes.sales.sale.model;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sassacakes.sales.core.model.AbstractEntity;
import com.sassacakes.sales.sale.dto.MethodPayment;
import com.sassacakes.sales.sale.dto.StatePayment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Payment implements AbstractEntity {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name="sale_id")
    @MapsId
    private Sale sale;

    private StatePayment estate;

    private MethodPayment method;

}
