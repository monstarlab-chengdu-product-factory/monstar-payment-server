/**
 * System Name    : xunyingge
 * SubSystem Nmae : Product
 * Class Name     : ProductService
 * Description    : 公共接口类
 */
package cn.monstar.payment.domain.service;

import java.io.Serializable;
import java.util.List;

/**
 * Revision ：
 * Rev -- Date---------Name------------Note
 * 1.0    2017.07.04   MONSTAR-LAB-CN  Created
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
