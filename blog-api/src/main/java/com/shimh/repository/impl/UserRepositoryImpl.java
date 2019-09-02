//package com.shimh.repository.impl;
//
//import com.shimh.repository.wrapper.UserWrapper;
//import com.shimh.vo.UserVo;
//import org.apache.shiro.session.Session;
//
//import javax.persistence.EntityManager;
//import javax.persistence.PersistenceContext;
//import java.util.List;
//
//public class UserRepositoryImpl implements UserWrapper {
//
//    @PersistenceContext
//    private EntityManager em;
//
//    @Override
//    public List<UserVo>findAllDetails(){
//
//    }
//
//    @Override
//    public UserVo getUserDetail(Integer userId){
//
//    }
//
//    private Session getSession(){return em.unwrap(Session.class);}
//
//
//}
