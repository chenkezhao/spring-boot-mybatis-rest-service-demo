package com.chenkezhao.test;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Mapper
@Repository
public interface GreetingMapper {

    @Select("select * from greeting")
    Collection<Greeting> findAll();
}
