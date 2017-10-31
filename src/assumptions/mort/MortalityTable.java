package assumptions.mort;

import people.Gender;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashMap;

public class MortalityTable {

    /**
     * hashmap to store the qx factors used in the current assumption
     */
    public static final HashMap<MortalityKey, Double> mortalityTbl = new HashMap<MortalityKey, Double>();

    static {
        try {
            BufferedReader br = new BufferedReader(new FileReader(new File(System.getProperty("user.dir") + "/loadFiles/vbt15_unismk_ult.mort")));
            String Line = br.readLine();

            while ((Line = br.readLine()) != null) {
                String[] data = Line.split(",");
                try {
                    int age = Integer.parseInt(data[0]);
                    MortalityKey mk_m = new MortalityKey(age, Gender.Male);
                    MortalityKey mk_f = new MortalityKey(age, Gender.Female);
                    double m_qx = Double.parseDouble(data[1]);
                    double f_qx = Double.parseDouble(data[2]);
                    mortalityTbl.put(mk_m, m_qx);
                    mortalityTbl.put(mk_f, f_qx);
                } catch (IllegalArgumentException e) {
                    String errstr = "Mortality Table unable to load factors " + Line;
                    System.out.println(errstr);
                }
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    /**
     * Method to get the qx factor; if the qx look up fails throw an error and return 1
     *
     * @param mk mortality key for the qx rate
     * @return mortality rate for the given age and gender of the mortality key
     */
    public static double getMortalityRate(MortalityKey mk) {
        double qx;
        try {
            qx = mortalityTbl.get(mk);
            return qx;
        } catch (NullPointerException e) {
            e.printStackTrace();
            System.exit(1);
        }
        return 1;
    }
}
