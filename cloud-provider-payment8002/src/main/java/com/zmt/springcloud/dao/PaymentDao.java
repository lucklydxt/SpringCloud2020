package com.zmt.springcloud.dao;

import com.zmt.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * Payment表数据库访问层
 */
@Mapper
public interface PaymentDao {

    /**
     * 新增一条记录
     * @param payment 实例对象
     * @return 返回影响行数
     */
    int insert(Payment payment);

    /**
     * 通过id查询单条数据
     * @param id
     * @return
     */
    Payment selectPaymentById(@Param("id") long id);
}
