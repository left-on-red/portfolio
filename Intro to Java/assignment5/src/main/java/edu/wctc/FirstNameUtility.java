package edu.wctc;

import java.util.HashMap;
import java.util.Map;

public class FirstNameUtility {
    private Map<String, Integer> firstNameCodeMap = new HashMap<String, Integer>();
    private Map<String, Integer> firstInitialCodeMap = new HashMap<String, Integer>();
    private Map<String, Integer> middleInitialCodeMap = new HashMap<String, Integer>();

    public FirstNameUtility() {
        firstNameCodeMap.put("ALBERT", 20);
        firstNameCodeMap.put("ALICE", 20);
        firstNameCodeMap.put("ANN", 40);
        firstNameCodeMap.put("ANNA", 40);
        firstNameCodeMap.put("ANNE", 40);
        firstNameCodeMap.put("ANNIE", 40);
        firstNameCodeMap.put("ARTHUR", 40);
        firstNameCodeMap.put("BERNARD", 80);
        firstNameCodeMap.put("BETTE", 80);
        firstNameCodeMap.put("BETTIE", 80);
        firstNameCodeMap.put("BETTY", 80);
        firstNameCodeMap.put("CARL", 120);
        firstNameCodeMap.put("CATHERINE", 120);
        firstNameCodeMap.put("CHARLES", 140);
        firstNameCodeMap.put("CLARA", 140);
        firstNameCodeMap.put("DONALD", 180);
        firstNameCodeMap.put("DOROTHY", 180);
        firstNameCodeMap.put("EDWARD", 220);
        firstNameCodeMap.put("ELIZABETH", 220);
        firstNameCodeMap.put("FLORENCE", 260);
        firstNameCodeMap.put("FRANK", 260);
        firstNameCodeMap.put("GEORGE", 300);
        firstNameCodeMap.put("GRACE", 300);
        firstNameCodeMap.put("HAROLD", 340);
        firstNameCodeMap.put("HARRIET", 340);
        firstNameCodeMap.put("HARRY", 360);
        firstNameCodeMap.put("HAZEL", 360);
        firstNameCodeMap.put("HELEN", 380);
        firstNameCodeMap.put("HENRY", 380);
        firstNameCodeMap.put("JAMES", 440);
        firstNameCodeMap.put("JANE", 440);
        firstNameCodeMap.put("JAYNE", 440);
        firstNameCodeMap.put("JEAN", 460);
        firstNameCodeMap.put("JOAN", 480);
        firstNameCodeMap.put("JOHN", 460);
        firstNameCodeMap.put("JOSEPH", 480);
        firstNameCodeMap.put("MARGARET", 560);
        firstNameCodeMap.put("MARTIN", 560);
        firstNameCodeMap.put("MARVIN", 580);
        firstNameCodeMap.put("MARY", 580);
        firstNameCodeMap.put("MELVIN", 600);
        firstNameCodeMap.put("MILDRED", 600);
        firstNameCodeMap.put("PATRICIA", 680);
        firstNameCodeMap.put("PAUL", 680);
        firstNameCodeMap.put("RICHARD", 740);
        firstNameCodeMap.put("ROBERT", 760);
        firstNameCodeMap.put("RUBY", 740);
        firstNameCodeMap.put("RUTH", 760);
        firstNameCodeMap.put("THELMA", 820);
        firstNameCodeMap.put("THOMAS", 820);
        firstNameCodeMap.put("WALTER", 900);
        firstNameCodeMap.put("WANDA", 900);
        firstNameCodeMap.put("WILLIAM", 920);
        firstNameCodeMap.put("WILMA", 920);

        firstInitialCodeMap.put("A", 0);
        firstInitialCodeMap.put("B", 60);
        firstInitialCodeMap.put("C", 100);
        firstInitialCodeMap.put("D", 160);
        firstInitialCodeMap.put("E", 200);
        firstInitialCodeMap.put("F", 240);
        firstInitialCodeMap.put("G", 280);
        firstInitialCodeMap.put("H", 320);
        firstInitialCodeMap.put("I", 400);
        firstInitialCodeMap.put("J", 420);
        firstInitialCodeMap.put("K", 500);
        firstInitialCodeMap.put("L", 520);
        firstInitialCodeMap.put("M", 540);
        firstInitialCodeMap.put("N", 620);
        firstInitialCodeMap.put("O", 640);
        firstInitialCodeMap.put("P", 660);
        firstInitialCodeMap.put("Q", 700);
        firstInitialCodeMap.put("R", 720);
        firstInitialCodeMap.put("S", 780);
        firstInitialCodeMap.put("T", 800);
        firstInitialCodeMap.put("U", 840);
        firstInitialCodeMap.put("V", 860);
        firstInitialCodeMap.put("W", 880);
        firstInitialCodeMap.put("X", 940);
        firstInitialCodeMap.put("Y", 960);
        firstInitialCodeMap.put("Z", 980);

        middleInitialCodeMap.put("A", 1);
        middleInitialCodeMap.put("B", 2);
        middleInitialCodeMap.put("C", 3);
        middleInitialCodeMap.put("D", 4);
        middleInitialCodeMap.put("E", 5);
        middleInitialCodeMap.put("F", 6);
        middleInitialCodeMap.put("G", 7);
        middleInitialCodeMap.put("H", 8);
        middleInitialCodeMap.put("I", 9);
        middleInitialCodeMap.put("J", 10);
        middleInitialCodeMap.put("K", 11);
        middleInitialCodeMap.put("L", 12);
        middleInitialCodeMap.put("M", 13);
        middleInitialCodeMap.put("N", 14);
        middleInitialCodeMap.put("O", 14);
        middleInitialCodeMap.put("P", 15);
        middleInitialCodeMap.put("Q", 15);
        middleInitialCodeMap.put("R", 16);
        middleInitialCodeMap.put("S", 17);
        middleInitialCodeMap.put("T", 18);
        middleInitialCodeMap.put("U", 18);
        middleInitialCodeMap.put("V", 18);
        middleInitialCodeMap.put("W", 19);
        middleInitialCodeMap.put("X", 19);
        middleInitialCodeMap.put("Y", 19);
        middleInitialCodeMap.put("Z", 19);
    }

    public int encodeFirstName(String firstName, String middleInitial) throws MissingNameException {
        if (firstName.equals("")) { throw new MissingNameException("firstName"); }

        int sum = firstNameCodeMap.containsKey(firstName.toUpperCase()) ? firstNameCodeMap.get(firstName.toUpperCase()) : firstInitialCodeMap.get(firstName.substring(0, 1).toUpperCase());
        if (!middleInitial.equals("")) { sum += middleInitialCodeMap.get(middleInitial.toUpperCase()); }

        return sum;
    }
}
