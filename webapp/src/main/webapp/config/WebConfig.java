@EnableWebMvc
@ComponentScan({ "ar.edu.itba.paw.webapp.controller" })
@Configuration
public class WebConfig {

@Bean
public ViewResolver viewResolver() {final InternalResourceViewResolver viewResolver =
new InternalResourceViewResolver();
viewResolver.setViewClass(JstlView.class);
viewResolver.setPrefix("/WEB-INF/jsp/");
viewResolver.setSuffix(".jsp");
return viewResolver;
}
}
