package com.example.powerproject;

import java.util.HashMap;

public class Parametro {
    HashMap<String, HashMap<String,Integer>> propierties;

    public HashMap<String, HashMap<String, Integer>> getPropierties() {
        return propierties;
    }

    public void setPropierties(HashMap<String, HashMap<String, Integer>> propierties) {
        this.propierties = propierties;
    }
}
