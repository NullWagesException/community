package com.zfalse.service.impl;

import com.zfalse.pojo.Lost;
import com.zfalse.service.LostService;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

/**
 * @Author: nullWagesException
 * @Date: 2019/12/22 17:54
 * @Description:
 */
@Service
public class LostServiceImpl implements LostService {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Lost> selectAll() {
        Session session = sessionFactory.getCurrentSession();
        Criteria cri = session.createCriteria(Lost.class);
        List<Lost> list = cri.list();

        return list;
    }

    @Override
    public List<Lost> selectSelf(String user) {
        Session session = sessionFactory.getCurrentSession();
        Criteria cri = session.createCriteria(Lost.class);
        cri.add(Restrictions.eq("user", user));
        List<Lost> list = cri.list();

        return list;
    }

    @Override
    public int save(Lost notice) {
        Session session = sessionFactory.getCurrentSession();
        Serializable id = session.save(notice);
        
            if (id != null){
                return 1;
            }else{
                return 0;
            }
    }

    @Override
    public void update(Lost notice) {
        Session session = sessionFactory.getCurrentSession();
        session.update(notice);
        
    }

    @Override
    public List<Lost> selectByPage(Integer page, Integer limit) {
        Session session = sessionFactory.getCurrentSession();
        Criteria cri = session.createCriteria(Lost.class);
        cri.setFirstResult((page-1)*limit);
        cri.setMaxResults(limit);
        List<Lost> list = cri.list();
        Collections.reverse(list);

        return list;
    }

    @Override
    public List<Lost> selectByPage(Integer page, Integer limit,String user) {
        Session session = sessionFactory.getCurrentSession();
        Criteria cri = session.createCriteria(Lost.class);
        cri.add(Restrictions.eq("user", user));
        cri.setFirstResult((page-1)*limit);
        cri.setMaxResults(limit);
        List<Lost> list = cri.list();
        Collections.reverse(list);

        return list;
    }

    @Override
    public void delete(Integer id) {
        Session session = sessionFactory.getCurrentSession();
        Lost notice = new Lost();
        notice.setId(id);
        session.delete(notice);
    }


}
