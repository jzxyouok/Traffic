import java.util.LinkedList;

/**
 * Created by wk_51920 on 2014/11/4.
 */
public class Volvo extends Cars {
    private final static int VELOCITY = 19;                //车辆每分钟行驶速度，为了便于速度控制，放大了10倍，使用整型来控制
    private final static int PASSENGER_LIMIT = 48;         //车辆的乘客上限
    private static int countCarNumber = 0;                 //用于在车辆对象建立时自动生成车牌号
    private final int carNumber = countCarNumber++;
    private String direction;                              //车辆行驶的方向，“XB”：西安到宝鸡  “BX”：宝鸡到西安
    private String positionInfo;                           //记录车辆当前位置信息的字符串
    private boolean runable = true;                        //汽车到发车点是否允许发车
    private float position = 0.0f;                         //用于记录车辆当前距离某站距离的变量
    private int driveredDistence = 0;                      //记录车辆从始发站出发至今行驶的距离
    private LinkedList<Passengers> carsPassengers = new LinkedList<Passengers>();//本车辆上车乘客的链表
    private boolean acceptive = true;                      //汽车此时是否允许乘客上车
    private boolean bjEndStation;                          //是否到达宝鸡终点站
    private boolean xnEndStation;                          //是否到达西安终点站

    private boolean carStop = false;                       //是否停车

