import java.util.Scanner;
import java.io.File;
/**
 * Taxation class used to calculate tax and taxable income of a family
 */
public class Taxation 
{   
    /**
     * @field socialSecurityRate rate of SSN tax, default value is 12.4%
     * @field socialSecurityIncomeLimit maximum taxable income for SSN, default value is $137,700
     * @field medicareRate rate of medicare tax, default value 2.9%
     * @field adultBaseExemption base exemption called in deduction (adult class), default value is $3000
     * @field childBaseExemption base exemption called in deduction (child class), default value is $2000
     * @field medianIncomePerCapita called in deduction, default value is $31,099
     */
    private static float socialSecurityRate = (float) 0.124;
    private static float socialSecurityIncomeLimit = (float) 137700.0;
    private static float medicareRate = (float) 0.029;
    private static float adultBaseExemption = (float) 3000.0;
    private static float childBaseExemption = (float) 2000.0;
    private static float medianIncomePerCapita = (float) 31099.0;

    /**
     * @return gets social security rate
     */
    public float getSocialSecurityRate() {
        return socialSecurityRate;
    }
    /**
     * @return gets social security income limit
     */
    public float getSocialSecurityIncomeLimit() {
        return socialSecurityIncomeLimit;
    }
    /**
     * @return gets medicare rate
     */
    public float getMedicareRate() {
        return medicareRate;
    }
    /**
     * @return gets adult base exemption
     */
    public float getAdultBaseExemption() {
        return adultBaseExemption;
    }
    /**
     * @return gets child base exemption
     */
    public float getChildBaseExemption() {
        return childBaseExemption;
    }
    /**
     * @return gets median income per capita
     */
    public float getMedianIncomePerCapita() {
        return medianIncomePerCapita;
    }
    
    /**
     * Loads values for all fields from a .txt file
     * @param filename file name for loading fields
     * @return makes sure values are valid and file is found
     */
    public static void loadParameters(String filename) {
        try {
            File newFile = new File(filename);
            Scanner scnr = new Scanner(newFile);
            for(int i = 0; i < 6; ++i) {
                String word = scnr.next();
                float num = scnr.nextFloat();
                if (word.equals("socialSecurityRate")) {
                    socialSecurityRate = num/100;
                }
                else if (word.equals("socialSecurityIncomeLimit")) {
                    socialSecurityIncomeLimit = num;
                }
                else if (word.equals("medicareRate")) {
                    medicareRate = num/100;
                }
                else if (word.equals("adultBaseExemption")) {
                    adultBaseExemption = num;
                }
                else if (word.equals("childBaseExemption")) {
                    childBaseExemption = num;
                }
                else if (word.equals("medianIncomePerCapita")) {
                    medianIncomePerCapita = num;
                }
            }
            scnr.close();
        } catch (Exception x) {
        }
    }
    
    /**
     * Table for income brackets
     * @param status the filing status of a family
     * @param b the desireable bracket number
     * @return the range of the income bracket with the specific filing status
     */
    private static double incomeBracket(byte status, byte b) {
        // Values for Married (separately) Filing Status
        if (status == 3 && b == 1) {
            return 12000;
        }else if (status == 3 && b == 2) {
            return 31999.99;
        }else if (status == 3 && b == 3) {
            return 43999.99;
        }else if (status == 3 && b == 4) {
            return 81999.99;
        }else if (status == 3) {
            return -170000;
        }

        // Values for Married Filing Status
        else if (status == 2 && b == 1) {
            return 20000;
        }else if (status == 2 && b == 2) {
            return 49999.99;
        }else if (status == 2 && b == 3) {
            return 89999.99;
        }else if (status == 2 && b == 4) {
            return 149999.99;
        }else if (status == 2) {
            return -310000;
        }

        // Values for Single Filing Status
        else if (status == 1 && b == 1) {
            return 10000;
        }else if (status == 1 && b == 2) {
            return 29999.99;
        }else if (status == 1 && b == 3) {
            return 39999.99;
        }else if (status == 1 && b == 4) {
            return 79999.99;
        }else if (status == 1) {
            return -160000;
        }
        return 0.0;
    }
    
