package starter.config;

import com.fasterxml.jackson.databind.JsonNode;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import starter.utils.JsonDataUtils;


/**
 * @author mamt
 * @version 1.0
 * @date 2026/3/17 10:26
 */
public class DataDrivenHooks {
    private static final ThreadLocal<JsonNode> CURRENT_DATA = new ThreadLocal<>();

    @Before
    public void loadJsonIfTagged(Scenario scenario) {
        String jsonPath = null;
        for (String tag : scenario.getSourceTagNames()) {
            if (tag.startsWith("@json:")) {
                jsonPath = tag.substring(6).trim();
                break;
            }
        }

        if (jsonPath != null && !jsonPath.isEmpty()) {
            try {
                CURRENT_DATA.set(JsonDataUtils.loadJsonNode(jsonPath));
            } catch (Exception e) {
                throw new RuntimeException("加载JSON失败: " + jsonPath, e);
            }
        }
    }

    @After
    public void cleanThreadLocal() {
        CURRENT_DATA.remove();
    }

    public static JsonNode getCurrentCase() {
        JsonNode data = CURRENT_DATA.get();
        if (data == null) {
            throw new IllegalStateException("当前场景没有加载 JSON 数据，请检查 @json: tag");
        }
        return data;
    }
}
