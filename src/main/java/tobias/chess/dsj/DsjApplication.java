package tobias.chess.dsj;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DsjApplication {

    public static void main(String[] args) {
        SpringApplication.run(DsjApplication.class, args);

        // Start Chrome with: "C:\Program Files (x86)\Google\Chrome\Application\chrome.exe" --disable-web-security --disable-gpu --user-data-dir=~/chromeTemp
    }

}
