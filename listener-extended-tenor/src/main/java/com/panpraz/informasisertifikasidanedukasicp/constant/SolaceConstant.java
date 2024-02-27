package com.panpraz.informasisertifikasidanedukasicp.constant;

public class SolaceConstant {
    public static final String THREAD_COUNT = "1";
    public static final String QUEUE_CPR_ENDING = "Q.PANPRAZ.CUSTOMERJOURNEY.INSURANCE.CPR.ENDING";
    public static final String QUEUE_EMAIL_EXTENDED = "Q.PANPRAZ.EXTENDEDTENOR.EMAIL";
    public static final String QUEUE_NONCPR_OFFERING_POSTREALIZATION = "Q.PANPRAZ.CUSTOMERJOURNEY.INSURANCE.CPR.OFFERING.NONCPR.POSTREALIZATION";
    public static final String QUEUE_CPR_REMINDER_POSTREALIZATION = "Q.PANPRAZ.CUSTOMERJOURNEY.INSURANCE.CPR.REMINDER.POSTREALIZATION";
    private SolaceConstant(){
        throw new IllegalStateException("Constant class");
    }
}
