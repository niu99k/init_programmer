package com.gg.dao;

import com.gg.domain.Role;

import java.util.List;

public interface IRoleDao {
//    查询所有
    List<Role> findAll();
    List<Role> findAllAssociatedWithUser();
}
