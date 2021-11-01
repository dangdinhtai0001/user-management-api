package com.phoenix.core.repository;

import com.phoenix.common.structure.DefaultTuple;
import com.phoenix.core.model.query.OrderBy;
import com.phoenix.core.model.query.QueryExpression;
import com.phoenix.core.model.query.SearchCriteria;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Predicate;
import com.querydsl.sql.RelationalPathBase;
import com.querydsl.sql.SQLQuery;
import com.querydsl.sql.SQLQueryFactory;
import com.querydsl.sql.dml.SQLInsertClause;
import lombok.extern.log4j.Log4j2;

import javax.transaction.Transactional;
import java.util.List;

@Log4j2
@Transactional
public abstract class AbstractSingleQueryDslRepository extends AbstractCoreQueryDslRepository implements SingleQueryDslRepository {

    protected final SQLQueryFactory queryFactory;

    protected AbstractSingleQueryDslRepository(SQLQueryFactory queryFactory) {
        super(queryFactory);
        this.queryFactory = queryFactory;
    }

    //===================================================

    @Override
    public List<Tuple> defaultFindAll(String... columns) {
        SQLQuery query = createSingleSelectQuery(columns);

        log.debug(query.getSQL().getSQL());
        return query.fetch();
    }

    @Override
    public List<Tuple> defaultFindAll(List<SearchCriteria> criteriaList, String... columns) {
        SQLQuery query = createSingleSelectQuery(criteriaList, columns);

        log.debug(query.getSQL().getSQL());
        return query.fetch();
    }

    @Override
    public List<Tuple> defaultFindAll(List<SearchCriteria> criteriaList, OrderBy[] orders, String... columns) {
        SQLQuery query = createSingleSelectQuery(criteriaList, orders, columns);

        log.debug(query.getSQL().getSQL());
        return query.fetch();
    }
    //===================================================

    @Override
    public <T extends RelationalPathBase<T>> Long defaultInsert(List<DefaultTuple> defaultTuples) {
        RelationalPathBase<T> relationalPathBase = getRelationalPathBase();
        SQLInsertClause sqlInsertClause = createInsertClause(relationalPathBase, defaultTuples);

        if (sqlInsertClause == null) {
            return 0L;
        }

        return sqlInsertClause.execute();
    }

    @Override
    public <T extends RelationalPathBase<T>> Long defaultInsert(DefaultTuple defaultTuple) {
        RelationalPathBase<T> relationalPathBase = getRelationalPathBase();
        SQLInsertClause sqlInsertClause = createInsertClause(relationalPathBase, defaultTuple);

        if (sqlInsertClause == null) {
            return 0L;
        }

        return sqlInsertClause.execute();
    }

    //===================================================

    /**
     * @return (something like : QFwResourceAction.class)
     */
    protected abstract <T extends RelationalPathBase<T>> Class<T> getRelationalPathBaseClass();
    //protected abstract  Class<? extends RelationalPathBase<?>> getRelationalPathBaseClass();

    /**
     * @return (something like : QFwResourceAction.fwResourceAction)
     */
    protected abstract <T extends RelationalPathBase<T>> RelationalPathBase<T> getRelationalPathBase();

    //===================================================

    /**
     * @param columns Danh sách các columns cần select (trong mệnh đề where)
     * @param <T>     Ví dụ: QFwMenu.fwMenu {@link com.querydsl.sql.RelationalPathBase}
     * @return Xem tại {@link AbstractCoreQueryDslRepository#createSelectQuery(RelationalPathBase, String...)}
     */
    protected <T extends RelationalPathBase<T>> SQLQuery<Tuple> createSingleSelectQuery(String... columns) {
        RelationalPathBase<T> relationalPathBase = getRelationalPathBase();
        return createSelectQuery(relationalPathBase, columns);
    }

    protected <T extends RelationalPathBase<T>> SQLQuery createSingleSelectQuery(List<SearchCriteria> criteriaList, List<QueryExpression> queryExpressions, String... fields) {
        SQLQuery query = createSingleSelectQuery(fields);
        RelationalPathBase<T> relationalPathBase = getRelationalPathBase();
        List<Predicate> predicates = getPredicateFromSearchCriteria(relationalPathBase, criteriaList);
        addWhereClause(query, predicates);
        addWhereClause(queryExpressions, query);

        return query;
    }

    protected <T extends RelationalPathBase<T>> SQLQuery createSingleSelectQuery(List<SearchCriteria> criteriaList, String... fields) {
        SQLQuery query = createSingleSelectQuery(fields);
        RelationalPathBase<T> relationalPathBase = getRelationalPathBase();
        List<Predicate> predicates = getPredicateFromSearchCriteria(relationalPathBase, criteriaList);
        addWhereClause(query, predicates);

        return query;
    }

    protected <T extends RelationalPathBase<T>> SQLQuery createSingleSelectQuery(OrderBy orderBy, String... fields) {
        SQLQuery query = createSingleSelectQuery(fields);
        RelationalPathBase<T> relationalPathBase = getRelationalPathBase();
        addOrderBy(query, relationalPathBase, orderBy);

        return query;
    }

    protected <T extends RelationalPathBase<T>> SQLQuery createSingleSelectQuery(List<SearchCriteria> criteriaList, List<QueryExpression> queryExpressions,
                                                                                 OrderBy orderBy, String... fields) {
        SQLQuery query = createSingleSelectQuery(criteriaList, queryExpressions, fields);
        RelationalPathBase<T> relationalPathBase = getRelationalPathBase();
        addOrderBy(query, relationalPathBase, orderBy);

        return query;
    }

    protected <T extends RelationalPathBase<T>> SQLQuery createSingleSelectQuery(List<SearchCriteria> criteriaList, List<QueryExpression> queryExpressions,
                                                                                 OrderBy[] orders, String... fields) {
        SQLQuery query = createSingleSelectQuery(criteriaList, queryExpressions, fields);
        RelationalPathBase<T> relationalPathBase = getRelationalPathBase();
        for (OrderBy orderBy : orders) {
            addOrderBy(query, relationalPathBase, orderBy);
        }

        return query;
    }

    protected <T extends RelationalPathBase<T>> SQLQuery createSingleSelectQuery(List<SearchCriteria> criteriaList, OrderBy[] orders, String... fields) {
        SQLQuery query = createSingleSelectQuery(criteriaList, fields);
        RelationalPathBase<T> relationalPathBase = getRelationalPathBase();
        for (OrderBy orderBy : orders) {
            addOrderBy(query, relationalPathBase, orderBy);
        }

        return query;
    }


    //===================================================


    @Override
    protected DefaultTuple getRelationalPathMap() {
        return null;
    }
}
