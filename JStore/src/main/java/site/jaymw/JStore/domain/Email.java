package site.jaymw.JStore.domain;

public class Email {
    private String posterAdress;

    private String posterName;

    /**
     * 收件人姓名
     **/
    private String recieverName;

    /**
     * 收件人邮箱地址
     **/
    private String recieverEmailAddress;

    /**
     * 邮件主题
     **/
    private String subject;

    /**
     * 邮件内容
     **/
    private String emailContent;

    public String getPosterAdress() {
        return posterAdress;
    }

    public void setPosterAdress(String posterAdress) {
        this.posterAdress = posterAdress;
    }

    public String getPosterName() {
        return posterName;
    }

    public void setPosterName(String posterName) {
        this.posterName = posterName;
    }

    public String getRecieverName() {
        return recieverName;
    }

    public void setRecieverName(String recieverName) {
        this.recieverName = recieverName;
    }

    public String getRecieverEmailAddress() {
        return recieverEmailAddress;
    }

    public void setRecieverEmailAddress(String recieverEmailAddress) {
        this.recieverEmailAddress = recieverEmailAddress;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getEmailContent() {
        return emailContent;
    }

    public void setEmailContent(String emailContent) {
        this.emailContent = emailContent;
    }
}
