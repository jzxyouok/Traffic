
/**
 * Created by wk_51920 on 2014/11/9.
 */
public class Time{
    private int minutes;
    private int hours;

    public Time() {       //初始时间
        this.minutes = 30;
        this.hours = 7;
    }

    public String showTime() {
        return hours + ":" + minutes;
    }  //时间的显示形式

    public void resetTime() {           //重置时间
        this.minutes = 30;
        this.hours = 7;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }  //向时间控制类提供修改和获取当前时间的接口
    public int getMinutes() {
        return this.minutes;
    }
    public void setHours(int hours) {
        this.hours = hours;
    }
    public int getHours()
    {
        return this.hours;
    }
}
