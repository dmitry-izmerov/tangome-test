package ru.dizmirov;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

/**
 * This class implements hit counting logic per user and must be thread safe
 */
public class UsersStats {
    private final Map<String, AtomicLong> hitsPerUser = new ConcurrentHashMap<>();

    public long onUserCall(String userId) {
        hitsPerUser.computeIfAbsent(userId, k -> new AtomicLong());
        return hitsPerUser.get(userId).incrementAndGet();
    }

    public long getHitsOf(String userId) {
        return hitsPerUser.getOrDefault(userId, new AtomicLong()).get();
    }
}
