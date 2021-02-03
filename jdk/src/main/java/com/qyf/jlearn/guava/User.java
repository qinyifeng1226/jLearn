package com.qyf.jlearn.guava;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 描述：
 *
 * @author qinyifeng
 * @version v1.0
 * @since 2021/2/3 14:06
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private Integer id;
    private String name;

//    public User(Integer id, String name) {
//        this.id = id;
//        this.name = name;
//    }
}
