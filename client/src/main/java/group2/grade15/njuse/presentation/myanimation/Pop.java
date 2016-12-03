package group2.grade15.njuse.presentation.myanimation;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.util.Duration;

/**
 * Created by George on 2016/12/3.
 */
public class Pop {

    private Timeline timeline;

    /**
     * 实现节点大小变化的动画
     * @param node 要变化的节点
     * @param duration 毫秒计的动画持续时间
     * @param toBigger true表示由小到大，false表示由大到小
     */
    public Pop(Node node, int duration, boolean toBigger) {
        timeline = new Timeline();
        timeline.getKeyFrames().addAll(
                new KeyFrame(Duration.ZERO, new KeyValue(node.scaleXProperty(), node.getScaleX() )),
                new KeyFrame(Duration.ZERO, new KeyValue(node.scaleYProperty(), node.getScaleY() )),
                new KeyFrame(new Duration(duration), new KeyValue(node.scaleXProperty(), toBigger ? 1 : 0.5)),
                new KeyFrame(new Duration(duration), new KeyValue(node.scaleYProperty(), toBigger ? 1 : 0.5))
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
