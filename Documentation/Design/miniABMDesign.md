# Design

## Scale
- monthly time step
- household level simulation

## Structure

### Interfaces
#### Person
Interface used for all the people who are in the model including the main policyholder and the other members of the household

**Functions**
- int incrementAge - increment age by a month return age
- healthState updateHealth - calls healthState.updateHealthStatus(...)
- boolean updateMortality - calls
- updateIncome - updates income that the person is generating
- updateCoreExpenses - updates core expenses the person causes
- updateHealthExpenses - updates health care costs for the person
- get/set
    - Age
    - health
    - income
    - coreexpenses
    - healthexpenses
    - hhRole
    - decisionWeight - feeds into decision weight functions of household joint decision making functions

#### Asset
Interface used for the assorted assets that the household has

**Functions**
- int incrementDuration() - increases the duration of asset by a month
- double updateMarketValues(Market mkt) - update balance based on market and allocation returns new balance
- double addMoney - contribute to the asset and return new balance
- double takeMoney - take money out of the asset and return new balance
- get/set
    - allocation
    - balance
    - duration

#### Insurance
Interface for life insurance contracts
**Functions**
- int incrementDuration() - increments the duration of the contract by a month
- boolean clearPremium(premiumPaid) - returns (premiumPaid == premiumDue)
- double payDeathClaim(boolean insuredDied) - if(inforce) return face else return 0
- boolean lapse(boolean !clearPremium) - if true inforce = false else inforce = true return inforce
- get/set
    - inforce
    - duration
    - insuredLife
    - underwritingClass
    - premium

#### market
interface that describes the market factor objects - making an interface here bc this will facilitate flexibility between scenario generators and scenario reading


### Classes

#### main
need this as the place that things run from

Objects


#### Household
Most of the decisions of the model will happen here
We are going to assume that the policyholders don't retire *need to shape the population to be young enough that this is realistic*

Functions
- Household updateHousehold(market) runs through the things that happen to the household in a month and returns the end of month hh
- boolean jointDecision([decision1, weight1], [decision2, weight2], decisionType) - take the decision type and applies a particular combination heuristic to return the "joint decision"
- family[] updateHouseholdInfo(family[]) - updates household age, health, and mortality
- family[] addMember(new Person()) - adds a new person to the family array returns new family array
- family[] dropMember(person p) - drops person p from family array returns new family (death / kids leave @ 18 - simp)
- double updateCashFlow(hh.updateIncome(), hh.updateAllExpenses(), hh.assets) returns net savings that has gone into/out of cash/investment
    - double updateIncome(family[]) - sums the result of each individual's updateIncome function
    - double updateAllExpenses(family[]) - sums the result of the individual core and health expenses as well as the household housing costs
    - void allocateSavings(savings, family[], assets[]) - calls the household heads allocate savings decision functions and then jointDecision to determine where the balance should go and deposits / withdrawals as needed
- updateInsuranceDecisions() - need to work on this but would call the premium, etc decisions for the insurance held by the household


Objects
- family - array of objects implementing person interface
- assets - array of objects implementing asset interface
- insurance - array of objects implementing insurance interface; just term life product for now

#### headOfHH - implements person
The adults in the household, including the insured



#### Health
Class that contains all the functions related to health. I think this can be static

Objects
- health transition probability table - use object key (age, gender, health)
- health status mortality adjustment table - use object key (age, gender, health)

Functions
- HealthState updateHealthStatus(age, gender, currentHHealth) - get probability and simulate transition, return new status
- double healthMortAdj(age, gender, health) - return impact on mortality of health status

Supported by healthStatus ENUM

#### mortality
Static class with mortality and improvement tables. People will call functions to get mortality

Objects
- mortality table(age, gender)
- improvement table(age, gender)
- improvement start year

Functions
*Model on the current mortality calculations in phabm*

#### expenses
Static (i think) class with all the expense simulation functions. Each month the person will call the functions of this class, passing in the parameters that are specific to them, to get the assorted expenses

Functions are:
- core expenses(hh role, age, region, core(t-1), cumulative inflation)
- health care expenses(age, health status, cumulative inflation)
- effective rent(zip+4, prior rent) - note this will be implemented purposfully inefficiently with way more look ups than needed
    - simulate growth rate = realEstate_beta[mkt(t)] + realEstate_alpha[zip+4]\*R[0,1]
    - return prior rent and multiply by simulated growth

#### income
Static class (i think) with the income simulation functions (right now just one). Each month the people in the model will call the function and get back their income

Functions are:
- getIncome(age, prior income, date) - if january return updateIncome() else return prior Income
- updateIncome(age, prior income, date)
    - simulate income growth if January (wages dont grow everymonth) as growth = wageGrowth_beta[mkt(t)] + wageGrowth_alpha(age) \*R[0,1]
    - return prior wage and multiply by simulated growth rate

#### dependant - implements person
class that will take care of the other household members

#### termLife - implements Insurance
The term life product will have the following functions, in addition to those required by the insurance interface
- clear premium - just to close out the cash flow loop
- setUpAutoPay - enroll in autopay premium
- removeAutoPay - unenroll in autopay
- get/set
    - years to maturity
    - auto pay status

### Enum
- healthState {very good, good, average, poor, very poor}
- mortalityStatus {alive, dead}
- gender {male, female}
- hhRole {insured, cohead, childDependent, adultDependent}

### Inputs
- markets
    - equity indices
    - yield curve
    - inflation
    - real estate beta
    - wage growth beta
- mortality
    - mortality
    - improvement
- parameters

### Outputs
- seriatim simulation results


## Tests

### Review
1. Review current model - identify and prioritize areas for improvement
2. Design solution for areas identified in step 1
3. Implment three most important solutions based on prioritization [do not show this task until the task 2 is completed, make sure design they propose is the one they implement - e.g., ensure eyes not bigger than stomach]
4. We provide an area from improvement and have them implement our design

### Design
1. Provide theoretical design for term conversion decision have candidate design implementation for model
2. Ask candidate to estimate time in work hours
4. Ask candidate to implement decision function as standalone fragment
3. Ask candidate to implement any new classes their design entails

## TODO
- References between the insured life and the insurance contract need to be specified
- headOfHH decision functions need to be specified
- investment and cash assets need to be specified 
