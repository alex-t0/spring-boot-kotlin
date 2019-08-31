package my.example.spring.scripting

import javax.script.ScriptEngine
import javax.script.ScriptEngineManager

class ScriptEngineFactoryManager {
    fun GetScriptEngine(engineName: String): ScriptEngine? {
        val classLoader = Thread.currentThread().getContextClassLoader()

        ScriptEngineManager(classLoader).engineFactories.forEach { x ->
            if (x.engineName == engineName){
                return x.scriptEngine
            }
        }

        return null
    }
}
