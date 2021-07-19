package com.example.Student.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author jobob
 * @since 2021-07-08
 */
@Data
public class CStudent implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String name;


}
