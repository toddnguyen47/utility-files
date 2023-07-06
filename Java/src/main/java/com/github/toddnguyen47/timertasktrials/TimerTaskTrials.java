package com.github.toddnguyen47.timertasktrials;

import java.util.Date;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicInteger;

public class TimerTaskTrials {

    private final AtomicInteger count;
    private final Timer timer;
    private final long period;
    private final Date nowForClass;

    public static void main(final String[] args) {
        TimerTaskTrials timerTaskTrials = new TimerTaskTrials(100);
        try {
            // Non-blocking call
            timerTaskTrials.execute();
            // Blocking call
            timerTaskTrials.startTimerTaskToIncrement();
        } finally {
            timerTaskTrials.shutDown();
        }
    }

    public TimerTaskTrials(final long period) {
        this.count = new AtomicInteger(0);
        this.timer = new Timer("myTimer");
        this.period = period;
        this.nowForClass = new Date();
    }

    private void execute() {
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                System.out.println("Count is: " + count.get());
                if (count.get() == 0) {
                    System.out.println("COUNT IS ZERO!");
                    System.out.println("---");
                }
                // Reset
                System.out.println("RESETTING...");
                count.set(0);
            }
        };
        timer.scheduleAtFixedRate(timerTask, nowForClass, period);
    }

    private void shutDown() {
        timer.cancel();
    }

    private void startTimerTaskToIncrement() {
        Random random = new Random(nowForClass.toInstant().toEpochMilli());
        // Timer task to increment
        TimerTask incrementTimerTask = new TimerTask() {
            @Override
            public void run() {
                count.incrementAndGet();
                // Sleep for a random amount [50, 150]
                int sleepTime = random.nextInt(100 + 1) + 50;
                System.out.println("sleeping for " + sleepTime);
                try {
                    Thread.sleep(sleepTime);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        };
        Timer timerIncrement = new Timer("timerIncrement");
        timerIncrement.scheduleAtFixedRate(incrementTimerTask, nowForClass, 1);
        TimerTaskTrials.sleepFor(5_000);
        timerIncrement.cancel();
    }

    public static void sleepFor(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
