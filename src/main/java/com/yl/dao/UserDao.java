package com.yl.dao;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.yl.entity.UserEntity;

@Component

public interface UserDao extends IBaseDao<UserEntity, Serializable> {

	
	
}
