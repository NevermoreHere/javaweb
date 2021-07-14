package com.example.Student.entity;

import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author jobob
 * @since 2021-07-08
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class CStudent implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String name;


}
