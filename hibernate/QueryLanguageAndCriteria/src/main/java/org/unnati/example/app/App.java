package org.unnati.example.app;

import org.hibernate.ScrollMode;
import org.junit.Assert;
import org.unnati.example.entities.Bid;
import org.unnati.example.entities.Item;
import org.unnati.example.entities.LogRecord;
import org.unnati.example.entities.User;

import javax.persistence.*;
import javax.persistence.criteria.*;
import java.math.BigDecimal;
import java.util.*;

public class App {
    public static EntityManager entityManager= Persistence.createEntityManagerFactory("QueryCriteria").createEntityManager();

    public static void main(String args[]){
        CreateDatabase.createDatabase(entityManager);
        (new App()).runApp();
    }

    public void runApp(){
        System.out.println("Application Running");
        this.getItemFromQuery();
        this.getItemFromTypedQuery();
       // this.getItemFromQueryWithPagination();
        this.executeExternalQuery();
        this.getBidsBetween(new BigDecimal(95),new BigDecimal(100));
        this.getBidsGreaterThan(new BigDecimal(100));
        this.getOperatorIn();
        this.getOperatorIsNull();
        this.getOperatorIsNotNull();
        this.getOperatorLike();
        this.getOperatorNotLike();
        this.getMixedOperator();
        this.executeCollectionIsNotEmpty();
        this.executeCollectionSize();
        this.executeCollectionIsMember();
        this.runSqlUpperFromJPA();
        this.runSqlUpperFromSQL();
        this.runOrderBy();
        this.selectFromTwoTable();
        this.selectDistinctFromTable();
        this.callFunctionInProject();
        this.joinItemBidAggrigatorFunction();
        this.implicitJoinByMappedProperty();
        this.explicitJoinByMappedProperty();
        this.dynamicEagerFetchWithJoin();
        this.thetaStyleJoin();
        this.coRelatedSubQuery();
        this.nonCorrelatedSubQuery();
        this.applyAllQuantifier();
        this.applyAnyQuantifier();
        this.applyExistsQuantifier();
    }

    public void getItemFromQuery(){
     //   Query query=entityManager.createQuery("Select i from Item i");
        Query query=entityManager.createNativeQuery("Select * from Item ",Item.class);
        List<Item> itemListFromQuery=query.getResultList();

        CriteriaBuilder criteriaBuilder=entityManager.getCriteriaBuilder();

        CriteriaQuery criteriaQuery=criteriaBuilder.createQuery();
        criteriaQuery.select(criteriaQuery.from(Item.class));

        query=entityManager.createQuery(criteriaQuery);
        List<Item> itemListFromCriteria=query.getResultList();

        Assert.assertTrue(itemListFromQuery.equals(itemListFromCriteria));

    }

    public void getItemFromTypedQuery(){
        int USER_ID=CreateDatabase.createUser(entityManager);
        int ITEM_ID=CreateDatabase.createItem(USER_ID,entityManager);

        System.out.println("============================String Query=============");

        TypedQuery<Item> query=entityManager.createQuery("select i from Item i where i.id=:id",Item.class);
        query.setParameter("id",ITEM_ID);
        Item itemListFromQuery=query.getSingleResult();

        System.out.println("============================Criteria Query=============");
        CriteriaBuilder criteriaBuilder=entityManager.getCriteriaBuilder();

        CriteriaQuery criteriaQuery=criteriaBuilder.createQuery(Item.class);
        Root<Item> item=criteriaQuery.from(Item.class);
        criteriaQuery.select(item);
        criteriaQuery.where(criteriaBuilder.equal(item.get("id"),ITEM_ID));

        query=entityManager.createQuery(criteriaQuery);
        Item itemListFromCriteria=query.getSingleResult();
        System.out.println("============================End Query=============");

        Assert.assertTrue(itemListFromQuery.equals(itemListFromCriteria));

    }

