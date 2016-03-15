import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.TimeUnit;

/**
 * Created by wk_51920 on 2014/11/10.
 */
public class Gui extends JFrame {
    private Passengers passengersXB = new Passengers("XB");         //用于在GUI上显示出西安到宝鸡各站点的下车概率
    private Passengers passengersBX = new Passengers("XB");         //用于在GUI上显示出宝鸡到西安各站点的下车概率
    private Time time = new Time();                                 //时间类的对象，用于显示时间
    private ControlTime ct = new ControlTime();                     //用于控制时间的变更
    private JTextField timeTxt = new JTextField(3);                 //GUI上显示时间的文本框
    private Font font = new Font("Serif", 0, 14);                   //设置字体大小
    private JLabel xnStationWaiters = new JLabel("西安站未上车等待人数：");
    private JLabel timeDisplay = new JLabel("当前时间：");
    private JLabel bjStationWaiters = new JLabel("宝鸡站未上车等待人数：");
    private JLabel xnStationVolvos = new JLabel("西安站当前沃尔沃车数量：");
    private JTextField xnVolvosNum = new JTextField(3);              //显示西安沃尔沃数量的文本框
    private JLabel xnStationIvecos = new JLabel("西安站当前依维柯车数量：");
    private JTextField xnIvecosNum = new JTextField(3);              //显示西安依维柯数量的文本框
    private JLabel bjStationVolvos = new JLabel("宝鸡站当前沃尔沃车数量：");
    private JTextField bjIvecosNum = new JTextField(3);              //显示宝鸡沃尔沃数量的文本框
    private JLabel bjStationIvecos = new JLabel("宝鸡站当前依维柯车数量：");
    private JTextField bjVolvosNum = new JTextField(3);              //显示宝鸡沃尔沃数量的文本框
    private JTextField bjStationWaitPassengers = new JTextField(3);  //显示当前在宝鸡站未上车的等待乘客
    private JTextField xnStationWaitPassengers = new JTextField(3);  //显示当前在西安站未上车的等待乘客
    private JButton restart = new JButton("重新设置");               //根据新参数重置系统
    private StationsControl stationsControl = new StationsControl(); //站台控制类的对象，用于进行车辆整体调度
    //用于显示所有车辆当前时刻的状态
    private JTextArea carsStatus = new JTextArea(stationsControl.getBjIvecos() + stationsControl.getBjVolvos() + stationsControl.getXnIvecos() + stationsControl.getXnVolvos(), 10);

    private JLabel xnInitVolvo = new JLabel("西安站初始沃尔沃数量：");
    private JLabel xnInitIveco = new JLabel("西安站初始依维柯数量：");
    private JLabel bjInitVolvo = new JLabel("宝鸡站初始沃尔沃数量：");
    private JLabel bjInitIveco = new JLabel("宝鸡站初始依维柯数量：");

    private JTextField xnInitVolvoNum = new JTextField(3);           //各站各车型初始车辆数目
    private JTextField xnInitIvecoNum = new JTextField(3);
    private JTextField bjInitVolvoNum = new JTextField(3);
    private JTextField bjInitIvecoNum = new JTextField(3);

    private JLabel P_XBxy = new JLabel("XB咸阳");
    private JLabel P_XBxp = new JLabel("XB兴平");
    private JLabel P_XBwg = new JLabel("XB武功");
    private JLabel P_XBcp = new JLabel("XB蔡家坡");
    private JLabel P_XBgz = new JLabel("XB虢镇");
    private JLabel P_XBbj = new JLabel("XB宝鸡");

    private JLabel P_BXxy = new JLabel("BX咸阳");
    private JLabel P_BXxp = new JLabel("BX兴平");
    private JLabel P_BXwg = new JLabel("BX武功");
    private JLabel P_BXcp = new JLabel("BX蔡家坡");
    private JLabel P_BXgz = new JLabel("BX虢镇");
    private JLabel P_BXxn = new JLabel("BX西安");

    private JTextField P_XBxyNum = new JTextField(3);            //用于显示和设置两个方向各站点的下车概率
    private JTextField P_XBxpNum = new JTextField(3);
    private JTextField P_XBwgNum = new JTextField(3);
    private JTextField P_XBcpNum = new JTextField(3);
    private JTextField P_XBgzNum = new JTextField(3);
    private JTextField P_XBbjNum = new JTextField(3);

    private JTextField P_BXxyNum = new JTextField(3);
    private JTextField P_BXxpNum = new JTextField(3);
    private JTextField P_BXwgNum = new JTextField(3);
    private JTextField P_BXcpNum = new JTextField(3);
    private JTextField P_BXgzNum = new JTextField(3);
    private JTextField P_BXxnNum = new JTextField(3);

    private JPanel timeAndWait = new JPanel();                   //用于界面布局的一些JPanel
    private JPanel probability = new JPanel();
    private JPanel proXB = new JPanel();
    private JPanel proBX = new JPanel();
    private JPanel firstFloor = new JPanel();
    private JPanel secondFloor = new JPanel();
    private JPanel thirdFloor = new JPanel();
    private JPanel forthFloor = new JPanel();
    private JPanel fifthFloor = new JPanel();

