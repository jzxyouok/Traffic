import java.util.*;

/**
 * Created by wk_51920 on 2014/11/4.
 */
// 此类中所有成员变量和成员方法的功能跟Volvo中类同，不再一一注释
public class Iveco extends Cars {
    private final static int VELOCITY = 14;
    private final static int PASSENGER_LIMIT = 21;
    private static int countCarNumber = 0;
    private final int carNumber = countCarNumber++;
    private String direction;
    private String positionInfo;
    private boolean runable = true;           //汽车到发车点是否允许发车
    private float position = 0.0f;
    private int driveredDistence = 0;
    private LinkedList<Passengers> carsPassengers = new LinkedList<Passengers>();
    private boolean acceptive = true;        //汽车此时是否允许乘客上车
    private boolean bjEndStation;     //是否到达宝鸡终点站
    private boolean xnEndStation;     //是否到达西安终点站
    private boolean carStop = false;      //到某站是否停车

    private void xTobControl() {
        if (driveredDistence < 120) {
            driveredDistence += VELOCITY;
            position = (float) (driveredDistence) / 10.0f;
            positionInfo = "Iveco #" + carNumber + "  西安以西:" + position + "Km" + " 目前乘客数：" + carsPassengers.size();
        } else if (driveredDistence >= 120 && driveredDistence < 220) {
            if ((220 - driveredDistence < 14) && driveredDistence != 219) {
                int passBeforeGetOff = carsPassengers.size();
                for (int i = 0; i < carsPassengers.size(); i++)
                    if (carsPassengers.get(i).wantedGetOffStation().equals("XY")) {
                        carStop = true;
                        carsPassengers.remove(i);
                    }
                if (carStop) {
                    driveredDistence = 219;
                    positionInfo = "Iveco #" + carNumber + " 汽车在咸阳停车两分钟！" + " 下车乘客数：" + (passBeforeGetOff - carsPassengers.size());
                    carStop = false;
                } else {
                    driveredDistence += VELOCITY;
                    position = ((float) (driveredDistence) - 220.0f) / 10.0f;
                    positionInfo = "Iveco #" + carNumber + " 咸阳以西:" + position + "Km" + " 目前乘客数：" + carsPassengers.size();
                }
            } else {
                if (driveredDistence == 219) {
                    driveredDistence = 220;
                    positionInfo = "Iveco #" + carNumber + " 汽车在咸阳停车两分钟！" + " 目前乘客数：" + carsPassengers.size();
                } else {
                    driveredDistence += VELOCITY;
                    position = (220.0f - (float) (driveredDistence)) / 10.0f;
                    positionInfo = "Iveco #" + carNumber + " 咸阳以东:" + position + "Km" + " 目前乘客数：" + carsPassengers.size();
                }
            }

        } else if (driveredDistence >= 220 && driveredDistence < 340) {
            driveredDistence += VELOCITY;
            position = ((float) (driveredDistence) - 220.0f) / 10.0f;
            positionInfo = "Iveco #" + carNumber + " 咸阳以西:" + position + "Km" + " 目前乘客数：" + carsPassengers.size();
        } else if (driveredDistence >= 340 && driveredDistence < 460) {
            if ((460 - driveredDistence < 14) && driveredDistence != 459) {
                int passBeforeGetOff = carsPassengers.size();
                for (int i = 0; i < carsPassengers.size(); i++)
                    if (carsPassengers.get(i).wantedGetOffStation().equals("XP")) {
                        carStop = true;
                        carsPassengers.remove(i);
                    }
                if (carStop) {
                    driveredDistence = 459;
                    positionInfo = "Iveco #" + carNumber + " 汽车在兴平停车两分钟！" + " 下车乘客数：" + (passBeforeGetOff - carsPassengers.size());
                    carStop = false;
                } else {
                    driveredDistence += VELOCITY;
                    position = ((float) driveredDistence - 460.0f) / 10.0f;
                    positionInfo = "Iveco #" + carNumber + " 兴平以西:" + position + "Km" + " 目前乘客数：" + carsPassengers.size();
                }
            } else {
                if (driveredDistence == 459) {
                    driveredDistence = 460;
                    positionInfo = "Iveco #" + carNumber + " 汽车在兴平停车两分钟！" + " 目前乘客数：" + carsPassengers.size();
                } else {
                    driveredDistence += VELOCITY;
                    position = (460.0f - (float) driveredDistence) / 10.0f;
                    positionInfo = "Iveco #" + carNumber + " 兴平以东:" + position + "Km" + " 目前乘客数：" + carsPassengers.size();
                }
            }
        } else if (driveredDistence >= 460 && driveredDistence < 560) {
            driveredDistence += VELOCITY;
            position = ((float) (driveredDistence) - 460.0f) / 10.0f;
            positionInfo = "Iveco #" + carNumber + " 兴平以西:" + position + "Km" + " 目前乘客数：" + carsPassengers.size();
        } else if (driveredDistence >= 560 && driveredDistence <= 670) {
            if ((670 - driveredDistence < 14) && driveredDistence != 669) {
                int passBeforeGetOff = carsPassengers.size();
                for (int i = 0; i < carsPassengers.size(); i++)
                    if (carsPassengers.get(i).wantedGetOffStation().equals("WG")) {
                        carStop = true;
                        carsPassengers.remove(i);
                    }
                if (carStop) {
                    driveredDistence = 669;
                    positionInfo = "Iveco #" + carNumber + " 汽车在武功停车两分钟！" + " 下车乘客数：" + (passBeforeGetOff - carsPassengers.size());
                    carStop = false;
                } else {
                    driveredDistence += VELOCITY;
                    position = ((float) driveredDistence - 670.0f) / 10.0f;
                    positionInfo = "Iveco #" + carNumber + " 武功以西:" + position + "Km" + " 目前乘客数：" + carsPassengers.size();
                }
            } else {
                if (driveredDistence == 669) {
                    driveredDistence = 671;
                    positionInfo = "Iveco #" + carNumber + " 汽车在武功停车两分钟！" + " 目前乘客数：" + carsPassengers.size();
                } else {
                    driveredDistence += VELOCITY;
                    position = (670.0f - (float) driveredDistence) / 10.0f;
                    positionInfo = "Iveco #" + carNumber + " 武功以东:" + position + "Km" + " 目前乘客数：" + carsPassengers.size();
                }
            }
        } else if (driveredDistence > 670 && driveredDistence < 980) {
            driveredDistence += VELOCITY;
            position = ((float) driveredDistence - 670.0f) / 10.0f;
            positionInfo = "Iveco #" + carNumber + " 武功以西:" + position + "Km" + " 目前乘客数：" + carsPassengers.size();
        } else if (driveredDistence >= 980 && driveredDistence < 1290) {
            if ((1290 - driveredDistence < 14) && driveredDistence != 1289) {
                int passBeforeGetOff = carsPassengers.size();
                for (int i = 0; i < carsPassengers.size(); i++)
                    if (carsPassengers.get(i).wantedGetOffStation().equals("CP")) {
                        carStop = true;
                        carsPassengers.remove(i);
                    }
                if (carStop) {
                    driveredDistence = 1289;
                    positionInfo = "Iveco #" + carNumber + " 汽车在蔡家坡停车两分钟！" + " 下车乘客数：" + (passBeforeGetOff - carsPassengers.size());
                    carStop = false;
                } else {
                    driveredDistence += VELOCITY;
                    position = ((float) driveredDistence - 1290.0f) / 10.0f;
                    positionInfo = "Iveco #" + carNumber + " 蔡家坡以西:" + position + "Km" + " 目前乘客数：" + carsPassengers.size();
                }
            } else {
                if (driveredDistence == 1289) {
                    driveredDistence = 1290;
                    positionInfo = "Iveco #" + carNumber + " 汽车在蔡家坡停车两分钟！" + " 目前乘客数：" + carsPassengers.size();
                } else {
                    driveredDistence += VELOCITY;
                    position = (1290.0f - (float) driveredDistence) / 10.0f;
                    positionInfo = "Iveco #" + carNumber + " 蔡家坡以东:" + position + "Km" + " 目前乘客数：" + carsPassengers.size();
                }
            }
        } else if (driveredDistence >= 1290 && driveredDistence < 1390) {
            driveredDistence += VELOCITY;
            position = ((float) driveredDistence - 1290.0f) / 10.0f;
            positionInfo = "Iveco #" + carNumber + " 蔡家坡以西:" + position + "Km" + " 目前乘客数：" + carsPassengers.size();
        } else if (driveredDistence >= 1390 && driveredDistence <= 1500) {
            if ((1500 - driveredDistence < 14) && driveredDistence != 1499) {
                int passBeforeGetOff = carsPassengers.size();
                for (int i = 0; i < carsPassengers.size(); i++)
                    if (carsPassengers.get(i).wantedGetOffStation().equals("GZ")) {
                        carStop = true;
                        carsPassengers.remove(i);
                    }
                if (carStop) {
                    driveredDistence = 1499;
                    positionInfo = "Iveco #" + carNumber + " 汽车在虢镇停车两分钟！" + " 下车乘客数：" + (passBeforeGetOff - carsPassengers.size());
                    carStop = false;
                } else {
                    driveredDistence += VELOCITY;
                    position = ((float) driveredDistence - 1500.0f) / 10.0f;
                    positionInfo = "Iveco #" + carNumber + " 虢镇以西:" + position + "Km" + " 目前乘客数：" + carsPassengers.size();
                }
            } else {
                if (driveredDistence == 1499) {
                    driveredDistence = 1501;
                    positionInfo = "Iveco #" + carNumber + " 汽车在虢镇停车两分钟！" + " 目前乘客数：" + carsPassengers.size();
                } else {
                    driveredDistence += VELOCITY;
                    position = (1500.0f - (float) driveredDistence) / 10.0f;
                    positionInfo = "Iveco #" + carNumber + " 虢镇以东:" + position + "Km" + " 目前乘客数：" + carsPassengers.size();
                }
            }
        } else if (driveredDistence >= 1501 && driveredDistence < 1620) {
            driveredDistence += VELOCITY;
            position = ((float) driveredDistence - 1501.0f) / 10.0f;
            positionInfo = "Iveco #" + carNumber + " 虢镇以西:" + position + "Km" + " 目前乘客数：" + carsPassengers.size();
        } else if (driveredDistence >= 1620 && driveredDistence <= 1740) {
            if ((1740 - driveredDistence < 14) && driveredDistence != 1730) {
                int passBeforeGetOff = carsPassengers.size();
                carsPassengers.clear();
                carStop = true;
                if (carStop) {
                    driveredDistence = 1730;
                    positionInfo = "Iveco #" + carNumber + " 汽车到达终点站宝鸡！" + " 下车乘客数：" + (passBeforeGetOff - carsPassengers.size());
                    carStop = false;
                }
            } else {
                if (driveredDistence == 1730) {
                    driveredDistence = 1740;
                    this.bjEndStation = true;
                    positionInfo = "Iveco #" + carNumber + " 汽车到达终点站宝鸡！" + " 目前乘客数：" + carsPassengers.size();
                } else {
                    driveredDistence += VELOCITY;
                    position = (1740.0f - (float) driveredDistence) / 10.0f;
                    positionInfo = "Iveco #" + carNumber + "  宝鸡以东:" + position + "Km" + " 目前乘客数：" + carsPassengers.size();
                }
            }
        }
    }