    public void getItemFromQueryWithPagination(){
        System.out.println("============================String Query=============");
         Query query=entityManager.createQuery("Select i from Item i");
        //Query query=entityManager.createNativeQuery("Select * from Item ");
        query.setFirstResult(5).setMaxResults(10);
        List<Item> itemListFromQuery=query.getResultList();

        System.out.println("============================Scrollable Result Query=============");
        Query queryForScroll=entityManager.createQuery("Select i from Item i ");

        org.hibernate.Query hbmQuery=queryForScroll.unwrap(org.hibernate.jpa.HibernateQuery.class).getHibernateQuery();
        org.hibernate.ScrollableResults cursor=hbmQuery.scroll(ScrollMode.SCROLL_INSENSITIVE);
        cursor.last();// This will not retrieve the data in memory
        int rowCount=cursor.getRowNumber()+1;
        cursor.close();
        queryForScroll.setFirstResult(5).setMaxResults(10);
        List<Item> itemListFromScrollQuery=queryForScroll.getResultList();

        System.out.println("============================End Query=============");

        Assert.assertTrue(itemListFromQuery.equals(itemListFromScrollQuery));

    }

    public void executeExternalQuery(){
        System.out.println("============================String Query=============");
        Query query=entityManager.createQuery("Select i from Item i");
        List<Item> itemListFromQuery=query.getResultList();

        System.out.println("============================ Named Query=============");
        Query namedQuery=entityManager.createNamedQuery("ItemList");
        List<Item> itemListFromCriteria=namedQuery.getResultList();

        System.out.println("============================End Named Query=============");

        Assert.assertTrue(itemListFromQuery.equals(itemListFromCriteria));
    }

    public void compareResult(Query queryFormString,Query queryFromCriteria , String operation){
        System.out.println("============================"+operation+"  Query=============");
        List listFromQuery=queryFormString.getResultList();

        System.out.println("============================"+operation+" Query=============");
        List listFromCriteria=queryFromCriteria.getResultList();

        System.out.println("============================"+operation+" Query=============");

        Assert.assertTrue(listFromQuery.equals(listFromCriteria));
        System.out.println("============================count ="+listFromQuery.size()+"=============");
    }

    public void getBidsBetween(BigDecimal startingAmount,BigDecimal endAmount){
        Query queryFromString=entityManager.createQuery("select b from Bid b where b.amount between :start and :end");
        queryFromString.setParameter("start",startingAmount);
        queryFromString.setParameter("end",endAmount);

        CriteriaBuilder criteriaBuilder=entityManager.getCriteriaBuilder();
        CriteriaQuery criteriaQuery=criteriaBuilder.createQuery(Bid.class);

        Root<Bid> bid=criteriaQuery.from(Bid.class);
        criteriaQuery.select(bid);
        criteriaQuery.where(criteriaBuilder.between(bid.get("amount"),startingAmount,endAmount));
        Query queryFromCriteria=entityManager.createQuery(criteriaQuery);

        this.compareResult(queryFromString,queryFromCriteria,"BETWEEN");

    }

    public void getBidsGreaterThan(BigDecimal startingAmount){
        Query queryFromString=entityManager.createQuery("select b from Bid b where b.amount > :start");
        queryFromString.setParameter("start",startingAmount);

        CriteriaBuilder criteriaBuilder=entityManager.getCriteriaBuilder();
        CriteriaQuery criteriaQuery=criteriaBuilder.createQuery(Bid.class);

        Root<Bid> bid=criteriaQuery.from(Bid.class);
        criteriaQuery.select(bid);
        criteriaQuery.where(criteriaBuilder.gt(bid.get("amount"),startingAmount));
        Query queryFromCriteria=entityManager.createQuery(criteriaQuery);

        this.compareResult(queryFromString,queryFromCriteria,"GREATER THAN");

    }

    public void getOperatorIn(){
        Query queryFromString=entityManager.createQuery("select u from User u where u.name in ('User1','User2')");

        CriteriaBuilder criteriaBuilder=entityManager.getCriteriaBuilder();
        CriteriaQuery criteriaQuery=criteriaBuilder.createQuery(User.class);

        Root<User> root=criteriaQuery.from(User.class);
        criteriaQuery.select(root);
        criteriaQuery.where(criteriaBuilder.in(root.get("name")).value("User1").value("User2"));
        Query queryFromCriteria=entityManager.createQuery(criteriaQuery);

        this.compareResult(queryFromString,queryFromCriteria,"OPERATOR IN");

    }

