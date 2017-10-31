package people;

import products.TermLife;
import utilities.Health;
import utilities.Mortality;

import java.time.LocalDate;
import java.util.Random;


public class Policyholder implements Person {


    HealthState hs;
    Gender gend;
    int age;
    TermLife tLife;
    boolean alive;
    Health hlt;
    Mortality mort;
    Random rnd;
    LocalDate dob;
    int polNo;

    public Policyholder(Gender gend, int age, int polNo, LocalDate dob, int cltPrem, int mnthPrem, int face, int dur, int origTerm) {
        this.hlt = new Health();
        this.mort = new Mortality();
        this.rnd = new Random(polNo);

        this.tLife = new TermLife(cltPrem, mnthPrem, face, dur, origTerm);

        this.polNo = polNo;
        this.hs = HealthState.Fair;
        this.gend = gend;
        this.age = age;
        this.alive = true;
        this.dob = dob;
    }

    public Policyholder(Policyholder ph) {
        this.polNo = ph.polNo;
        this.hs = ph.hs;
        this.gend = ph.gend;
        this.age = ph.age;
        this.tLife = ph.tLife;
        this.alive = ph.alive;
        this.hlt = ph.hlt;
        this.mort = ph.mort;
        this.rnd = ph.rnd;
        this.dob = ph.dob;
    }

    @Override
    public Gender getGender() {
        return this.gend;
    }

    @Override
    public int getAge() {
        return this.age;
    }

    public int setAge(int a) {
        this.age = a;
        return this.getAge();
    }

    @Override
    public int updateAge() {
        this.age++;
        return this.getAge();

    }

    @Override
    public HealthState getHealth() {
        return this.hs;
    }

    @Override
    public Person updatePerson(LocalDate dt) {
        this.updateDemography(dt);
        return this;
    }

    @Override
    public String toString() {
        return "Policyholder{" +
                "hs=" + hs +
                ", gend=" + gend +
                ", age=" + age +
                ", tLife=" + tLife.toString() +
                ", alive=" + alive +
                ", hlt=" + hlt +
                ", mort=" + mort +
                ", rnd=" + rnd +
                ", dob=" + dob +
                ", polNo=" + polNo +
                '}';
    }

    @Override
    public HealthState setHealth(HealthState hs) {
        this.hs = hs;
        return this.getHealth();
    }

    @Override
    public HealthState updateHealth() {
        try {
            hs = hlt.updateHealthStatus((Person) this);
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
        return this.hs;
    }

    @Override
    public boolean getAlive() {
        return this.alive;
    }

    @Override
    public boolean setAlive(boolean d) {
        this.alive = d;
        return this.getAlive();
    }

    @Override
    public boolean updateAlive() {
        double m = mort.getMortality(this);
        if (m < rnd.nextDouble()) {
            this.setAlive(false);
            tLife.payClaim();
        }
        return this.getAlive();
    }



    public void updateDemography(LocalDate date) {
        if (date.getMonthValue() == dob.getMonthValue()) {
            this.updateAge();
        }
        tLife.updateDuration();
        this.updateHealth();
        this.updateAlive();
    }


    public int getDuration() {
        return tLife.getDuration();
    }


    public boolean premiumDecision() {
        if (tLife.getDuration() < tLife.getOrigLevelTerm()) {
            tLife.payPremium();
        } else if (this.getHealth() == HealthState.Good) {
            tLife.surrender();
        } else {
            tLife.payPremium();
        }
        return tLife.getInforce();
    }

}
