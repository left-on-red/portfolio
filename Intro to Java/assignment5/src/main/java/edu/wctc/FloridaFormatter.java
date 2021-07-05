package edu.wctc;

public class FloridaFormatter implements DriversLicenseFormatter {

    @Override
    public String formatLicenseNumber(DriversLicense driversLicense) {
        String SSSS = driversLicense.getSoundexCode();
        String FFF = Integer.toString(driversLicense.getFirstNameMiddleInitial());
        String YY = Integer.toString(driversLicense.getBirthYear());
        String DDD = Integer.toString(driversLicense.getBirthMonthDayGender());
        String NN = Integer.toString(driversLicense.getOverflow());

        String seg1 = SSSS;
        String seg2 = FFF;
        String seg3 = YY.length() == 2 ? YY : YY.substring(2);
        String seg4 = Character.toString(NN.charAt(0));

        return String.format("%s-%s-%s-%s", seg1, seg2, seg3, seg4);
    }
}
