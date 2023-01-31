package issueabroad.first.repository.search;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.JPQLQuery;
import issueabroad.first.entity.article.QScrap;
import issueabroad.first.entity.article.Scrap;
import issueabroad.first.entity.member.QMember;
import issueabroad.first.entity.reply.QScrapReply;
import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;
import java.util.stream.Collectors;

@Log4j2
public class SearchScrapRepositoryImpl extends QuerydslRepositorySupport implements SearchScrapRepository {

    /**
     * Creates a new {@link QuerydslRepositorySupport} instance for the given domain type.
     *
//     * @param domainClass must not be {@literal null}.
     */
    public SearchScrapRepositoryImpl() {
        super(Scrap.class);
    }

    @Override
    public Scrap search1() {
        log.info("search1...");

        QScrap scrap = QScrap.scrap;
        QScrapReply scrapReply = QScrapReply.scrapReply;

        JPQLQuery<Scrap> jpqlQuery = from(scrap);
        jpqlQuery.leftJoin(scrapReply).on(scrapReply.scrap.eq(scrap));

        JPQLQuery<Tuple> tuple = jpqlQuery.select(scrap, scrapReply.count());
        tuple.groupBy(scrap);

        log.info("-----------");
        log.info(tuple);
        log.info("-----------");

        List<Tuple> res = tuple.fetch();

        return null;
    }

    @Override
    public Page<Object[]> searchPage(String keyword, Pageable pageable) {
        log.info("------------------SearchPage-----------------------");

        QScrap scrap = QScrap.scrap;
        QScrapReply scrapReply = QScrapReply.scrapReply;

        JPQLQuery<Scrap> jpqlQuery = from(scrap);
        jpqlQuery.leftJoin(scrapReply).on(scrapReply.scrap.eq(scrap));

        JPQLQuery<Tuple> tuple = jpqlQuery.select(scrap, scrapReply.count());

        BooleanBuilder booleanBuilder = new BooleanBuilder();
        BooleanExpression expression = scrap.sno.gt(0l);

        booleanBuilder.and(expression);

        BooleanBuilder conditionBuilder = new BooleanBuilder();

        conditionBuilder.or(scrap.originTitle.contains(keyword));
        conditionBuilder.or(scrap.title.contains(keyword));

        booleanBuilder.and(conditionBuilder);

        tuple.where(booleanBuilder);

        Sort sort = pageable.getSort();

        sort.stream().forEach(order -> {
            Order direction = order.isAscending() ? Order.ASC : Order.DESC;
            String property = order.getProperty();

            PathBuilder orderByExpression = new PathBuilder(Scrap.class, "scrap");

            tuple.orderBy(new OrderSpecifier(direction, orderByExpression.get(property)));
        });

        tuple.groupBy(scrap);

        tuple.offset(pageable.getOffset());
        tuple.limit(pageable.getPageSize());

        List<Tuple> res = tuple.fetch();

//        log.info("-------------------");
//        log.info(res);
//        log.info("-------------------");

        long count = tuple.fetchCount();

//        log.info("-------------------");
//        log.info("count : " + count);
//        log.info("-------------------");

        return new PageImpl<Object[]>(
                res.stream().map(t -> t.toArray()).collect(Collectors.toList()), pageable, count);
    }


}
