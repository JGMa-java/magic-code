package com.jgma.xcode.mapper;

import com.jgma.xcode.pojo.TUser;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {

    List<TUser>  queryAllWithMaster();
}
