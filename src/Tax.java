import java.util.ArrayList;
import java.util.List;

/**
 * 阶梯税计算器
 */
public class Tax {

    public static void main(String[] args) {
        double preTax = 9355-1500;//9355
        System.out.println("税前金额：" + preTax);
        double postTax = new Tax().getPostTax(preTax);
        System.out.println("税后金额：" + postTax);
    }


    public double getPostTax(double preTax){
        List<RateStage> list = getRateStageList();
        double postTax = 0;
        int i = list.size()-1;

        for(; i>=0; i--){
            RateStage rateStage = list.get(i);
            if(preTax > rateStage.getStart()){ //适用税率定位到当前
                System.out.println("适用税率："+rateStage.getRate());
                System.out.println("速算扣除金额："+rateStage.getTotalTax());
                System.out.println("当前税率扣除金额：" + rateStage.getActualTax(preTax));
                System.out.println("总扣税金额：" + rateStage.getTax(preTax));
                postTax = rateStage.getPostTax(preTax);
                break;
            }
        }

        return postTax;
    }

    public List<RateStage> getRateStageList(){
        List<RateStage> list = new ArrayList<>();

        //list中的数据必须是有序的
        list.add(new RateStage(0,5000,0));
        list.add(new RateStage(5000,8000,0.03));
        list.add(new RateStage(8000,17000,0.1));
        list.add(new RateStage(17000,30000,0.2));
        list.add(new RateStage(30000,-1,0.25));

        double totalTax = 0;
        for(RateStage rateStage : list){
            rateStage.setTotalTax(totalTax);
            totalTax += rateStage.getFullTax();
        }

        return list;
    }

    //税率阶梯
    class RateStage{
        private double start;//起始点（不包含）
        private double end;//终止点（包含）
        private double rate;//税率（百分比）
        private double totalTax;//之前阶段的税额总和

        public RateStage(){}

        public RateStage(double start, double end, double rate){
            this.start = start;
            this.end = end;
            this.rate = rate;
        }

        public double getStart() {
            return this.start;
        }

        public double getEnd() {
            return this.end;
        }

        public double getRate() {
            return this.rate;
        }

        public void setTotalTax(double totalTax) {
            this.totalTax = totalTax;
        }

        public double getTotalTax() {
            return this.totalTax;
        }

        public double getFullTax(){//获取当前阶段满税额（最高）
            return this.getActualTax(this.end);
        }

        public double getActualTax(double end){//获取当前阶段实际税额
            return (end - this.start) * this.rate;
        }

        public double getTax(double preTax){//获取总税额
            return this.getTotalTax() + this.getActualTax(preTax);
        }

        public double getPostTax(double preTax){//获取税后金额
            return preTax - this.getTax(preTax);
        }
    }
}
