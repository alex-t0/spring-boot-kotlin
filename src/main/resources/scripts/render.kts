import org.springframework.web.servlet.view.script.RenderingContext
import org.springframework.context.support.ResourceBundleMessageSource
import javax.script.*
import org.springframework.beans.factory.getBean
import java.util.concurrent.ConcurrentHashMap

fun render(template: String, model: Map<String, Any>, renderingContext: RenderingContext): String {
    var cache = renderingContext.applicationContext.getBean<ConcurrentHashMap<String, CompiledScript>>()
    val compilableEngine = ScriptEngineManager().getEngineByName("kotlin") as Compilable
    val compiledScript = cache.getOrPut(renderingContext.url,  { compilableEngine.compile(template);  })
    val bindings = SimpleBindings(model)
    val messageSource = renderingContext.applicationContext.getBean<ResourceBundleMessageSource>()
    bindings.put("i18n", { code: String -> messageSource.getMessage(code, null, renderingContext.locale) })
    bindings.put("include", { path: String -> renderingContext.templateLoader.apply("templates/$path.kts") })
    bindings.put("cache", cache)
    return compiledScript.eval(bindings) as String
}