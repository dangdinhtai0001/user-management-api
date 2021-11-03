package com.phoenix.common.util.clone;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Child {
    private String id;
    private Foo foo;
}
