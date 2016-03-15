import java.util.*;

/**
 * Created by wk_51920 on 2014/11/13.
 */
public class StationsControl {
    private int pN = 2;                          //每分钟产生的最大乘客数
    private int xnVolvos = 5;                    //西安沃尔沃初始车辆数目
    private int xnIvecos = 12;                   //西安依维柯初始车辆数目
    private int bjVolvos = 4;                    //宝鸡沃尔沃初始车辆数目
    private int bjIvecos = 15;                   //宝鸡依维柯初始车辆数目
    private Random random = new Random();        //用于产生随机数
    private Queue<Passengers> xnWaitQueue = new LinkedList<Passengers>();  //西安等车乘客的等待队列（未上车）
    private Queue<Passengers> bjWaitQueue = new LinkedList<Passengers>();  //宝鸡等车乘客的等待队列（未上车）

    private Queue<Volvo> xnStationVReady = new LinkedList<Volvo>(); //西安站沃尔沃准备出发的列车队列
    private Queue<Iveco> xnStationIReady = new LinkedList<Iveco>(); //西安站依维柯准备出发的列车队列
    private Queue<Volvo> bjStationVReady = new LinkedList<Volvo>(); //宝鸡站沃尔沃准备出发的列车队列
    private Queue<Iveco> bjStationIReady = new LinkedList<Iveco>(); //宝鸡站依维柯准备出发的列车队列

    private Queue<Volvo> runningVolvo = new LinkedList<Volvo>();     //行驶中的沃尔沃队列
    private Queue<Iveco> runningIveco = new LinkedList<Iveco>();     //行驶中的依维柯队列

    private List<Cars> displayCars = new LinkedList<Cars>();    //所有初始化的车辆都在此队列，用于全程显示各车辆的当前时刻信息

    public int getXnVolvos() {
        return this.xnVolvos;
    }       //向GUI提供各车站初始化的车辆数量

    public int getXnIvecos() {
        return this.xnIvecos;
    }

    public int getBjVolvos() {
        return this.bjVolvos;
    }

    public int getBjIvecos() {
        return this.bjIvecos;
    }


    public void setXnVolvos(int num) {
        this.xnVolvos = num;
    } //向GUI提供设置各车站初始化车辆数量的接口

    public void setXnIvecos(int num) {
        this.xnIvecos = num;
    }

    public void setBjVolvos(int num) {
        this.bjVolvos = num;
    }

    public void setBjIvecos(int num) {
        this.bjIvecos = num;
    }


    public int getXnVReadyNum() {
        return this.xnStationVReady.size();
    }  //向GUI提供当前时刻停留在各车站的车辆的数目

    public int getXnIReadyNum() {
        return this.xnStationIReady.size();
    }

    public int getBjVReadyNum() {
        return this.bjStationVReady.size();
    }

    public int getBjIReadyNum() {
        return this.bjStationIReady.size();
    }

    private void vCarStart()                         //控制沃尔沃按时发车
    {
        Volvo temp1 = null;
        if (!xnStationVReady.isEmpty()) {
            temp1 = xnStationVReady.poll();
            if (temp1.getRunable()) {
                temp1.setAcceptive(false);
                temp1.setXnEndStation(false);
                temp1.setBjEndStation(false);
                runningVolvo.add(temp1);
            }
        }

        Volvo temp2 = null;
        if (!bjStationVReady.isEmpty()) {
            temp2 = bjStationVReady.poll();
            if (temp2.getRunable()) {
                temp2.setAcceptive(false);
                temp2.setBjEndStation(false);
                temp2.setXnEndStation(false);
                runningVolvo.add(temp2);
            }
        }
    }

    private void iCarStart()    //控制依维柯按时发车
    {
        Iveco temp1 = null;
        if (!xnStationIReady.isEmpty()) {
            temp1 = xnStationIReady.poll();
            if (temp1.getRunable()) {
                temp1.setAcceptive(false);
                temp1.setXnEndStation(false);
                temp1.setBjEndStation(false);
                runningIveco.add(temp1);
            }
        }

        Iveco temp2 = null;
        if (!bjStationIReady.isEmpty()) {
            temp2 = bjStationIReady.poll();
            if (temp2.getRunable()) {
                temp2.setAcceptive(false);
                temp2.setBjEndStation(false);
                temp2.setXnEndStation(false);
                runningIveco.add(temp2);
            }
        }
    }

