package group2.grade15.njuse.presentation.myanimation;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

/**
 * 无限循环的图片切换动画
 * Created by George on 2016/12/10.
 */
public class ChangeImage {

    private Timeline timeline;

    /**
     * @param view 要改变图片的ImageView
     * @param cycleLength 以毫秒计的改变周期长度，正数
     * @param imageList 图片库
     **/
    public ChangeImage(ImageView view, int cycleLength, Image[] imageList) {
        timeline = new Timeline();
        KeyFrame[] keyFrameList=new KeyFrame[imageList.length];

        for(int a=0;a<imageList.length;a++) {
            keyFrameList[a] = new KeyFrame(new Duration(a * cycleLength), new KeyValue(view.imageProperty(), imageList[a]));
        }

        timeline.getKeyFrames().addAll(keyFrameList);
        timeline.setCycleCount(Timeline.INDEFINITE);
    }

    public void play() {
        timeline.play();
    }
}
