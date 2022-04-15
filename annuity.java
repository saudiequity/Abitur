import java.util.*;

public class annuity {
    // This is the annuity class that contains various methods for
    // calculating annuity PV and FV for unit annuity, annuity, and
    // annuity with various payment/installment frequencies

    public static double unitAnnuityPV(double n, double interest){
        // This is the unit annuity PV, which is the annuity's PV in
        // one currency unit (e.g. USD)
        // The formula is: (1 - (1 + i )^(-n)) / i
        if(interest == 0){
            throw new IllegalArgumentException("Interest cannot be zero");
        }else{
            return (1 - Math.pow((1 + interest), -n)) / interest;
        }
    }

    public static double unitAnnuityFV(double n, double interest){
        // This is the unit annuity FV, which is the annuity's FV in
        // one currency unit (e.g. USD)
        // The formula is: ((1 + i )^(n) - 1) / i
        if(interest == 0){
            throw new IllegalArgumentException("Interest cannot be zero");
        }else{
            return (Math.pow((1 + interest), n) - 1) / interest;
        }
    }

    public static double unitAnnuityDuePV(double n, double interest){
        // This is the unit annuity due PV, which is the PV in one currency
        // unit (e.g. USD) of an annuity due. Annuity Due is the type of annuity
        // that receives payments at the beginning of each payment/installment
        // period
        // The formula is: (1 - (1 + i)^(-n)) / (1 - (1 + i)^(-1))

        if(interest == 0){
            throw new IllegalArgumentException("Interest cannot be zero");
        }else{
            return (1 - Math.pow(1+interest, -n))/(1 - Math.pow(1 + interest, -1));
        }
    }

    public static double unitAnnuityDueFV(double n, double interest){
        // This is the unit annuity due FV, which is the FV in one currency
        // unit (e.g. USD) of an annuity due. Annuity Due is the type of annuity
        // that receives payments at the beginning of each payment/installment
        // period
        // The formula is: ((1 + i)^(n) - 1) / (1 - (1 + i)^(-1))

        if(interest == 0){
            throw new IllegalArgumentException("Interest cannot be zero");
        }else{
            return (Math.pow(1+interest, n) - 1)/(1 - Math.pow(1 + interest, -1));
        }
    }

    public static double annuityPV(double cashFlow, double n, double interest){
        // This is the annuity PV
        // The formula is: CF*(1 - (1 + i )^(-n)) / i
        if(interest == 0){
            throw new IllegalArgumentException("Interest cannot be zero");
        }else{
            return cashFlow * unitAnnuityPV(n, interest);
        }
    }

    public static double annuityFV(double cashFlow, double n, double interest){
        // This is the annuity FV
        // The formula is: CF*((1 + i )^(n) - 1) / i
        if(interest == 0){
            throw new IllegalArgumentException("Interest cannot be zero");
        }else{
            return cashFlow * unitAnnuityFV(n, interest);
        }
    }

    public static double annuityDuePV(double cashFlow, double n, double interest){
        // This is the annuity due PV, annuity Due is the type of annuity
        // that receives payments at the beginning of each payment/installment
        // period
        // The formula is: CF * (1 - (1 + i)^(-n)) / (1 - (1 + i)^(-1))

        if(interest == 0){
            throw new IllegalArgumentException("Interest cannot be zero");
        }else{
            return cashFlow * unitAnnuityDuePV(n, interest);
        }
    }

    public static double annuityDueFV(double cashFlow, double n, double interest){
        // This is the unit annuity due FV, annuity Due is the type of annuity
        // that receives payments at the beginning of each payment/installment
        // period
        // The formula is: CF * ((1 + i)^(n) - 1) / (1 - (1 + i)^(-1))

        if(interest == 0){
            throw new IllegalArgumentException("Interest cannot be zero");
        }else{
            return cashFlow * unitAnnuityDueFV(n, interest);
        }
    }

