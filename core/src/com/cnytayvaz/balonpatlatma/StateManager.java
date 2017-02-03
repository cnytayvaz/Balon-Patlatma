package com.cnytayvaz.balonpatlatma;

import java.util.Stack;

/**
 * Created by Cuneyt on 27.4.2016.
 */
public class StateManager {
    private Stack<State> states;
    public StateManager(){
        states=new Stack<State>();
    }
    public void render(){
        states.peek().render();
    }
    public void pushState(State state){
        states.push(state);
    }
    public void popState(){
        states.pop();
    }
}
