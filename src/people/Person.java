package people;

import java.time.LocalDate;

/**
 * Methods for Policyholder
 */

public interface Person {

    Gender getGender();

    public int getAge();

    public int setAge(int a);

    public int updateAge();

    public HealthState getHealth();

    public HealthState setHealth(HealthState hs);

    public HealthState updateHealth();

    public boolean getAlive();

    public boolean setAlive(boolean d);

    public boolean updateAlive();

    public void updateDemography(LocalDate dt);

    public Person updatePerson(LocalDate dt);

}
