package cn.monstar.payment.domain.service;

import java.io.Serializable;
import java.util.List;

/**
 * @author zhangshuai
 * @version 1.0
 * @description BaseService
 * @date 2017/11/22 16:09
 */
public interface BaseService<T, ID extends Serializable> {
    T createSelective(T resource);

    T create(T resource);

    T update(T resource);

    T updateSelective(T resource);

    void delete(ID id);

    T findOne(ID id);

    List<T> findAll();
}
