package annotation.test;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

@Anno1("sss")
@Anno2(name ="dd")
@Anno3("ddd")
public class Test {


    public static void main(String[] args) throws Exception{
        Class<?> clazz = Test.class;
        Anno1 anno1 = clazz.getDeclaredAnnotation(Anno1.class);
        Anno2 anno2 = clazz.getDeclaredAnnotation(Anno2.class);
        Anno3 anno3 = clazz.getDeclaredAnnotation(Anno3.class);

        System.out.println(anno1.value());
        System.out.println(anno2.name());
        System.out.println(anno3.value());
        System.out.println(anno3.name());
    }

}
