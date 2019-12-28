package com.zfalse.service.impl;

import com.zfalse.pojo.Notice;
import com.zfalse.service.NoticeService;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

/**
 * @Author: nullWagesException
 * @Date: 2019/12/22 17:54
 * @Description:
 */
@Service
public class NoticeServiceImpl implements NoticeService {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Notice> selectAll() {
        Session session = sessionFactory.getCurrentSession();
        Criteria cri = session.createCriteria(Notice.class);
        List<Notice> list = cri.list();
        Collections.reverse(list);
        
        return list;
    }

    @Override
    public int save(Notice notice) {
        Session session = sessionFactory.getCurrentSession();
        Serializable id = session.save(notice);
        
            if (id != null){
                return 1;
            }else{
                return 0;
            }
    }

    @Override
    public void update(Notice notice) {
        Session session = sessionFactory.getCurrentSession();
        session.update(notice);
        
    }

    @Override
    public List<Notice> selectByPage(Integer page, Integer limit) {
        Session session = sessionFactory.getCurrentSession();
        Criteria cri = session.createCriteria(Notice.class);
        cri.setFirstResult((page-1)*limit);
        cri.setMaxResults(limit);
        List<Notice> list = cri.list();
        Collections.reverse(list);

        return list;
    }

    @Override
    public void delete(Integer id) {
        Session session = sessionFactory.getCurrentSession();
        Notice notice = new Notice();
        notice.setId(id);
        session.delete(notice);
    }


}
