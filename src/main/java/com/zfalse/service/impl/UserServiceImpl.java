package com.zfalse.service.impl;

import com.zfalse.pojo.User;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.zfalse.service.UserService;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

/**
 * @Author: nullWagesException
 * @Date: 2019/12/21 22:23
 * @Description:
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public User login(User user) {
        List<User> users = selectUserByNamePWD(user.getUsername(),user.getPassword());
        if (users.size() == 1){
            return users.get(0);
        }else{
            return null;
        }
    }

    @Override
    @Transactional(readOnly=true)
    public int register(User user) {
        Session session = sessionFactory.getCurrentSession();
        List<User> users = selectUserByName(user.getUsername());
        if (users.size() > 0){
            
            return -1;
        }else{
            Serializable id = session.save(user);
            
            if (id != null){
                return 1;
            }else{
                return 0;
            }
        }
    }

    @Override
    public List<User> selectUserByName(String username) {
        Session session = sessionFactory.getCurrentSession();
        Criteria cri = session.createCriteria(User.class);
        cri.add(Restrictions.eq("username", username));
        List<User> list = cri.list();
        return list;
    }

    @Override
    public List<User> selectUserByNamePWD(String username, String password) {
        Session session = sessionFactory.getCurrentSession();
        Criteria cri = session.createCriteria(User.class);
        cri.add(Restrictions.eq("username", username));
        cri.add(Restrictions.eq("password", password));
        List<User> list = cri.list();

        return list;
    }

    @Override
    public void update(User user) {
        Session session = sessionFactory.getCurrentSession();
        session.update(user);
    }

    @Override
    public List<User> selectByPage(Integer page, Integer limit) {
        Session session = sessionFactory.getCurrentSession();
        Criteria cri = session.createCriteria(User.class);
        cri.setFirstResult((page-1)*limit);
        cri.setMaxResults(limit);
        List<User> list = cri.list();
        Collections.reverse(list);

        return list;
    }

    @Override
    public void delete(User user) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(user);
    }


}