    private void bToxControl() {
        if (driveredDistence < 120) {
            driveredDistence += VELOCITY;
            position = (float) (driveredDistence) / 10.0f;
            positionInfo = "Iveco #" + carNumber + " 宝鸡以东:" + position + "Km" + " 目前乘客数：" + carsPassengers.size();
        } else if (driveredDistence >= 120 && driveredDistence < 240) {
            if ((240 - driveredDistence < 14) && driveredDistence != 239) {
                int passBeforeGetOff = carsPassengers.size();
                for (int i = 0; i < carsPassengers.size(); i++)
                    if (carsPassengers.get(i).wantedGetOffStation().equals("GZ")) {
                        carStop = true;
                        carsPassengers.remove(i);
                    }
                if (carStop) {
                    driveredDistence = 239;
                    positionInfo = "Iveco #" + carNumber + " 汽车在虢镇停车两分钟！" + " 下车乘客数：" + (passBeforeGetOff - carsPassengers.size());
                    carStop = false;
                } else {
                    driveredDistence += VELOCITY;
                    position = ((float) driveredDistence - 240.0f) / 10.0f;
                    positionInfo = "Iveco #" + carNumber + " 虢镇以东:" + position + "Km" + " 目前乘客数：" + carsPassengers.size();
                }
            } else {
                if (driveredDistence == 239) {
                    driveredDistence = 240;
                    positionInfo = "Iveco #" + carNumber + " 汽车在虢镇停车两分钟！" + " 目前乘客数：" + carsPassengers.size();
                } else {
                    driveredDistence += VELOCITY;
                    position = (240.0f - (float) driveredDistence) / 10.0f;
                    positionInfo = "Iveco #" + carNumber + " 虢镇以西:" + position + "Km" + " 目前乘客数：" + carsPassengers.size();
                }
            }
        } else if (driveredDistence >= 240 && driveredDistence < 345) {
            driveredDistence += VELOCITY;
            position = ((float) driveredDistence - 240.0f) / 10.0f;
            positionInfo = "Iveco #" + carNumber + " 虢镇以东:" + position + "Km" + " 目前乘客数：" + carsPassengers.size();
        } else if (driveredDistence >= 345 && driveredDistence <= 450) {
            if ((450 - driveredDistence < 14) && driveredDistence != 449) {
                int passBeforeGetOff = carsPassengers.size();
                for (int i = 0; i < carsPassengers.size(); i++)
                    if (carsPassengers.get(i).wantedGetOffStation().equals("CP")) {
                        carStop = true;
                        carsPassengers.remove(i);
                    }
                if (carStop) {
                    driveredDistence = 449;
                    positionInfo = "Iveco #" + carNumber + " 汽车在蔡家坡停车两分钟！" + " 下车乘客数：" + (passBeforeGetOff - carsPassengers.size());
                    carStop = false;
                } else {
                    driveredDistence += VELOCITY;
                    position = ((float) driveredDistence - 450.0f) / 10.0f;
                    positionInfo = "Iveco #" + carNumber + " 蔡家坡以东:" + position + "Km" + " 目前乘客数：" + carsPassengers.size();
                }
            } else {
                if (driveredDistence == 449) {
                    driveredDistence = 451;
                    positionInfo = "Iveco #" + carNumber + " 汽车在蔡家坡停车两分钟！" + " 目前乘客数：" + carsPassengers.size();
                } else {
                    driveredDistence += VELOCITY;
                    position = (450.0f - (float) driveredDistence) / 10.0f;
                    positionInfo = "Iveco #" + carNumber + " 蔡家坡以西:" + position + "Km" + " 目前乘客数：" + carsPassengers.size();
                }
            }
        } else if (driveredDistence >= 451 && driveredDistence < 760) {
            driveredDistence += VELOCITY;
            position = ((float) driveredDistence - 450.0f) / 10.0f;
            positionInfo = "Iveco #" + carNumber + " 蔡家坡以东:" + position + "Km" + " 目前乘客数：" + carsPassengers.size();
        } else if (driveredDistence >= 760 && driveredDistence < 1070) {
            if ((1070 - driveredDistence < 14) && driveredDistence != 1068) {
                int passBeforeGetOff = carsPassengers.size();
                for (int i = 0; i < carsPassengers.size(); i++)
                    if (carsPassengers.get(i).wantedGetOffStation().equals("WG")) {
                        carStop = true;
                        carsPassengers.remove(i);
                    }
                if (carStop) {
                    driveredDistence = 1068;
                    positionInfo = "Iveco #" + carNumber + " 汽车在武功停车两分钟！" + " 下车乘客数：" + (passBeforeGetOff - carsPassengers.size());
                    carStop = false;
                } else {
                    driveredDistence += VELOCITY;
                    position = ((float) driveredDistence - 1070.0f) / 10.0f;
                    positionInfo = "Iveco #" + carNumber + " 武功以东:" + position + "Km" + " 目前乘客数：" + carsPassengers.size();
                }
            } else {
                if (driveredDistence == 1068) {
                    driveredDistence = 1070;
                    positionInfo = "Iveco #" + carNumber + " 汽车在武功停车两分钟！" + " 目前乘客数：" + carsPassengers.size();
                } else {
                    driveredDistence += VELOCITY;
                    position = (1070.0f - (float) driveredDistence) / 10.0f;
                    positionInfo = "Iveco #" + carNumber + " 武功以西:" + position + "Km" + " 目前乘客数：" + carsPassengers.size();
                }
            }
        } else if (driveredDistence >= 1070 && driveredDistence < 1175) {
            driveredDistence += VELOCITY;
            position = ((float) driveredDistence - 1070.0f) / 10.0f;
            positionInfo = "Iveco #" + carNumber + " 武功以东:" + position + "Km" + " 目前乘客数：" + carsPassengers.size();
        } else if (driveredDistence >= 1175 && driveredDistence <= 1280) {
            if ((1280 - driveredDistence < 14) && driveredDistence != 1279) {
                int passBeforeGetOff = carsPassengers.size();
                for (int i = 0; i < carsPassengers.size(); i++)
                    if (carsPassengers.get(i).wantedGetOffStation().equals("XP")) {
                        carStop = true;
                        carsPassengers.remove(i);
                    }
                if (carStop) {
                    driveredDistence = 1279;
                    positionInfo = "Iveco #" + carNumber + " 汽车在兴平停车两分钟！" + " 下车乘客数：" + (passBeforeGetOff - carsPassengers.size());
                    carStop = false;
                } else {
                    driveredDistence += VELOCITY;
                    position = ((float) driveredDistence - 1280.0f) / 10.0f;
                    positionInfo = "Iveco #" + carNumber + " 兴平以东:" + position + "Km" + " 目前乘客数：" + carsPassengers.size();
                }
            } else {
                if (driveredDistence == 1279) {
                    driveredDistence = 1281;
                    positionInfo = "Iveco #" + carNumber + " 汽车在兴平停车两分钟！" + " 目前乘客数：" + carsPassengers.size();
                } else {
                    driveredDistence += VELOCITY;
                    position = (1280.0f - (float) driveredDistence) / 10.0f;
                    positionInfo = "Iveco #" + carNumber + " 兴平以西:" + position + "Km" + " 目前乘客数：" + carsPassengers.size();
                }
            }
        } else if (driveredDistence >= 1281 && driveredDistence < 1400) {
            driveredDistence += VELOCITY;
            position = ((float) driveredDistence - 1281.0f) / 10.0f;
            positionInfo = "Iveco #" + carNumber + " 兴平以东:" + position + "Km" + " 目前乘客数：" + carsPassengers.size();
        } else if (driveredDistence >= 1400 && driveredDistence < 1520) {
            if ((1520 - driveredDistence < 14) && driveredDistence != 1517) {
                int passBeforeGetOff = carsPassengers.size();
                for (int i = 0; i < carsPassengers.size(); i++)
                    if (carsPassengers.get(i).wantedGetOffStation().equals("XY")) {
                        carStop = true;
                        carsPassengers.remove(i);
                    }
                if (carStop) {
                    driveredDistence = 1517;
                    positionInfo = "Iveco #" + carNumber + " 汽车在咸阳停车两分钟！" + " 下车乘客数：" + (passBeforeGetOff - carsPassengers.size());
                    carStop = false;
                } else {
                    driveredDistence += VELOCITY;
                    position = ((float) driveredDistence - 1520.0f) / 10.0f;
                    positionInfo = "Iveco #" + carNumber + " 咸阳以东:" + position + "Km" + " 目前乘客数：" + carsPassengers.size();
                }
            } else {
                if (driveredDistence == 1517) {
                    driveredDistence = 1520;
                    positionInfo = "Iveco #" + carNumber + " 汽车在咸阳停车两分钟！" + " 目前乘客数：" + carsPassengers.size();
                } else {
                    driveredDistence += VELOCITY;
                    position = (1520.0f - (float) driveredDistence) / 10.0f;
                    positionInfo = "Iveco #" + carNumber + " 咸阳以西:" + position + "Km" + " 目前乘客数：" + carsPassengers.size();
                }
            }
        } else if (driveredDistence >= 1520 && driveredDistence < 1630) {
            driveredDistence += VELOCITY;
            position = ((float) driveredDistence - 1520.0f) / 10.0f;
            positionInfo = "Iveco #" + carNumber + " 咸阳以东:" + position + "Km" + " 目前乘客数：" + carsPassengers.size();
        } else if (driveredDistence >= 1630 && driveredDistence <= 1740) {
            if ((1740 - driveredDistence < 14) && driveredDistence != 1735) {
                int passBeforeGetOff = carsPassengers.size();
                carsPassengers.clear();
                carStop = true;
                if (carStop) {
                    driveredDistence = 1735;
                    positionInfo = "Iveco #" + carNumber + " 汽车到达终点站西安！" + " 下车乘客数：" + (passBeforeGetOff - carsPassengers.size());
                    carStop = false;
                }
            } else {
                if (driveredDistence == 1735) {
                    driveredDistence = 1740;
                    this.xnEndStation = true;
                    positionInfo = "Iveco #" + carNumber + " 汽车到达终点站西安！" + " 目前乘客数：" + carsPassengers.size();
                } else {
                    driveredDistence += VELOCITY;
                    position = (1740.0f - (float) driveredDistence) / 10.0f;
                    positionInfo = "Iveco #" + carNumber + "  西安以西:" + position + "Km" + " 目前乘客数：" + carsPassengers.size();
                }
            }
        }
    }

