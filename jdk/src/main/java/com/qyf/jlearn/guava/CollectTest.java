package com.qyf.jlearn.guava;

import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.collect.*;

import java.time.Instant;
import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * 描述：
 *
 * @author qinyifeng
 * @version v1.0
 * @since 2021/2/3 11:11
 */
public class CollectTest {

    /**
     * 本地缓存
     */
    volatile static Cache<Integer, Object> COMPANY_LOCAL_CACHE = CacheBuilder.newBuilder().maximumSize(1000).expireAfterWrite(2, TimeUnit.HOURS).concurrencyLevel(10).recordStats().build();


    public static void main(String[] args) throws ExecutionException, InterruptedException {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        List readOnlyList = Collections.unmodifiableList(list);
        List readOnlyList2 = Collections.unmodifiableList(new ArrayList<>(list));
        List readOnlyList3 = ImmutableList.copyOf(list);
        list.add(3);

        // 3
        System.out.println(readOnlyList.size());
        // 2
        System.out.println(readOnlyList2.size());
        // 2
        System.out.println(readOnlyList3.size());

        Multimap<String, String> multimap = ArrayListMultimap.create();
        multimap.put("a", "10");
        multimap.put("a", "20");
        multimap.put("b", "30");
        // [10, 20]
        System.out.println(multimap.get("a"));

        // biMap.inverse()  != biMap ；biMap.inverse().inverse() == biMap
        BiMap<Integer, String> bimap = HashBiMap.create();
        bimap.put(1000, "1000@gmail.com");
        // value already present: 1000@gmail.com
        // bimap.put(2000,"1000@gmail.com");
        bimap.forcePut(2000, "1000@gmail.com");
        bimap.forcePut(3000, "3000@gmail.com");
        // null
        System.out.println(bimap.get("1000@gmail.com"));
        // 2000
        System.out.println(bimap.inverse().get("1000@gmail.com"));

        Table<String, String, Integer> table = HashBasedTable.create();
        table.put("zhangsan", "语文", 100);
        table.put("zhangsan", "数学", 90);
        table.put("lisi", "语文", 95);
        table.put("lisi", "数学", 85);

        Set<Table.Cell<String, String, Integer>> cellSet = table.cellSet();
        for (Table.Cell cell : cellSet) {
            System.out.println("rowKey=" + cell.getRowKey() + ",columnKey=" + cell.getColumnKey() + ",value=" + cell.getValue());
        }

        System.out.println(table.rowKeySet());
        System.out.println(table.columnKeySet());
        System.out.println(table.row("zhangsan"));
        System.out.println(table.column("语文"));

        //Collections2.transform();
        List<String> list1 = Lists.newArrayList("1111", "1234");
        Collection<String> list2 = Collections2.filter(list1, new Predicate<String>() {
            @Override
            public boolean apply(String s) {
                return new StringBuilder(s).reverse().toString().equals(s);
            }
        });
        // 1111
        list2.forEach(System.out::println);

        Preconditions.checkNotNull(1, "is null");
        Preconditions.checkArgument(2 > 0);

        // local cache
        for (int i = 0; i < 2; i++) {
            System.out.println("首次：" + LOADING_CACHE.get(i).toString());
        }
        Thread.sleep(1000L);
        for (int i = 0; i < 3; i++) {
            System.out.println("二次：" + LOADING_CACHE.get(i).toString());
        }
    }

    /**
     * 定义缓存加载策略
     */
    private static final CacheLoader<Integer, User> CACHE_LOADER = new CacheLoader<Integer, User>() {
        @Override
        public User load(Integer s) {
            String name = "NAME-" + Instant.now().toEpochMilli();
            User user = new User(s, name);
            return user;
        }
    };

    /**
     * 定义缓存策略
     */
    private static final LoadingCache LOADING_CACHE = CacheBuilder.newBuilder()
            .expireAfterAccess(2, TimeUnit.SECONDS)
            .expireAfterWrite(3, TimeUnit.SECONDS)
            .refreshAfterWrite(4, TimeUnit.SECONDS)
            .concurrencyLevel(10)
            .maximumSize(10000)
            .recordStats()
            .build(CACHE_LOADER);

}