    /**
     * The table for tax rates
     * @param bracketNum the desireable bracket number
     * @param status filing status of a family
     * @return returns rate for the specific bracket and filing status
     */
    private static double taxRate(byte bracketNum, byte status) {
        
        // Values for Married (separately) Filing Status
        if (status == 3 && bracketNum == 1) {
            return 0.10;
        }else if (status == 3 && bracketNum == 2) {
            return 0.12;
        }else if (status == 3 && bracketNum == 3) {
            return 0.24;
        }else if (status == 3 && bracketNum == 4) {
            return 0.26;
        }else if (status == 3 && bracketNum == 5) {
            return 0.35;
        }

        // Values for Married Filing Status
        else if (status == 2 && bracketNum == 1) {
            return 0.10;
        }else if (status == 2 && bracketNum == 2) {
            return 0.12;
        }else if (status == 2 && bracketNum == 3) {
            return 0.23;
        }else if (status == 2 && bracketNum == 4) {
            return 0.25;
        }else if (status == 2 && bracketNum == 5) {
            return 0.33;
        }

        // Values for Single Filing Status
        else if (status == 1 && bracketNum == 1) {
            return 0.10;
        }else if (status == 1 && bracketNum == 2) {
            return 0.12;
        }else if (status == 1 && bracketNum == 3) {
            return 0.22;
        }else if (status == 1 && bracketNum == 4) {
            return 0.24;
        }else if (status == 1 && bracketNum == 5) {
            return 0.32;
        }
        return 0.0;
    }

    /**
     * @return gets the number of tax brackets
     */
    public static byte getNumTaxBrackets() {
        return 5;
    }
    
    /**
     * the maximum income bracket the family's taxable income is in
     * @param newFam gets the filing status and taxable income of the family
     * @return returns maximum tax bracket
     */
    public static byte maxIncomeTaxBracket(Family newFam) {
        float taxable = newFam.getTaxableIncome();
        int status = newFam.getFilingStatus();

        // Values for Married (separately) Filing Status
        if (status == 3 && taxable <= 12000) {
            return 1;
        }else if (status == 3 && taxable <= 44000) {
            return 2;
        }else if (status == 3 && taxable <= 88000) {
            return 3;
        }else if (status == 3 && taxable <= 170000) {
            return 4;
        }else if (status == 3) {
            return 5;
        }

        // Values for Married Filing Status
        else if (status == 2 && taxable <= 20000) {
            return 1;
        }else if (status == 2 && taxable <= 70000) {
            return 2;
        }else if (status == 2 && taxable <= 160000) {
            return 3;
        }else if (status == 2 && taxable <= 310000) {
            return 4;
        }else if (status == 2) {
            return 5;
        }

        // Values for Single Filing Status
        else if (status == 1 && taxable <= 10000) {
            return 1;
        }else if (status == 1 && taxable <= 40000) {
            return 2;
        }else if (status == 1 && taxable <= 80000) {
            return 3;
        }else if (status == 1 && taxable <= 160000) {
            return 4;
        }else if (status == 1) {
            return 5;
        }
        return 0;
    }
    
    /**
     * the taxable income of a family inside a specific taxbracket
     * @param newFam to get filing status and taxable income of a family
     * @param b desired bracket
     * @return returnsa a familiy's taxable income inside a bracket
     */
    public static float bracketIncome(Family newFam, byte b) {
        
        byte maxBracket = maxIncomeTaxBracket(newFam);

        double ret = 0;
        if (maxBracket == b && b == 5) {
            ret = newFam.getTaxableIncome() + incomeBracket((byte)newFam.getFilingStatus(), maxBracket);
        }
        else if (maxBracket == b) {
            double taxableIncome = newFam.getTaxableIncome(); // total taxable income
            byte status = newFam.getFilingStatus(); // gets status
            for (byte i = 1; i < maxBracket; ++i) { //iterares til max bracket (up to 4 loops) and subtracts value of the lower tax bracket
                taxableIncome -= incomeBracket(status, i);
            }
            ret = taxableIncome;
        }
        else if (maxBracket < b) {
            ret = 0;
        }
        else if (maxBracket > b) {
            ret = incomeBracket(newFam.getFilingStatus(), b);
        }
        return (float) ret;
    }

    /**
     * finds the tax rate of a specified bracket.
     * @param b the bracket number
     * @param f the filing status
     * @return float value taxRate
     */
    public static float bracketTaxRate(byte b, byte f) {
        double taxRate = taxRate(b, f);
        return (float) taxRate;
    }
}
