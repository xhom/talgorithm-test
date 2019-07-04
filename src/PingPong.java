
public class PingPong extends Thread{
    String words;
    int delay;

    PingPong(String words, int delay){
        this.words = words;
        this.delay = delay;
    }

    @Override
    public void run() {
        try {
            for(;;){
                System.out.println(words+"");
                sleep(delay);
            }
        }catch (InterruptedException e){
            return;
        }
    }

    public static void main(String[] args) {
        new PingPong("ping",33).start();
        new PingPong("PONG",100).start();
    }
}
