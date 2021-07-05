package edu.wctc;

import java.time.DateTimeException;
import java.time.LocalDate;

public class MonthDayGenderUtility {
    private final int MOD_MALE = 0;
    private final int MOD_FEMALE = 500;
    private final char CODE_MALE = 'M';
    private final char CODE_FEMALE = 'F';

    public int encodeMonthDayGender(int year, int month, int day, char genderCode) throws UnknownGenderCodeException, InvalidBirthdayException {
        if (genderCode != CODE_MALE && genderCode != CODE_FEMALE) { throw new UnknownGenderCodeException(genderCode); }

        try { LocalDate.of(year, month, day); }
        catch(DateTimeException e) { throw new InvalidBirthdayException(year, month, day); }

        return (month - 1) * 40 + day + genderCode == CODE_MALE ? MOD_MALE : MOD_FEMALE;
    }
}
