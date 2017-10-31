package utilities;

import people.HealthState;
import people.Person;

import java.util.Random;

public class Health {

    Random rand;

    public Health() {
        this.rand = new Random();
        this.rand.setSeed(424242);
    }

    public Health(long sd) {
        this.rand = new Random();
        this.rand.setSeed(sd);
    }

    /**
     * probability that the policyholder will go from healthy to fair health
     *
     * @param age
     * @return probability
     */
    private double getProbH_F(int age) {
        return 0.5 * age / 115.0;
    }

    /**
     * probability that the policyholder will stay healthy
     *
     * @param age
     * @return probability
     */
    private double getProbH_H(int age) {
        return 0.5 * (1 - age / 115.0);
    }

    /**
     * probability that the policyholder will stay in fair health
     *
     * @param age
     * @return probability
     */
    private double getProbF_F(int age) {
        return 0.5 * (1 - age / 115.0);
    }

    /**
     * probability that the policyholder will go from fair to poor health
     *
     * @param age
     * @return probability
     */
    private double getProbF_P(int age) {
        return 0.5 * age / 115.0;
    }

    /**
     * probability that the policyholder will stay in poor health
     *
     * @param age
     * @return probability
     */
    private double getProbP_P(int age) {
        return 0.5 * age / 115.0;
    }

    /**
     * probability that the policyholder will go from poor health to fair health, note there is a zero probability of
     * going from poor health to healthy
     *
     * @param age
     * @return probability
     */
    private double getProbP_F(int age) {
        return (1 - getProbP_P(age));
    }


    /**
     * Method to simulate health state transitions called by classes that implement Person to update their health state
     *
     * @param p Person object
     * @return new healthstate
     * @throws Exception bad health state
     */
    public HealthState updateHealthStatus(Person p) throws Exception {
        double rd = rand.nextDouble();

        if (p.getHealth() == HealthState.Poor) {
            if (rd < getProbP_P(p.getAge())) {
                return HealthState.Poor;
            } else {
                return HealthState.Fair;
            }
        } else if (p.getHealth() == HealthState.Fair) {
            if (rd < getProbF_F(p.getAge())) {
                return HealthState.Fair;
            } else if (rd < (getProbF_F(p.getAge()) + getProbF_P(p.getAge()))) {
                return HealthState.Poor;
            } else {
                return HealthState.Good;
            }
        } else if (p.getHealth() == HealthState.Good) {
            if (rd < getProbH_H(p.getAge())) {
                return HealthState.Good;
            } else if (rd < (getProbH_H(p.getAge()) + getProbH_F(p.getAge()))) {
                return HealthState.Fair;
            } else {
                return HealthState.Poor;
            }
        } else {
            throw new Exception("non-conforming health state");
        }
    }


    /**
     * Returns the mortality adjustment factor for the person's health state; this is called by the mortality class
     *
     * @param p person object
     * @return adjustment factor to be applied to the base qx
     * @throws Exception base health state
     */
    public double getHealthMortAdj(Person p) throws Exception {
        switch (p.getHealth()) {
            case Good:
                return 0.8;
            case Fair:
                return 1.0;
            case Poor:
                return 1.2;
            default:
                throw new Exception("non-conforming health state");
        }
    }

    /**
     * Returns the mortality adjustment factor for the person's health state; this is called by the mortality class
     *
     * @param hs health status
     * @return adjustment factor to be applied to the base qx
     * @throws Exception base health state
     */
    public double getHealthMortAdj(HealthState hs) throws Exception {
        switch (hs) {
            case Good:
                return 0.8;
            case Fair:
                return 1.0;
            case Poor:
                return 1.2;
            default:
                throw new Exception("non-conforming health state");
        }
    }


}
