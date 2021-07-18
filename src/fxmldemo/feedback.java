/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fxmldemo;

/**
 *
 * @author Ehsan Huq
 */
public class feedback {
    String userid,flightid,feedback;

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public void setFlightid(String flightid) {
        this.flightid = flightid;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public String getUserid() {
        return userid;
    }

    public String getFlightid() {
        return flightid;
    }

    public String getFeedback() {
        return feedback;
    }

    public feedback(String userid, String flightid, String feedback) {
        this.userid = userid;
        this.flightid = flightid;
        this.feedback = feedback;
    }
}
