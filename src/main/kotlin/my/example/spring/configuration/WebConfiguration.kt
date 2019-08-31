package my.example.spring.configuration

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import my.example.spring.mvc.KotlinViewResolver

@Configuration
class WebConfiguration : WebMvcConfigurer {
    @Bean
    fun kotlinScriptConfigurer() = KotlinTemplateConfigurer().apply {
        engineName = "kotlin"
        setScripts("scripts/render.kts")
        renderFunction = "render"
        isSharedEngine = false
    }

    /*@Bean
    fun kotlinScriptViewResolver() = KotlinTemplateViewResolver().apply {
        setPrefix("templates/")
        setSuffix(".html.kts")
    }*/

    @Bean
    fun kotlintViewResolver() = KotlinViewResolver().apply {

    }
}
