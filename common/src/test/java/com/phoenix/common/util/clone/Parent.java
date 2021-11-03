package com.phoenix.common.util.clone;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Parent {
    private String id;
    private Child child;
}
