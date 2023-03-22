/**
 * A collection of Person Objects
 */
public class Family 
{   
    /**
     * @field numMembers is the maximum number of members in a family
     * @field filingStatus is the filing status of the family (1, 2 or 3)
     * @field members is an array of Person objects with length numMembers
     */
    private byte numMembers;
    private byte filingStatus;
    private Person[] members;
    
    /**
     * Constructor for the Family class
     * @param numMembers is the length of Person array
     * @param filingStatus sets filing status
     */
    public Family(byte numMembers, byte filingStatus) {
        this.members = new Person[numMembers];
        this.filingStatus = filingStatus;
        this.numMembers = numMembers;
    }
    
    /**
     * A private constructor for the Family class (it is only called inside the class)
     * @param members the array of people which gets assigned to the members field
     * @param filingStatus sets the filing status of the family
     */
    private Family(Person[] members, byte filingStatus) {
        this.filingStatus = filingStatus;
        this.members = members;
        this.numMembers = (byte) members.length;
    }

    /**
     * @return getter for the number of members in the family
     */
    public byte getNumMembers() {
        return this.numMembers;
    }

    /**
     * @return getter for the person array of the family
     */
    public Person[] getMembers() { // gets array of person objects
        return this.members;
    }

    /**
     * @return getter for the filing status of the family
     */
    public byte getFilingStatus() {
        return this.filingStatus;
    }

    /**
     * Adds a new member to the person array
     * @param newPerson person being added to array
     */
    public void addMember(Person newPerson) {
        
        Person[] newMembers = new Person[getNumMembers()]; // creates a new array of Person objects
        int count = 0;
        for (int i = 0; i < getNumMembers(); ++i) {
            if (this.members[i] == null) { // Finds the next null Person object
                break;
            }
            count += 1; // Counts number of pre-stored Person Objects
            newMembers[i] = this.members[i]; // Copies pre-stored Person Objects
        }
        if (newPerson instanceof Adult) {
            Adult newAdult = (Adult) newPerson;
            newMembers[count] = newAdult;
        }
        else if (newPerson instanceof Child) {
            Child newChild = (Child) newPerson;
            newMembers[count] = newChild;
        }
        this.members = newMembers;
    }

    /**
     * @returns number of Adults in the family
     */
    public byte getNumAdults() {
        byte count = 0;
        for (byte i = 0; i < getNumMembers(); ++i) {
            if (this.members[i] instanceof Adult) {
                count += 1;
            }
        }
        return count;
    }

    /**
     * @returns number of children in the family
     */
    public byte getNumChildren() {
        byte count = 0;
        for (byte i = 0; i < getNumMembers(); ++i) {
            if (this.members[i] instanceof Child) {
                count += 1;
            }
        }
        return count;
    }
    
    /**
     * gets the taxable income based on income - deduction
     * @return returns the total taxable income for the family
     */
    public float getTaxableIncome() {
        double taxable = 0;
        Family thisFam = new Family(getMembers(), this.filingStatus); //Creates a new Family object to be refrenced later in Deduction call
        for (int i = 0; i < getNumMembers(); ++i) {
            
            if (this.members[i] instanceof Adult) {
                Adult newAdult = (Adult) this.members[i]; // Creates a new Adult object if current person is adult
                taxable += (newAdult.adjustedIncome() - newAdult.deduction(thisFam)); // finds taxable income for an adult
            }
            else if (this.members[i] instanceof Child) {
                Child newChild = (Child) this.members[i]; // Creates a new Child object if current person is child
                taxable += (newChild.getGrossIncome() - newChild.deduction(thisFam)); // finds taxable income for a child
            }
        }
        return (float) taxable;
    }

    /**
     * @return calculates the preCredit tax
     */
    public float preCreditTax() {
        Family newFam = new Family(getMembers(), this.filingStatus); //Creates a new Family object to be refrenced later in Deduction call

        byte maxBracket = Taxation.maxIncomeTaxBracket(newFam); // max bracket the family tax has
        double result = 0;

        // iterates through each of the brackets the family is in, compounding the alotted tax rate multiplied by the taxable income within the bracket
        for (byte i = 1; i <= maxBracket; ++i) {
            double taxRate = Taxation.bracketTaxRate(i, newFam.getFilingStatus());
            double currTax = Taxation.bracketIncome(newFam, i);
            result += (taxRate * currTax);
        }
        return (float) result;
    }
    
    /**
     * The taxCredit is the total tax credit a family is eligble to recieve
     * @return returns total taxCredit for family
     */
    public float taxCredit() {
        Taxation tax = new Taxation();
        double median = tax.getMedianIncomePerCapita();
        double taxInc = getTaxableIncome();
        if (taxInc > median * 0.5) { // Checks if the taxable income is on the low half of the median
            return (float) 0.0;
        }
        int taxCredNumerator = (int) (taxInc/1000);
        double taxCred = taxCredNumerator * 30.0;
        for (int i = 0; i < getNumMembers(); ++i) { // iterates through family members and checks which are children
            if (this.members[i] instanceof Child) {
                Child curr = (Child) this.members[i];
                if (curr.getTuition() < 1000) { // if/else adds tuition or 1000 to taxCred, whichever is lower
                    taxCred += curr.getTuition();
                } else {
                    taxCred += 1000;
                }
            }
        }

        if (this.filingStatus == 3) { // if parents are filing seperately, taxcredit is halved
            taxCred /= 2; 
        }
        if (taxCred > 2000) { // Compares taxCred to 2000, returning the lower one
            taxCred = 2000;
        } 
        if (preCreditTax() < taxCred) {
            taxCred = preCreditTax();
        }

        return (float) taxCred;
        
    }

    /**
     * Calculates the tax via brackets and rates then subtracts taxWitheld and taxCredit
     * @return calculates tax for entire family
     */
    public float calculateTax() {
        Family newFam = new Family(getMembers(), this.filingStatus); //Creates a new Family object to be refrenced later in Deduction call

        int maxBracket = Taxation.maxIncomeTaxBracket(newFam); // max bracket the family tax has
        double result = 0;

        // iterates through each of the brackets the family is in, compounding the alotted tax rate multiplied by the taxable income within the bracket
        for (byte i = 1; i <= maxBracket; ++i) {
            double taxRate = Taxation.bracketTaxRate(i, newFam.getFilingStatus());
            double currTax = Taxation.bracketIncome(newFam, i);
            result += (taxRate * currTax);
        }

        result -= taxCredit();
        
        for (int i = 0; i < getNumMembers(); ++i) {
            if (this.members[i] instanceof Adult) {
                Adult newAdult = (Adult) this.members[i];
                result -= newAdult.taxWithheld();
            }
        }
        return (float) result;
    }
    
}