    private JLabel personGen = new JLabel("每秒各车站产生人数：");
    private JTextField personGenNum = new JTextField(3);        //用于显示各车站每秒随机产生最大人数的文本框

    public Gui() {
        stationsControl.init();                         //初始化车站控制类

        timeTxt.setEditable(false);                     //将只用于显示的文本框设为不可编辑
        bjVolvosNum.setEditable(false);
        bjIvecosNum.setEditable(false);
        xnIvecosNum.setEditable(false);
        xnVolvosNum.setEditable(false);
        bjStationWaitPassengers.setEditable(false);
        xnStationWaitPassengers.setEditable(false);
        carsStatus.setEditable(false);

        xnInitVolvoNum.setText(stationsControl.getXnVolvos() + "");   //设置各车站初始车辆的文本框的内容
        xnInitIvecoNum.setText(stationsControl.getXnIvecos() + "");
        bjInitVolvoNum.setText(stationsControl.getBjVolvos() + "");
        bjInitIvecoNum.setText(stationsControl.getBjIvecos() + "");

        P_XBxyNum.setText(passengersXB.getXbGetOffProbability("XY") + "");   //设置两个方向各车站下车概率文本框的内容
        P_XBxpNum.setText(passengersXB.getXbGetOffProbability("XP") + "");
        P_XBwgNum.setText(passengersXB.getXbGetOffProbability("WG") + "");
        P_XBcpNum.setText(passengersXB.getXbGetOffProbability("CP") + "");
        P_XBgzNum.setText(passengersXB.getXbGetOffProbability("GZ") + "");
        P_XBbjNum.setText(passengersXB.getXbGetOffProbability("BJ") + "");

        P_BXgzNum.setText(passengersBX.getBxGetOffProbability("GZ") + "");
        P_BXcpNum.setText(passengersBX.getBxGetOffProbability("CP") + "");
        P_BXwgNum.setText(passengersBX.getBxGetOffProbability("WG") + "");
        P_BXxpNum.setText(passengersBX.getBxGetOffProbability("XP") + "");
        P_BXxyNum.setText(passengersBX.getBxGetOffProbability("XY") + "");
        P_BXxnNum.setText(passengersBX.getBxGetOffProbability("XN") + "");

        personGenNum.setText(stationsControl.getPN()+"");                 //设置各车站每秒随机产生人数的最大值

        firstFloor.setLayout(new FlowLayout());                           //添加各种GUI组件
        firstFloor.add(timeDisplay);
        firstFloor.add(timeTxt);
        firstFloor.add(xnStationWaiters);
        firstFloor.add(xnStationWaitPassengers);
        firstFloor.add(bjStationWaiters);
        firstFloor.add(bjStationWaitPassengers);
        firstFloor.add(personGen);
        firstFloor.add(personGenNum);

        secondFloor.setLayout(new FlowLayout());
        secondFloor.add(xnStationVolvos);
        secondFloor.add(xnVolvosNum);
        secondFloor.add(xnStationIvecos);
        secondFloor.add(xnIvecosNum);
        secondFloor.add(bjStationVolvos);
        secondFloor.add(bjVolvosNum);
        secondFloor.add(bjStationIvecos);
        secondFloor.add(bjIvecosNum);

        thirdFloor.setLayout(new FlowLayout());
        thirdFloor.add(xnInitVolvo);
        thirdFloor.add(xnInitVolvoNum);
        thirdFloor.add(xnInitIveco);
        thirdFloor.add(xnInitIvecoNum);
        thirdFloor.add(bjInitVolvo);
        thirdFloor.add(bjInitVolvoNum);
        thirdFloor.add(bjInitIveco);
        thirdFloor.add(bjInitIvecoNum);

        proXB.setLayout(new FlowLayout());
        proXB.add(P_XBxy);
        proXB.add(P_XBxyNum);
        proXB.add(P_XBxp);
        proXB.add(P_XBxpNum);
        proXB.add(P_XBwg);
        proXB.add(P_XBwgNum);
        proXB.add(P_XBcp);
        proXB.add(P_XBcpNum);
        proXB.add(P_XBgz);
        proXB.add(P_XBgzNum);
        proXB.add(P_XBbj);
        proXB.add(P_XBbjNum);

        proBX.add(P_BXgz);
        proBX.add(P_BXgzNum);
        proBX.add(P_BXcp);
        proBX.add(P_BXcpNum);
        proBX.add(P_BXwg);
        proBX.add(P_BXwgNum);
        proBX.add(P_BXxp);
        proBX.add(P_BXxpNum);
        proBX.add(P_BXxy);
        proBX.add(P_BXxyNum);
        proBX.add(P_BXxn);
        proBX.add(P_BXxnNum);

        probability.setLayout(new GridLayout(2, 1));
        probability.add(proXB);
        probability.add(proBX);

        forthFloor.setLayout(new FlowLayout());
        forthFloor.add(probability);

        timeAndWait.setLayout(new GridLayout(4, 1));
        timeAndWait.add(firstFloor);
        timeAndWait.add(secondFloor);
        timeAndWait.add(thirdFloor);
        timeAndWait.add(forthFloor);
        setLayout(new BorderLayout());
        add(BorderLayout.NORTH, timeAndWait);

        restart.addActionListener(new ActionListener() {  //“重置”按钮的监听器
            @Override
            public void actionPerformed(ActionEvent e) {
                time.resetTime();                         //重置时间
                stationsControl.setXnVolvos(Integer.parseInt(xnInitVolvoNum.getText()));  //根据修改的文本框重置新的各车站初始车辆数目
                stationsControl.setXnIvecos(Integer.parseInt(xnInitIvecoNum.getText()));
                stationsControl.setBjVolvos(Integer.parseInt(bjInitVolvoNum.getText()));
                stationsControl.setBjIvecos(Integer.parseInt(bjInitIvecoNum.getText()));

                //根据文本框输入的值，修改各车站下车概率 ，当和为1的时候才会真正修改
                if (Math.abs(Float.parseFloat(P_XBxyNum.getText()) + Float.parseFloat(P_XBxpNum.getText()) + Float.parseFloat(P_XBwgNum.getText()) + Float.parseFloat(P_XBcpNum.getText()) + Float.parseFloat(P_XBgzNum.getText()) + Float.parseFloat(P_XBbjNum.getText()) - 1.0f) <= 0.001f) {
                    passengersXB.setXbGetOffProbability("XY", Float.parseFloat(P_XBxyNum.getText()));
                    passengersXB.setXbGetOffProbability("XP", Float.parseFloat(P_XBxpNum.getText()));
                    passengersXB.setXbGetOffProbability("WG", Float.parseFloat(P_XBwgNum.getText()));
                    passengersXB.setXbGetOffProbability("CP", Float.parseFloat(P_XBcpNum.getText()));
                    passengersXB.setXbGetOffProbability("GZ", Float.parseFloat(P_XBgzNum.getText()));
                    passengersXB.setXbGetOffProbability("BJ", Float.parseFloat(P_XBbjNum.getText()));
                }
                if (Math.abs(Float.parseFloat(P_BXxyNum.getText()) + Float.parseFloat(P_BXxpNum.getText()) + Float.parseFloat(P_BXwgNum.getText()) + Float.parseFloat(P_BXcpNum.getText()) + Float.parseFloat(P_BXgzNum.getText()) + Float.parseFloat(P_BXxnNum.getText()) - 1.0f) <= 0.001f) {
                    passengersBX.setBxGetOffProbability("GZ", Float.parseFloat(P_BXgzNum.getText()));
                    passengersBX.setBxGetOffProbability("CP", Float.parseFloat(P_BXcpNum.getText()));
                    passengersBX.setBxGetOffProbability("WG", Float.parseFloat(P_BXwgNum.getText()));
                    passengersBX.setBxGetOffProbability("XP", Float.parseFloat(P_BXxpNum.getText()));
                    passengersBX.setBxGetOffProbability("XY", Float.parseFloat(P_BXxyNum.getText()));
                    passengersBX.setBxGetOffProbability("XN", Float.parseFloat(P_BXxnNum.getText()));
                }
                stationsControl.setpN(Integer.parseInt(personGenNum.getText()));       //根据当前文本框内容，设置每秒产生乘客数的最大值
                stationsControl.init();                //对车站调度类进行初始化
            }
        });
        add(BorderLayout.CENTER, new JScrollPane(carsStatus));
        add(BorderLayout.SOUTH, restart);
    }

