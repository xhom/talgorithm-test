import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class TestHashMap {
    public static void main(String []args) throws Exception{
        System.out.println("当前JDK版本："+System.getProperty("java.version")+"\n");
        Map<String,String> map = new HashMap<>();
        //儿女&农丰 hashCode相同,可做测试数据
        map.put("儿女","aaa");
        map.put("农丰","AAA");
        String b = "b";
        map.put(b,"bbb");
        String rt = map.put(b,"BBB");

        for(Map.Entry<String,String> entry : map.entrySet()){
            System.out.println(entry.getKey()+" -> "+entry.getValue()+" ["+entry.hashCode()+"]");
            System.out.println("++++++++++++++++++++++");
            printFields(entry);
            System.out.println("----------------------");
        }
        System.out.println("b.hashCode = "+(b.hashCode()));
        System.out.println("map.size = "+map.size());
        System.out.println("put(b,BBB)的返回值："+ rt);

        new TestHashMap().test();
    }

    public void test(){
        System.out.println("----------------------");
        Test test = new Test();
        Test test2 = new Test();
        System.out.println(test.hashCode());
        System.out.println(test2.hashCode());//求hashCode用hashCode()是不靠谱的，因为它阔以被重写
        System.out.println(System.identityHashCode(test));//用System.identityHashCode才靠谱
        System.out.println(System.identityHashCode(test2));
    }

    public static void printFields(Object obj) throws Exception{
        Class<?> clazz = obj.getClass();
        Field[] fields = clazz.getDeclaredFields();
        Field.setAccessible(fields,true);
        for(Field field : fields){
            String fieldName = field.getName();
            Object fieldValue = field.get(obj);
            boolean isNext = "next".equals(fieldName) && fieldValue!=null;
            System.out.println(fieldName+": "+( isNext ? fieldValue.hashCode() : fieldValue));
        }
    }

    class Test{
        public int hashCode(){
            return 1;
        }
    }
}
