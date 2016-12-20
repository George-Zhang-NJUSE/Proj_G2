package group2.grade15.njuse.cache;


import java.util.HashMap;

public class CacheManager{

    private static CacheManager cacheManager = null;
    private HashMap<String, Cache> hashMap;

    private CacheManager(){
        hashMap = new HashMap<>();
    }

    public static CacheManager getInstance(){
        if(cacheManager == null){
            cacheManager = new CacheManager();
        }
        return cacheManager;
    }

    public boolean containsCache(String key){
        return hashMap.containsKey(key);
    }

    public Cache getCache(String key){
        return hashMap.get(key);
    }

    public void putChace(String key, Object element){
        Cache cache = new Cache(key, element);
        hashMap.put(key, cache);
    }

    public void removeChace(String key){
        hashMap.remove(key);
    }
}
