package aap.mobile.gameslot;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView ivSlot1;
    private ImageView ivSlot2;
    private ImageView ivSlot3;
    private ArrayList<Integer> arImage;
    private Thread thread1;
    private Thread thread2;
    private Thread thread3;
    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btStartStop)
                .setOnClickListener(this);

        arImage = new ArrayList<Integer>();
        arImage.add(R.drawable.slot_1);
        arImage.add(R.drawable.slot_2);
        arImage.add(R.drawable.slot_3);
        arImage.add(R.drawable.slot_4);
        arImage.add(R.drawable.slot_5);
        arImage.add(R.drawable.slot_6);
        arImage.add(R.drawable.slot_7);
        arImage.add(R.drawable.slot_8);
        arImage.add(R.drawable.slot_9);

        this.ivSlot1 = (ImageView) findViewById(R.id.ivSlot1);
        this.ivSlot2 = (ImageView) findViewById(R.id.ivSlot2);
        this.ivSlot3 = (ImageView) findViewById(R.id.ivSlot3);
        this.handler = new Handler(Looper.getMainLooper());

        // Set initial background resource for each ImageView
        ivSlot1.setBackgroundResource(R.drawable.slot_9);
        ivSlot2.setBackgroundResource(R.drawable.slot_9);
        ivSlot3.setBackgroundResource(R.drawable.slot_9);

        createThread1();
        createThread2();
        createThread3();
    }

    private void createThread1() {
        this.thread1 = new Thread(new Runnable() {
            @Override
            //dijalankan dalam background
            public void run() {
                try{
                    Random rand = new Random();
                    while(true) {
                        int index = rand.nextInt(9);
                        handler.post(new Runnable() {
                            @Override
                            //akan dijalankan di UI Thread
                            public void run() {
                                ivSlot1.setBackgroundResource(arImage.get(index));
                            }
                        });
                        Thread.sleep(200);
                    }
                } catch (Exception e) {}
            }
        });
    }

    private void createThread2() {
        this.thread2 = new Thread(new Runnable() {
            @Override
            //dijalankan dalam background
            public void run() {
                try{
                    Random rand = new Random();
                    while(true) {
                        int index = rand.nextInt(9);
                        handler.post(new Runnable() {
                            @Override
                            //akan dijalankan di UI Thread
                            public void run() {
                                ivSlot2.setBackgroundResource(arImage.get(index));
                            }
                        });
                        Thread.sleep(200);
                    }
                } catch (Exception e) {}
            }
        });
    }

    private void createThread3() {
        this.thread3 = new Thread(new Runnable() {
            @Override
            //dijalankan dalam background
            public void run() {
                try{
                    Random rand = new Random();
                    while(true) {
                        int index = rand.nextInt(9);
                        handler.post(new Runnable() {
                            @Override
                            //akan dijalankan di UI Thread
                            public void run() {
                                ivSlot3.setBackgroundResource(arImage.get(index));
                            }
                        });
                        Thread.sleep(200);
                    }
                } catch (Exception e) {}
            }
        });
    }

    @Override
    public void onClick(View view) {
        if (this.thread1.isAlive())
            this.thread1.interrupt();
        else if (this.thread2.isAlive()){
            this.thread2.interrupt();
        } else if (this.thread3.isAlive()) {
            this.thread3.interrupt();
        }else {
            this.createThread1();
            this.createThread2();
            this.createThread3();

            thread1.start();
            thread2.start();
            thread3.start();
        }
    }
}