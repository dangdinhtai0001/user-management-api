package com.phoenix.core.repository;

import com.querydsl.core.types.Constant;
import com.querydsl.core.types.Ops;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.core.types.dsl.StringExpression;
import com.querydsl.sql.RelationalPathBase;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class AbstractCoreQueryDslRepositoryTest {
    @Test
    public void testGetTableName() {
        RelationalPathBase<QFwMenu> relationalPathBase = QFwMenu.fwMenu;
        System.out.println(relationalPathBase.getTableName());
    }

    @Test
    public void testGetSchema() {
        RelationalPathBase<QFwMenu> relationalPathBase = QFwMenu.fwMenu;
        System.out.println(relationalPathBase.getSchemaName());
    }

    @Test
    public void testGetPathBuilderMetadata() {
        RelationalPathBase<QFwMenu> relationalPathBase = QFwMenu.fwMenu;
        PathBuilder<QFwMenu> pathBuilder = new PathBuilder<>(QFwMenu.class, relationalPathBase.getTableName());
        System.out.println(pathBuilder.getMetadata());
    }

    @Test
    public void testGetPath() {
        RelationalPathBase<QFwMenu> relationalPathBase = QFwMenu.fwMenu;
        String[] columns = {"description", "displayName", "displayOrder", "icon"};

        List<Path<?>> pathList = relationalPathBase.getColumns();
        List<Path<?>> result = new ArrayList<>(columns.length);

        for (Path path : pathList) {
            for (String column : columns) {
                if (path.getMetadata().getName().equals(column)) {
                    result.add(path);
                    break;
                }
            }
        }


        for (Path path : result) {
            System.out.println(path);
        }
    }

    @Test
    public void testDynamicExpression() {
        Path<QFwMenu> person = Expressions.path(QFwMenu.class, "person");
        Path<String> userId = Expressions.path(String.class, person, "id");

        System.out.println(userId);
    }

    @Test
    public void testDynamicPath() {
        PathBuilder<QFwMenu> entityPath = new PathBuilder<>(QFwMenu.class, "entity");
        // fully generic access
        PathBuilder<Object> pathBuilder0 = entityPath.get("username");
        // .. or with supplied type
        PathBuilder<String> pathBuilder1 = entityPath.get("username", String.class);
        // .. and correct signature
        StringExpression pathBuilder2 = entityPath.getString("userName").lower();

        System.out.println("pathBuilder0: " + pathBuilder0);
        System.out.println("pathBuilder1: " + pathBuilder1);
        System.out.println("pathBuilder2: " + pathBuilder2);
    }

}