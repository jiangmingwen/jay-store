package site.jaymw.JStore.domain;

public class SliderCheck {
    // 原图
    private String sourceImg;
    private Integer imgWidth;
    private Integer imgHeight;
    // 扣过图的图片
    private String modifyImg;
    // 拼图
    private String puzzleImg;
    private String puzzleWidth;
    private String puzzleHeight;
    // 坐标
    private String puzzleYAxis;
    private String puzzleXAxis;

    public String getSourceImg() {
        return sourceImg;
    }

    public void setSourceImg(String sourceImg) {
        this.sourceImg = sourceImg;
    }

    public Integer getImgWidth() {
        return imgWidth;
    }

    public void setImgWidth(Integer imgWidth) {
        this.imgWidth = imgWidth;
    }

    public Integer getImgHeight() {
        return imgHeight;
    }

    public void setImgHeight(Integer imgHeight) {
        this.imgHeight = imgHeight;
    }

    public String getModifyImg() {
        return modifyImg;
    }

    public void setModifyImg(String modifyImg) {
        this.modifyImg = modifyImg;
    }

    public String getPuzzleImg() {
        return puzzleImg;
    }

    public void setPuzzleImg(String puzzleImg) {
        this.puzzleImg = puzzleImg;
    }

    public String getPuzzleWidth() {
        return puzzleWidth;
    }

    public void setPuzzleWidth(String puzzleWidth) {
        this.puzzleWidth = puzzleWidth;
    }

    public String getPuzzleHeight() {
        return puzzleHeight;
    }

    public void setPuzzleHeight(String puzzleHeight) {
        this.puzzleHeight = puzzleHeight;
    }

    public String getPuzzleYAxis() {
        return puzzleYAxis;
    }

    public void setPuzzleYAxis(String puzzleYAxis) {
        this.puzzleYAxis = puzzleYAxis;
    }

    public String getPuzzleXAxis() {
        return puzzleXAxis;
    }

    public void setPuzzleXAxis(String puzzleXAxis) {
        this.puzzleXAxis = puzzleXAxis;
    }
}