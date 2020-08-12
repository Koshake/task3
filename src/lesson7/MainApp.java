package lesson7;

import lesson7.tests.AfterSuite;
import lesson7.tests.BeforeSuite;
import lesson7.tests.Test;
import lesson7.tests.TestClass;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

public class MainApp {
    public static void main(String[] args) {
        try {
            start(TestClass.class);
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    private static void start(Class c) throws InvocationTargetException, IllegalAccessException {
        Method[] methods = c.getDeclaredMethods();
        Map<Integer, List<Method>> map = new TreeMap<>();
        int beforeCount = 0;

        for (Method m : methods) {
            if (m.isAnnotationPresent(BeforeSuite.class)) {
                beforeCount++;
                if (beforeCount > 1) {
                    throw new RuntimeException("BeforeSuite должен быть только один!");
                }
                m.invoke(null);
            }
            if(m.isAnnotationPresent(Test.class)){
                if (map.containsKey(m.getAnnotation(Test.class).priority())) {
                    List<Method> list = map.get((m.getAnnotation(Test.class).priority()));
                    list.add(m);
                } else {
                    List<Method> list= new LinkedList<>();
                    list.add(m);
                    map.put(m.getAnnotation(Test.class).priority(), list);
                }
                //map.put(m.getAnnotation(Test.class).priority(), m);
            }
        }
        for (Integer integer : map.keySet()) {
            for (Method method : map.get(integer)) {
                method.invoke(null);
            }
        }
        int afterCount = 0;
        for (Method m : methods) {
            if (m.isAnnotationPresent(AfterSuite.class)) {
                afterCount++;
                if (afterCount > 1) {
                    throw new RuntimeException("AfterSuite должен быть только один!");
                }
                m.invoke(null);
            }
        }

    }
}
