/*
 * System Name    : BaseServiceImpl
 * SubSystem Name : base
 * Class Name     : BaseServiceImpl
 * Description    : BaseServiceImpl
 */

package cn.monstar.payment.domain.service;

import cn.monstar.payment.domain.dao.BaseMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

/**
 * Revision ：
 * Rev -- Date---------Name------------Note
 * 1.0    2017.06.12   MONSTAR-LAB-CN  Created
 */
public abstract class BaseServiceImpl <T, ID extends Serializable, R extends BaseMapper<T, ID>> implements
        BaseService<T, ID> {

    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    protected R repository;

    public abstract void setRepository(R repository) ;

    @Override
    @Transactional(readOnly = false)
    public T createSelective(T resource) {
        repository.insertSelective(resource);
        return resource;
    }

    @Override
    @Transactional(readOnly = false)
    public T create(T resource) {
        repository.insert(resource);
        return resource;
    }

    @Override
    @Transactional(readOnly = false)
    public T update(T resource) {
        repository.updateByPrimaryKey(resource);
        return resource;
    }

    @Override
    @Transactional(readOnly = false)
    public T updateSelective(T resource) {
        repository.updateByPrimaryKeySelective(resource);
        return resource;
    }

    @Override
    @Transactional(readOnly = false)
    public void delete(ID id) {
        repository.deleteByPrimaryKey(id);
    }

    @Override
    public T findOne(ID id) {
        return repository.selectByPrimaryKey(id);
    }

    @Override
    public List<T> findAll() {
        return repository.selectAll();
    }
}
