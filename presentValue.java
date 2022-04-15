import java.util.*;

public class presentValue {
    // This is the class that calculates various present values
    // for given cash flows
    
    public static double perpetuityPV(double cashFlow, double interest){
        // The PV of a perpetuity, such as the UK Consols, is given by the
        // formula: PV = CF/i, given i != 0
        // Since this formula is a lot easier compared with the others, I will
        // not illustrate it with a test example below.
        
        if(interest == 0){
            throw new IllegalArgumentException("The interest rate cannot be 0");
        }else{
            return cashFlow/interest;
        }
    }
    
    public static double annualPV(double n, double cashFlow, double interest){
        if(interest == 0){
            throw new IllegalArgumentException("The interest rate cannot be 0 because it is the denominator");
        }else{
            return cashFlow*(1 - Math.pow(1+interest, -n)) / interest;
        }
    }

    public static double dailyBasisPV(double n, double cashFlow, double interest){
        // PV with equivalent CF discounted on a daily basis has the following
        // formula:
        // PV = CF*((1+i/365)^(-365) - (i+i/365)^(-365*(n+1))) / ((1 - (1+i/365)^(-365))

        if(interest == 0){
            throw new IllegalArgumentException("The interest rate cannot be 0 because it is the denominator");
        }else{
            double iFactor = 1 + interest/365;
            double iBasePart = Math.pow(iFactor, -365);
            double iBasePartN = Math.pow(iBasePart, n+1);

            double upperPart = cashFlow*(iBasePart - iBasePartN);
            double lowerPart = 1 - iBasePart;
            return upperPart/lowerPart;
        }
    }
    public static double daily360PV(double n, double cashFlow, double interest){
        // PV with equivalent CF discounted on a daily basis with 360 days has the following
        // formula:
        // PV = CF*((1+i/360)^(-360) - (i+i/360)^(-360*(n+1))) / ((1 - (1+i/360)^(-360))

        if(interest == 0){
            throw new IllegalArgumentException("The interest rate cannot be 0 because it is the denominator");
        }else{
            double iFactor = 1 + interest/360;
            double iBasePart = Math.pow(iFactor, -360);
            double iBasePartN = Math.pow(iBasePart, n+1);

            double upperPart = cashFlow*(iBasePart - iBasePartN);
            double lowerPart = 1 - iBasePart;
            return upperPart/lowerPart;
        }
    }

    public static double annualConPV(double n, double cashFlow, double interest){
        // With equivalent annual CF for each year, the PV of continuous compounding
        // using natural e is obtained using the following formula:
        // PV = CF*(e^(-i)-e^(-(n+1)i)) / (1-e^(-i))

        if(interest == 0){
            throw new IllegalArgumentException("Interest rate/Denominator cannot be 0");
        }else{
            return (cashFlow*(Math.exp(-interest)-Math.exp(-(n+1)*interest)))/(1-Math.exp(-interest));
        }
    }

    public static void main(String[] args){
        double n;
        double cashFlow;
        double interest;

        Scanner in = new Scanner(System.in);
        System.out.print("Please enter the number of years: ");
        n = in.nextDouble();
        System.out.print("Please enter the amount of cash flows: ");
        cashFlow = in.nextDouble();
        System.out.print("Please enter the amount of interest: ");
        interest = in.nextDouble();

        double result = annualPV(n, cashFlow, interest);
        System.out.print("The present value of cash flow " + cashFlow + ", interest "
        + interest + ", and " + n + " years period is: ");
        System.out.printf("%.2f", result);
        System.out.println();

        System.out.println("Example: CF: 2000, Interest: 0.06, Periods: 10");
        System.out.printf("Expected: %5.2f", 14720.17);
        System.out.println();
        System.out.printf("Actual:   %7.2f", annualPV(10.0,2000.0,0.06));
        System.out.println();

        double resultDaily = dailyBasisPV(n, cashFlow, interest);
        System.out.println();
        System.out.println("The present value of cash flow " + cashFlow + ", interest "
                + interest + ", and " + n + " years period discounted on a daily basis is: ");
        System.out.printf("Actual:PV365  %.4f", resultDaily);
        System.out.println();

        double resultDaily360 = daily360PV(n, cashFlow, interest);
        System.out.println();
        System.out.println("The present value of cash flow " + cashFlow + ", interest "
                + interest + ", and " + n + " years period discounted on a 360 day basis is: ");
        System.out.printf("Actual:PV360  %.4f", resultDaily360);
        System.out.println();

        double resultContinuous = annualConPV(n, cashFlow, interest);
        System.out.println();
        System.out.println("The present value of cash flow " + cashFlow + ", interest "
                + interest + ", and " + n + " years period discounted on a continuous basis is: ");
        System.out.printf("Actual:PVContinuous  %.4f", resultContinuous);
        System.out.println();
    }
}
