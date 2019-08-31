package my.example.spring.mvc

import org.jetbrains.kotlin.cli.common.environment.setIdeaIoUseFallback
import org.springframework.web.servlet.View
import javax.script.Compilable
import javax.script.CompiledScript
import javax.script.ScriptEngineManager
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import org.springframework.core.io.ResourceLoader
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.io.ClassPathResource
import java.util.stream.Collectors
import java.io.BufferedReader
import javassist.bytecode.ByteArray
import my.example.spring.scripting.ScriptEngineFactoryManager
import java.io.InputStreamReader
import java.nio.charset.Charset


class KotlinView constructor(path: String) : View {
    companion object ViewData {
        var Script: CompiledScript? = null
    }

    private var viewPath: String? = null

    init {
        viewPath = path
    }

    override fun render(model: MutableMap<String, *>?, request: HttpServletRequest, response: HttpServletResponse) {
        // next line throws exception when launched as executable war
        // ScriptEngineFactoryManager().GetScriptEngine("kotlin");

        val classLoader = Thread.currentThread().getContextClassLoader();
        // next line ScriptEngineManager(classLoader).getEngineByName("kotlin") == null when launched as executable war
        val compilableEngine = ScriptEngineManager(classLoader).getEngineByName("kotlin") as Compilable

        setIdeaIoUseFallback(); // hack to use kotlin script engine in spring boot environment

        var resourceStream = classLoader.getResourceAsStream("classpath:$viewPath")

        var script = "3 + 2";

        if (ViewData.Script == null)
            ViewData.Script = compilableEngine.compile(script);

        response.outputStream.print(Script!!.eval().toString())
    }
}
