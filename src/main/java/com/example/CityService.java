package com.example;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface CityService {

    @Select("SELECT * FROM company")
    List<Company> findAll();

    @Select("SELECT * FROM company WHERE LOWER(name) LIKE CONCAT('%', LOWER(#{name}), '%')")
    List<Company> findByName(String name);

    @Update("UPDATE company SET name = #{name}, website = #{website} WHERE id = #{id}")
    void update(Company company);

}
