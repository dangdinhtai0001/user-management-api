package com.phoenix.core.model.specification;

import javax.annotation.Nullable;
import javax.persistence.criteria.*;

public class NotLikeSpecification<T> extends AbstractSpecification<T> {
    private final String property;
    private final String[] patterns;

    public NotLikeSpecification(String property, String... patterns) {
        this.property = property;
        this.patterns = patterns;
    }

    @Override
    public Predicate toPredicate(@Nullable Root<T> root, @Nullable CriteriaQuery<?> query, @Nullable CriteriaBuilder cb) {
        assert cb != null;
        From from = getRoot(property, root);
        String field = getProperty(property);
        if (patterns.length == 1) {
            return cb.like(from.get(field), patterns[0]).not();
        }
        Predicate[] predicates = new Predicate[patterns.length];
        for (int i = 0; i < patterns.length; i++) {
            predicates[i] = cb.like(from.get(field), patterns[i]).not();
        }
        return cb.or(predicates);
    }
}
