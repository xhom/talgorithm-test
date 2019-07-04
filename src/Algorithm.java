import java.util.ArrayList;
import java.util.List;

public class Algorithm {

    public static void main(String[] args) {
        List<Integer> result = test01(5, 4);
        System.out.println(result);

        System.out.println("--------------------");
        for(int i=1;i<=10;i++)
            System.out.println("第"+i+"月兔子数："+test02(i));
        System.out.println("--------------------");

        int[] circle = {1,2,3,4,5,6,7};
        test03(circle,3);
    }

    //顺时针扫描矩阵算法
    //m 行数 n 列数
    public static List<Integer> test01(int m, int n) {
        int[][] arr = new int[m][n];
        for (int i = 0; i < m * n; i++) {
            arr[i / n][i % n] = i + 1;
        }

        for (int j = 0; j < arr.length; j++) {
            for (int k = 0; k < arr[j].length; k++) {
                System.out.print(arr[j][k] + "\t");
            }
            System.out.println();
        }

        int x = 0, y = -1; //起始点坐标
        int x_start = 0, x_end = m - 1;//x的偏移范围（闭区间）
        int y_start = 0, y_end = n - 1;//y的偏移范围（闭区间）
        boolean x_y_move = false;//true代表x偏移,false代表y偏移
        boolean x_incr = true; //x递增递减标识（true递增，false递减）
        boolean y_incr = true; //y递增递减标识（true递增，false递减）
        List<Integer> result = new ArrayList<>(m*n);//扫描结果

        while (x_start <= x_end && y_start <= y_end) {
            if (x_y_move) {
                if (x_incr) {//x递增
                    if (x >= x_end) {
                        y_end --;
                        x_incr = !x_incr;//换x递减
                        x_y_move = !x_y_move;//换y偏移
                    } else {
                        x ++;
                        result.add(arr[x][y]);
                    }
                } else {//x递减
                    if (x <= x_start) {
                        y_start ++;
                        x_incr = !x_incr;//换x递增
                        x_y_move = !x_y_move;//换y偏移
                    } else {
                        x --;
                        result.add(arr[x][y]);
                    }
                }
            } else {
                if (y_incr) {//y递增
                    if (y >= y_end) {
                        x_start ++;
                        y_incr = !y_incr;//换y递减
                        x_y_move = !x_y_move;//换x偏移
                    } else {
                        y ++;
                        result.add(arr[x][y]);
                    }
                } else {//y递减
                    if (y <= y_start) {
                        x_end --;
                        y_incr = !y_incr;//换y递增
                        x_y_move = !x_y_move;//换x偏移
                    } else {
                        y --;
                        result.add(arr[x][y]);
                    }
                }
            }
        }

        return result;
    }

    //生兔子问题算法
    //问题描述：有一对兔子，从出生后第3个月起每个月都生一对兔子（假设每一对兔子都是一雌一雄），
    //小兔子长到第3个月起每个月又生一对兔子，假如兔子都不死，问每个月的兔子总数为多少？
    //months 月数
    public static int test02(int months){
        int total = 2;
        if(months < 3){//出生周期不满3个月，则只有一对兔子
            return total;
        }

        for(int month=1;month <= months-3+1;month++){
            total += test02(month);
        }

        return total;
    }

    //n个人围成一圈报数，报道m的人退出，请问最后剩下的一个人是几号
    public static void test03(int[] queue, int m) {
        for(int item: queue)
            System.out.print(item+" ");
        System.out.println();

        int len = queue.length;
        if (len <= 1) {//队列中不超过一个人时报数结束
            return;
        }

        int newLen = 0;//新队列的长度
        int index = 0;//游标
        int[] newQueue = null;//新队列

        if(len <= m){//人数不超过 m 时
            newLen = len - 1; //人数<m时，一轮报数只能淘汰一个人，所以队列长度-1
            newQueue = new int[newLen];

            for(int i=0; i<len; i++){
                if(i != (m-1) % len){//木有报到m的人留下，报到m的人满足这种数学关系：i = (m-1) % len
                    newQueue[index] = queue[i];
                    index ++;
                }
            }
        }else{//人数超过 m 时
            newLen = len - len / m;//人数大于m人时会淘汰掉 len/m 个人
            int offset = len % m;//偏移量，即剩余的人数不够从1报到m,这部分人会留到下一轮的开头
            index = offset;//游标有一定偏移，多出的位置留给上一轮剩余未报数的人
            int start = 0;
            newQueue = new int[newLen];

            for(int i=0; i<len; i++){
                if(i < len-offset){
                    if((i+1) % m != 0){//没出局
                        newQueue[index] = queue[i];
                        index ++;
                    }
                }else{//上一轮未报数的人依次放到新队列的头部
                    newQueue[start] = queue[i];
                    start ++;
                }
            }
        }

        //下一轮报数
        test03(newQueue,m);
    }
}
