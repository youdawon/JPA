package jpa.database.auth.repository.impl;

import jpa.config.SvcQuerydslRepositorySupport;
import jpa.database.common.SearchFilter;
import jpa.database.auth.entity.AdminUser;
import jpa.database.auth.entity.QAdminUser;
import jpa.database.auth.repository.AdminUserCustomRepository;
import jpa.util.Consts;
import jpa.util.QueryDslUtil;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.DateTimeExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.sql.Timestamp;
import java.util.List;

public class AdminUserRepositoryImpl extends SvcQuerydslRepositorySupport implements AdminUserCustomRepository {

    private JPAQueryFactory svcJpaQueryFactory;

    private QAdminUser qAdminUser = QAdminUser.adminUser;

    public AdminUserRepositoryImpl(JPAQueryFactory svcJpaQueryFactory) {
        super(AdminUser.class);
        this.svcJpaQueryFactory = svcJpaQueryFactory;
    }

    @Override
    public Page<AdminUser> findAllForPagination(SearchFilter searchFilter, Pageable pageable) {

        List<OrderSpecifier> orders = QueryDslUtil.getAllOrderSpecifiers(pageable, qAdminUser);

        QueryResults<AdminUser> queryResults = svcJpaQueryFactory.select(qAdminUser)
                .from(qAdminUser)
                .where(setBooleanExpression(searchFilter))
                .orderBy(orders.stream().toArray(OrderSpecifier[]::new))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();

        return new PageImpl<>(queryResults.getResults(), pageable, queryResults.getTotal());
    }

    @Override
    public void updateAdminStatus(AdminUser adminUser){

        svcJpaQueryFactory.update(qAdminUser)
                .set(qAdminUser.userStatus, adminUser.getUserStatus())
                .set(qAdminUser.modDt, DateTimeExpression.currentTimestamp(Timestamp.class))
                .where(qAdminUser.userUid.eq(adminUser.getUserUid()))
                .execute();
    }

    public BooleanBuilder setBooleanExpression(SearchFilter searchFilter) {

        BooleanBuilder booleanBuilder = new BooleanBuilder();

        if (searchFilter.getFilterValue(qAdminUser.userUid.getMetadata().getName()) != null) {
            booleanBuilder.and(qAdminUser.userUid.eq((String) searchFilter.getFilterValue(qAdminUser.userUid.getMetadata().getName())));
        }

        if (searchFilter.getFilterValue(qAdminUser.userName.getMetadata().getName()) != null) {
            booleanBuilder.and(qAdminUser.userName.eq((String) searchFilter.getFilterValue(qAdminUser.userName.getMetadata().getName())));
        }

        if (searchFilter.getFilterValue(qAdminUser.userType.getMetadata().getName()) != null) {
            booleanBuilder.and(qAdminUser.userType.eq((String) searchFilter.getFilterValue(qAdminUser.userType.getMetadata().getName())));
        }

        if (searchFilter.getFilterValue(qAdminUser.userStatus.getMetadata().getName()) != null) {
            String[] adminStatusArr = String.valueOf(searchFilter.getFilterValue(qAdminUser.userStatus.getMetadata().getName()))
                    .split(Consts.SEARCH_COND_SUB_SEPARATOR);
            booleanBuilder.and(qAdminUser.userStatus.in(adminStatusArr));
        }
        return booleanBuilder;
    }
}