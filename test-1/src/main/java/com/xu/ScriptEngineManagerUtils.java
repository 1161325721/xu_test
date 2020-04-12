package com.xu;


import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class ScriptEngineManagerUtils {

    private  ScriptEngine scriptEngine;

    public ScriptEngineManagerUtils(){
        ScriptEngineManager scriptEngineManager = new ScriptEngineManager();
        scriptEngine = scriptEngineManager.getEngineByName("JavaScript");
    }

    public String runJs(String jsText){
        try {
            scriptEngine.eval(jsText+"function tel(){return str;}");
        } catch (ScriptException e) {
            e.printStackTrace();
        }
        return (String) scriptEngine.get("str");
    };

    public Object getVar(String varName){
        return  scriptEngine.get(varName);
    }

    public void setVar(String varName,Object varValue){
        scriptEngine.put(varName,varValue);
    }

    public Object runScriptFunction(String funcTionName,Object... args){
        try {
            Invocable invocable = (Invocable) scriptEngine;
            return invocable.invokeFunction(funcTionName,args);
        } catch (ScriptException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return null;
    }

}