    private void xTobControl() {                           //车辆从西安到宝鸡的控制方法
        if (driveredDistence < 120) {
            driveredDistence += VELOCITY;
            position = (float) (driveredDistence) / 10.0f;
            positionInfo = "Iveco #" + carNumber + "  西安以西:" + position + "Km" + " 目前乘客数：" + carsPassengers.size();
        } else if (driveredDistence >= 120 && driveredDistence < 220) {
            if ((220 - driveredDistence < 19) && driveredDistence != 219) {
                int passBeforeGetOff = carsPassengers.size();
                for (int i = 0; i < carsPassengers.size(); i++)
                    if (carsPassengers.get(i).wantedGetOffStation().equals("XY")) { //检查是否有乘客在此站下车，下同
                        carStop = true;
                        carsPassengers.remove(i);
                    }
                if (carStop) {
                    driveredDistence = 219;
                    positionInfo = "Volvo #" + carNumber + " 汽车在咸阳停车两分钟！" + " 下车乘客数：" + (passBeforeGetOff - carsPassengers.size());
                    carStop = false;
                } else {
                    driveredDistence += VELOCITY;
                    position = ((float) (driveredDistence) - 220.0f) / 10.0f;
                    positionInfo = "Volvo #" + carNumber + " 咸阳以西:" + position + "Km" + " 目前乘客数：" + carsPassengers.size();
                }
            } else {
                if (driveredDistence == 219) {
                    driveredDistence = 220;
                    positionInfo = "Volvo #" + carNumber + " 汽车在咸阳停车两分钟！" + " 目前乘客数：" + carsPassengers.size();
                } else {
                    driveredDistence += VELOCITY;
                    position = (220.0f - (float) (driveredDistence)) / 10.0f;
                    positionInfo = "Volvo #" + carNumber + " 咸阳以东:" + position + "Km" + " 目前乘客数：" + carsPassengers.size();
                }
            }

        } else if (driveredDistence >= 220 && driveredDistence < 340) {
            driveredDistence += VELOCITY;
            position = ((float) (driveredDistence) - 220.0f) / 10.0f;
            positionInfo = "Volvo #" + carNumber + " 咸阳以西:" + position + "Km" + " 目前乘客数：" + carsPassengers.size();
        } else if (driveredDistence >= 340 && driveredDistence < 460) {
            if ((460 - driveredDistence < 19) && driveredDistence != 459) {
                int passBeforeGetOff = carsPassengers.size();
                for (int i = 0; i < carsPassengers.size(); i++)
                    if (carsPassengers.get(i).wantedGetOffStation().equals("XP")) {
                        carStop = true;
                        carsPassengers.remove(i);
                    }
                if (carStop) {
                    driveredDistence = 459;
                    positionInfo = "Volvo #" + carNumber + " 汽车在兴平停车两分钟！" + " 下车乘客数：" + (passBeforeGetOff - carsPassengers.size());
                    carStop = false;
                } else {
                    driveredDistence += VELOCITY;
                    position = ((float) driveredDistence - 460.0f) / 10.0f;
                    positionInfo = "Volvo #" + carNumber + " 兴平以西:" + position + "Km" + " 目前乘客数：" + carsPassengers.size();
                }
            } else {
                if (driveredDistence == 459) {
                    driveredDistence = 460;
                    positionInfo = "Volvo #" + carNumber + " 汽车在兴平停车两分钟！" + " 目前乘客数：" + carsPassengers.size();
                } else {
                    driveredDistence += VELOCITY;
                    position = (460.0f - (float) driveredDistence) / 10.0f;
                    positionInfo = "Volvo #" + carNumber + " 兴平以东:" + position + "Km" + " 目前乘客数：" + carsPassengers.size();
                }
            }
        } else if (driveredDistence >= 460 && driveredDistence < 560) {
            driveredDistence += VELOCITY;
            position = ((float) (driveredDistence) - 460.0f) / 10.0f;
            positionInfo = "Volvo #" + carNumber + " 兴平以西:" + position + "Km" + " 目前乘客数：" + carsPassengers.size();
        } else if (driveredDistence >= 560 && driveredDistence < 670) {
            if ((670 - driveredDistence < 19) && driveredDistence != 667) {
                int passBeforeGetOff = carsPassengers.size();
                for (int i = 0; i < carsPassengers.size(); i++)
                    if (carsPassengers.get(i).wantedGetOffStation().equals("WG")) {
                        carStop = true;
                        carsPassengers.remove(i);
                    }
                if (carStop) {
                    driveredDistence = 667;
                    positionInfo = "Volvo #" + carNumber + " 汽车在武功停车两分钟！" + " 下车乘客数：" + (passBeforeGetOff - carsPassengers.size());
                    carStop = false;
                } else {
                    driveredDistence += VELOCITY;
                    position = ((float) driveredDistence - 670.0f) / 10.0f;
                    positionInfo = "Volvo #" + carNumber + " 武功以西:" + position + "Km" + " 目前乘客数：" + carsPassengers.size();
                }
            } else {
                if (driveredDistence == 667) {
                    driveredDistence = 670;
                    positionInfo = "Volvo #" + carNumber + " 汽车在武功停车两分钟！" + " 目前乘客数：" + carsPassengers.size();
                } else {
                    driveredDistence += VELOCITY;
                    position = (670.0f - (float) driveredDistence) / 10.0f;
                    positionInfo = "Volvo #" + carNumber + " 武功以东:" + position + "Km" + " 目前乘客数：" + carsPassengers.size();
                }
            }
        } else if (driveredDistence >= 670 && driveredDistence < 980) {
            driveredDistence += VELOCITY;
            position = ((float) driveredDistence - 670.0f) / 10.0f;
            positionInfo = "Volvo #" + carNumber + " 武功以西:" + position + "Km" + " 目前乘客数：" + carsPassengers.size();
        } else if (driveredDistence >= 980 && driveredDistence < 1290) {
            if ((1290 - driveredDistence < 19) && driveredDistence != 1289) {
                int passBeforeGetOff = carsPassengers.size();
                for (int i = 0; i < carsPassengers.size(); i++)
                    if (carsPassengers.get(i).wantedGetOffStation().equals("CP")) {
                        carStop = true;
                        carsPassengers.remove(i);
                    }
                if (carStop) {
                    driveredDistence = 1289;
                    positionInfo = "Volvo #" + carNumber + " 汽车在蔡家坡停车两分钟！" + " 下车乘客数：" + (passBeforeGetOff - carsPassengers.size());
                    carStop = false;
                } else {
                    driveredDistence += VELOCITY;
                    position = ((float) driveredDistence - 1290.0f) / 10.0f;
                    positionInfo = "Volvo #" + carNumber + " 蔡家坡以西:" + position + "Km" + " 目前乘客数：" + carsPassengers.size();
                }
            } else {
                if (driveredDistence == 1289) {
                    driveredDistence = 1290;
                    positionInfo = "Volvo #" + carNumber + " 汽车在蔡家坡停车两分钟！" + " 目前乘客数：" + carsPassengers.size();
                } else {
                    driveredDistence += VELOCITY;
                    position = (1290.0f - (float) driveredDistence) / 10.0f;
                    positionInfo = "Volvo #" + carNumber + " 蔡家坡以东:" + position + "Km" + " 目前乘客数：" + carsPassengers.size();
                }
            }
        } else if (driveredDistence >= 1290 && driveredDistence < 1390) {
            driveredDistence += VELOCITY;
            position = ((float) driveredDistence - 1290.0f) / 10.0f;
            positionInfo = "Volvo #" + carNumber + " 蔡家坡以西:" + position + "Km" + " 目前乘客数：" + carsPassengers.size();
        } else if (driveredDistence >= 1390 && driveredDistence < 1500) {
            if ((1500 - driveredDistence < 19) && driveredDistence != 1495) {
                int passBeforeGetOff = carsPassengers.size();
                for (int i = 0; i < carsPassengers.size(); i++)
                    if (carsPassengers.get(i).wantedGetOffStation().equals("GZ")) {
                        carStop = true;
                        carsPassengers.remove(i);
                    }
                if (carStop) {
                    driveredDistence = 1495;
                    positionInfo = "Volvo #" + carNumber + " 汽车在虢镇停车两分钟！" + " 下车乘客数：" + (passBeforeGetOff - carsPassengers.size());
                    carStop = false;
                } else {
                    driveredDistence += VELOCITY;
                    position = ((float) driveredDistence - 1500.0f) / 10.0f;
                    positionInfo = "Volvo #" + carNumber + " 虢镇以西:" + position + "Km" + " 目前乘客数：" + carsPassengers.size();
                }
            } else {
                if (driveredDistence == 1495) {
                    driveredDistence = 1500;
                    positionInfo = "Volvo #" + carNumber + " 汽车在虢镇停车两分钟！" + " 目前乘客数：" + carsPassengers.size();
                } else {
                    driveredDistence += VELOCITY;
                    position = (1500.0f - (float) driveredDistence) / 10.0f;
                    positionInfo = "Volvo #" + carNumber + " 虢镇以东:" + position + "Km" + " 目前乘客数：" + carsPassengers.size();
                }
            }
        } else if (driveredDistence >= 1500 && driveredDistence < 1620) {
            driveredDistence += VELOCITY;
            position = ((float) driveredDistence - 1501.0f) / 10.0f;
            positionInfo = "Volvo #" + carNumber + " 虢镇以西:" + position + "Km" + " 目前乘客数：" + carsPassengers.size();
        } else if (driveredDistence >= 1620 && driveredDistence <= 1740) {
            if ((1740 - driveredDistence < 19) && driveredDistence != 1735) {
                int passBeforeGetOff = carsPassengers.size();
                carsPassengers.clear();
                carStop = true;
                if (carStop) {
                    driveredDistence = 1735;
                    positionInfo = "Volvo #" + carNumber + " 汽车到达终点站宝鸡！" + " 下车乘客数：" + (passBeforeGetOff - carsPassengers.size());
                    carStop = false;
                }
            } else {
                if (driveredDistence == 1735) {
                    driveredDistence = 1740;
                    this.bjEndStation = true;
                    positionInfo = "Volvo #" + carNumber + " 汽车到达终点站宝鸡！" + " 目前乘客数：" + carsPassengers.size();
                } else {
                    driveredDistence += VELOCITY;
                    position = (1740.0f - (float) driveredDistence) / 10.0f;
                    positionInfo = "Volvo #" + carNumber + "  宝鸡以东:" + position + "Km" + " 目前乘客数：" + carsPassengers.size();
                }
            }
        }
    }


