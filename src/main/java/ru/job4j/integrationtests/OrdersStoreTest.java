package ru.job4j.integrationtests;

import org.apache.commons.dbcp2.BasicDataSource;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.*;

public class OrdersStoreTest {
    private BasicDataSource pool = new BasicDataSource();

    @Before
    public void setUp() throws SQLException {
        pool.setDriverClassName("org.hsqldb.jdbcDriver");
        pool.setUrl("jdbc:hsqldb:mem:tests;sql.syntax_pgs=true");
        pool.setUsername("sa");
        pool.setPassword("");
        pool.setMaxTotal(2);
        StringBuilder builder = new StringBuilder();
        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(new FileInputStream("./db/update_001.sql")))
        ) {
            br.lines().forEach(line -> builder.append(line).append(System.lineSeparator()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        pool.getConnection().prepareStatement(builder.toString()).executeUpdate();
    }

    @After
    public void clear() throws SQLException {
        pool.setDriverClassName("org.hsqldb.jdbcDriver");
        pool.setUrl("jdbc:hsqldb:mem:tests;sql.syntax_pgs=true");
        pool.setUsername("sa");
        pool.setPassword("");
        pool.setMaxTotal(2);
        StringBuilder builder = new StringBuilder();
        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(new FileInputStream("./db/clear_001.sql")))
        ) {
            br.lines().forEach(line -> builder.append(line).append(System.lineSeparator()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        pool.getConnection().prepareStatement(builder.toString()).executeUpdate();
    }

    @Test
    public void test1() {
        OrdersStore store = new OrdersStore(pool);
        store.save(Order.of("name1", "description1"));
        List<Order> all = (List<Order>) store.findAll();

        assertEquals(all.size(), 1);
        assertEquals(all.get(0).getDescription(), "description1");
        assertEquals(all.get(0).getId(), 1);
    }

    @Test
    public void test2() {
        OrdersStore store = new OrdersStore(pool);
        store.save(Order.of("name1", "description1"));
        String description = store.findById(1).getDescription();

        assertEquals(description, "description1");
    }

    @Test
    public void test3() {
        OrdersStore store = new OrdersStore(pool);
        store.save(Order.of("name1", "description1"));
        String description = store.findByName("name1").getDescription();

        assertEquals(description, "description1");
    }

    @Test
    public void test4() {
        OrdersStore store = new OrdersStore(pool);
        store.save(Order.of("name1", "description1"));
        store.delete(1);
        var rsl = store.findByName("name1");

        assertNull(rsl);
    }

    @Test
    public void test5() {
        OrdersStore store = new OrdersStore(pool);
        store.save(Order.of("name1", "description1"));
        store.update(1, "NEWname");
        var rsl = store.findById(1).getName();

        assertEquals(rsl, "NEWname");
    }
}