    public void getOperatorIsNull(){
        Query queryFromString=entityManager.createQuery("select i from Item i where i.winningBid is null");

        CriteriaBuilder criteriaBuilder=entityManager.getCriteriaBuilder();
        CriteriaQuery criteriaQuery=criteriaBuilder.createQuery(Item.class);

        Root<Item> root=criteriaQuery.from(Item.class);
        criteriaQuery.select(root);
        criteriaQuery.where(criteriaBuilder.isNull(root.get("winningBid")));
        Query queryFromCriteria=entityManager.createQuery(criteriaQuery);

        this.compareResult(queryFromString,queryFromCriteria,"OPERATOR IS NULL");

    }

    public void getOperatorIsNotNull(){
        Query queryFromString=entityManager.createQuery("select i from Item i where i.winningBid is not null");

        CriteriaBuilder criteriaBuilder=entityManager.getCriteriaBuilder();
        CriteriaQuery criteriaQuery=criteriaBuilder.createQuery(Item.class);

        Root<Item> root=criteriaQuery.from(Item.class);
        criteriaQuery.select(root);
        criteriaQuery.where(criteriaBuilder.isNotNull(root.get("winningBid")));
        Query queryFromCriteria=entityManager.createQuery(criteriaQuery);

        this.compareResult(queryFromString,queryFromCriteria,"OPERATOR IS NOT NULL");

    }

    public void getOperatorLike(){
        Query queryFromString=entityManager.createQuery("select i from Item i where i.name like 'Item%'");

        CriteriaBuilder criteriaBuilder=entityManager.getCriteriaBuilder();
        CriteriaQuery criteriaQuery=criteriaBuilder.createQuery(Item.class);

        Root<Item> root=criteriaQuery.from(Item.class);
        criteriaQuery.select(root);
        criteriaQuery.where(criteriaBuilder.like(root.get("name"),"Item%"));
        Query queryFromCriteria=entityManager.createQuery(criteriaQuery);

        this.compareResult(queryFromString,queryFromCriteria,"OPERATOR LIKE");

    }

    public void getOperatorNotLike(){
        Query queryFromString=entityManager.createQuery("select i from Item i where i.name not like 'Item%'");
        CriteriaBuilder criteriaBuilder=entityManager.getCriteriaBuilder();
        CriteriaQuery criteriaQuery=criteriaBuilder.createQuery(Item.class);
        Root<Item> root=criteriaQuery.from(Item.class);
        criteriaQuery.select(root);
        criteriaQuery.where(criteriaBuilder.like(root.get("name"),"Item%").not());
        Query queryFromCriteria=entityManager.createQuery(criteriaQuery);
        this.compareResult(queryFromString,queryFromCriteria,"OPERATOR NOT LIKE");
    }

    public void getMixedOperator(){
        Query queryFromString=entityManager.createQuery("select i from Item i where i.name='Item2' or (i.name like '%em1' and i.winningBid is not null)");
        CriteriaBuilder criteriaBuilder=entityManager.getCriteriaBuilder();
        CriteriaQuery criteriaQuery=criteriaBuilder.createQuery(Item.class);
        Root<Item> root=criteriaQuery.from(Item.class);
        criteriaQuery.select(root);
        Predicate predicate=criteriaBuilder.and(criteriaBuilder.like(root.get("name"),"%em1"),criteriaBuilder.isNotNull(root.get("winningBid")));
        predicate=criteriaBuilder.or(criteriaBuilder.equal(root.get("name"),"Item2"),predicate);
        criteriaQuery.where(predicate);
        Query queryFromCriteria=entityManager.createQuery(criteriaQuery);
        this.compareResult(queryFromString,queryFromCriteria,"MIXED OPERATOR");
    }

    public void executeCollectionIsNotEmpty(){
        Query queryFromString=entityManager.createQuery("select i from Item i where i.bids is not empty");

        CriteriaBuilder criteriaBuilder=entityManager.getCriteriaBuilder();
        CriteriaQuery criteriaQuery=criteriaBuilder.createQuery(Item.class);

        Root<Item> root=criteriaQuery.from(Item.class);
        criteriaQuery.select(root);
        criteriaQuery.where(criteriaBuilder.isNotEmpty(root.get("bids")));
        Query queryFromCriteria=entityManager.createQuery(criteriaQuery);

        this.compareResult(queryFromString,queryFromCriteria,"COLLECTION IS NOT EMPTY");
    }

