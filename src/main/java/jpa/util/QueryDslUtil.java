package jpa.util;

import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.Expressions;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;

public class QueryDslUtil {

    public static List<OrderSpecifier> getAllOrderSpecifiers(Pageable pageable, Path<?> table) {

        List<OrderSpecifier> orders = new ArrayList<>();

        pageable.getSort().forEach((order) -> {
            Order direction = order.getDirection().isAscending() ? Order.ASC : Order.DESC;
            Path<Object> fieldPath = Expressions.path(Object.class, table, order.getProperty());
            orders.add(new OrderSpecifier(direction, fieldPath));
        });

        return orders;
    }
}
