package edu.wctc;

public class WisconsinFormatter implements DriversLicenseFormatter {
    @Override
    public String formatLicenseNumber(DriversLicense driversLicense) {
        String SSSS = driversLicense.getSoundexCode();
        String FFF = Integer.toString(driversLicense.getFirstNameMiddleInitial());
        String YY = Integer.toString(driversLicense.getBirthYear());
        String DDD = Integer.toString(driversLicense.getBirthMonthDayGender());
        String NN = Integer.toString(driversLicense.getOverflow());

        String y = YY.length() == 2 ? YY : YY.substring(2);

        String seg1 = SSSS;
        String seg2 = FFF + y.charAt(0);
        String seg3 = y.charAt(1) + DDD;
        String seg4 = NN;

        return String.format("%s-%s-%s-%s", seg1, seg2, seg3, seg4);
    }
}