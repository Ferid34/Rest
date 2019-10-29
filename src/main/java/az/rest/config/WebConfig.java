package az.rest.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        configurer.defaultContentType(MediaType.APPLICATION_JSON);//default olaraq json olacaq

        configurer.favorParameter(true);//parametre ustunlik versin
        configurer.parameterName("mediaType");// hemin parametrin adi


        //2 extion config etme
        configurer.favorPathExtension(true);
        configurer.mediaType("json",MediaType.APPLICATION_JSON);
        configurer.mediaType("xml",MediaType.APPLICATION_XML);
    }
}
