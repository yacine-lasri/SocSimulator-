package soc.ui;

import soc.model.Analyst;
import soc.model.AnalystLevel;

public class TestAnalyst {

    public static void main(String[] args) {

        Analyst analyst = new Analyst(
                1, "Saad", AnalystLevel.N1
        );

        System.out.println("ID : " + analyst.getId());
        System.out.println("Name : " + analyst.getName());
        System.out.println("Level : " + analyst.getLevel());
    }
}