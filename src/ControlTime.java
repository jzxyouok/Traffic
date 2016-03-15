import java.awt.*;

/**
 * Created by wk_51920 on 2014/11/10.
 */
public class ControlTime {
    public void control(Time time) {   //根据传入的时间对象，更新时间状态
        if (time.getMinutes() < 59)
            time.setMinutes(time.getMinutes() + 1);
        else {
            time.setHours(time.getHours() + 1);
            time.setMinutes(0);
        }
    }
}
