package UMC6.GrowPalette;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class GrowPaletteApplication {

	public static void main(String[] args) {
		SpringApplication.run(GrowPaletteApplication.class, args);
	}

}
