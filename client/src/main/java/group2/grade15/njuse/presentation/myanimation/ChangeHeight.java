package group2.grade15.njuse.presentation.myanimation;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

/**
 * Created by George on 2016/12/6.
 */
public class ChangeHeight {
    private Timeline timeline;

    /**
     * @param pane     要改变高度的面板
     * @param duration 以毫秒计的持续时间长度，正数
     * @param endHeight 结束时的高度
     **/
    public ChangeHeight(Pane pane, int duration, int endHeight) {
        timeline = new Timeline();
        timeline.getKeyFrames().addAll(
                new KeyFrame(Duration.ZERO, new KeyValue(pane.prefHeightProperty(), pane.getPrefHeight())),
                new KeyFrame(new Duration(duration), new KeyValue(pane.prefHeightProperty(), endHeight))
        );
    }

    /**
     * 设置动画结束时执行的操作
     *
     * @param handler 动画结束事件的监听器，为匿名类，建议用lambda表达式
     * @see EventHandler
     **/
    public void setOnFinished(EventHandler<ActionEvent> handler) {
        timeline.setOnFinished(handler);
    }

    public void play() {
        timeline.play();
    }
}
