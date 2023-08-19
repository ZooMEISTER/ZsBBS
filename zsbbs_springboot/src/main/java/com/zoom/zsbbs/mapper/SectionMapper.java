package com.zoom.zsbbs.mapper;

/*
 *    Created by ZooMEISTER
 *    on 2023/8/19 15:16
 *
 */

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zoom.zsbbs.entity.Section;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface SectionMapper extends BaseMapper<Section> {

    @Select("SELECT * FROM sys_section")
    List<Section> queryAllSection();

    @Select("SELECT * FROM sys_section WHERE sectionid=#{sectionid}")
    Section querySingleSection(int sectionid);

}
