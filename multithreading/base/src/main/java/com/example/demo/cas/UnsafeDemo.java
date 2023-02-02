package com.example.demo.cas;


import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * 测试Unsafe的CAS
 */
public class UnsafeDemo {
    private static Unsafe unsafe = null;
    private static Field getUnsafe = null;

    static {
        try {
            getUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
            getUnsafe.setAccessible(true);
            unsafe = (Unsafe) getUnsafe.get(null);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    static class  User{
       private   int value;

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }
    }

    public static void main(String[] args) throws NoSuchFieldException {
        User user = new User();
        user.setValue(1);
        Field value = user.getClass().getDeclaredField("value");
        long offset = unsafe.objectFieldOffset(value);
        value.setAccessible(true);
        unsafe.compareAndSwapInt(user,offset,1,4);
        System.out.println(user.getValue());
    }
}


