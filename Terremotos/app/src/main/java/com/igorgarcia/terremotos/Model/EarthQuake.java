package com.igorgarcia.terremotos.Model;

import java.util.Date;

/**
 * Created by cursomovil on 25/03/15.
 */
public class EarthQuake {


    private String _id;
    private String place;
    private Coordinate coords;
    private double magnitud;
    private Date time;
    private String url;



    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public EarthQuake(){

    }

    public EarthQuake(Date time, Coordinate coords, double magnitud, String _id, String place) {
        this.time = time;
        this.coords = coords;
        this.magnitud = magnitud;
        this._id = _id;
        this.place = place;
    }

    @Override
    public String toString() {
        return this.place;
    }

    public Date getTime() {

        return time;
    }

    public void setTime(Long time) {
        this.time = new Date (time);

    }

    public Coordinate getCoords() {
        return coords;
    }

    public void setCoords(Coordinate coords) {
        this.coords = coords;
    }

    public double getMagnitud() {
        return magnitud;
    }

    public void setMagnitud(double magnitud) {
        this.magnitud = magnitud;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }
}
