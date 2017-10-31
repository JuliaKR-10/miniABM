package products;


import people.Person;

import java.time.LocalDate;

public class TermLife implements Insurance {


    /**
     * Variables needed are monthly premium charged, collected premium from policyholder, face amount of Term product, and inforce characteristics
     */
    int collectedPremium;
    int monthlyPremium;
    int face;
    boolean inforce;
    int duration;//in months
    int origLevelTerm; //in months


    public TermLife(int collectedPremium, int monthlyPremium, int face, int duration, int origLevelTerm) {
        this.collectedPremium = collectedPremium;
        this.monthlyPremium = monthlyPremium;
        this.face = face;
        this.inforce = true;
        this.duration = duration * 12;
        this.origLevelTerm = origLevelTerm * 12;
    }

    /**
     * This method calculates the collected premiums over the course of the projection
     */
    @Override
    public void payPremium() {
        collectedPremium += this.getPremium();
    }

    /**
     * This method gets the duration from the Person class
     */
    @Override
    public int getDuration() {
        return this.duration;
    }

    public int getOrigLevelTerm() {
        return this.origLevelTerm;
    }

    public void updateTermLife(Person p, LocalDate dt) {
        this.updateDuration();
        if (this.duration == (this.origLevelTerm + 1)) {
            setPremium(calculatePremium(p, dt));
        }
    }

    /**
     * This method sets the duration
     *
     * @param u
     */
    @Override
    public int setDuration(int u) {
        return this.duration = u;
    }

    /**
     * This method updates the Duration
     */
    @Override
    public int updateDuration() {
        this.duration++;
        return this.duration;
    }

    /**
     * This method retrieves the current monthly premium for the insurance product
     *
     * @return monthly premium
     */

    public int getPremium() {
        return this.monthlyPremium;
    }

    /**
     * This method sets the new monthly premium each month
     *
     * @param p
     */

    public int setPremium(int p) {
        this.monthlyPremium = p;
        return getPremium();
    }

    /**
     * This method calculates the monthly premium in the post level term period
     *
     * @parim p, d
     */

    public int calculatePremium(Person p, LocalDate d) {

        return this.monthlyPremium;

        /**
         * not sure how to look up post level term rate in table, have we decided on structure?
         */

    }

    /**
     * This method calculates the collected premiums over the course of the projection
     *
     * @param p
     */

    void payPremium(int p) {
        collectedPremium += p;
    }

    /**
     * This method pays out the Death Benefit claim if the policyholder passes away
     */

    public int payClaim() {
        if (this.inforce)
            return this.getFace();
        else {
            return 0;
        }
    }

    /**
     * This method lapses the policy if they surrender within the projection, calling the terminate method
     *
     * @return inforce characteristics
     */

    public boolean surrender() {
        this.terminate();
        return this.getInforce();
    }

    public boolean getInforce() {
        return this.inforce;
    }

    public boolean setInforce(boolean inf) {
        this.inforce = inf;
        return this.inforce;
    }

    /**
     * This method retrieves the current face amount for the insurance product
     *
     * @return current face amount
     */

    public int getFace() {
        return this.face;
    }

    /**
     * This method sets the face amount each month - should be level
     *
     * @param f
     */

    public int setFace(int f) {
        this.face = f;
        return this.getFace();
    }

    /**
     * This method lapses the policy - zeroes out face and monthly premium values
     */
    public void terminate() {
        this.setInforce(false);
        this.face = 0;
        this.monthlyPremium = 0;
    }

    @Override
    public String toString() {
        return "TermLife{" +
                "collectedPremium=" + collectedPremium +
                ", monthlyPremium=" + monthlyPremium +
                ", face=" + face +
                ", inforce=" + inforce +
                ", duration=" + duration +
                ", origLevelTerm=" + origLevelTerm +
                '}';
    }
}
