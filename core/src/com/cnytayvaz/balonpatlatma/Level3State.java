package com.cnytayvaz.balonpatlatma;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by Cuneyt on 27.4.2016.
 */
public class Level3State extends State {
    private static int skor;
    private static int highSkor;

    private static Texture black, green, red, yellow;
    private static TextureRegion blackReg,greenReg,redReg,yellowReg;

    private static int bgx, bgy, redx, redy, yellowx, yellowy;
    private static int redspeed, bgspeed;
    private static int yellowtime,bgchangey;
    private static boolean redxctrl,redyctrl, yellowctrl,blackgreenctrl,redpatladimi,yellowpatladimi,greenpatladimi;
    private static float time;

    private Sound bom;
    private static SpriteBatch sb,sbForString;
    private static OrthographicCamera camera;
    private static BitmapFont font;
    private static CharSequence str;
    AssetManager manager;

    public Level3State(int skorFromLvl2){
        manager=new AssetManager();
        manager.load("black.png",Texture.class);
        manager.load("greenlvl3.png",Texture.class);
        manager.load("redlvl3.png",Texture.class);
        manager.load("yellowlvl3.png",Texture.class);
        manager.load("bom.wav",Sound.class);
        manager.finishLoading();
        skor=0;
        highSkor=skorFromLvl2;
        redpatladimi=false;
        yellowpatladimi=false;
        greenpatladimi=false;

        black=manager.get("black.png",Texture.class);
        blackReg=new TextureRegion(black,black.getWidth(),black.getHeight());
        blackReg.flip(false,true);
        green=manager.get("greenlvl3.png",Texture.class);
        greenReg=new TextureRegion(green,green.getWidth(),green.getHeight());
        greenReg.flip(false,true);
        red=manager.get("redlvl3.png",Texture.class);
        redReg=new TextureRegion(red,red.getWidth(),red.getHeight());
        redReg.flip(false,true);
        yellow=manager.get("yellowlvl3.png",Texture.class);
        yellowReg=new TextureRegion(yellow,yellow.getWidth(),yellow.getHeight());
        yellowReg.flip(false,true);

        bom = manager.get("bom.wav",Sound.class);
        camera = new OrthographicCamera();
        camera.setToOrtho(true);
        sb = new SpriteBatch();
        sbForString =new SpriteBatch();
        font = new BitmapFont();

        time=0;

        redx = (int) (Math.random()*(Gdx.graphics.getWidth()-redReg.getRegionWidth()));
        redy = (int) (Math.random()*(Gdx.graphics.getHeight()-redReg.getRegionHeight()));
        redspeed = 200;

        yellowx = (int) (Math.random()*(Gdx.graphics.getWidth()-yellowReg.getRegionWidth()));
        yellowy = (int) (Math.random()*(Gdx.graphics.getHeight()-yellowReg.getRegionHeight()));
        yellowtime= (int) (Math.random()*2)+5;
        yellowctrl = false;

        bgx = (int) (Math.random()*(Gdx.graphics.getWidth()-blackReg.getRegionWidth()));
        bgy = Gdx.graphics.getHeight()-blackReg.getRegionHeight();
        bgchangey= (int) (Math.random()*(Gdx.graphics.getHeight()-blackReg.getRegionHeight()));
        bgspeed=200;
        if(Math.random()<=0.5){
            blackgreenctrl=true;
        }
        else {
            blackgreenctrl=false;
        }
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        sb.setProjectionMatrix(camera.combined);

        time+=Gdx.graphics.getDeltaTime();
        if(time<=30) {
            sb.begin();

            sb.draw(redReg, redx, redy);

            if (yellowctrl == true) {
                sb.draw(yellowReg, yellowx, yellowy);
            }

            if (blackgreenctrl == true) {
                sb.draw(greenReg, bgx, bgy);
            } else {
                sb.draw(blackReg, bgx, bgy);
            }

            sb.end();

            //red x control
            if (redx >= Gdx.graphics.getWidth() - redReg.getRegionWidth()) {
                redxctrl = false;
            }
            if (redx <= 0) {
                redxctrl = true;
            }
            if (redxctrl == true) {
                redx += (int) (redspeed * Gdx.graphics.getDeltaTime());
            } else {
                redx -= (int) (redspeed * Gdx.graphics.getDeltaTime());
            }
            //red y control
            if (redy >= Gdx.graphics.getHeight() - redReg.getRegionHeight()) {
                redyctrl = false;
            }
            if (redy <= 0) {
                redyctrl = true;
            }
            if (redyctrl == true) {
                redy += (int) (redspeed * Gdx.graphics.getDeltaTime());
            } else {
                redy -= (int) (redspeed * Gdx.graphics.getDeltaTime());
            }

            //yellow control
            if ((int) (time) % yellowtime == 0) {
                yellowctrl = true;
            } else {
                yellowctrl = false;
                yellowx = (int) (Math.random() * (Gdx.graphics.getWidth() - yellowReg.getRegionWidth()));
                yellowy = (int) (Math.random() * (Gdx.graphics.getHeight() - yellowReg.getRegionHeight()));
            }

            //black green control
            if (bgy <= 0) {
                bgx = (int) (Math.random() * (Gdx.graphics.getWidth() - blackReg.getRegionWidth()));
                bgy = Gdx.graphics.getHeight() - blackReg.getRegionHeight();
                bgchangey = (int) (Math.random() * (Gdx.graphics.getHeight() - blackReg.getRegionHeight()));
                if (Math.random() <= 0.5) {
                    blackgreenctrl = true;
                } else {
                    blackgreenctrl = false;
                }
            } else {
                bgy -= (int) (bgspeed * Gdx.graphics.getDeltaTime());
            }
            if (bgy <= bgchangey + 2 && bgy >= bgchangey) {
                if (blackgreenctrl == true) {
                    blackgreenctrl = false;
                } else {
                    blackgreenctrl = true;
                }
                bgchangey = (int) (Math.random() * (Gdx.graphics.getHeight() - blackReg.getRegionHeight()));
            }

            //touch control
            if (Gdx.input.isTouched()) {
                if (Gdx.input.getX() >= redx &&
                        Gdx.input.getX() <= redx + redReg.getRegionWidth() &&
                        Gdx.input.getY() >= redy &&
                        Gdx.input.getY() <= redy + redReg.getRegionHeight()) {
                    //System.out.println("kırmızıya dokunuldu");
                    if(MenuState.vol){
                        bom.play();
                    }
                    redpatladimi = true;
                    redx = (int) (Math.random() * (Gdx.graphics.getWidth() - redReg.getRegionWidth()));
                    redy = (int) (Math.random() * (Gdx.graphics.getHeight() - redReg.getRegionHeight()));
                    skor += 10;
                }
                if (Gdx.input.getX() >= bgx &&
                        Gdx.input.getX() <= bgx + blackReg.getRegionWidth() &&
                        Gdx.input.getY() >= bgy &&
                        Gdx.input.getY() <= bgy + blackReg.getRegionHeight()) {
                    if (blackgreenctrl == true) {
                        //System.out.println("yeşile dokunuldu");
                        if(MenuState.vol){
                            bom.play();
                        }
                        greenpatladimi = true;
                        bgx = (int) (Math.random() * (Gdx.graphics.getWidth() - blackReg.getRegionWidth()));
                        bgy = Gdx.graphics.getHeight() - blackReg.getRegionHeight();
                        bgchangey = (int) (Math.random() * (Gdx.graphics.getHeight() - blackReg.getRegionHeight()));
                        if (Math.random() <= 0.5) {
                            blackgreenctrl = true;
                        } else {
                            blackgreenctrl = false;
                        }
                        skor += 5;
                    } else {
                        //System.out.println("siyaha dokunuldu");
                        if(MenuState.vol){
                            bom.play();
                        }
                        bgx = (int) (Math.random() * (Gdx.graphics.getWidth() - blackReg.getRegionWidth()));
                        bgy = Gdx.graphics.getHeight() - blackReg.getRegionHeight();
                        bgchangey = (int) (Math.random() * (Gdx.graphics.getHeight() - blackReg.getRegionHeight()));
                        if (Math.random() <= 0.5) {
                            blackgreenctrl = true;
                        } else {
                            blackgreenctrl = false;
                        }
                        skor -= 10;
                    }
                }
                if (Gdx.input.getX() >= yellowx &&
                        Gdx.input.getX() <= yellowx + yellowReg.getRegionWidth() &&
                        Gdx.input.getY() >= yellowy &&
                        Gdx.input.getY() <= yellowy + yellowReg.getRegionHeight() &&
                        yellowctrl == true) {
                    //System.out.println("sariya dokunuldu");
                    if(MenuState.vol){
                        bom.play();
                    }
                    yellowpatladimi = true;
                    yellowx = (int) (Math.random() * (Gdx.graphics.getWidth() - yellowReg.getRegionWidth()));
                    yellowy = (int) (Math.random() * (Gdx.graphics.getHeight() - yellowReg.getRegionHeight()));
                    yellowtime = (int) (Math.random() * 2) + 5;
                    skor += 20;
                }
            }
        }
        else if(time<=32){
            str=""+skor;
            sbForString.begin();
            font.draw(sbForString,str,Gdx.graphics.getWidth()/2,Gdx.graphics.getHeight()/2);
            sbForString.end();
            //skoru göster
        }
        else if(time<=35){
            str="High Skor "+(highSkor+skor);
            sbForString.begin();
            font.draw(sbForString,str,Gdx.graphics.getWidth()/2-40,Gdx.graphics.getHeight()/2);
            sbForString.end();
            //high skoru göster
        }
        else{
            BalonPatlatma.sm.popState();
            //menüye dön
        }
    }
}
