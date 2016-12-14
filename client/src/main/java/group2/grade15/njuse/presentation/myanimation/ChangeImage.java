package group2.grade15.njuse.presentation.myanimation;

import javafx.event.ActionEvent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * 无限循环的图片切换动画
 * Created by George on 2016/12/10.
 */
public class ChangeImage {

    private Fade[] fades;

    /**
     * @param view 要改变图片的ImageView
     * @param cycleLength 以毫秒计的改变周期长度，正数
     * @param imageList 图片库
     **/
    public ChangeImage(ImageView view, int cycleLength, Image[] imageList) {
        fades = new Fade[imageList.length*2];
        view.setImage(imageList[0]);
        view.setOpacity(0);

        //创建循环图片切换动画，每张图片对应两个动画
        for(int a=0;a<imageList.length;a++) {
            int inIndex=a*2;
            int outIndex=inIndex+1;

            fades[inIndex] = new Fade(view, 300, true);
            fades[outIndex] = new Fade(view, 300, false);
        }

        //设置动画间衔接
        for(int a=0;a<imageList.length;a++) {
            int inIndex=a*2;
            int outIndex=inIndex+1;
            int nextImageIndex = (a==imageList.length-1)? 0:a+1;
            fades[inIndex].setOnFinished((ActionEvent e)->{
                try {
                    Thread.currentThread().sleep(cycleLength);
                    fades[outIndex].play();
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
            });

            fades[outIndex].setOnFinished((ActionEvent e)->{
                view.setImage(imageList[nextImageIndex]);
                fades[nextImageIndex*2].play();  //下一张图片的fadeIn
            });
        }

    }


    public void play() {
        fades[0].play();
    }
}
