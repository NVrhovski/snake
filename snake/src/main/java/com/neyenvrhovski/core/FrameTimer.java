package com.neyenvrhovski.core;

import java.awt.event.ActionListener;

import javax.swing.Timer;

public class FrameTimer {

    public static Timer getAndStartTimer(ActionListener taskPerformer){
        Timer timer = new Timer(200, taskPerformer);
        timer.start();
        return timer;
    }
}
