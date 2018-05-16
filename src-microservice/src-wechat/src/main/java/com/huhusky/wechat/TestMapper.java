package com.huhusky.wechat;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;


@Mapper
public interface TestMapper {
	

	@Select("select * from scope")
	List<Scope> getAll();
}