    private void turnDirection() {              //汽车到站后转向
        Volvo tempVxn = null;
        Volvo tempVbj = null;
        Iveco tempIxn = null;
        Iveco tempIbj = null;

        if (!runningVolvo.isEmpty())
            for (Volvo volvo : runningVolvo)
                if (volvo.getBjEndStation()) {         //检查是否到终点站
                    volvo.clearPresentPassengers();    //清空车内的乘客
                    volvo.setAcceptive(true);          //将车辆设为可以接受乘客上车
                    volvo.setDirection("BX");          //将车辆的行驶方向调转
                    volvo.setDriveredDistence(0);      //重置车辆的行驶距离
                    tempVbj = volvo;
                    bjStationVReady.offer(tempVbj);    //将车辆插入等待发车的队列
                    runningVolvo.remove(tempVbj);      //将车辆从正在行驶车辆队列中移除
                    break;
                }

        if (!runningVolvo.isEmpty()) {
            for (Volvo volvo : runningVolvo)          //同上类似
                if (volvo.getXnEndStation()) {
                    volvo.clearPresentPassengers();
                    volvo.setAcceptive(true);
                    volvo.setDirection("XB");
                    volvo.setDriveredDistence(0);
                    tempVxn = volvo;
                    xnStationVReady.offer(tempVxn);
                    runningVolvo.remove(tempVxn);
                    break;
                }
        }

        if (!runningIveco.isEmpty()) { //同上类似
            for (Iveco iveco : runningIveco)
                if (iveco.getXnEndStation()) {
                    iveco.clearPresentPassengers();
                    iveco.setAcceptive(true);
                    iveco.setDirection("XB");
                    iveco.setDriveredDistence(0);
                    tempIxn = iveco;
                    xnStationIReady.offer(tempIxn);
                    runningIveco.remove(tempIxn);
                    break;
                }
        }
        if (!runningIveco.isEmpty())
            for (Iveco iveco : runningIveco)
                if (iveco.getBjEndStation()) {
                    iveco.clearPresentPassengers();
                    iveco.setAcceptive(true);
                    iveco.setDirection("BX");
                    iveco.setDriveredDistence(0);
                    tempIbj = iveco;
                    bjStationIReady.offer(tempIbj);
                    runningIveco.remove(tempIbj);
                    break;
                }

    }

    public void carStartControl(Time time) {
        if (time.getHours() < 18)           //只在18点之前产生乘客
            passengersGenerator();          //用于产生随机的乘客
        selectTrueGetOnCar();               //乘客选择上哪辆车
        turnDirection();                    //若车到站，则转向

        if (time.getHours() == 8 && time.getMinutes() == 30)         //沃尔沃按时发车
            vCarStart();
        else if (time.getHours() > 8 && time.getHours() < 18)
            if (time.getMinutes() == 30)
                vCarStart();

        if (time.getHours() >= 8 && time.getHours() < 18) {          //依维柯按时发车
            if (time.getMinutes() % 20 == 0)
                iCarStart();
        } else if (time.getHours() == 18 && time.getMinutes() == 0)
            iCarStart();

        if (time.getHours() >= 17 && time.getMinutes() > 30) {     //到下午5:30后，沃尔沃停止发车
            for (Volvo volvo : xnStationVReady) {
                volvo.setRunable(false);
                volvo.setAcceptive(false);
                volvo.clearPresentPassengers();
            }

            for (Volvo volvo : bjStationVReady) {
                volvo.setRunable(false);
                volvo.setAcceptive(false);
                volvo.clearPresentPassengers();
            }
        }

        if (time.getHours() >= 18) {
            for (Iveco iveco : xnStationIReady) {                //到下午6:00之后，依维柯停止发车
                iveco.setRunable(false);
                iveco.setAcceptive(false);
                iveco.clearPresentPassengers();
            }
            for (Iveco iveco : bjStationIReady) {
                iveco.setRunable(false);
                iveco.setAcceptive(false);
                iveco.clearPresentPassengers();
            }
        }

        for (Cars car : displayCars) {                  //当车辆在终点站时，车辆应返回的信息，若在行驶中，则每秒调用一次car.run()更新状态
            if (car.getBjEndStation()) {
                if (car instanceof Volvo)
                    car.setPositionInfo("volov " + ((Volvo) car).getCarNumber() + " 正在宝鸡站内！" + " 目前乘客人数：" + ((Volvo) car).getPresentPassengers());
                if (car instanceof Iveco)
                    car.setPositionInfo("Iveco " + ((Iveco) car).getCarNumber() + " 正在宝鸡站内！" + " 目前乘客人数：" + ((Iveco) car).getPresentPassengers());
            } else if (car.getXnEndStation()) {
                if (car instanceof Volvo)
                    car.setPositionInfo("volov " + ((Volvo) car).getCarNumber() + " 正在西安站内！" + " 目前乘客人数：" + ((Volvo) car).getPresentPassengers());
                if (car instanceof Iveco)
                    car.setPositionInfo("Iveco " + ((Iveco) car).getCarNumber() + " 正在西安站内！" + " 目前乘客人数：" + ((Iveco) car).getPresentPassengers());
            } else car.run();
        }
    }