    public void executeCollectionSize(){
        Query queryFromString=entityManager.createQuery("select i from Item i where size(i.bids)>2");
        CriteriaBuilder criteriaBuilder=entityManager.getCriteriaBuilder();
        CriteriaQuery criteriaQuery=criteriaBuilder.createQuery(Item.class);

        Root<Item> root=criteriaQuery.from(Item.class);
        criteriaQuery.select(root);
        criteriaQuery.where(criteriaBuilder.gt(criteriaBuilder.size(root.get("bids")),2));
        Query queryFromCriteria=entityManager.createQuery(criteriaQuery);

        this.compareResult(queryFromString,queryFromCriteria,"COLLECTION SIZE");
    }

    public void executeCollectionIsMember(){
        TypedQuery<Bid> query=entityManager.createQuery("select b from Bid b where b.id=:id",Bid.class);
        query.setParameter("id",1);
        Bid bid=query.getSingleResult();

        Query queryFromString=entityManager.createQuery("select i from Item i where :bid member of i.bids");
        queryFromString.setParameter("bid",bid);

        CriteriaBuilder criteriaBuilder=entityManager.getCriteriaBuilder();
        CriteriaQuery criteriaQuery=criteriaBuilder.createQuery(Item.class);

        Root<Item> root=criteriaQuery.from(Item.class);
        criteriaQuery.select(root);
        criteriaQuery.where(criteriaBuilder.isMember(criteriaBuilder.parameter(Bid.class,"bid"),root.<Collection<Bid>>get("bids")));
        Query queryFromCriteria=entityManager.createQuery(criteriaQuery);
        queryFromCriteria.setParameter("bid",bid);

        this.compareResult(queryFromString,queryFromCriteria,"COLLECTION IsMember");
    }

    public void runSqlUpperFromJPA(){
        Query queryFromString=entityManager.createQuery("select i from Item i where UPPER(i.name) = 'ITEM1'");
        CriteriaBuilder criteriaBuilder=entityManager.getCriteriaBuilder();
        CriteriaQuery criteriaQuery=criteriaBuilder.createQuery(Item.class);
        Root<Item> root=criteriaQuery.from(Item.class);
        criteriaQuery.select(root);
        criteriaQuery.where(criteriaBuilder.equal(criteriaBuilder.upper(root.<String>get("name")),"ITEM1"));
        Query queryFromCriteria=entityManager.createQuery(criteriaQuery);
        this.compareResult(queryFromString,queryFromCriteria,"FUNCTION UPPER JPA");
    }

    public void runSqlUpperFromSQL(){
        // This will use CriteriaBuilder#funtion method

        Query queryFromString=entityManager.createQuery("select i from Item i where UPPER(i.name) = 'ITEM1'");
        CriteriaBuilder criteriaBuilder=entityManager.getCriteriaBuilder();
        CriteriaQuery criteriaQuery=criteriaBuilder.createQuery(Item.class);
        Root<Item> root=criteriaQuery.from(Item.class);
        criteriaQuery.select(root);
        criteriaQuery.where(criteriaBuilder.equal(criteriaBuilder.function("upper",String.class,root.get("name")),"ITEM1"));
        Query queryFromCriteria=entityManager.createQuery(criteriaQuery);
        this.compareResult(queryFromString,queryFromCriteria,"FUNCTION UPPER sql ");
    }

    public void runOrderBy(){
        // This will use CriteriaBuilder#funtion method

        Query queryFromString=entityManager.createQuery("select i from Item i order by i.name desc");
        CriteriaBuilder criteriaBuilder=entityManager.getCriteriaBuilder();
        CriteriaQuery criteriaQuery=criteriaBuilder.createQuery(Item.class);
        Root<Item> root=criteriaQuery.from(Item.class);
        criteriaQuery.select(root);
        criteriaQuery.orderBy( criteriaBuilder.desc(root.get("name")));
        Query queryFromCriteria=entityManager.createQuery(criteriaQuery);
        this.compareResult(queryFromString,queryFromCriteria,"ORDER BY ");
    }

    public void selectFromTwoTable(){
        //"select i,b from Item i ,Bid b";
        System.out.println("==============================TWO SELECT START===========");

        CriteriaBuilder criteriaBuilder=entityManager.getCriteriaBuilder();
        CriteriaQuery criteriaQuery=criteriaBuilder.createQuery();
        Root<Item> item=criteriaQuery.from(Item.class);
        Root<Bid> bid=criteriaQuery.from(Bid.class);
        criteriaQuery.multiselect(item,bid);
        Query query=entityManager.createQuery(criteriaQuery);
        query.getResultList();
        System.out.println("==============================TWO SELECT END===========");

    }

