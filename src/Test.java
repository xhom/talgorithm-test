import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Test {
    public static void main(String[] args) throws Exception{

        UserCond cond = new UserCond();
        cond.setName("齐天大圣");
        cond.setAge(25);
        System.out.println(getProperties(listPage(cond,-1,null)));
    }

    public static Response<Pagination<UserCond,User>> listPage(UserCond cond,Integer pageIndex,Integer pageSize){
        Pagination<UserCond,User> pagination = new Pagination<>(pageIndex,pageSize,cond);
        Integer totalRecords = 1;
        List<User> list = new ArrayList<>();
        User user = new User();
        user.setName("张三");
        user.setAge(23);
        user.setNo(112132L);
        list.add(user);

        Response<Pagination<UserCond,User>> response = new Response<>(pagination.page(totalRecords,list));
        return response;
    }

    public static Map<String,Object> getProperties(Object obj) throws Exception{
        Map<String,Object> map = new HashMap<>();
        if(obj == null){
            return map;
        }
        Class<?> clazz = obj.getClass();
        Field[] fields = clazz.getDeclaredFields();
        Field.setAccessible(fields,true);
        for(Field field : fields){
            map.put(field.getName(),field.get(obj));
        }
        return map;
    }
}
