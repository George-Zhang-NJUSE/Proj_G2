package group2.grade15.njuse.presentation.myanimation;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.util.Duration;

/**
 * Created by George on 2016/12/6.
 */
public class Rotate {

    private Timeline timeline;

    /**
     * @param node     要旋转的面板
     * @param duration 以毫秒计的持续时间长度，正数
     * @param degree 旋转后的度数，默认状态为0
     **/
    public Rotate(Node node, int duration, int degree) {
        timeline = new Timeline();

        timeline.getKeyFrames().addAll(
                new KeyFrame(Duration.ZERO, new KeyValue(node.rotateProperty(), node.getRotate())),
                new KeyFrame(new Duration(duration), new KeyValue(node.rotateProperty(), degree))
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
