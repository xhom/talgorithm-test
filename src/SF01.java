import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class SF01 {
    private Integer pp = 0;


    public static void main(String[] args) {
        Integer[] S = {1, 2, 3, 4, 5, 6, 7};

        List<List<Integer>> result = getGroup(S, 3);

        for (List<Integer> subList : result) {
            //if (subList.size() < 3) continue;
            for (Integer i : subList) {
                System.out.print(i + " ");
            }
            System.out.println();
        }
        Integer pp =3;

    }

    //S是有序的，每m个为一组
    public static List<List<Integer>> getGroup(Integer[] S, int m) {
        List<List<Integer>> result = new ArrayList<>();

        warp(S, result, m);

        return result;
    }

    public static void warp(Integer[] S, List<List<Integer>> list, int m) {
        if (list.isEmpty()) {
            for (int i = 0; i < S.length; i++) {
                List<Integer> subList = new ArrayList<>();
                subList.add(S[i]);
                list.add(subList);
                warp(copy(S,i),list,m);
            }
        } else {
            for (int i = 0; i < S.length; i++) {
                for(List<Integer> subList : list){
                    int s = S[i];
                    if(subList.size()>=m || s<=subList.get(subList.size()-1)){
                        continue;
                    }

                    List<Integer> newSubList = new ArrayList<>(subList);
                    newSubList.add(S[i]);
                    warp(copy(S,i),list,m);
                }
            }
        }
    }

    public static Integer[] copy(Integer[] source, int start) {
        Integer[] target = new Integer[source.length - start - 1];
        for (int i = 0; i < target.length; i++) {
            target[i] = source[i + start + 1];
        }
        Integer pp = 0;
        return target;
    }

    public void SS(){
        Integer pp = 3232;
    }


}
