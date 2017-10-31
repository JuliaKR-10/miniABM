package products;

import people.Person;

import java.time.LocalDate;

public interface Insurance {


/**
 * This interface will be implemented by all insurance type products in the model. 
 * It describes the core common methods that all insurance products must have.
 */

    /**
     * This method retrieves the current monthly premium for the insurance product
     *
     * @return monthly premium
     */
    int getPremium();

    /**
     * This method sets the new monthly premium each month
     *
     * @param p
     */
    int setPremium(int p);

    /**
     * This method calculates the monthly premium in the post level term period
     *
     * @parim p, d
     */
    int calculatePremium(Person p, LocalDate d);


    /**
     * This method calculates the collected premiums over the course of the projection
     *
     * @param p
     */
    void payPremium();

    /**
     * This method pays out the Death Benefit claim if the policyholder passes away
     */
    int payClaim();

    /**
     * This method gets the duration from the Person class
     */
    int getDuration();

    /**
     * This method sets the duration
     */
    int setDuration(int u);

    /**
     * This method updates the Duration
     */
    int updateDuration();

    /**
     * This method lapses the policy if they surrender within the projection, calling the terminate method
     *
     * @return inforce characteristics
     */
    boolean surrender();

    /**
     * This method retrieves the current face amount for the insurance product
     *
     * @return current face amount
     */
    int getFace();

    /**
     * This method sets the face amount each month - should be level
     *
     * @param f
     */
    int setFace(int f);

    /**
     * This method lapses the policy - zeroes out face and monthly premium values
     */
    void terminate();

}
