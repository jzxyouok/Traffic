import javax.swing.*;

/**
 * Created by wk_51920 on 2014/11/4.
 */
public class TrafficSystem {
    private Gui gui = new Gui();

    public TrafficSystem() {
        gui.setTitle("Traffic");
        gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gui.setSize(1000, 1000);
        gui.setVisible(true);
    }

    public static void main(String[] args) {
        final TrafficSystem trafficSystem = new TrafficSystem();
        trafficSystem.gui.myDisplay();

    }
}
