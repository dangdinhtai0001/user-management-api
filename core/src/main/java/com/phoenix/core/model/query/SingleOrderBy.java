package com.phoenix.core.model.query;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class SingleOrderBy {
    private String key;
    private OrderDirection direction;
}
