# miniABM
The design of this ABM is based on a publicly presented model developed by PWC for the SOA in 2014

Agent based simulation models, ABMs, are models of individual actor / agent behavior simulated within a system. In our case we used ABMs to simulate policyholder behavior within life insurance and annuity contracts.

The ABM in this repo is a simple model of decision making for a term life insurance contract. The policyholders are simulated taking a handful of decisions throughout the policy lifecycle repeatedly and then the results are used on gain insight in to future behaivor, how policyholders would react to certain external events, or even how policyholders would react to some change in contract policy.

## Language
- java 8

## Candidate instructions
Please complete the following tasks within 5 days of delivery and send back a zipped version of your work including the git commit record and applicable markdown documents

## Task 1 - Project Review
One of the main tasks of the ABM project is to review the current java project code base and identify, prioritize, and fix weaknesses, bugs, inefficiencies, etc in the code.

Please include the answers to this section in a markdown document entitled projectReview.md that will be located in a new directory in the repo in Documentation/Assessment

### Idenitify
For the first task please review the java code for the mini ABM model and in the markdown document identify areas where the model needs improvement from a java best practices (both in terms of style, efficiency, bugs, maintainability, etc.) perspective

Commit your progress

### Design
To your markdown document, please add a solution design, along with any pseudocode or examples, to fix each of the issues identified above

Commit your progress

### Prioritize
Given the issues and solutions you've already identified, reorder the items in your markdown document in order how we should prioritize them in our issue backlog. Please feel free to include any justification for the choices you make in the markdown with your issue description and solution

Commit your progress

## Task 2 - Implement Fixes
The work for this task will be completed in the body of the code. Create a new branch called task2 to track this work.  

Take the top two (or if small three) issues you prioritized for correction in task 1 and implement your solution as though this were a regular project (e.g., your normal style of branching / forking / committing / etc).

## Task 3 - Implement new decision design
The final task is meant to resemble the other core aspect to our project - namely adding new decisions / revising the current decision structure.

Please create a new branch called task3 to record your work

Please implement the decision design below to replace the decision structure in Policyholder.premiumDecision(). Note, you may need to add additional functionality in other aspects of the model to support this decision structure. Additionally, we do not expect you to come up with "good" values for the parameters, merely to accommodate easy modification of `b_<n>` values

### New premium decision design
- keep the decision the same during the level term period (i.e., while duration < level term always pay the premium)
- post level term keep the always lapse if good health (i.e., if duration > level term && good health then don't pay prem)
- if, after the level term period, the policyholder is not in good health update the decision function to account for how much it will cost the policyholder to keep the policy inforce versus how likely the policyholder is to die in the near future:
    - prob = 1-(exp(x)/(1+exp(x)))
        - x = b_0 + b_1 * benefitRatio + b_2 * premium_jump
        - benefitRatio = expectedPremium(t,x) / expectedBenefit(t,x); t = age when decision is taken, x = 5
            - expectedPremium = tpx * premium * 12 <hint look in Mortality for tpx>
            - expectedBenefit = (1-tpx) * face
        - premium_jump = premium(t) / premium(t-1); basically, premium this year / premium last year
    - take a random draw from the uniform distribution and if the random number is less than the probability lapse, else pay the premium

## Submission / Questions
If you have any questions please feel free to reach out to Julia Romero at (julia.romero@axa.us.com).

Upon completion please zip up all of your work in the repo (including the .git tree) and send it back to Julia (see above). Please including any commentary or supporting documentation that you think would help us understand your process / decision making / thinking during this exercise

Good Luck!
