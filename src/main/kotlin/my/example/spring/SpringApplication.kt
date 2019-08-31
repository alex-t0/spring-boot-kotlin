package my.example.spring

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.web.servlet.config.annotation.EnableWebMvc

@SpringBootApplication
@EnableWebMvc
class SpringApplication

fun main(args: Array<String>) {
	runApplication<SpringApplication>(*args)
}
