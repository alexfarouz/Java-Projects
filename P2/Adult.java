/**
 * An sub-type of the Person class
*/
public class Adult extends Person 
{
    /**
     * @field the employer of the adult
     */
    private String employer;

    /**    
     * Constructor for the Adult class.
     * @param name the name of the adult
     * @param birthday the birthday of the adult
     * @param ssn the social security number of the adult
     * @param grossIncome the gross income of the adult
     * @param employer the employer of the adult
     */
    public Adult(String name, String birthday, String ssn, float grossIncome, String employer) {
        super.setName(name);
        super.setBirthday(birthday);
        super.setSSN(ssn);
        this.setGrossIncome(grossIncome);
        this.employer = employer;
    }

    /** 
     * @Override the toString method from the Person class to include the gross income of the adult.
     */
    public String toString() {
        String ret = super.toString();
        ret += " $" + getGrossIncome();
        return ret;
    }

    /**
     * @return returns employer of the adult
     */
    public String getEmployer() {
        return this.employer;
    }

    /**
     * Calculates the adjusted income of the adult after applying medicare tax rate and social security tax rate
     * @return returns adjusted income of adult object
     */
    public float adjustedIncome() {
        double adjIncome = this.getGrossIncome();
        Taxation tax = new Taxation();
        // Checks if gross income is greater than the social security limit to deduct the correct amount
        if (adjIncome > tax.getSocialSecurityIncomeLimit()) {
            adjIncome -= (tax.getSocialSecurityRate() * tax.getSocialSecurityIncomeLimit() + tax.getMedicareRate() * this.getGrossIncome())/2;
        }
        // If not then socialSecurity rate affects all of gross income
        else {
            adjIncome -= ((tax.getSocialSecurityRate() + tax.getMedicareRate()) * this.getGrossIncome())/2;
        }
        return (float) adjIncome;
    }

    /**
     * Calculates the tax withheld for the adult based on their gross income.
     * @return the tax withheld for the adult
     */
    public float taxWithheld() {
        double taxW = 0;
        // For gross income <= 50k
        if (this.getGrossIncome() <= 50000) {
            taxW += this.getGrossIncome() * 0.10;
        }
        // For gross income > 50k but <= 150k
        else if (this.getGrossIncome() > 50000 && this.getGrossIncome() <= 150000) {
            taxW += 50000 * 0.10;
            taxW += (this.getGrossIncome() - 50000) * 0.15;
        }
        // For gross income > 150k
        else {
            taxW += 50000 * 0.10;
            taxW += 100000 * 0.15;
            taxW += (this.getGrossIncome() - 150000) * 0.20;
        }
        return (float) taxW;
    }

    /**
     * @Override Person class deduction method
     */
    public float deduction(Family newFam) {
        Taxation tax = new Taxation();
        double exemption = tax.getAdultBaseExemption(); // stores the original exemption amount
        
        if (newFam.getFilingStatus() == 1) { // Checks if it is a single family status
            exemption *= 2;
        }
        if (adjustedIncome() > 100000) { // Checks if the adjusted income is > 100k
            double adjDeduct = adjustedIncome() - 100000; 
            if (adjDeduct > 60000) { // makes sure the exemption is not reduced more than 30%
                adjDeduct = 60000;
            }
            adjDeduct -= adjDeduct % 1000;
            adjDeduct /= 200000;
            exemption *= (1-adjDeduct);
        }

        if (exemption > adjustedIncome()) {
            exemption = adjustedIncome();
        }

        return (float) exemption;
    }

}
