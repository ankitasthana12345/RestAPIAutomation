package com.restapibasis.framework.api.Rough;

import java.util.*;

public class TestScenario {

    public static void main(String[] args) {

        String [] arrayState ={"UP","Bihar","MP","UP","AP","AP", "Rajasthan","Maharastra","Delhi"};
        Set<String> set = new TreeSet<String>(Arrays.asList(arrayState));
        System.out.println(set);

    }
}
