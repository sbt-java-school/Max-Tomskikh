package ru.sbt.javaschool;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.*;

/**
 * Realization cache proxy
 * @author Tomskikh Maksim
 */

public class ProxyUtils implements InvocationHandler {

    private Map ourCache = new HashMap(); // General cache, contains <Method, Map<Method args, Method result>


    private Object object;
    private ProxyUtils (Object obj) {
        object = obj;
    }

    public static Object makeCache (Object object){
        Class clazz = object.getClass();
        return Proxy.newProxyInstance(clazz.getClassLoader(),clazz.getInterfaces(),new ProxyUtils(object));
    }


    /**
     * Method realizes cache proxy work
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (method.isAnnotationPresent(Cache.class)) {
            Map cache = getCache(method);
            List key = Arrays.asList(args);
            Object value = cache.get(key);
            if (value == null && !cache.containsKey(key)) {
                value = invoke(method, args);
                cache.put(key, value);
            } else {System.out.println("Cache");
        }
        return value;
        }
        else return invoke(method,args);
    }

    public static Object makeCached(Object object) {
        Class clazz = object.getClass();
        return Proxy.newProxyInstance(clazz.getClassLoader(), clazz.getInterfaces(), new ProxyUtils(object));
    }

    private Object invoke(Method method, Object[] args) throws Throwable {
        try {
            return method.invoke(object, args);
        } catch (InvocationTargetException e) {
                throw (e.getTargetException());}
    }
    /**
     * Method work with caches, if caches contains key - method its return this map else create new
     * @param method to search or check
     * @return map <Method args, Method result>
     */
    private synchronized Map getCache(Method method) {
        Map cache = (Map) ourCache.get(method);
         if (cache == null) {
             cache = Collections.synchronizedMap(new HashMap());
             ourCache.put(method, cache);
         }
         return cache;
    }
}
