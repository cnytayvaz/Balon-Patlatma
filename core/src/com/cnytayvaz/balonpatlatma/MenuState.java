package com.cnytayvaz.balonpatlatma;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by Cuneyt on 27.4.2016.
 */
public class MenuState extends State {

    public static boolean vol;
    private static Sound bom;
    private static Texture play,about,quit,volumeon,volumeoff;
    private static TextureRegion playReg,aboutReg,quitReg,volumeonReg,volumeoffReg;

    private static SpriteBatch sb;
    private static OrthographicCamera camera;
    AssetManager manager;

    public MenuState(){
        manager=new AssetManager();
        manager.load("bom.wav",Sound.class);
        manager.load("play.png",Texture.class);
        manager.load("about.png",Texture.class);
        manager.load("quit.png",Texture.class);
        manager.load("volumeon.png",Texture.class);
        manager.load("volumeoff.png",Texture.class);
        manager.finishLoading();
        vol=true;
        bom=manager.get("bom.wav",Sound.class);
        play=manager.get("play.png",Texture.class);
        playReg=new TextureRegion(play,play.getWidth(),play.getHeight());
        playReg.flip(false,true);
        about=manager.get("about.png",Texture.class);
        aboutReg=new TextureRegion(about,about.getWidth(),about.getHeight());
        aboutReg.flip(false,true);
        quit=manager.get("quit.png",Texture.class);
        quitReg=new TextureRegion(quit,quit.getWidth(),quit.getHeight());
        quitReg.flip(false,true);
        volumeon=manager.get("volumeon.png",Texture.class);
        volumeonReg=new TextureRegion(volumeon,volumeon.getWidth(),volumeon.getHeight());
        volumeonReg.flip(false,true);
        volumeoff=manager.get("volumeoff.png",Texture.class);
        volumeoffReg=new TextureRegion(volumeoff,volumeoff.getWidth(),volumeoff.getHeight());
        volumeoffReg.flip(false,true);

        camera=new OrthographicCamera();
        camera.setToOrtho(true);
        sb=new SpriteBatch();
    }
    @Override
    public void render() {
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        sb.setProjectionMatrix(camera.combined);

        sb.begin();

        sb.draw(playReg,Gdx.graphics.getWidth()/2-playReg.getRegionWidth()/2,Gdx.graphics.getHeight()/4-playReg.getRegionHeight()/2);
        sb.draw(aboutReg,Gdx.graphics.getWidth()/2-aboutReg.getRegionWidth()/2,Gdx.graphics.getHeight()/2-aboutReg.getRegionHeight()/2);
        sb.draw(quitReg,Gdx.graphics.getWidth()/2-quitReg.getRegionWidth()/2,Gdx.graphics.getHeight()*3/4-quitReg.getRegionHeight()/2);
        sb.draw(volumeonReg,Gdx.graphics.getWidth()-volumeonReg.getRegionWidth(),0);
        sb.draw(volumeoffReg,Gdx.graphics.getWidth()-volumeonReg.getRegionWidth()-volumeoffReg.getRegionWidth(),0);

        sb.end();

        if(Gdx.input.isTouched()){
            //System.out.println("x="+Gdx.input.getX()+" y="+Gdx.input.getY());
            if(Gdx.input.getX()>=Gdx.graphics.getWidth()/2-playReg.getRegionWidth()/2 &&
                    Gdx.input.getX()<=Gdx.graphics.getWidth()/2-playReg.getRegionWidth()/2+playReg.getRegionWidth() &&
                    Gdx.input.getY()>=Gdx.graphics.getHeight()/4-playReg.getRegionHeight()/2 &&
                    Gdx.input.getY()<=Gdx.graphics.getHeight()/4-playReg.getRegionHeight()/2+playReg.getRegionHeight()){
                //System.out.println("Play");
                BalonPatlatma.sm.pushState(new Level1State());
            }
            if(Gdx.input.getX()>=Gdx.graphics.getWidth()/2-aboutReg.getRegionWidth()/2 &&
                    Gdx.input.getX()<=Gdx.graphics.getWidth()/2-aboutReg.getRegionWidth()/2+aboutReg.getRegionWidth() &&
                    Gdx.input.getY()>=Gdx.graphics.getHeight()/2-aboutReg.getRegionHeight()/2 &&
                    Gdx.input.getY()<=Gdx.graphics.getHeight()/2-aboutReg.getRegionHeight()/2+aboutReg.getRegionHeight()){
                //System.out.println("About");
                BalonPatlatma.sm.pushState(new AboutState());
            }
            if(Gdx.input.getX()>=Gdx.graphics.getWidth()/2-quitReg.getRegionWidth()/2 &&
                    Gdx.input.getX()<=Gdx.graphics.getWidth()/2-quitReg.getRegionWidth()/2+quitReg.getRegionWidth() &&
                    Gdx.input.getY()>=Gdx.graphics.getHeight()*3/4-quitReg.getRegionHeight()/2 &&
                    Gdx.input.getY()<=Gdx.graphics.getHeight()*3/4-quitReg.getRegionHeight()/2+quitReg.getRegionHeight()){
                //System.out.println("Quit");
                System.exit(0);
            }
            if(Gdx.input.getX()>=Gdx.graphics.getWidth()-volumeonReg.getRegionWidth() &&
                    Gdx.input.getX()<=Gdx.graphics.getWidth()-volumeonReg.getRegionWidth()+volumeonReg.getRegionWidth() &&
                    Gdx.input.getY()>=0 &&
                    Gdx.input.getY()<=volumeonReg.getRegionWidth()){
                //System.out.println("Volumeon");
                bom.play();
                vol=true;
            }
            if(Gdx.input.getX()>=Gdx.graphics.getWidth()-volumeonReg.getRegionWidth()-volumeoffReg.getRegionWidth() &&
                    Gdx.input.getX()<=Gdx.graphics.getWidth()-volumeonReg.getRegionWidth()-volumeoffReg.getRegionWidth()+volumeoffReg.getRegionWidth() &&
                    Gdx.input.getY()>=0 &&
                    Gdx.input.getY()<=volumeoffReg.getRegionWidth()){
                //System.out.println("Volumeoff");
                vol=false;
            }

        }
    }
}
