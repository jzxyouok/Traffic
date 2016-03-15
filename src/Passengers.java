import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Created by wk_51920 on 2014/11/13.
 */
public class Passengers {
    private static Map<String, Integer> xbGetOffProbability = new HashMap<String, Integer>();    //存储西安到宝鸡方向各站站名和下车概率
    private static Map<String, Integer> bxGetOffProbability = new HashMap<String, Integer>();    //存储宝鸡道西安方向各站站名和下车概率
    private String direction;     //乘客想要去的方向

    public Passengers(String direction) {
        this.direction = direction;
        //初始化两个方向的各站下车概率，默认值
        xbGetOffProbability.put("XY", 50);
        xbGetOffProbability.put("XP", 100);
        xbGetOffProbability.put("WG", 150);
        xbGetOffProbability.put("CP", 200);
        xbGetOffProbability.put("GZ", 250);
        xbGetOffProbability.put("BJ", 250);

        bxGetOffProbability.put("GZ", 50);
        bxGetOffProbability.put("CP", 100);
        bxGetOffProbability.put("WG", 150);
        bxGetOffProbability.put("XP", 200);
        bxGetOffProbability.put("XY", 250);
        bxGetOffProbability.put("XN", 250);
    }


    public void setXbGetOffProbability(String station, float probability) { //设置某站的下车概率
        int prob = (int) (probability * 1000);
        xbGetOffProbability.replace(station, prob);
    }

    public void setBxGetOffProbability(String station, float probability) {
        int prob = (int) (probability * 1000);
        bxGetOffProbability.replace(station, prob);
    }

    //得到某站的下车概率
    public float getXbGetOffProbability(String station)
    {
        return  ((float)xbGetOffProbability.get(station))/1000;
    }
    public float getBxGetOffProbability(String station)
    {
        return  ((float)bxGetOffProbability.get(station))/1000;
    }

    public String wantedGetOffStation() {       //随机产生该乘客想要下车的站名
        Random random = new Random();
        String wantedStation = "";
        int xbXY = xbGetOffProbability.get("XY");
        int xbXP = xbGetOffProbability.get("XY") + xbGetOffProbability.get("XP");
        int xbWG = xbGetOffProbability.get("XY") + xbGetOffProbability.get("XP") + xbGetOffProbability.get("WG");
        int xbCP = xbGetOffProbability.get("XY") + xbGetOffProbability.get("XP") + xbGetOffProbability.get("WG") + xbGetOffProbability.get("CP");
        int xbGZ = xbGetOffProbability.get("XY") + xbGetOffProbability.get("XP") + xbGetOffProbability.get("WG") + xbGetOffProbability.get("CP") + xbGetOffProbability.get("GZ");
        int xbBJ = xbGetOffProbability.get("XY") + xbGetOffProbability.get("XP") + xbGetOffProbability.get("WG") + xbGetOffProbability.get("CP") + xbGetOffProbability.get("GZ") + xbGetOffProbability.get("BJ");

        int bxGZ = bxGetOffProbability.get("GZ");
        int bxCP = bxGetOffProbability.get("GZ") + bxGetOffProbability.get("CP");
        int bxWG = bxGetOffProbability.get("GZ") + bxGetOffProbability.get("CP") + bxGetOffProbability.get("WG");
        int bxXP = bxGetOffProbability.get("GZ") + bxGetOffProbability.get("CP") + bxGetOffProbability.get("WG") + bxGetOffProbability.get("XP");
        int bxXY = bxGetOffProbability.get("GZ") + bxGetOffProbability.get("CP") + bxGetOffProbability.get("WG") + bxGetOffProbability.get("XP") + bxGetOffProbability.get("XY");
        int bxXN = bxGetOffProbability.get("GZ") + bxGetOffProbability.get("CP") + bxGetOffProbability.get("WG") + bxGetOffProbability.get("XP") + bxGetOffProbability.get("XY") + bxGetOffProbability.get("XN");

        if (direction.equals("XB")) {
            int num = random.nextInt(1000);
            if (num >= 0 && num < xbXY)
                wantedStation = "XY";
            else if (num >= xbXY && num < xbXP)
                wantedStation = "XP";
            else if (num >= xbXP && num < xbWG)
                wantedStation = "WG";
            else if (num >= xbWG && num < xbCP)
                wantedStation = "CP";
            else if (num >= xbCP && num < xbGZ)
                wantedStation = "GZ";
            else if (num >= xbGZ && num < xbBJ)
                wantedStation = "BJ";
        } else if (direction.equals("BX")) {
            int num = random.nextInt(1000);
            if (num >= 0 && num < bxGZ)
                wantedStation = "XY";
            else if (num >= bxGZ && num < bxCP)
                wantedStation = "XP";
            else if (num >= bxCP && num < bxWG)
                wantedStation = "WG";
            else if (num >= bxWG && num < bxXP)
                wantedStation = "CP";
            else if (num >= bxXP && num < bxXY)
                wantedStation = "GZ";
            else if (num >= bxXY && num < bxXN)
                wantedStation = "BJ";
        }
        return wantedStation;
    }
}
