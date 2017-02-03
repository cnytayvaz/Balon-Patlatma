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
public class Level1State extends State {

    private static int skor;

    private static Texture black, green, red, red1, yellow;
    private static TextureRegion blackReg,greenReg,redReg,red1Reg,yellowReg;

    private static int bgx, bgy, redx, redy, red1x, red1y, yellowx, yellowy;
    private static int redspeed, bgspeed, red1speed;
    private static int yellowtime,bgchangey;
    private static boolean redxctrl,redyctrl, red1xctrl,red1yctrl, yellowctrl,blackgreenctrl,redpatladimi,yellowpatladimi,greenpatladimi;
    private static float time;

    private Sound bom;
    private static SpriteBatch sb,sbForString;
    private static OrthographicCamera camera;
    private static BitmapFont font;
    private static CharSequence str;
    AssetManager manager;

    public Level1State(){
        manager=new AssetManager();
        manager.load("black.png",Texture.class);
        manager.load("green.png",Texture.class);
        manager.load("red.png",Texture.class);
        manager.load("yellow.png",Texture.class);
        manager.load("bom.wav",Sound.class);
        manager.finishLoading();
        skor=0;
        redpatladimi=false;
        yellowpatladimi=false;
        greenpatladimi=false;

        black=manager.get("black.png",Texture.class);
        blackReg=new TextureRegion(black,black.getWidth(),black.getHeight());
        blackReg.flip(false,true);
        green=manager.get("green.png",Texture.class);
        greenReg=new TextureRegion(green,green.getWidth(),green.getHeight());
        greenReg.flip(false,true);
        red=manager.get("red.png",Texture.class);
        redReg=new TextureRegion(red,red.getWidth(),red.getHeight());
        redReg.flip(false,true);
        red1=manager.get("red.png",Texture.class);
        red1Reg=new TextureRegion(red1,red1.getWidth(),red1.getHeight());
        red1Reg.flip(false,true);
        yellow=manager.get("yellow.png",Texture.class);
        yellowReg=new TextureRegion(yellow,yellow.getWidth(),yellow.getHeight());
        yellowReg.flip(false,true);

        bom = manager.get("bom.wav",Sound.class);
        camera = new OrthographicCamera();
        camera.setToOrtho(true);
        sb = new SpriteBatch();
        sbForString = new SpriteBatch();
        font = new BitmapFont();

        time=0;

        redx = (int) (Math.random()*(Gdx.graphics.getWidth()-redReg.getRegionWidth()));
        redy = (int) (Math.random()*(Gdx.graphics.getHeight()-redReg.getRegionHeight()));
        redspeed = 100;
        red1x = (int) (Math.random()*(Gdx.graphics.getWidth()-red1Reg.getRegionWidth()));
        red1y = (int) (Math.random()*(Gdx.graphics.getHeight()-red1Reg.getRegionHeight()));
        red1speed = 100;

        yellowx = (int) (Math.random()*(Gdx.graphics.getWidth()-yellowReg.getRegionWidth()));
        yellowy = (int) (Math.random()*(Gdx.graphics.getHeight()-yellowReg.getRegionHeight()));
        yellowtime= (int) (Math.random()*2)+3;
        yellowctrl = false;

        bgx = (int) (Math.random()*(Gdx.graphics.getWidth()-blackReg.getRegionWidth()));
        bgy = Gdx.graphics.getHeight()-blackReg.getRegionHeight();
        bgchangey= (int) (Math.random()*(Gdx.graphics.getHeight()-blackReg.getRegionHeight()));
        bgspeed=100;
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
            sb.draw(red1Reg, red1x, red1y);

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

            //red1 x control
            if (red1x >= Gdx.graphics.getWidth() - red1Reg.getRegionWidth()) {
                red1xctrl = false;
            }
            if (red1x <= 0) {
                red1xctrl = true;
            }
            if (red1xctrl == true) {
                red1x += (int) (red1speed * Gdx.graphics.getDeltaTime());
            } else {
                red1x -= (int) (red1speed * Gdx.graphics.getDeltaTime());
            }
            //red1 y control
            if (red1y >= Gdx.graphics.getHeight() - red1Reg.getRegionHeight()) {
                red1yctrl = false;
            }
            if (red1y <= 0) {
                red1yctrl = true;
            }
            if (red1yctrl == true) {
                red1y += (int) (red1speed * Gdx.graphics.getDeltaTime());
            } else {
                red1y -= (int) (red1speed * Gdx.graphics.getDeltaTime());
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
                if (Gdx.input.getX() >= red1x &&
                        Gdx.input.getX() <= red1x + red1Reg.getRegionWidth() &&
                        Gdx.input.getY() >= red1y &&
                        Gdx.input.getY() <= red1y + red1Reg.getRegionHeight()) {
                    //System.out.println("kırmızıya dokunuldu");
                    if(MenuState.vol){
                        bom.play();
                    }
                    redpatladimi = true;
                    red1x = (int) (Math.random() * (Gdx.graphics.getWidth() - red1Reg.getRegionWidth()));
                    red1y = (int) (Math.random() * (Gdx.graphics.getHeight() - red1Reg.getRegionHeight()));
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
                    yellowtime = (int) (Math.random() * 2) + 3;
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
        else{
            if(skor>=100 && redpatladimi==true && greenpatladimi==true && yellowpatladimi==true){
                BalonPatlatma.sm.popState();
                BalonPatlatma.sm.pushState(new Level2State(skor));
            }
            else if(time<=35){
                str="High Skor "+skor;
                sbForString.begin();
                font.draw(sbForString,str,Gdx.graphics.getWidth()/2-40,Gdx.graphics.getHeight()/2);
                sbForString.end();
                //high skoru göster
            }
            else {
                BalonPatlatma.sm.popState();
            }
        }
    }
}