    public void selectDistinctFromTable(){
        //"select distinct i.name from Item i ";
        System.out.println("==============================DISTINCT SELECT START===========");

        CriteriaBuilder criteriaBuilder=entityManager.getCriteriaBuilder();
        CriteriaQuery criteriaQuery=criteriaBuilder.createQuery();
        Root<Item> item=criteriaQuery.from(Item.class);
        criteriaQuery.select(item.get("name")).distinct(true);
        TypedQuery<String> query=entityManager.createQuery(criteriaQuery);

        List<String> list =query.getResultList();
        Set<String> set=new HashSet<>();
        for(String name : list){
            set.add(name);
        }
        Assert.assertTrue(list.size()==set.size());
        System.out.println("==============================DISTINCT SELECT END===========");

    }

    public void callFunctionInProject(){
        //"select distinct i.name from Item i ";
        System.out.println("============================== SELECT  IN PROJECT START===========");

        CriteriaBuilder criteriaBuilder=entityManager.getCriteriaBuilder();
        CriteriaQuery criteriaQuery=criteriaBuilder.createQuery(Tuple.class);
        Root<Item> item=criteriaQuery.from(Item.class);
        criteriaQuery.multiselect(item.get("name").alias("name"),
                             criteriaBuilder.function("upper",String.class,item.get("name")).alias("UpperName"));
        TypedQuery<Tuple> query=entityManager.createQuery(criteriaQuery);

        List<Tuple> list =query.getResultList();
        for(Tuple tuple : list){
            Assert.assertTrue(tuple.get(0).toString().toUpperCase().equals(tuple.get(1)));
        }

        System.out.println("==============================SELECT  IN PROJECT  END===========");

    }

    public void joinItemBidAggrigatorFunction(){
     // select i.name , avg(b.amount) from item i, Bid b group by i.name

        System.out.println("============================== Group By Aggregator START===========");

        CriteriaBuilder criteriaBuilder=entityManager.getCriteriaBuilder();
        CriteriaQuery criteriaQuery=criteriaBuilder.createQuery(Tuple.class);
        Root<Item> item=criteriaQuery.from(Item.class);
        Root<Item> bid=criteriaQuery.from(Bid.class);
        criteriaQuery.multiselect(item.get("name"),
                                  criteriaBuilder.avg(bid.get("amount")));
        criteriaQuery.groupBy(item.get("name"));
        TypedQuery<Tuple> query=entityManager.createQuery(criteriaQuery);
        List<Tuple> list =query.getResultList();
        System.out.println("==============================Group By Aggregator  END===========");
    }

    public void implicitJoinByMappedProperty(){
        System.out.println("============================== IMPLICIT JOIN START===========");
        // select bid from Bid b where b.item.name == 'Item2'
        CriteriaBuilder criteriaBuilder=entityManager.getCriteriaBuilder();
        CriteriaQuery itemBidCountQuery= criteriaBuilder.createQuery();
        Root<Item> item=itemBidCountQuery.from(Item.class);
        itemBidCountQuery.select(criteriaBuilder.size(item.get("bids")));
        itemBidCountQuery.where(criteriaBuilder.equal(item.get("name"),"Item2"));
        TypedQuery<Integer> bidCount=entityManager.createQuery(itemBidCountQuery);
        Integer count=bidCount.getSingleResult();


        CriteriaQuery criteriaQuery=criteriaBuilder.createQuery(Bid.class);
        Root<Bid> bid=criteriaQuery.from(Bid.class);
        criteriaQuery.select(bid);
        criteriaQuery.where(
                        criteriaBuilder.equal(bid.get("item").get("name"),"Item2")
        );
        TypedQuery<Bid> query=entityManager.createQuery(criteriaQuery);
        List<Bid> list =query.getResultList();

        Assert.assertTrue(list.size()==count);
        System.out.println("==============================IMPLICIT JOIN  END===========");
    }

