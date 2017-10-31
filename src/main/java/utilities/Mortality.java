package utilities;

import assumptions.mort.MortalityKey;
import assumptions.mort.MortalityTable;
import people.Gender;
import people.HealthState;
import people.Person;

public class Mortality {

    /**
     * health object for health look ups
     */
    Health h;

    /**
     * constructor initializes the health object
     */
    public Mortality() {
        this.h = new Health();
    }

    /**
     * get the current mortality rate for the person object
     *
     * @param p object that implements person
     * @return qx
     */
    public double getMortality(Person p) {
        MortalityKey mk = new MortalityKey(p.getAge(), p.getGender());
        double qx = 1.0;
        try {
            qx = MortalityTable.getMortalityRate(mk) * h.getHealthMortAdj(p);
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
        return qx;
    }

    /**
     * get the current mortality rate for a person of the age and gender specified
     *
     * @param age age
     * @param g   gender
     * @param hs  health status
     * @return qx
     */
    public double getMortality(int age, Gender g, HealthState hs) {
        MortalityKey mk = new MortalityKey(age, g);
        double qx = 1.0;
        try {
            qx = MortalityTable.getMortalityRate(mk) * h.getHealthMortAdj(hs);
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
        return qx;
    }

    /**
     * get the probability that a person will survive t years
     *
     * @param p  person
     * @param hs health status for mort adj
     * @param t  number of survival years
     * @return tpx
     */
    public double get_tPx(Person p, HealthState hs, int t) {
        double px = 1.0;
        int age = p.getAge();
        Gender g = p.getGender();
        for (int y = 0; y < t; y++) {
            px *= (1 - getMortality(age + y, g, hs));
        }
        return px;
    }

}
