package com.example;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CityMapper {

    @Select("SELECT * FROM COMPANY")
    List<Company> findAll();

    @Select("SELECT * FROM COMPANY WHERE LOWER(name) LIKE CONCAT('%', LOWER(#{name}), '%')")
    List<Company> findByName(@Param("name") String name);

}
