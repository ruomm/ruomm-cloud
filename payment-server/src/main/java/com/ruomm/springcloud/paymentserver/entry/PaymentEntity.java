package com.ruomm.springcloud.paymentserver.entry;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * tableName：tbl_payment
 * tableRemarks：Payment表
 * @copyright wanruome-2024
 * @author wanruome
 * @create 2024-04-20 21:37
 *
 * @mbg.generated do_not_delete_during_merge 2024-04-20 21:37:49
 */
@Entity
@Getter
@Setter
@ToString
@Table(name = "tbl_payment")
public class PaymentEntity {
    /**
     * column: tbl_payment.id, datatype: BIGINT, length: 19, nullable: false
     * remark: 主键ID
     */
    @Id
    @Column( name = "id" )
    private Long id;

    /**
     * column: tbl_payment.serial, datatype: VARCHAR, length: 200, nullable: true
     * remark: 名称
     */
    @Column( name = "serial" )
    private String serial;
}