    public void explicitJoinByMappedProperty(){
        // select i from item i join Bid b where b.amount >100;

        System.out.println("==============================INNER join  START===========");
        entityManager.clear();
        CriteriaBuilder criteriaBuilder=entityManager.getCriteriaBuilder();
        CriteriaQuery criteriaQuery=criteriaBuilder.createQuery();
        Root<Item> item=criteriaQuery.from(Item.class);
        Join<Item,Bid> bid=item.join("bids");
        criteriaQuery.select(item).where(
          criteriaBuilder.gt(bid.<BigDecimal>get("amount"),new BigDecimal(100))
        );
        TypedQuery<Item> query=entityManager.createQuery(criteriaQuery);
        List<Item> list =query.getResultList();
        Assert.assertTrue(list.size()==2);
        System.out.println("==============================INNER join   END===========");

        System.out.println("==============================Left Outer join  START===========");
      //  select i from item i join Bid b where decode(b.amount,null,101,b.amount) >100;
         criteriaBuilder=entityManager.getCriteriaBuilder();
         criteriaQuery=criteriaBuilder.createQuery(Item.class);
         item=criteriaQuery.from(Item.class);
         bid=item.join("bids",JoinType.LEFT);
         criteriaQuery.select(item).where(
                criteriaBuilder.gt(
                        criteriaBuilder.function("decode",
                                                  BigDecimal.class,
                                                  bid.get("amount"),
                                                  criteriaBuilder.nullLiteral(BigDecimal.class),
                                                  criteriaBuilder.literal(new BigDecimal(101)),
                                                  bid.get("amount")
                                                 )
                        ,new BigDecimal(100))
        );
         query=entityManager.createQuery(criteriaQuery);
         list =query.getResultList();
         Assert.assertTrue(list.size()==3);
        System.out.println("==============================INNER join   END===========");
    }


    public void dynamicEagerFetchWithJoin(){
        System.out.println("==============================Dynamic Eager Fetch  START===========");
        entityManager.clear();
        CriteriaBuilder criteriaBuilder=entityManager.getCriteriaBuilder();
        CriteriaQuery criteriaQuery=criteriaBuilder.createQuery();
        Root<Item> item=criteriaQuery.from(Item.class);
        item.fetch("bids",JoinType.LEFT);
        criteriaQuery.select(item);
        TypedQuery<Item> query=entityManager.createQuery(criteriaQuery);
        List<Item> list =query.getResultList();
        Assert.assertTrue(list.size()==5);


        Set<Item> set =new LinkedHashSet<>(list);
        Assert.assertTrue(set.size()==2);
        System.out.println("==============================Dynamic Eager Fetch  END===========");
    }

    public  void thetaStyleJoin(){
        System.out.println("==============================Theta Style JOIN  START===========");

        CriteriaBuilder criteriaBuilder=entityManager.getCriteriaBuilder();
        CriteriaQuery criteriaQuery=criteriaBuilder.createQuery();
        Root<Item> item=criteriaQuery.from(Item.class);
        Root<LogRecord> logRecord=criteriaQuery.from(LogRecord.class);
        criteriaQuery.multiselect(item,logRecord);
        criteriaQuery.where(criteriaBuilder.equal(item.get("id"),logRecord.get("userId")));
        TypedQuery<Item> query=entityManager.createQuery(criteriaQuery);
        List<Item> list =query.getResultList();
        System.out.println("==============================Theta Style JOIN  END===========");
    }

    public void coRelatedSubQuery(){
        // select u form User u where (select count(i) from Item i where i.seller=u)>1

        System.out.println("==============================Co-Related Sub Query  START===========");

        CriteriaBuilder criteriaBuilder=entityManager.getCriteriaBuilder();
        CriteriaQuery criteriaQuery=criteriaBuilder.createQuery();

        Root<Item> userRoot=criteriaQuery.from(User.class);
        Subquery<Long> subquery=criteriaQuery.subquery(Long.class);
        Root<Item> itemRoot=subquery.from(Item.class);
        subquery.select(criteriaBuilder.count(itemRoot));
        subquery.where(criteriaBuilder.equal(itemRoot.get("seller"),userRoot));

        criteriaQuery.select(userRoot);
        criteriaQuery.where(criteriaBuilder.gt(subquery,1));
        entityManager.createQuery(criteriaQuery).getResultList();

        System.out.println("==============================Co-Related Sub Query  START===========");
    }
    public void nonCorrelatedSubQuery(){
        // select b from Bid b where b.amount+1<= (select max(b2.amount) from Bid b2)

        System.out.println("==============================Non Co-Related Sub Query  START===========");

        CriteriaBuilder criteriaBuilder=entityManager.getCriteriaBuilder();
        CriteriaQuery criteriaQuery=criteriaBuilder.createQuery();

        Root<Bid> root1=criteriaQuery.from(Bid.class);

        Subquery<BigDecimal> subquery=criteriaQuery.subquery(BigDecimal.class);
        Root<Bid> root2=subquery.from(Bid.class);
        subquery.select(criteriaBuilder.max(root2.get("amount")));

        criteriaQuery.select(root1);
        criteriaQuery.where(criteriaBuilder.greaterThanOrEqualTo(criteriaBuilder.sum(root1.<BigDecimal>get("amount"),new BigDecimal(1)),subquery));
        entityManager.createQuery(criteriaQuery).getResultList();

        System.out.println("==============================Non Co-Related Sub Query  START===========");
    }

