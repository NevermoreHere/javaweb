package com.example.Student.service.impl;

import com.example.Student.entity.CStudent;
import com.example.Student.mapper.CStudentMapper;
import com.example.Student.service.ICStudentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jobob
 * @since 2021-07-08
 */
@Service
public class CStudentServiceImpl extends ServiceImpl<CStudentMapper, CStudent> implements ICStudentService {
    @Autowired
    CStudentMapper cStudentMapper;

    public CStudent getStudent(){
        CStudent s = cStudentMapper.selectById(1L);
        System.out.println(s);
        return s;
    }
}