    private void selectGetOnICar() {                     //乘客选择登上依维柯汽车
        for (Iveco temp : xnStationIReady) {
            if (!(xnWaitQueue.isEmpty())) {             //判断站点候车乘客队列是否为空
                if (temp.getAcceptive()) {              //判断此车是否允许乘客上车
                    temp.addPassengers(xnWaitQueue.poll());  //从等车队列移除乘客，加入到此车的乘客中
                    if (temp.getPresentPassengers() >= temp.getPassengersLimit())     //若乘客已满，则不能再上此车
                        temp.setAcceptive(false);
                    break;
                }
            } else break;
        }
        for (Iveco temp : bjStationIReady) {         //同上
            if (!(bjWaitQueue.isEmpty())) {
                if (temp.getAcceptive()) {
                    temp.addPassengers(bjWaitQueue.poll());
                    if (temp.getPresentPassengers() >= temp.getPassengersLimit())
                        temp.setAcceptive(false);
                    break;
                }
            } else break;
        }
    }

    private void selectGetOnVCar() {               //乘客选择登上沃尔沃汽车， 后续解释同上类似
        for (Volvo temp : xnStationVReady) {
            if (!(xnWaitQueue.isEmpty())) {
                if (temp.getAcceptive()) {
                    temp.addPassengers(xnWaitQueue.poll());
                    if (temp.getPresentPassengers() >= temp.getPassengersLimit())
                        temp.setAcceptive(false);
                    break;
                }
            } else break;
        }
        for (Volvo temp : bjStationVReady) {
            if (!(bjWaitQueue.isEmpty())) {
                if (temp.getAcceptive()) {
                    temp.addPassengers(bjWaitQueue.poll());
                    if (temp.getPresentPassengers() >= temp.getPassengersLimit())
                        temp.setAcceptive(false);
                    break;
                }
            } else break;
        }
    }

    private void selectTrueGetOnCar() {
        while (!xnWaitQueue.isEmpty() || !bjWaitQueue.isEmpty()) {
            selectGetOnICar();             //乘客交替上两种不同类型的车
            selectGetOnVCar();
        }
    }

    public void init() {
        xnStationIReady.clear();            //清除各状态队列，以便重新初始化
        xnStationVReady.clear();
        bjStationIReady.clear();
        bjStationVReady.clear();
        runningVolvo.clear();
        runningIveco.clear();
        displayCars.clear();
        xnWaitQueue.clear();
        bjWaitQueue.clear();

        Volvo volvo = new Volvo("XB");
        volvo.clearCountCarNumber();         //当重置后，车牌号仍从0开始计算


        for (int i = 0; i < xnVolvos; i++) {          //初始化各车辆，并加入相关队列
            Volvo tempV = new Volvo("XB");
            tempV.setXnEndStation(true);
            xnStationVReady.offer(tempV);
            displayCars.add(tempV);
        }

        Iveco iveco = new Iveco("XB");
        iveco.clearCountCarNumber();        //当重置后，车牌号仍从0开始计算


        for (int i = 0; i < xnIvecos; i++) {
            Iveco tempI = new Iveco("XB");
            tempI.setXnEndStation(true);
            xnStationIReady.offer(tempI);
            displayCars.add(tempI);
        }
        for (int i = 0; i < bjVolvos; i++) {
            Volvo tempV = new Volvo("BX");
            tempV.setBjEndStation(true);
            bjStationVReady.offer(tempV);
            displayCars.add(tempV);
        }
        for (int i = 0; i < bjIvecos; i++) {
            Iveco tempI = new Iveco("BX");
            tempI.setBjEndStation(true);
            bjStationIReady.offer(tempI);
            displayCars.add(tempI);
        }
    }

    public List<Cars> getDisplayCars() {
        return this.displayCars;
    }       //向GUI返回displayCars队列

    public int getXnWaitPassengers() {
        return this.xnWaitQueue.size();
    }  //向GUI提供当前西安站还未上车的乘客数

    public int getBjWaitPassengers() {
        return this.bjWaitQueue.size();
    }  //向GUI提供当前宝鸡站还未上车的乘客数

    public int getPN() {        //向GUI提供每秒产生的最大乘客数
        return this.pN;
    }

    public void setpN(int pn) {   //向GUI提供修改每秒产生最大乘客数的接口
        this.pN = pn;
    }


    private void passengersGenerator() {     //用于产生乘客，并加入等车队列
        for (int i = 0; i < random.nextInt(pN + 1); i++)
            xnWaitQueue.offer(new Passengers("XB"));
        for (int i = 0; i < random.nextInt(pN + 1); i++)
            bjWaitQueue.offer(new Passengers("BX"));
    }
}
