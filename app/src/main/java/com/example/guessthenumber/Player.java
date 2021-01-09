package com.example.guessthenumber;

import android.net.Uri;

public class Player {

    private String _name;
    private int _intents;
    private String _imageRoute;


    public Player(){}

    public Player(String _name, int _intents, String imageRoute){

        this._intents=_intents;
        this._name=_name;
        this._imageRoute=imageRoute;

    }

    public String get_name() {
        return _name;
    }

    public void set_name(String _name) {
        this._name = _name;
    }

    public int get_intents() {
        return _intents;
    }

    public void set_intents(int _intents) {
        this._intents = _intents;
    }

    public String get_imageRoute() {
        return _imageRoute;
    }

    public void set_imageRoute(String _imageRoute) {
        this._imageRoute = _imageRoute;
    }
}
