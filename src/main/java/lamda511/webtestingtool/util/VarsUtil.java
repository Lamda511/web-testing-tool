package lamda511.webtestingtool.util;


import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

public class VarsUtil {

    public static String resolveGetVarValue(String varName, Map<String, String> variables) {
        String varNameStr = StringUtils.defaultIfBlank(varName, "").trim();

        return StringUtils.startsWith(varNameStr, "${") && StringUtils.endsWith(varNameStr,"}")
            ? variables.get(varNameStr)
            : varNameStr;
    }

    public static void resolveSetVarValue(String varContent, String varValue, Map<String, String> variables) {
        String varContentStr = StringUtils.defaultIfBlank(varContent, "").trim();
        String varValueStr = StringUtils.defaultIfBlank(varValue, "").trim();

        String key = StringUtils.startsWith(varContentStr, "${") && StringUtils.endsWith(varContentStr,"}")
                ? varContentStr
                : "${" + varContentStr + "}";

        variables.put(key, varValueStr);
    }

    public static Map<String, String> getVariablesMapOrNew(Map<String, String> map) {
        return map == null ? new HashMap<String, String>() : map;

    }
}
