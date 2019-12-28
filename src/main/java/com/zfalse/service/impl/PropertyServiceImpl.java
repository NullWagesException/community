package com.zfalse.service.impl;

import com.zfalse.pojo.Property;
import com.zfalse.service.PropertyService;
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
public class PropertyServiceImpl implements PropertyService {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Property> selectAll() {
        Session session = sessionFactory.getCurrentSession();
        Criteria cri = session.createCriteria(Property.class);
        List<Property> list = cri.list();

        return list;
    }

    @Override
    public Property selectSelf(String user) {
        Session session = sessionFactory.getCurrentSession();
        Criteria cri = session.createCriteria(Property.class);
        List<Property> list = cri.list();
        for (Property property : list) {
            if (property.getUser().equals(user)){
                return property;
            }
        }
        return null;
    }

    @Override
    public int save(Property notice) {
        Session session = sessionFactory.getCurrentSession();
        Serializable id = session.save(notice);
        
            if (id != null){
                return 1;
            }else{
                return 0;
            }
    }

    @Override
    public void update(Property notice) {
        Session session = sessionFactory.getCurrentSession();
        session.update(notice);
        
    }

    @Override
    public List<Property> selectByPage(Integer page, Integer limit) {
        Session session = sessionFactory.getCurrentSession();
        Criteria cri = session.createCriteria(Property.class);
        cri.setFirstResult((page-1)*limit);
        cri.setMaxResults(limit);
        List<Property> list = cri.list();
        Collections.reverse(list);

        return list;
    }

    @Override
    public void delete(Integer id) {
        Session session = sessionFactory.getCurrentSession();
        Property notice = new Property();
        notice.setId(id);
        session.delete(notice);
    }


}
