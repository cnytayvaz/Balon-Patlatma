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
public class AboutState extends State {

    public static Texture back,aboutbg;
    public static TextureRegion backReg,aboutbgReg;

    public static SpriteBatch sb;
    public static OrthographicCamera camera;
    AssetManager manager;

    public AboutState(){
        manager=new AssetManager();
        manager.load("aboutbg.png",Texture.class);
        manager.load("back.png",Texture.class);
        manager.finishLoading();
        //aboutbg=new Texture(Gdx.files.internal("aboutbg.png"));
        aboutbg=manager.get("aboutbg.png",Texture.class);
        aboutbgReg=new TextureRegion(aboutbg,aboutbg.getWidth(),aboutbg.getHeight());
        aboutbgReg.flip(false,true);
        //back=new Texture(Gdx.files.internal("back.png"));
        back=manager.get("back.png",Texture.class);
        backReg=new TextureRegion(back,back.getWidth(),back.getHeight());
        backReg.flip(false,true);

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

        sb.draw(aboutbgReg,0,0);
        sb.draw(backReg,Gdx.graphics.getWidth()/2-backReg.getRegionWidth()/2,aboutbgReg.getRegionHeight());
        sb.end();

        if(Gdx.input.isTouched()){

            if(Gdx.input.getX()>=Gdx.graphics.getWidth()/2-backReg.getRegionWidth()/2 &&
                    Gdx.input.getX()<=Gdx.graphics.getWidth()/2-backReg.getRegionWidth()/2+backReg.getRegionWidth() &&
                    Gdx.input.getY()>=aboutbgReg.getRegionHeight() &&
                    Gdx.input.getY()<=aboutbgReg.getRegionHeight()+backReg.getRegionHeight()){

                BalonPatlatma.sm.popState();
            }

        }
    }
}