    private void bToxControl() {  //宝鸡到西安的行驶控制方法，内容与上面类似
        if (driveredDistence < 120) {
            driveredDistence += VELOCITY;
            position = (float) (driveredDistence) / 10.0f;
            positionInfo = "Volvo #" + carNumber + " 宝鸡以东:" + position + "Km" + " 目前乘客数：" + carsPassengers.size();
        } else if (driveredDistence >= 120 && driveredDistence < 240) {
            if ((240 - driveredDistence < 19) && driveredDistence != 239) {
                int passBeforeGetOff = carsPassengers.size();
                for (int i = 0; i < carsPassengers.size(); i++)
                    if (carsPassengers.get(i).wantedGetOffStation().equals("GZ")) {
                        carStop = true;
                        carsPassengers.remove(i);
                    }
                if (carStop) {
                    driveredDistence = 239;
                    positionInfo = "Volvo #" + carNumber + " 汽车在虢镇停车两分钟！" + " 下车乘客数：" + (passBeforeGetOff - carsPassengers.size());
                    carStop = false;
                } else {
                    driveredDistence += VELOCITY;
                    position = ((float) driveredDistence - 240.0f) / 10.0f;
                    positionInfo = "Volvo #" + carNumber + " 虢镇以东:" + position + "Km" + " 目前乘客数：" + carsPassengers.size();
                }
            } else {
                if (driveredDistence == 239) {
                    driveredDistence = 240;
                    positionInfo = "Volvo #" + carNumber + " 汽车在虢镇停车两分钟！" + " 目前乘客数：" + carsPassengers.size();
                } else {
                    driveredDistence += VELOCITY;
                    position = (240.0f - (float) driveredDistence) / 10.0f;
                    positionInfo = "Volvo #" + carNumber + " 虢镇以西:" + position + "Km" + " 目前乘客数：" + carsPassengers.size();
                }
            }
        } else if (driveredDistence >= 240 && driveredDistence < 345) {
            driveredDistence += VELOCITY;
            position = ((float) driveredDistence - 240.0f) / 10.0f;
            positionInfo = "Volvo #" + carNumber + " 虢镇以东:" + position + "Km" + " 目前乘客数：" + carsPassengers.size();
        } else if (driveredDistence >= 345 && driveredDistence < 450) {
            if ((450 - driveredDistence < 19) && driveredDistence != 445) {
                int passBeforeGetOff = carsPassengers.size();
                for (int i = 0; i < carsPassengers.size(); i++)
                    if (carsPassengers.get(i).wantedGetOffStation().equals("CP")) {
                        carStop = true;
                        carsPassengers.remove(i);
                    }
                if (carStop) {
                    driveredDistence = 445;
                    positionInfo = "Volvo #" + carNumber + " 汽车在蔡家坡停车两分钟！" + " 下车乘客数：" + (passBeforeGetOff - carsPassengers.size());
                    carStop = false;
                } else {
                    driveredDistence += VELOCITY;
                    position = ((float) driveredDistence - 450.0f) / 10.0f;
                    positionInfo = "Volvo #" + carNumber + " 蔡家坡以东:" + position + "Km" + " 目前乘客数：" + carsPassengers.size();
                }
            } else {
                if (driveredDistence == 445) {
                    driveredDistence = 450;
                    positionInfo = "Volvo #" + carNumber + " 汽车在蔡家坡停车两分钟！" + " 目前乘客数：" + carsPassengers.size();
                } else {
                    driveredDistence += VELOCITY;
                    position = (450.0f - (float) driveredDistence) / 10.0f;
                    positionInfo = "Volvo #" + carNumber + " 蔡家坡以西:" + position + "Km" + " 目前乘客数：" + carsPassengers.size();
                }
            }
        } else if (driveredDistence >= 450 && driveredDistence < 760) {
            driveredDistence += VELOCITY;
            position = ((float) driveredDistence - 450.0f) / 10.0f;
            positionInfo = "Volvo #" + carNumber + " 蔡家坡以东:" + position + "Km" + " 目前乘客数：" + carsPassengers.size();
        } else if (driveredDistence >= 760 && driveredDistence < 1070) {
            if ((1070 - driveredDistence < 19) && driveredDistence != 1068) {
                int passBeforeGetOff = carsPassengers.size();
                for (int i = 0; i < carsPassengers.size(); i++)
                    if (carsPassengers.get(i).wantedGetOffStation().equals("WG")) {
                        carStop = true;
                        carsPassengers.remove(i);
                    }
                if (carStop) {
                    driveredDistence = 1068;
                    positionInfo = "Volvo #" + carNumber + " 汽车在武功停车两分钟！" + " 下车乘客数：" + (passBeforeGetOff - carsPassengers.size());
                    carStop = false;
                } else {
                    driveredDistence += VELOCITY;
                    position = ((float) driveredDistence - 1070.0f) / 10.0f;
                    positionInfo = "Volvo #" + carNumber + " 武功以东:" + position + "Km" + " 目前乘客数：" + carsPassengers.size();
                }
            } else {
                if (driveredDistence == 1068) {
                    driveredDistence = 1070;
                    positionInfo = "Volvo #" + carNumber + " 汽车在武功停车两分钟！" + " 目前乘客数：" + carsPassengers.size();
                } else {
                    driveredDistence += VELOCITY;
                    position = (1070.0f - (float) driveredDistence) / 10.0f;
                    positionInfo = "Volvo #" + carNumber + " 武功以西:" + position + "Km" + " 目前乘客数：" + carsPassengers.size();
                }
            }
        } else if (driveredDistence >= 1070 && driveredDistence < 1175) {
            driveredDistence += VELOCITY;
            position = ((float) driveredDistence - 1070.0f) / 10.0f;
            positionInfo = "Volvo #" + carNumber + " 武功以东:" + position + "Km" + " 目前乘客数：" + carsPassengers.size();
        } else if (driveredDistence >= 1175 && driveredDistence < 1280) {
            if ((1280 - driveredDistence < 19) && driveredDistence != 1275) {
                int passBeforeGetOff = carsPassengers.size();
                for (int i = 0; i < carsPassengers.size(); i++)
                    if (carsPassengers.get(i).wantedGetOffStation().equals("XP")) {
                        carStop = true;
                        carsPassengers.remove(i);
                    }
                if (carStop) {
                    driveredDistence = 1275;
                    positionInfo = "Volvo #" + carNumber + " 汽车在兴平停车两分钟！" + " 下车乘客数：" + (passBeforeGetOff - carsPassengers.size());
                    carStop = false;
                } else {
                    driveredDistence += VELOCITY;
                    position = ((float) driveredDistence - 1280.0f) / 10.0f;
                    positionInfo = "Volvo #" + carNumber + " 兴平以东:" + position + "Km" + " 目前乘客数：" + carsPassengers.size();
                }
            } else {
                if (driveredDistence == 1275) {
                    driveredDistence = 1280;
                    positionInfo = "Volvo #" + carNumber + " 汽车在兴平停车两分钟！" + " 目前乘客数：" + carsPassengers.size();
                } else {
                    driveredDistence += VELOCITY;
                    position = (1280.0f - (float) driveredDistence) / 10.0f;
                    positionInfo = "Volvo #" + carNumber + " 兴平以西:" + position + "Km" + " 目前乘客数：" + carsPassengers.size();
                }
            }
        } else if (driveredDistence >= 1280 && driveredDistence < 1400) {
            driveredDistence += VELOCITY;
            position = ((float) driveredDistence - 1281.0f) / 10.0f;
            positionInfo = "Volvo #" + carNumber + " 兴平以东:" + position + "Km" + " 目前乘客数：" + carsPassengers.size();
        } else if (driveredDistence >= 1400 && driveredDistence <= 1520) {
            if ((1520 - driveredDistence < 19) && driveredDistence != 1517) {
                int passBeforeGetOff = carsPassengers.size();
                for (int i = 0; i < carsPassengers.size(); i++)
                    if (carsPassengers.get(i).wantedGetOffStation().equals("XY")) {
                        carStop = true;
                        carsPassengers.remove(i);
                    }
                if (carStop) {
                    driveredDistence = 1517;
                    positionInfo = "Volvo #" + carNumber + " 汽车在咸阳停车两分钟！" + " 下车乘客数：" + (passBeforeGetOff - carsPassengers.size());
                    carStop = false;
                } else {
                    driveredDistence += VELOCITY;
                    position = ((float) driveredDistence - 1520.0f) / 10.0f;
                    positionInfo = "Volvo #" + carNumber + " 咸阳以东:" + position + "Km" + " 目前乘客数：" + carsPassengers.size();
                }
            } else {
                if (driveredDistence == 1517) {
                    driveredDistence = 1521;
                    positionInfo = "Volvo #" + carNumber + " 汽车在咸阳停车两分钟！" + " 目前乘客数：" + carsPassengers.size();
                } else {
                    driveredDistence += VELOCITY;
                    position = (1520.0f - (float) driveredDistence) / 10.0f;
                    positionInfo = "Volvo #" + carNumber + " 咸阳以西:" + position + "Km" + " 目前乘客数：" + carsPassengers.size();
                }
            }
        } else if (driveredDistence >= 1521 && driveredDistence < 1630) {
            driveredDistence += VELOCITY;
            position = ((float) driveredDistence - 1520.0f) / 10.0f;
            positionInfo = "Volvo #" + carNumber + " 咸阳以东:" + position + "Km" + " 目前乘客数：" + carsPassengers.size();
        } else if (driveredDistence >= 1630 && driveredDistence <= 1740) {
            if ((1740 - driveredDistence < 19) && driveredDistence != 1735) {
                int passBeforeGetOff = carsPassengers.size();
                carsPassengers.clear();
                carStop = true;
                if (carStop) {
                    driveredDistence = 1735;
                    positionInfo = "Volvo #" + carNumber + " 汽车到达终点站西安！" + " 下车乘客数：" + (passBeforeGetOff - carsPassengers.size());
                    carStop = false;
                }
            } else {
                if (driveredDistence == 1735) {
                    driveredDistence = 1740;
                    this.xnEndStation = true;
                    positionInfo = "Volvo #" + carNumber + " 汽车到达终点站西安！" + " 目前乘客数：" + carsPassengers.size();
                } else {
                    driveredDistence += VELOCITY;
                    position = (1740.0f - (float) driveredDistence) / 10.0f;
                    positionInfo = "Volvo #" + carNumber + "  西安以西:" + position + "Km" + " 目前乘客数：" + carsPassengers.size();
                }
            }
        }
    }

