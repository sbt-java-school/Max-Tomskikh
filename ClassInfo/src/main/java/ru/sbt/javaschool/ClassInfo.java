package ru.sbt.javaschool;

import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.List;
/*
@author Tomskikh Maksim
*/

public class ClassInfo {
    public static void main(String[] args) {
        try {
            Class <?> clazz = Class.forName("ru.sbt.javaschool.Test");
            printAll(clazz);
        } catch (ClassNotFoundException e) {
            System.out.println("You don't have any classes");;
        }
    }

    private static void printAll(Class <?> clazz){
        System.out.println("Print all information about  classes");
        System.out.println();
        System.out.println("-----------Print all Members---------------");
        printMembers(clazz.getFields());
        System.out.println();
        System.out.println("-----------Print all Declared Fields-----------");
        printDeclaredFields(clazz);
        System.out.println();
        System.out.println("-------------Print all Getters-----------------");
        printGetters(clazz);
        System.out.println();
        System.out.println("------------- Print hierarchy-----------------");
        getClassHierarchy(clazz);
        System.out.println();
        System.out.println("---------------Print Methods------------------");
        printMethod(clazz);

    }

    private static void printMembers(Member... members) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Member member : members) {
            int modify = member.getModifiers();
            if (Modifier.isPublic(modify)) {
                stringBuilder.append("Public ");
            }
            if (Modifier.isAbstract(modify)){
                stringBuilder.append("Abstract ");
            }
            if (Modifier.isFinal(modify)) {
                stringBuilder.append("Final ");
            }
            if (Modifier.isPrivate(modify)) {
                stringBuilder.append("Private ");
            }
            if (Modifier.isProtected(modify)) {
                stringBuilder.append("Protected ");
            }
            stringBuilder.append(member.getName());
        }
        System.out.println(stringBuilder);

    }

    private static void getClassHierarchy(Class clazz) {
        List<String> classNames = new ArrayList<>();
        while (clazz != null) {
            classNames.add(clazz.getName());
            clazz = clazz.getSuperclass();
        }
        for (int i = classNames.size() - 1; i >= 0; i--) {
            System.out.println(classNames.get(i));
            if (i > 0) {
                System.out.println("     ^");
                System.out.println("     |");
            }
        }
    }

    private static void printMethod(Class<?> clazz) {
        while (clazz != null) {
            for (Method method : clazz.getDeclaredMethods()) {
                System.out.println(method);
            }
            clazz = clazz.getSuperclass();
        }
    }

    private static void printGetters(Class<?> clazz) {
        for (Method method : clazz.getDeclaredMethods()) {
            if (method.getName().startsWith("get") && method.getParameters().length == 0) {
                System.out.println(method);
            }
        }
    }

    private static void getConstructions(Class<?> clazz) {
        for (Constructor constcructor : clazz.getConstructors()) {
            System.out.println(constcructor);
        }
    }

    private static void printDeclaredFields(Class<?> clazz) {
        for (Field field : clazz.getDeclaredFields()) {
            int modifiers = Modifier.fieldModifiers();
            try {
                if (Modifier.isPublic(modifiers) && Modifier.isStatic(modifiers) && Modifier.isFinal(modifiers)
                        && field.getType() == String.class && !field.getName().equals(field.get(clazz))) {
                    System.out.println(field + " ,this field must be a value " + field.getName());
                } else {
                    System.out.println(field);
                }
            } catch (IllegalAccessException e) {
                throw new RuntimeException("unavailable", e);
            }
        }
    }
}
