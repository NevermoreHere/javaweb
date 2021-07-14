package com.example.Student.controller;


import com.example.Student.entity.CStudent;
import com.example.Student.service.ICStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author jobob
 * @since 2021-07-08
 */
@RestController
@RequestMapping("/Student/c-student")
public class CStudentController {
    @Autowired
    ICStudentService icStudentService;

    @GetMapping("/get-student")
    public CStudent getStudent(){
        return icStudentService.getStudent();
    }
}