    public Volvo(String direction) {    //构造函数，初始化车辆行驶方向，及一些状态
        this.direction = direction;
        if (direction.equals("BX")) {
            bjEndStation = true;
            xnEndStation = false;
        } else if (direction.equals("XB")) {
            bjEndStation = false;
            xnEndStation = true;
        }
    }

    public void clearCountCarNumber() {
        this.countCarNumber = 0;
    }

    public boolean getRunable() {
        return this.runable;
    } //向StationsControl类提供此车到出发点时是否允许发车

    public void setRunable(boolean runable) {
        this.runable = runable;
    }//设置到发车点能否发车的布尔值

    public void setAcceptive(boolean flag) {
        this.acceptive = flag;
    } //设置和得到此车是否允许乘客上车

    public boolean getAcceptive() {
        return this.acceptive;
    }

    public void addPassengers(Passengers passengers) {
        this.carsPassengers.offer(passengers);
    } //增加乘客

    public int getPassengersLimit() {
        return this.PASSENGER_LIMIT;
    }  //获得乘客上限


    public void setDirection(String direction) {
        this.direction = direction;
    } //设置车辆行驶方向

    public String getCarNumber() {
        return "#" + carNumber;
    } //获得车牌号

    public void setDriveredDistence(int distence) {
        this.driveredDistence = distence;
    }//设置车辆离开始发站的距离


    public String getPositionInfo() {
        return positionInfo;
    } //获得和设置车辆当前时刻位置信息

    public void setPositionInfo(String positionInfo) {
        this.positionInfo = positionInfo;
    }

    public int getPresentPassengers() {
        return this.carsPassengers.size();
    }   //获得当前车辆乘客数量

    public void clearPresentPassengers() {
        carsPassengers.clear();
    }           //清空乘客

    public void setBjEndStation(boolean end) {
        this.bjEndStation = end;
    } //设置和获得车辆是否到终点站

    public boolean getBjEndStation() {
        return this.bjEndStation;
    }

    public void setXnEndStation(boolean end) {
        this.xnEndStation = end;
    }

    public boolean getXnEndStation() {
        return this.xnEndStation;
    }

    public void run() {          //用于每秒更新车辆行驶状态
        if (direction.equals("XB")) {
            xTobControl();
        } else if (direction.equals("BX")) {
            bToxControl();
        }
    }
}