    public void myDisplay() {
        while (true) {
            SwingUtilities.invokeLater(new Runnable() {    //使用单线程每秒更新各状态来实现系统仿真
                @Override
                public void run() {
                    StringBuilder stringBuilder = new StringBuilder();  //用于连接各车辆当前时刻的信息
                    bjStationWaitPassengers.setText("" + stationsControl.getBjWaitPassengers());     //设置当前在车站未上车的等带人数文本框的内容
                    xnStationWaitPassengers.setText("" + stationsControl.getXnWaitPassengers());
                    ct.control(time);                 //更新一次时间
                    timeTxt.setText(time.showTime()); //在时间文本框中显示更新后的时间

                    xnVolvosNum.setText(stationsControl.getXnVReadyNum() + "");     //更新当前时刻在车站等待发车的各车辆数目文本框的内容
                    xnIvecosNum.setText(stationsControl.getXnIReadyNum() + "");
                    bjVolvosNum.setText(stationsControl.getBjVReadyNum() + "");
                    bjIvecosNum.setText(stationsControl.getBjIReadyNum() + "");

                    stationsControl.carStartControl(time);                          //将更新后的时间传入车辆调度函数中，以便根据当前时刻控制发车
                    for (Cars cars : stationsControl.getDisplayCars())              //拼接各车辆的当前运行信息字符串
                        stringBuilder.append(cars.getPositionInfo() + " " + "\n");
                    carsStatus.setFont(font);
                    carsStatus.setText(stringBuilder.toString());
                    carsStatus.setCaretPosition(1);
                }
            });
            try {
                TimeUnit.MILLISECONDS.sleep(1000);
            } catch (InterruptedException e) {
                System.err.println(e);
            }
        }
    }
}
