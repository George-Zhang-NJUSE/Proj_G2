package group2.grade15.njuse.presentation.myanimation;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

/**
 * Created by George on 2016/11/25.
 */
public class ChangeWidth {

    private Timeline timeline;

    /**
     * @param pane     要改变宽度的面板
     * @param duration 以毫秒计的持续时间长度，正数
     * @param endWidth 结束时的宽度
     **/
    public ChangeWidth(Pane pane, int duration, int endWidth) {
        timeline = new Timeline();
        timeline.getKeyFrames().addAll(
                new KeyFrame(Duration.ZERO, new KeyValue(pane.prefWidthProperty(), pane.getPrefWidth())),
                new KeyFrame(new Duration(duration), new KeyValue(pane.prefWidthProperty(), endWidth))
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
