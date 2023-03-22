/**
 * A sub-type of the Person class
*/
public class Child extends Person
{
    /**
     * @field school is the school of the child object
     * @field tuition is the tuition of the child object
     */
    private String school;
    private float tuition;

    /**
     * Constructor for the Child object
     * @param name sets child object name
     * @param birthday sets child object bday
     * @param ssn sets child object ssn
     * @param grossIncome sets child object gross income
     * @param school sets child object school
     * @param tuition sets child object tuition
     */
    public Child (String name, String birthday, String ssn, float grossIncome, String school, float tuition) {
        super.setName(name);
        super.setBirthday(birthday);
        super.setSSN(ssn);
        super.setGrossIncome(grossIncome);
        this.school = school;
        this.tuition = tuition;
    }

    /**
     * @Override perosn class tostring method to add the school
     */
    public String toString() {
        String ret = super.toString();
        ret += " " + this.school;
        return ret;
    }

    /**
     * @return gets the tuition of the Child object
     */
    public float getTuition() {
        return this.tuition;
    }

    /**
     * @Override the person class deduction method
     */
    public float deduction(Family newFam) {
        Taxation tax = new Taxation();
        double exemption = tax.getChildBaseExemption();

        int numChildren = newFam.getNumChildren();
        double exReduction = 1;
        if (numChildren > 2) { // for every additional child from 3 on, exemption is reduced by 5%
            exReduction -= 0.05*(numChildren-2);
        }
        if (exReduction < 0.50) {
            exReduction = 0.50; 
        }
        
        exemption *= exReduction;
        if (exemption > getGrossIncome()) { // ensures the exemption is less than grossincome
            exemption = getGrossIncome();
        }
        return (float) exemption;
    }

}
