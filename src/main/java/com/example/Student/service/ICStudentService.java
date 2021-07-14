package com.example.Student.service;

import com.example.Student.entity.CStudent;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jobob
 * @since 2021-07-08
 */
public interface ICStudentService extends IService<CStudent> {

    CStudent getStudent();
}
