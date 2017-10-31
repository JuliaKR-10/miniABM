# Mini ABM Design - adapted from PWC

## To do
1. specify the premium decision for level term -  decided to use PWC method of always prem pay before level term period ends, and only pay if not healthy after level term period.
2. design the increase in premium for post level term - premiums will be read in.
3. design the update to the premium decision for the post level term  - if healthy do not pay, if not healthy pay.

## Interfaces

### Person
- int getAge()
- int setAge(a)
- int updateAge()
- HealthState getHealth()
- HealthState setHealth(hs)
- HealthState updateHealth()
- boolean getAlive()
- boolean setAlive(d)
- boolean updateAlive(date)
- void updateDemography(date) <this could also return an object that implements Person>

### Insurance
- int getPremium()
- int setPremium(p)
- int calculatePremium(Person, date)
- int payClaim()
- int getDuration()
- int setDuration(u)
- int updateDuration()
- boolean surrender()
- int getFace()
- int setFace(f)
- void terminate()

## Classes

### Policyholder implements Person
The main policyholder making decisions in the model

#### Packages - usual suspects, dependencies, and random

#### Variables
int age
int gender
HealthState hs
boolean alive
TermInsurance termLife
int termDuration
double paidDB

#### Functions
- int getAge() return this.age;
- private int setAge(a)
    this.age = a;
    return this.getAge();
- int updateAge()
    this.age ++;
    return this.getAge();
- HealthState getHealth() return this.hs;
- HealthState setHealth(hs)
    this.hs = hs;
    return this.getHealth();
- HealthState updateHealth()
    return Health.updateHealthState(this);
- boolean getAlive() return this.alive;
- boolean setAlive(d)
    this.alive = d;
    return this.getAlive();
- boolean updateAlive(date)
    m = Mortality.getMortality(this, date);
    if(m < random.nextUniform())
        this.setAlive(false)
        this.makeDeathClaim(this.termLife)
    return this.getAlive()
- void updateDemography(date) - goes through and updates age if date.month == date.dob, updates health, and mortality
- int getDuration() return this.termDuration;
- private int setDuration(u)
    this.termDuration = u;
    return this.getDuration();
- int updateDuration()
    this.termDuration ++;
    return this.getDuration();
- double makeDeathClaim(termLife)
    paidDB = termLife.payClaim()
    return paidDB;
- boolean premiumDecision()
	if (termLife.policyYear < termLife.termPeriod){
		termLife.payPremium(termLife.getPremium())
	} else{
		if(this.getHealth() == Healthy)
	        termLife.surrender()
	    else
	        termLife.payPremium(termLife.getPremium());
	}

### TermLife implements Insurance

#### Variables
- inforce
- collectedPremium
- monthlyPremium
- face

#### functions
- int getPremium() return this.monthlyPremium;
- int setPremium(p)
    this.monthlyPremium = p;
    return getPremium();
- int calculatePremium(Person, date) - will update the monthly premium for the post level term period
- int payClaim()
    if(this.inforce)
        return this.getFace()
- boolean surrender()
    this.terminate()
    return this.getInforce
- int getFace() return this.face
- int setFace(f)
    this.face =f
    return this.getFace()
- void terminate()
    this.setInforce(false)
    this.face = 0
    this.monthlyPremium = 0
- void payPremium(p)
    collectedPremium += p
- boolean getInforce() return this.inforce;
- boolean setInforce(b)
    this.inforce = b
    return this.getInforce()

### Health <static>
Static class that houses the functions that calculate health transitions, simulate health transitions, and mortlaity impacts of health
- HealthState updateHealthState(Person) - simulate transitions given probabilities for person age, gender, and current health state and return new HealthState
- double getMortAdj(Person) - look up and return the mortality adjustment for the person's health state and demographics

### Mortality <static>
Static class that houses the functions that calculate and simulate mortality in the model
- getMortality(Person, date) - looks up and calculates the improved health adjusted mortality for the person given age, gender, health status, and years of improvement
- getSurvival(Person, date) - tpx


### Utilities <static>
Class of generic helper functions

## Enums

### HealthState
- Good
- Poor
- Serverly Ill

### Gender
- male
- female
