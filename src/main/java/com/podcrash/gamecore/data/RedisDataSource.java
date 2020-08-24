package com.podcrash.gamecore.data;

import redis.clients.jedis.Jedis;

public class RedisDataSource implements DataSource<Jedis> {

    private final Jedis jedis;

    public RedisDataSource() {
        this.jedis = new Jedis();
    }

    @Override
    public Jedis getConnection() {
        return jedis;
    }
}
