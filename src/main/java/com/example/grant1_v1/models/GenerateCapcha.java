package com.example.grant1_v1.models;

import cn.apiclub.captcha.Captcha;
import cn.apiclub.captcha.backgrounds.GradiatedBackgroundProducer;
import cn.apiclub.captcha.noise.CurvedLineNoiseProducer;
import cn.apiclub.captcha.noise.StraightLineNoiseProducer;
import cn.apiclub.captcha.text.producer.DefaultTextProducer;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;

public class GenerateCapcha {
    public static String create(String path){
        Captcha captcha = createCaptcha(170, 100);
        createImage(captcha, path);

        return captcha.getAnswer();
    }
    public static Object[] create(){
        Captcha captcha = createCaptcha(200, 60);
        return new Object[]{captcha.getAnswer(), getImage(captcha)};
    }
    private static Captcha createCaptcha(int width, int height) {

        return new Captcha.Builder(width, height)
                .addBackground(new GradiatedBackgroundProducer(Color.RED, Color.CYAN))
                .addText(new DefaultTextProducer())
                .addNoise(new CurvedLineNoiseProducer(Color.orange, 4.5f))
                .addNoise(new CurvedLineNoiseProducer(Color.magenta, 2f))
                .addNoise(new CurvedLineNoiseProducer(Color.magenta, 5f)).build();
    }
    private static void createImage(Captcha captcha, String path) {
        try {
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            ImageIO.write(captcha.getImage(), "png", os);
            FileOutputStream fos = new FileOutputStream(path);
            fos.write(os.toByteArray());
            fos.flush();
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static BufferedImage getImage(Captcha captcha){
        return captcha.getImage();
    }
}