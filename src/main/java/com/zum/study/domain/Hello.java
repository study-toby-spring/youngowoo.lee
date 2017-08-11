package com.zum.study.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by Joeylee on 2017-07-29.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Hello {

    private int id;
    private String title;

    @Override
    public String toString() {
        return String.format("id : " + id + "title : " + title);
    }
}