    public static double annuityFreqPV(double cashFlow, double freq, double n, double interest){
        // This is the adjusted annuity PV that allows adjustment in payment frequency
        // ranging from 1 to 365. For payment frequency more than 365, we simply use the
        // continuous discounting/compounding model
        // For unit annuity with adjustment in payment frequency, just use CF = 1
        // Formula: The formula is: CF*(1 - (1 + i/freq )^(-n*freq)) / (i/freq)
        if(interest == 0){
            throw new IllegalArgumentException("Interest cannot be zero");
        }else{
            return annuityPV(cashFlow, n*freq, interest/freq);
        }
    }

    public static double annuityFreqFV(double cashFlow, double freq, double n, double interest){
        // This is the adjusted annuity FV that allows adjustment in payment frequency
        // ranging from 1 to 365. For payment frequency more than 365, we simply use the
        // continuous discounting/compounding model
        // For unit annuity with adjustment in payment frequency, just use CF = 1
        // Formula: The formula is: (CF*((1 + i/freq )^(n*freq) - 1) / (i/freq)
        if(interest == 0){
            throw new IllegalArgumentException("Interest cannot be zero");
        }else{
            return annuityFV(cashFlow, n*freq, interest/freq);
        }
    }

    public static double annuityDueFreqPV(double cashFlow, double freq, double n, double interest) {
        // This is the adjusted annuity due PV that allows adjustment in payment frequency
        // ranging from 1 to 365. For payment frequency more than 365, we simply use the
        // continuous discounting/compounding model
        // For unit annuity with adjustment in payment frequency, just use CF = 1
        // The formula is: CF * (1 - (1 + i/freq)^(-n*freq)) / (1 - (1 + i/freq)^(-1))
        if (interest == 0) {
            throw new IllegalArgumentException("Interest cannot be zero");
        }else {
            return annuityDuePV(cashFlow, n*freq, interest / freq);
        }
    }

    public static double annuityDueFreqFV(double cashFlow, double freq, double n, double interest){
        // This is the adjusted annuity due FV that allows adjustment in payment frequency
        // ranging from 1 to 365. For payment frequency more than 365, we simply use the
        // continuous discounting/compounding model
        // For unit annuity with adjustment in payment frequency, just use CF = 1
        // The formula is: CF * ((1 + i/freq)^(n*freq) - 1) / (1 - (1 + i/freq)^(-1))
        if (interest == 0) {
            throw new IllegalArgumentException("Interest cannot be zero");
        }else {
            return annuityDueFV(cashFlow, n*freq, interest / freq);
        }
    }

    public static void main(String[] args){
        // This is the client main class that tests the methods in annuity class
        Scanner in = new Scanner(System.in);
        System.out.print("Please enter the annuity's amount of cash flows: ");
        double cf = in.nextDouble();
        System.out.println();
        System.out.print("Please enter the annuity's payment frequency: ");
        double frequency = in.nextDouble();
        System.out.println();
        System.out.print("Please enter the number of years to maturity: ");
        double year = in.nextDouble();
        System.out.println();
        System.out.print("Please enter the interest rate: ");
        double i = in.nextDouble();

        System.out.print("The unit annuity PV is: ");
        System.out.printf("%10.4f", unitAnnuityPV(year, i));
        System.out.println();

        System.out.print("The annuity PV is: ");
        System.out.printf("%10.4f", annuityPV(cf, year, i));
        System.out.println();

        System.out.print("The annuity PV  with frequency: " + frequency +" is: ");
        System.out.printf("%10.4f", annuityFreqPV(cf, frequency, year, i));
        System.out.println();

        System.out.print("The unit annuity due PV is: ");
        System.out.printf("%10.4f", unitAnnuityDuePV(year, i));
        System.out.println();

        System.out.print("The annuity due PV is: ");
        System.out.printf("%10.4f", annuityDuePV(cf, year, i));
        System.out.println();

        System.out.print("The annuity PV Due with frequency: " + frequency +" is: ");
        System.out.printf("%10.4f", annuityDueFreqPV(cf, frequency, year, i));
        System.out.println();
    }
}
