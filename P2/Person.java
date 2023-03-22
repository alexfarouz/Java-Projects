/**
 * Represents a person with basic information:
 * name, birthday, SSN, gross income and an ID
 *
 */
public class Person 
{
    /**
     * @field prevID tracks the previous id to base the id field off of
     * @field id increments for every new person
     * @field name is the name of the person
     * @field birthday is the birthday of the person
     * @field ssn is the Social Security number of the person
     * @field grossIncome is the gross income of the person
     */
    private static int prevID = 1;
    private int id;
    private String name;
    private String birthday;
    private String ssn;
    private float grossIncome;

    
    /**
     * Creates a new Person object and increments the ID count
     */
    public Person() {
        this.id = prevID;
        prevID++;
    }

    /**
     * Sets the name of the person.
     * @param name the name of the person to set
     * @return true if the name only has letters and spaces in it
     */
    public boolean setName(String name)
    {
        boolean ret = true; 
        for(int i = 0; i < name.length(); i++) { // iterates through the string name and makes sure they valid
            int x = name.charAt(i); // x is the current char of the paramater name
            if (x >= 65 && x <= 90) {
                continue;
            }
            else if (x >= 97 && x <= 122) {
                continue;
            }
            else if (x == 32) {
                continue;
            }
            else {
                ret = false;
                break;
            }
        }
        if (ret == true) {
            this.name = name;
        }
        return ret;
    }

    /**
     * Sets the birthday of the person.
     * @param birthday the birthday of the person to set
     * @return true if the birthday has '/' at index 4 and 7 and numbers in between are digits
     */
    public boolean setBirthday(String birthday) {
        boolean ret = true;
        if (birthday.length() != 10) {
            ret = false;
        } else {

            for (int j = 0; j < birthday.length(); j ++) {
                int currChar = birthday.charAt(j);
                if ((j == 4 || j == 7) && currChar != '/') {
                    ret = false;
                    break;
                }
                else if ((currChar < 48 || currChar > 57) && currChar != '/') {
                    ret = false;
                    break;
                }
            }
        }
        if (ret == true) {
            this.birthday = birthday;
        }
        return ret;
    }

    /**
     * Sets the SSN of the person.
     * @param ssn the SSN of the person to set
     * @return true if the SSN has '-' at index 3 and 6 and all indexes in between are digits
     */
    public boolean setSSN(String ssn) {
        
        boolean ret = true;
        
        if(ssn.length() != 11) {
            ret = false;
        } else {

            for (int i = 0; i < ssn.length(); i++) {                
                int currChar = ssn.charAt(i);
                if ((i == 3 || i == 6) &&  currChar != '-') {
                    ret = false;
                }
                else if ((currChar < 48 || currChar > 57) && currChar != '-') {
                    ret = false;
                }
            }
        }
        if (ret == true) {
            this.ssn = ssn;
        }
        return ret;
    }
    
    /**
     * Sets the gross income of the person.
     * @param grossIncome the gross income of the person to set
     * @return true if the gross income is positive
     */
    public boolean setGrossIncome(float grossIncome) {
        if (grossIncome < 0) {
            return false;
        }
        
        this.grossIncome = grossIncome;
        return true;
    }

    /**
     * @return returns name of the person
     */
    public String getName() {
        return this.name;
    }
    /**
     * @return returns birthday of the person
     */
    public String getBirthday() {
        return this.birthday;
    }
    /**
     * @return returns SSN of the person
     */
    public String getSsn() {
        return this.ssn;
    }
    /**
     * @return returns gross income of the person
     */
    public float getGrossIncome() {
        return this.grossIncome;
    }
    /**
     * @return returns ID of the person
     */
    public int getId() {
        return this.id;
    }
    /**
     * @return returns the name, ssn and birthday of the person in string format
     */
    public String toString() {
        String ret = this.name + " " + this.ssn + " " + this.birthday;
        return ret;
    }
    /**
     * Defined specifically for Child and Adult class
     * @param newFam determines the deduction amount
     * @return float deduction
     */
    public float deduction(Family newFam) {
        return (float) 0.0;
    }

}
