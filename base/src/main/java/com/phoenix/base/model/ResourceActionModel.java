package com.phoenix.base.model;

import com.google.common.base.Objects;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ResourceActionModel {
    private Long id;
    private String resource;
    private String action;
    private String beanName;
    private String displayResource;
    private String displayAction;
    private String description;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ResourceActionModel)) return false;
        ResourceActionModel that = (ResourceActionModel) o;
        return Objects.equal(id, that.id) && Objects.equal(resource, that.resource) && Objects.equal(action, that.action) && Objects.equal(beanName, that.beanName) && Objects.equal(displayResource, that.displayResource) && Objects.equal(displayAction, that.displayAction) && Objects.equal(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id, resource, action, beanName, displayResource, displayAction, description);
    }
}
