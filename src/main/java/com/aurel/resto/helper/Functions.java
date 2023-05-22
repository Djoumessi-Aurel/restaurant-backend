package com.aurel.resto.helper;

import java.util.List;

public class Functions {
    public static boolean contains(List<?> container, Object element){
        for(Object el: container){
            if(el == element) return true;
        }

        return false;
    }
}
