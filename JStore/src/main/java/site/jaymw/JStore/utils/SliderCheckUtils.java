package site.jaymw.JStore.utils;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import site.jaymw.JStore.domain.SliderCheck;
import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Random;

/**
 * 滑块验证码工具类
 */
public class SliderCheckUtils {
    private static final Logger logger = LoggerFactory.getLogger(SliderCheckUtils.class);

    static int targetLength = 55;//小图长
    static int targetWidth = 45;//小图宽
    static int circleR = 6;//半径
    static int r1 = 3;//距离点

  /*  public static void main(String[] args)  throws Exception{
        int[][] blockData = getBlockData();
        BufferedImage resourceImg = ImageIO.read(new File("D://ver-3.png"));
        //BufferedImage target = ImageIO.read(new File("D://ver-3.png"));
        BufferedImage target= new BufferedImage(targetLength, targetWidth, BufferedImage.TYPE_4BYTE_ABGR);


        cutByTemplate(resourceImg, target, blockData, 250, 50);

        String imageBASE64 = getImageBASE64(resourceImg);
        String imageBASE641 = getImageBASE64(target);
        System.out.println(2222);
    }*/

    public static SliderCheck build() {
        try {
            int max = 430;
            int min = 150;
            int x = new Random().nextInt(max - min) + min;
            // 生成base64
            int[][] blockData = getBlockData();
            int number = new Random().nextInt(6) + 1;
            BufferedImage resourceImg = ImageIO.read(new File("H:\\toux\\v2\\" + number + ".jpg"));
            String resourceImgSt = getImageBASE64(resourceImg);
            BufferedImage puzzleImg = new BufferedImage(targetLength, targetWidth, BufferedImage.TYPE_4BYTE_ABGR);

            cutByTemplate(resourceImg, puzzleImg, blockData, x, 50);

            String resourceUpImgSt = getImageBASE64(resourceImg);
            String puzzleImgSt = getImageBASE64(puzzleImg);

            SliderCheck sliderCheck = new SliderCheck();
            sliderCheck.setSourceImg("data:image/png;base64," + resourceImgSt);
            sliderCheck.setImgWidth(500);
            sliderCheck.setImgHeight(315);
            sliderCheck.setModifyImg("data:image/png;base64," + resourceUpImgSt);
            sliderCheck.setPuzzleImg("data:image/png;base64," + puzzleImgSt);
            sliderCheck.setPuzzleWidth(targetLength + "");
            sliderCheck.setPuzzleHeight(targetWidth + "");
            sliderCheck.setPuzzleYAxis("50");
            sliderCheck.setPuzzleXAxis(x + "");
            return sliderCheck;
        } catch (Exception e) {
            logger.error("滑块", e);
            return null;
        }
    }

    public static String getImageBASE64(BufferedImage image) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ImageIO.write(image, "png", out);
        byte[] b = out.toByteArray();//转成byte数组
        BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encode(b);//生成base64编码
    }

    /**
     * @return int[][]
     * @throws
     * @Title: getBlockData
     * @Description: 生成小图轮廓
     */
    private static int[][] getBlockData() {

        int[][] data = new int[targetLength][targetWidth];
        double x2 = targetLength - circleR;

        //随机生成圆的位置
        double h1 = circleR + Math.random() * (targetWidth - 3 * circleR - r1);
        double po = circleR * circleR;

        double xbegin = targetLength - circleR - r1;
        double ybegin = targetWidth - circleR - r1;

        for (int i = 0; i < targetLength; i++) {
            for (int j = 0; j < targetWidth; j++) {
                double d3 = Math.pow(i - x2, 2) + Math.pow(j - h1, 2);
                double d2 = Math.pow(j - 2, 2) + Math.pow(i - h1, 2);
                if ((j <= ybegin && d2 <= po) || (i >= xbegin && d3 >= po)) {
                    data[i][j] = 0;
                } else {
                    data[i][j] = 1;
                }

            }
        }
        return data;
    }

    /**
     * @param oriImage
     * @param targetImage
     * @param templateImage
     * @param x
     * @param y             void
     * @throws
     * @Title: cutByTemplate
     * @Description: 生成小图片、给大图片添加阴影
     */
    private static void cutByTemplate(BufferedImage oriImage, BufferedImage targetImage, int[][] templateImage, int x, int y) {
        for (int i = 0; i < targetLength; i++) {
            for (int j = 0; j < targetWidth; j++) {
                int rgb = templateImage[i][j];
                // 原图中对应位置变色处理
                int rgb_ori = oriImage.getRGB(x + i, y + j);

                if (rgb == 1) {
                    //抠图上复制对应颜色值
                    targetImage.setRGB(i, j, rgb_ori);
                    //原图对应位置颜色变化
                    oriImage.setRGB(x + i, y + j, rgb_ori & 0x363636);
                } else {
                    //这里把背景设为透明
                    targetImage.setRGB(i, j, rgb_ori & 0x00ffffff);
                }
            }
        }
    }


}

