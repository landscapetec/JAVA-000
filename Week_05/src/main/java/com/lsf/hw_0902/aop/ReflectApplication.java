package com.lsf.hw_0902.aop;

import javax.lang.model.element.PackageElement;
import java.lang.annotation.Annotation;

public class ReflectApplication {
    interface Parent {
        String method1(String name);
    }

    static class Child implements Parent {
        public Child() {
        }

        @Override
        @MyCache
        public String method1(String name) {
            return null;
        }
    }

    public static void main(String[] args) throws NoSuchMethodException {
        ReflectApplication.Parent parent = new ReflectApplication.Child();

        Annotation[] result = parent.getClass().getMethod("method1").getDeclaredAnnotations();
        for(Annotation annotation:result){
            System.out.println(annotation.annotationType());
        }
    }
}
