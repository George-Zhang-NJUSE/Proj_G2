package group2.grade15.njuse.presentation.myanimation;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.util.Duration;

/**
 * Created by George on 2016/12/16.
 */
public class Translate {
    private Timeline timeline;

    /**
     * @param node     要平移的面板
     * @param duration 以毫秒计的持续时间长度，正数
     * @param endLocation 结束时的位置
     * @param isHorrizontal true表示水平平移，false表示垂直平移
     **/
    public Translate(Node node, int duration, int endLocation, boolean isHorrizontal) {
        timeline = new Timeline();
        if(isHorrizontal){
            timeline.getKeyFrames().addAll(
                    new KeyFrame(Duration.ZERO, new KeyValue(node.translateXProperty(), node.getTranslateX())),
                    new KeyFrame(new Duration(duration), new KeyValue(node.translateXProperty(), endLocation))
            );
        }else{
            timeline.getKeyFrames().addAll(
                    new KeyFrame(Duration.ZERO, new KeyValue(node.translateYProperty(), node.getTranslateY())),
                    new KeyFrame(new Duration(duration), new KeyValue(node.translateYProperty(), endLocation))
            );
        }

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
