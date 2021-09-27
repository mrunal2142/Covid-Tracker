package com.md.covidtracker.models;

import java.util.ArrayList;

public class vaccineModel2 {

    public ArrayList<vaccineModel> getSessions() {
        return sessions;
    }

    public void setSessions(ArrayList<vaccineModel> sessions) {
        this.sessions = sessions;
    }

    public vaccineModel2(ArrayList<vaccineModel> list) {
        this.sessions = list;
    }

    ArrayList<vaccineModel> sessions;
}