    public void applyAllQuantifier(){
        // select i from Item i where 10 <= all(select b.amount from Bid b where bid.item = i )
        System.out.println("==============================All QUANTIFIERS START===========");
        CriteriaBuilder criteriaBuilder=entityManager.getCriteriaBuilder();
        CriteriaQuery criteriaQuery=criteriaBuilder.createQuery();

        Root<Bid> item=criteriaQuery.from(Item.class);

        Subquery<BigDecimal> subquery=criteriaQuery.subquery(BigDecimal.class);

        Root<Bid> bid=subquery.from(Bid.class);
        subquery.select(bid.get("amount"));
        subquery.where(criteriaBuilder.equal(bid.get("item"),item));

        criteriaQuery.select(item);
        // If Subquery Return no rows, following conditions will be considered as true
        criteriaQuery.where(criteriaBuilder.lessThanOrEqualTo(criteriaBuilder.literal(new BigDecimal(100)),
                                                              criteriaBuilder.all(subquery)));

        List<Item> items=entityManager.createQuery(criteriaQuery).getResultList();
        int count=items.size();
        Assert.assertTrue(count==1);
        System.out.println("==============================All QUANTIFIERS  End===========");
    }

    public void applyAnyQuantifier(){
        // select i from Item i where 10 <= all(select b.amount from Bid b where bid.item = i )
        System.out.println("==============================ANY QUANTIFIERS  START===========");
        CriteriaBuilder criteriaBuilder=entityManager.getCriteriaBuilder();
        CriteriaQuery criteriaQuery=criteriaBuilder.createQuery();

        Root<Bid> item=criteriaQuery.from(Item.class);

        Subquery<BigDecimal> subquery=criteriaQuery.subquery(BigDecimal.class);

        Root<Bid> bid=subquery.from(Bid.class);
        subquery.select(bid.get("amount"));
        subquery.where(criteriaBuilder.equal(bid.get("item"),item));

        criteriaQuery.select(item);
        // If Subquery Return no rows, following conditions will be considered as false because there will not be any row
        criteriaQuery.where(criteriaBuilder.lessThanOrEqualTo(criteriaBuilder.literal(new BigDecimal(100)),
                criteriaBuilder.any(subquery)));

        List<Item> items=entityManager.createQuery(criteriaQuery).getResultList();
        int count=items.size();
        Assert.assertTrue(count==1);
        System.out.println("==============================All QUANTIFIERS  End===========");
    }

    public void applyExistsQuantifier(){
        // select i from Item i where 10 <= all(select b.amount from Bid b where bid.item = i )
        System.out.println("==============================EXISTS QUANTIFIERS  START===========");
        CriteriaBuilder criteriaBuilder=entityManager.getCriteriaBuilder();
        CriteriaQuery criteriaQuery=criteriaBuilder.createQuery();

        Root<Bid> item=criteriaQuery.from(Item.class);

        Subquery<BigDecimal> subquery=criteriaQuery.subquery(BigDecimal.class);

        Root<Bid> bid=subquery.from(Bid.class);
        subquery.select(bid.get("amount"));
        subquery.where(criteriaBuilder.equal(bid.get("item"),item));

        criteriaQuery.select(item);
        criteriaQuery.where(criteriaBuilder.exists(subquery));

        List<Item> items=entityManager.createQuery(criteriaQuery).getResultList();
        int count=items.size();
        Assert.assertTrue(count==1);
        System.out.println("==============================EXISTS QUANTIFIERS  End===========");
    }
}
