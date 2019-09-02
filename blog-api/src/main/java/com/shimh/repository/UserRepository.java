package com.shimh.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shimh.entity.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;


public interface UserRepository extends JpaRepository<User, Long> {

    User findByAccount(String account);



    @Transactional
    @Modifying
    @Query("update User u set u.password = ?1 where u.account = ?2")
    int modifyPasswordByUserAccount(String password, String account);
}
