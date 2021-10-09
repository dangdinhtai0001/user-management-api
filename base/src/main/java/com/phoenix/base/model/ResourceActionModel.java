package com.phoenix.base.model;

import com.google.common.base.Objects;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ResourceActionModel {
    private Integer id;
    private String action;
    private String resource;
    private String beanName;
    private String displayAction;
    private String displayResource;
    private String description;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ResourceActionModel)) return false;
        ResourceActionModel that = (ResourceActionModel) o;
        return Objects.equal(resource, that.resource)
                && Objects.equal(action, that.action);

    }

    @Override
    public int hashCode() {
        return Objects.hashCode(resource, action);
    }
}
