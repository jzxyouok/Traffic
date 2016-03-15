
/**
 * Created by wk_51920 on 2014/11/4.
 */
public class Cars {      //此类只是为了使用多态来方便统一获取各车辆的信息，具体各函数功能在Volvo Iveco类中
    private String positionInfo;
    private boolean bjEndStation;     //是否到达宝鸡终点站
    private boolean xnEndStation;     //是否到达西安终点站
    private String carNumber;
    private float driveredDistence = 0.0f;

    public String getPositionInfo() {
        return positionInfo;
    }


    public boolean getBjEndStation() {
        return this.bjEndStation;
    }


    public boolean getXnEndStation() {
        return this.xnEndStation;
    }

    public void setPositionInfo(String positionInfo) {
    }

    public String getCarNumber() {
        return carNumber;
    }


    public void run() {

    }

}