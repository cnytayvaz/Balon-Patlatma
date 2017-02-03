package com.cnytayvaz.balonpatlatma;

import com.badlogic.gdx.Game;

public class BalonPatlatma extends Game {
	public static StateManager sm;

	@Override
	public void create() {
		sm=new StateManager();
		sm.pushState(new MenuState());
	}
	public void render(){
		sm.render();
	}
}
