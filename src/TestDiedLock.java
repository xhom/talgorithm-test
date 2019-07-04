
public class TestDiedLock {
    private Thread thread1 = new TestThread("th1");
    private Thread thread2 = new TestThread("th2");

    public void m1() throws InterruptedException{
        thread1.start();
        thread1.join();
        thread2.start();
        thread2.join();
        System.out.println("mainTh");
    }


    public static void main(String[] args) throws InterruptedException{
        TestDiedLock test = new TestDiedLock();
        test.m1();
    }

    class TestThread extends Thread{
        private String words;

        public TestThread(String words){
            this.words = words;
        }

        @Override
        public void run(){
            if(this.words.equals("th1")){
                try{
                    Thread.sleep(2000);
                }catch (InterruptedException e){ }
            }

            System.out.println(this.words);
        }
    }
}
