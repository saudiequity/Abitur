import java.util.*;

public class pvBetweenCF {
    // The name of pvBetweenCF means that if we investment money in a
    // sequence of cash flows in between the period of two cash flows,
    // then the discounting for the first CF we get will be only simple
    // interest discounting based on exactly how many days we have from
    // the starting point of investment to the time of first cash flow.

    public static double pvBetweenCF365(double days, double n, double cashFlow, double interest){
        // The formula of such discounting method can be summarized as:
        // PV = CF / (1 + i/365)^d * (1/(i + i/365)^(n*365) - 1) / (1/(i + i/365)^(365) - 1)
        // Assuming that we are using the 365-day rule.
        // d, is the number of days remaining to the first CF from investment date
        // CF, is the amount of cash flow for each period, CF are all equivalent
        // i, is the annual interest
        // i/365, is the daily interest converted from annual interest i
        if(interest == 0){
            throw new IllegalArgumentException("Interest rate cannot be 0");
        }else{
            double firstPart = cashFlow / Math.pow((1 + interest/365), days);
            double secondPart = 1 / Math.pow(1 + interest/365, n * 365) - 1;
            double thirdPart = 1 / Math.pow(1 + interest/365, 365) - 1;

            return firstPart*secondPart/thirdPart;
        }
    }

    public static double pvPerpetuityBetweenCF(double days, double cashFlow, double interest) {
        // The PV formula of investment between cash flows for a perpetuity is:
        // CF * (1 + i/365)^(365*d) / ((1 + i/365)^365 - 1)
        if(interest == 0){
            throw new IllegalArgumentException("Interest rate cannot be 0");
        }else{
            double firstPart = Math.pow(1 + interest / 365, 365-days);
            double secondPart = Math.pow(1 + interest / 365, 365) - 1;

            return cashFlow * (firstPart / secondPart);
        }
    }

    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        System.out.print("Please enter the number of days between the two cash flows: ");
        double days = in.nextDouble();
        System.out.print("Please enter the number of years: ");
        double n = in.nextDouble();
        System.out.print("Please enter the amount of cash flows: ");
        double cashFlow = in.nextDouble();
        System.out.print("Please enter the amount of interest: ");
        double interest = in.nextDouble();

        double result = pvBetweenCF365(days, n, cashFlow, interest);
        System.out.println("The present value of cash flow " + cashFlow + ", interest "
                + interest + ", and ");
        System.out.println(n + " years period, with " + days + " before the first Cash Flow is: ");
        System.out.printf("PV CF In Between 365 Rule:  %.2f", result);
        System.out.println();

        double result2 = pvPerpetuityBetweenCF(days, cashFlow, interest);
        System.out.println("The present value of a perpetuity with cash flow " + cashFlow + ", interest "
                + interest + ", and invested ");
        System.out.println(days + " before the first Cash Flow is: ");
        System.out.printf("PV CF In Between 365 Rule:  %.2f", result2);
        System.out.println();
    }
}
