/**
 * The taxYear class stores many families in a single tax year
 */
public class TaxYear 
{
    /**
     * @field stories families in a local storage array
     */
    private Family[] families;
    
    /**
     * constructor sets the max amount of families in a taxYear
     * @param max the length of the families array
     */
    public TaxYear(int max) {
        this.families = new Family[max];
    }
    
    /**
     * @return getter method returns all the families in a tax year
     */
    public Family[] getFamilies() {
        return this.families;
    }
    
    /**
     * The taxFiling method checks if the family and its members are legal before adding it to local storage
     * @param newFam the familiy being added to local storage
     * @return true if family was added, false if the family was invalid
     */
    public boolean taxFiling(Family newFam) {
        
        /*
        for(int i = 0; i < newFam.getNumMembers(); ++i) { // checks validity of all the person objects
            Person newPerson = newFam.getMembers()[i];
            boolean[] boolArray = new boolean[4];
            try {
                boolArray[0] = newPerson.setName(newPerson.getName());
                boolArray[1] = newPerson.setBirthday(newPerson.getBirthday());
                boolArray[2] = newPerson.setSSN(newPerson.getSsn());
                boolArray[3] = newPerson.setGrossIncome(newPerson.getGrossIncome());
                if (boolArray[0] == false || boolArray[1] == false || boolArray[2] == false || boolArray[3] == false) {
                    return false;
                }
            } catch (Exception e) {
                return false;
            }
            
        }
        */
        
        if (newFam.getNumAdults() < 1 || newFam.getNumAdults() > 2) {
            return false;
        }
        if (((newFam.getFilingStatus() == 1 || newFam.getFilingStatus() == 3) && newFam.getNumAdults() != 1) || (newFam.getFilingStatus() == 2 && newFam.getNumAdults() != 2)) {
            return false;
        }
        
        for (int i = 0; i < families.length; ++i) { // goes through this.families
            if (this.families[i] == null) { // if value is null newFam is added
                this.families[i] = newFam;
                break;
            }
        }
        return true;
    }

    /**
     * calculates taxwithheld for all families in the taxyear
     * @return float of taxwithheld for all families
     */
    public float taxWithheld() {
    
        double tax = 0;
        for (int i = 0; i < this.families.length - counter(); ++i) { // accesses families array
            Family newFam = this.families[i];
            for (int j = 0; j < newFam.getNumMembers(); ++j) { // iterates through family
                if (newFam.getMembers()[j] instanceof Adult) { // takes tax withheld for every adult in the family
                    Adult newAdult = (Adult) newFam.getMembers()[j];
                    tax += newAdult.taxWithheld();
                }
            }
        }
        
        return (float) tax;
    }

    /**
     * Gets tax owed of all families based on taxable income put in tax brackets
     * @return returns the float value of tax owed for all families
     */
    public float taxOwed() {
        double tax = 0;
        for (int i = 0; i < this.families.length - counter(); ++i) { // accesses families array
            Family newFam = this.families[i]; // current iteration of families (Family Object)
            
            int maxBracket = Taxation.maxIncomeTaxBracket(newFam); // max bracket the family tax has
            double result = 0;
            for (byte j = 1; j <= maxBracket; ++j) { // goes through the tax brackets which the family is eligible for
                double taxRate = Taxation.bracketTaxRate(j, newFam.getFilingStatus()); // the tax rate for current iteration of the bracket
                double currTax = Taxation.bracketIncome(newFam, j); // the amount of taxable income which is in the current bracket
                result += (taxRate * currTax);
            }
            // if the max bracket is 5, finds the taxable income for bracket 5
            tax += result;
        }
        return (float) tax;
    }
    
    /**
     * @return the tax credit for all families in a tax year
     */
    public float taxCredits() {
        double taxCredit = 0;
        for(int i = 0; i < this.families.length - counter(); ++i) {
            Family newFam = this.families[i];
            taxCredit += newFam.taxCredit();
        }
        return (float) taxCredit;
    }

    /**
     * @return tax due calculated by taxOwed - taxWithheld - taxCredits
     */
    public float taxDue() {
        double tax = taxOwed() - taxWithheld() - taxCredits();
        return (float) tax;
    }

    /**
     * @return finds number of returns filed so far in the tax year
     */
    public int numberOfReturnsFiled() {
        int taxReturns = 0;
        for (int i = 0; i < this.families.length - counter(); ++i) {
            taxReturns += 1;
        }
        return taxReturns;
    }

    /**
     * @return finds number of people filed so far in the tax year
     */
    public int numberOfPersonsFiled() {
        int numPeople = 0;

        for (int i = 0; i < this.families.length - counter(); ++i) {
            Family newFam = this.families[i];
            numPeople += newFam.getNumMembers();
        }
        return numPeople;
    }

    /**
     * @param index for the specified family's tax return
     * @return finds the specific tax return of a family
     */
    public Family getTaxReturn(int index) {
        Family ret = new Family((byte) 0, (byte) 0);
        for (int i = 0; i < this.families.length; ++i) {
            if (i == index) {
                ret = this.families[i];
                break;
            }
        }
        return ret;
    }

    /**
     * @return counter which counts the number of null families in a tax year
     */
    public int counter() {
        int count = 0;
        for(int i = 0; i < this.families.length; ++i) { // checks if any families are null
            if (this.families[i] == null) {
                count += 1;
            }
        }
        return count;
    }
}
