package com.example.guessthenumber;

public class Player {

    private String _name;
    private int _intents;


    public Player(){}

    public Player(String _name, int _intents){

        this._intents=_intents;
        this._name=_name;

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
}
