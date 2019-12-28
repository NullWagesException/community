package com.zfalse.service.impl;

import com.zfalse.pojo.FeedBack;
import com.zfalse.service.FeedBackService;
import com.zfalse.service.FeedBackService;
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
public class FeedBackServiceImpl implements FeedBackService {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<FeedBack> selectAll() {
        Session session = sessionFactory.getCurrentSession();
        Criteria cri = session.createCriteria(FeedBack.class);
        List<FeedBack> list = cri.list();

        return list;
    }

    @Override
    public List<FeedBack> selectSelf(String user) {
        Session session = sessionFactory.getCurrentSession();
        Criteria cri = session.createCriteria(FeedBack.class);
        cri.add(Restrictions.eq("user", user));
        List<FeedBack> list = cri.list();

        return list;
    }

    @Override
    public int save(FeedBack feedBack) {
        Session session = sessionFactory.getCurrentSession();
        Serializable id = session.save(feedBack);
        
            if (id != null){
                return 1;
            }else{
                return 0;
            }
    }

    @Override
    public void update(FeedBack feedBack) {
        Session session = sessionFactory.getCurrentSession();
        session.update(feedBack);
        
    }

    @Override
    public List<FeedBack> selectByPage(Integer page, Integer limit) {
        Session session = sessionFactory.getCurrentSession();
        Criteria cri = session.createCriteria(FeedBack.class);
        cri.setFirstResult((page-1)*limit);
        cri.setMaxResults(limit);
        List<FeedBack> list = cri.list();
        Collections.reverse(list);

        return list;
    }

    @Override
    public List<FeedBack> selectByPage(Integer page, Integer limit,String user) {
        Session session = sessionFactory.getCurrentSession();
        Criteria cri = session.createCriteria(FeedBack.class);
        cri.add(Restrictions.eq("user", user));
        cri.setFirstResult((page-1)*limit);
        cri.setMaxResults(limit);
        List<FeedBack> list = cri.list();
        Collections.reverse(list);

        return list;
    }

    @Override
    public void delete(Integer id) {
        Session session = sessionFactory.getCurrentSession();
        FeedBack feedBack = new FeedBack();
        feedBack.setId(id);
        session.delete(feedBack);
    }


}
