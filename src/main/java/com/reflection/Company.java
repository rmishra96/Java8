package com.reflection;

import javax.smartcardio.CommandAPDU;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Company {

    private String name;

    public Company(String name)
    {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void displayName() {
        System.out.println("Private name to display " +name);
    }

    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {

        Company company = new Company("Youtube");
        Field newfield = Company.class.getDeclaredField("name") ;
        newfield.setAccessible(true);
        newfield.set(company,"Youtube");
        System.out.println(company.getName());

        try {
            Method method = Company.class.getDeclaredMethod("displayName");
            method.setAccessible(true);
            method.invoke(company);
        } catch (NoSuchMethodException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }
}
