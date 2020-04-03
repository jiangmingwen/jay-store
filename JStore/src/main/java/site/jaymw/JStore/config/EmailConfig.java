package site.jaymw.JStore.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:application.properties")
public class EmailConfig {
    @Value("${spring.mail.username}")
    private String posterAdress;

    @Value("${mail.name}")
    private String posterName;

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
}
