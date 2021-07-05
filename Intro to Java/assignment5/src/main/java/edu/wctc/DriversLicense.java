package edu.wctc;

public class DriversLicense
{
    private String soundexCode;
    private int firstNameMiddleInitial;
    private int birthYear;
    private int birthMonthDayGender;
    private int overflow;

    public String getSoundexCode() { return soundexCode; }
    public int getFirstNameMiddleInitial() { return firstNameMiddleInitial; }
    public int getBirthYear() { return birthYear; }
    public int getBirthMonthDayGender() { return birthMonthDayGender; }
    public int getOverflow() { return overflow; }

    public void setSoundexCode(String soundexCode) { this.soundexCode = soundexCode; }
    public void setFirstNameMiddleInitial(int firstNameMiddleInitial) { this.firstNameMiddleInitial = firstNameMiddleInitial; }
    public void setBirthYear(int birthYear) { this.birthYear = birthYear; }
    public void setBirthMonthDayGender(int birthMonthDayGender) { this.birthMonthDayGender = birthMonthDayGender; }
    public void setOverflow(int overflow) { this.overflow = overflow; }
}
