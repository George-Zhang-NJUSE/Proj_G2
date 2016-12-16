package group2.grade15.njuse.presentation.myanimation;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.util.Duration;

/**
 * 渐变动画，通过改变节点不透明度实现。注意：动画播放完毕后，节点会自动恢复其原有不透明度属性
 * Created by George on 2016/11/25.
 */
public class Fade {

    private Timeline timeline;

    /**
     * @param node     要渐变的节点
     * @param duration 以毫秒计的持续时间长度，正数
     * @param fadeIn   true表示由不可见到可见，false表示由可见到不可见
     **/
    public Fade(Node node, int duration, boolean fadeIn) {
        timeline = new Timeline();
        timeline.getKeyFrames().addAll(
                new KeyFrame(Duration.ZERO, new KeyValue(node.opacityProperty(), fadeIn ? 0 : 1)),
                new KeyFrame(new Duration(duration), new KeyValue(node.opacityProperty(), fadeIn ? 1 : 0))
        );
    }

    /**
     * 仅供其他动画类调用
    * */
    protected Timeline getTimeline() {
        return timeline;
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
