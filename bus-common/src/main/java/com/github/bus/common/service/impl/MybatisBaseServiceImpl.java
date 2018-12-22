package com.github.bus.common.service.impl;

import com.github.bus.common.dao.BaseDao;
import com.github.bus.common.model.Pager;
import com.github.bus.common.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * service基础实现类
 */
public class MybatisBaseServiceImpl<T, PK extends Serializable> implements BaseService<T, PK> {

    @Autowired
    private BaseDao<T, PK> baseDao;

    @Override
    public T getById(PK pk) {
        return baseDao.getById(pk);
    }

    @Override
    public int getTotalCount(T obj) {
        if (null == obj) {
            throw new NullPointerException("response bean is null");
        }

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("model", obj);

        return baseDao.getTotalCount(params);
    }

    @Override
    public List<T> findList(T obj) {
        if (null == obj) {
            throw new NullPointerException("response bean is null");
        }

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("model", obj);

        return baseDao.findList(obj);
    }

    @Override
    public Pager findPageList(T obj, Integer pageNumber, Integer pageSize) {
        Integer total = getTotalCount(obj);
        Pager pager = new Pager(pageNumber, pageSize, total);
        pager.setBegin((pageNumber - 1) * pageSize);
        pager.setEnd(pageNumber * pageSize);
        if (total > 0) {
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("offset", (pager.getPageNumber() - 1) * pageSize);
            params.put("rows", pageSize);
            params.put("pager", pager);
            params.put("model", obj);

            List<T> list = baseDao.findPageList(params);

            pager.setDatas(list); // 数据库集合
        }

        return pager;
    }
//
//    @Override
//    @Transactional
//    public int save(T obj) {
//        if (null == obj) {
//            throw new NullPointerException("response bean is null");
//        }
//        return baseDao.save(obj);
//    }
//
//    @Override
//    @Transactional
//    public int update(T obj) {
//        if (null == obj) {
//            throw new NullPointerException("response bean is null");
//        }
//
//        return baseDao.update(obj);
//    }
//
//    @Override
//    @Transactional
//    public int delete(PK pk) {
//        return baseDao.delete(pk);
//    }

}