    public Iveco(String direction) {
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
    }

    public void setRunable(boolean runable) {
        this.runable = runable;
    }

    public void setAcceptive(boolean flag) {
        this.acceptive = flag;
    }

    public boolean getAcceptive() {
        return this.acceptive;
    }

    public void addPassengers(Passengers passengers) {
        this.carsPassengers.offer(passengers);
    }

    public int getPassengersLimit() {
        return this.PASSENGER_LIMIT;
    }

    public String getDirection() {
        return this.direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getCarNumber() {
        return "#" + carNumber;
    }

    public void setDriveredDistence(int distence) {
        this.driveredDistence = distence;
    }

    public float getDriveredDistence() {
        return this.driveredDistence;
    }

    public String getPositionInfo() {
        return this.positionInfo;
    }

    public void setPositionInfo(String positionInfo) {
        this.positionInfo = positionInfo;
    }


    public int getPresentPassengers() {
        return this.carsPassengers.size();
    }

    public void clearPresentPassengers() {
        carsPassengers.clear();
    }

    public void setBjEndStation(boolean end) {
        this.bjEndStation = end;
    }

    public boolean getBjEndStation() {
        return this.bjEndStation;
    }

    public void setXnEndStation(boolean end) {
        this.xnEndStation = end;
    }

    public boolean getXnEndStation() {
        return this.xnEndStation;
    }

    public void run() {
        if ("XB".equals(direction)) {
            xTobControl();
        } else if ("BX".equals(direction)) {
            bToxControl();
        }
    }


}
