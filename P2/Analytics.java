/**
 * Analytics class analyzes values in a tax year
 */
public class Analytics 
{
    /**
     * @field povertyThreshold static float default value is $27,750
     * @field taxYear, A taxYear object from taxYear class
     */
    private static float povertyThreshold = 27750;
    private TaxYear taxYear;

    /**
     * Constructor for Analytics class
     * @param taxYear sets the taxYear to be analyzed
     */
    public Analytics (TaxYear taxYear) {
        this.taxYear = taxYear;
    }

    /**
     * @param povertyThreshold sets the poverty threshold to be tested
     */
    public void setPovertyThreshold(float povertyThreshold) {
        Analytics.povertyThreshold = povertyThreshold;
    }

    /**
     * @return average family income for families in a taxYear
     */
    public float averageFamilyIncome() {
        Family[] newFamilies = this.taxYear.getFamilies(); // gets Families from specific TaxYear
        float totIncome = 0;
        int realLength = newFamilies.length - this.taxYear.counter();
        for (int i = 0; i < realLength; i++) {
            Family newFam = newFamilies[i];
            totIncome += newFam.getTaxableIncome();
        }

        return totIncome/realLength;
    }

    /**
     * @return average person income for people in a taxYear
     */
    public float averagePersonIncome() {
        Family[] newFamilies = this.taxYear.getFamilies();

        float totIncome = 0;
        int numPeople = 0;

        for (int i = 0; i < newFamilies.length - this.taxYear.counter(); ++i) {
            Family currFam = newFamilies[i];
            for (int j = 0; j < currFam.getMembers().length; ++j) {

                Person currPerson = currFam.getMembers()[j];  // the current person object
                
                if (currPerson instanceof Adult) {
                    Adult currAdult = (Adult) currPerson;
                    totIncome += currAdult.adjustedIncome() - currAdult.deduction(currFam);
                }
                else if (currPerson instanceof Child) {
                    Child currChild = (Child) currPerson;
                    totIncome += currChild.getGrossIncome() - currChild.deduction(currFam);
                }

            }
            numPeople += currFam.getNumMembers();
        }

        return totIncome/numPeople;
    }

    /**
     * @return the maximum family income for all families in a taxYear
     */
    public float maxFamilyIncome() {
        Family[] newFamilies = this.taxYear.getFamilies();
        float maxIncome = newFamilies[0].getTaxableIncome();
        for (int i = 0; i < newFamilies.length - this.taxYear.counter(); ++i) {
            if (newFamilies[i].getTaxableIncome() > maxIncome) {
                maxIncome = newFamilies[i].getTaxableIncome();
            }
        }
        return maxIncome;
    }

    /**
     * @return the maximum person income for all people in a taxYear
     */
    public float maxPersonIncome() {
        Family[] newFamilies = this.taxYear.getFamilies();

        float maxIncome = 0;
        float income = 0;

        for (int i = 0; i < newFamilies.length - this.taxYear.counter(); ++i) {
            Family currFam = newFamilies[i];
            for (int j = 0; j < currFam.getMembers().length; ++j) {
                Person currPerson = currFam.getMembers()[j];  // the current person object
                if (currPerson instanceof Adult) {
                    Adult currAdult = (Adult) currPerson;
                    income = currAdult.adjustedIncome() - currAdult.deduction(currFam);
                }
                else if (currPerson instanceof Child) {
                    Child currChild = (Child) currPerson;
                    income = currChild.getGrossIncome() - currChild.deduction(currFam);
                }
                if (maxIncome < income) {
                    maxIncome = income;
                }
            }
        }
        return maxIncome;
    }
    
    /**
     * @return the number of families below the poverty limit
     */
    public int familiesBelowPovertyLimit() {
        Family[] newFamilies = this.taxYear.getFamilies();
        int fams = 0;
        for (int i = 0; i < newFamilies.length - this.taxYear.counter(); ++i) {
            if (newFamilies[i].getTaxableIncome() < povertyThreshold) { // increments fams
                fams += 1;
            }
        }
        return fams;
    }

    /**
     * @param newFam the input family
     * @return the rank of a families income in a taxYear
     */
    public int familyRank(Family newFam) {
        Family[] newFamilies = this.taxYear.getFamilies();
        float[] rankArray = new float[newFamilies.length - this.taxYear.counter()];
        
        int count = 0;
        int ceiling = newFamilies.length - this.taxYear.counter();
        while (count < ceiling) {
            int iteration;
            if (newFamilies.length - this.taxYear.counter() > 0) {
                iteration = newFamilies.length - this.taxYear.counter();
            } else {
                iteration = newFamilies.length;
            }
            float maxIncome = newFamilies[0].getTaxableIncome();
            for (int i = 0; i < iteration; i++) {
                Family currFamily = newFamilies[i];
                if (currFamily.getTaxableIncome() > maxIncome) {
                    maxIncome = currFamily.getTaxableIncome();
                }
            }
            if (newFamilies.length > 1) {
                Family[] newFamiliesCopy = new Family[iteration - 1]; //reduces the size of rank array
                int dupeCounter = 0;
                for (int j = 0; j < iteration; ++j) {
                    if (newFamilies[j].getTaxableIncome() == maxIncome) {
                        dupeCounter += 1;
                        continue;
                    }
                    newFamiliesCopy[j] = newFamilies[j - dupeCounter];
                }
                newFamilies = newFamiliesCopy;
            }
            else {
                rankArray[rankArray.length - 1] = maxIncome; // only executed to assign the lowest taxable income to rankarray
                break;
            }
            rankArray[count] = maxIncome;
            ++count;
        }

        int rank = 0;
        for (int i = 0; i < rankArray.length; i++) {
            if (newFam.getTaxableIncome() == rankArray[i]) {
                rank = i;
                break;
            }
        }
        return rank + 1;
    }

}
