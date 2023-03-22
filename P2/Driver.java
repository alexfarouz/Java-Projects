public class Driver 
{
    public static void main(String[] args) {
        Adult a1 = new Adult("name", "1232/02/22", "987-65-4320", 0.00f, "GMU");
        Adult a2 = new Adult("name", "1332/02/22", "987-65-4321", 1234.56f, "GMU");
        Adult a3 = new Adult("name", "1432/02/22", "987-65-4322", 13456.78f, "GMU");
        Adult a4 = new Adult("name", "1572/02/22", "987-65-4323", 23979.54f, "GMU");
        Adult a5 = new Adult("name", "1632/02/22", "987-65-4324", 67890.12f, "GMU");
        Adult a6 = new Adult("name", "1732/02/22", "987-65-4325", 123456.78f, "GMU");
        Adult a7 = new Adult("name", "1876/05/01", "789-56-1236", 145000.98f, "Mason");
        Adult a8 = new Adult("name", "1932/02/22", "987-65-4327", 267890.12f, "GMU");
        Adult a9 = new Adult("name", "2032/02/22", "987-65-4328", 312346.78f, "GMU");
        Child c1 = new Child("kid", "1200/01/01", "999-65-1110", 0.0f, "FHS", 3300.0f);
        Child c2 = new Child("kid", "1300/01/01", "999-65-1111", 100.0f, "FHS", 0.0f);
        Child c3 = new Child("kid", "1400/01/01", "999-65-1112", 300.0f, "FHS", 0.0f);
        Child c4 = new Child("kid", "1500/01/01", "999-65-1113", 900.0f, "FHS", 900.0f);
        Child c5 = new Child("kid", "1600/01/01", "999-65-1114", 1600.0f, "FHS", 1234.0f);
        Child c6 = new Child("kid", "1700/01/01", "999-65-1115", 7300.0f, "FHS", 6650.0f);
        Child c7 = new Child("kid", "1800/01/01", "999-65-1116", 12000.0f, "FHS", 11999.0f);
        Child c8 = new Child("kid", "1900/01/01", "999-65-1117", 27000.0f, "FHS", 100.0f);
        Child c9 = new Child("kid", "2000/01/01", "999-65-1118", 41560.0f, "FHS", 8765.0f);
        Family f1 = new Family((byte)2,(byte) 3);
        f1.addMember(a1);
        f1.addMember(c1);
        f1.calculateTax();
        Family f2 = new Family((byte)4, (byte)2);
        f2.addMember(a2);
        f2.addMember(a3);
        f2.addMember(c2);
        f2.addMember(c3);
        f2.calculateTax();
        Family f3 = new Family((byte)3, (byte)2);
        f3.addMember(a4);
        f3.addMember(a5);
        f3.addMember(c4);
        f3.calculateTax();
        Family f4 = new Family((byte)6, (byte) 2);
        f4.addMember(a6);
        f4.addMember(a7);
        f4.addMember(c5);
        f4.addMember(c6);
        f4.addMember(c7);
        f4.addMember(c8);
        f4.getNumChildren();
        Family f5 = new Family((byte)2, (byte)2);
        f5.addMember(a8);
        f5.addMember(a9);
        f5.calculateTax();
        Family f6 = new Family((byte)1, (byte)1);
        f6.addMember(c9);
        //TaxYear y = new TaxYear(100);

        /*
        System.out.println(y.taxFiling(f1)); // returns true
        System.out.println(y.taxFiling(f2)); // returns true
        System.out.println(y.taxFiling(f3)); // returns true
        System.out.println(y.taxFiling(f4)); // returns true
        System.out.println(y.taxFiling(f5)); // returns true
        System.out.println(y.taxFiling(f6)); // returns false
        System.out.println(y.numberOfReturnsFiled()); // returns 5
        System.out.println(y.numberOfPersonsFiled()); // returns 17
        System.out.println(y.taxWithheld()); // returns 142866.65
        System.out.println(y.taxOwed()); // returns 216255.72
        System.out.println(y.taxDue()); // returns 73119.06
        System.out.println(y.taxCredits()); // returns 270.0
        */
        
        Adult a10 = new Adult("name", "1232/02/22", "987-65-4320", 53228.73f, "GMU");
        Adult a11 = new Adult("name", "1332/02/22", "987-65-4321", 8721.52f, "GMU");
        Adult a12 = new Adult("name", "1432/02/22", "987-65-4322", 136268.89f, "GMU");
        Adult a13 = new Adult("name", "1572/02/22", "987-65-4323", 23979.54f, "GMU"); // invalid
        Adult a14 = new Adult("name", "1632/02/22", "957-65-4324", 67890.12f, "GMU"); // invalid
        Adult a15 = new Adult("name", "1732/02/22", "987-65-4325", 77777.77f, "GMU"); //invalid
        Adult a16 = new Adult("name", "1876/05/01", "789-56-1236", 150000.50f, "Mason"); //invalid
        Adult a17 = new Adult("name", "1932/02/22", "987-65-4327", 267890.12f, "GMU");
        Adult a18 = new Adult("name", "2032/02/22", "987-65-4328", 8697.021f, "GMU"); //invalid
        Child c10 = new Child("name", "1200/01/01", "999-65-1110", 848.71f, "FHS", 3300.0f);
        Child c11 = new Child("name", "1300/01/01", "999-65-1111", 9999.0f, "FHS", 3787.98f);
        Child c12 = new Child("name", "1400/01/01", "999-65-1112", 100000.00f, "FHS", 78900.00f);
        Child c13 = new Child("name", "1500/01/01", "999-65-1113", 876.0f, "FHS", 9887.04f);
        Child c14 = new Child("name", "1600/01/01", "999-65-1114", 1600.0f, "FHS", 90321.25f);
        Child c15 = new Child("name ", "1700/01/01", "999-65-1115", 5.0f, "FHS", 6650.0f); // invalid
        Child c16 = new Child("name", "1800/01/01", "999-65-1116", 15000.0f, "FHS", 11999.0f); //invalid
        Child c17 = new Child("name", "1900/01/01", "999-65-1117", 270000.0f, "FHS", 100000.07f); //invalid
        Child c18 = new Child("name", "2000/01/01", "999-65-1118", 41560.0f, "FHS", 8765.0f);

        Family f7 = new Family((byte) 3, (byte) 3);
        f7.addMember(a10);
        f7.addMember(c10);
        f7.addMember(c11);
        Family f8 = new Family((byte) 2, (byte) 1);
        f8.addMember(a11);
        f8.addMember(c12);
        Family f9 = new Family((byte) 3, (byte) 1);
        f9.addMember(a13);
        f9.addMember(c13);
        f9.addMember(c14);
        Family f10 = new Family((byte) 3, (byte) 3);
        f10.addMember(a12);
        f10.addMember(a14);
        f10.addMember(c15);
        Family f11 = new Family((byte) 2, (byte) 2);
        f11.addMember(a15);
        f11.addMember(c16);
        Family f12 = new Family((byte) 1, (byte) 2);
        f12.addMember(a16);
        Family f13 = new Family((byte) 2, (byte) 3);
        f13.addMember(a17);
        f13.addMember(c18);
        Family f14 = new Family((byte) 2, (byte) 1);
        f14.addMember(a18);
        f14.addMember(c17);

        System.out.println("\n--------------\nTAX FILING\n");

        TaxYear newYear = new TaxYear(50);
        System.out.println("f1 expected [ true ] returned -> [ " + newYear.taxFiling(f1) + " ]\n");
        System.out.println("f2 expected [ true returned -> [ " + newYear.taxFiling(f2) + " ]\n");
        System.out.println("f3 expected [ true ] returned -> [ " + newYear.taxFiling(f3) + " ]\n");
        System.out.println("f4 expected [ true ] returned -> [ " + newYear.taxFiling(f4) + " ]\n");
        System.out.println("f5 expected [ true ] returned -> [ " + newYear.taxFiling(f5) + " ]\n");
        System.out.println("f6 expected [ false ] returned -> [ " + newYear.taxFiling(f6) + " ]\n");
        
        System.out.println("f7 expected [ true ] returned -> [ " + newYear.taxFiling(f7) + " ]\n");
        System.out.println("f8 expected [ true ] returned -> [ " + newYear.taxFiling(f8) + " ]\n");
        System.out.println("f9 expected [ true ] returned -> [ " + newYear.taxFiling(f9) + " ]\n");
        System.out.println("f10 expected [ false ] returned -> [ " + newYear.taxFiling(f10) + " ]\n");
        System.out.println("f11 expected [ false ] returned -> [ " + newYear.taxFiling(f11) + " ]\n");
        System.out.println("f12 expected [ false ] returned -> [ " + newYear.taxFiling(f12) + " ]\n");
        System.out.println("f13 expected [ true ] returned -> [ " + newYear.taxFiling(f13) + " ]\n");
        System.out.println("f14 expected [ true ] returned -> [ " + newYear.taxFiling(f14) + " ]\n");
        

        
        System.out.println("\n--------------\nRETURNS FILED\n");
        System.out.println("Number of returns filed expected [ 10 ] returned -> [ " + newYear.numberOfReturnsFiled() + " ]\n");
        System.out.println("Number of returns filed expected [ 29 ] returned -> [ " + newYear.numberOfPersonsFiled() + " ]\n");
        

        
        System.out.println("\n--------------\nTAXABLE INCOME\n");
        System.out.println("f1 taxable income expected [ 0.0 ] returned -> [ " + f1.getTaxableIncome() + " ]\n");
        System.out.println("f2 taxable income expected [ 9427.337 ] returned -> [ " + f2.getTaxableIncome() + " ]\n");
        System.out.println("f3 taxable income expected [ 78841.625 ] returned -> [ " + f3.getTaxableIncome() + " ]\n");
        System.out.println("f4 taxable income expected [ 283993.4 ] returned -> [ " + f4.getTaxableIncome() + " ]\n");
        System.out.println("f5 taxable income expected [ 550548.6 ] returned -> [ " + f5.getTaxableIncome() + " ]\n");
        System.out.println("\nNEW PARAMETERS LOADED\n\n");
        Taxation.loadParameters("loadParameters1.txt");
        System.out.println("f7 taxable income expected [ 50767.863 ] returned -> [ " + f7.getTaxableIncome() + " ]\n");
        System.out.println("f8 taxable income expected [ 96000.0 ] returned -> [ " + f8.getTaxableIncome() + " ]\n");
        System.out.println("f9 taxable income expected [ 12420.869 ] returned -> [ " + f9.getTaxableIncome() + " ]\n");
        System.out.println("f13 taxable income expected [ 287931.78 ] returned -> [ " + f13.getTaxableIncome() + " ]\n");
        System.out.println("f14 taxable income expected [ 266000.0 ] returned -> [ " + f14.getTaxableIncome() + " ]\n");
        

        System.out.println("\n--------------\nTAX CREDIT\n");
        System.out.println("\nORIGINAL PARAMETERS LOADED\n\n");
        Taxation.loadParameters("loadParameters2.txt");
        System.out.println("f1 tax credit expected [ 0.0 ] returned -> [ " + f1.taxCredit() + " ]\n");
        System.out.println("f2 tax credit expected [ 270.0 ] returned -> [ " + f2.taxCredit() + " ]\n");
        System.out.println("f3 tax credit expected [ 0.0 ] returned -> [ " + f3.taxCredit() + " ]\n");
        System.out.println("f4 tax credit expected [ 0.0 ] returned -> [ " + f4.taxCredit() + " ]\n");
        System.out.println("f5 tax credit expected [ 0.0 ] returned -> [ " + f5.taxCredit() + " ]\n");
        System.out.println("\nNEW PARAMETERS LOADED\n\n");
        Taxation.loadParameters("loadParameters1.txt");
        System.out.println("f7 tax credit expected [ 0.0 ] returned -> [ " + f7.taxCredit() + " ]\n");
        System.out.println("f8 tax credit expected [ 0.0 ] returned -> [ " + f8.taxCredit() + " ]\n");
        System.out.println("f9 tax credit expected [ 1290.5043 ] returned -> [ " + f9.taxCredit() + " ]\n");
        System.out.println("f13 tax credit expected [ 0.0 ] returned -> [ " + f13.taxCredit() + " ]\n");
        System.out.println("f14 tax credit expected [ 0.0 ] returned -> [ " + f14.taxCredit() + " ]\n");

        System.out.println(c18.getId());


    }    
}
