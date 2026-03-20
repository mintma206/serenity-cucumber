package starter.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.MissingNode;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * @author mamt
 * @version 1.0
 * @date 2026/3/17 10:03
 */
public class JsonDataUtils {
    private static final ObjectMapper mapper = new ObjectMapper();


    public static JsonNode loadJsonNode(String relativePath) throws Exception {
        InputStream is = JsonDataUtils.class.getClassLoader().getResourceAsStream(relativePath);
        if (is == null) {
            throw new IllegalArgumentException("找不到 JSON 文件: " + relativePath);
        }
        return mapper.readTree(is);
    }

    public static JsonNode parseJsonNode(String json) throws Exception {
        return mapper.readTree(json);
    }

    public static JsonNode getNode(JsonNode root, String path) {
        if (root == null || path == null || path.isEmpty()) {
            return MissingNode.getInstance();
        }
        return root.at("/" + path.replace(".", "/"));
    }

    /**
     * 获取路径指向的数组，并转为 List<JsonNode>
     */
    public static List<JsonNode> getArrayAsList(JsonNode root, String path) {
        JsonNode node = getNode(root, path);
        if (node.isArray()) {
            return StreamSupport.stream(node.spliterator(), false)
                    .collect(Collectors.toList());
        }
        return new ArrayList<>();
    }

    /**
     * 获取路径指向的数组，并转为 List<String>
     */
    public static List<String> getArrayAsStringList(JsonNode root, String path) {
        return getArrayAsList(root, path).stream()
                .map(JsonNode::asText)
                .collect(Collectors.toList());
    }

    public static String getStrWithDefault(JsonNode jsonNode, String key, String defaultValue)  {
        JsonNode node = getNode(jsonNode, key);
        if (!node.isTextual()) {
            return defaultValue;
        }
        return node.asText(defaultValue);
    }

    public static boolean getBooleanWithDefault(JsonNode jsonNode, String key, boolean defaultValue)  {
        JsonNode node = getNode(jsonNode, key);
        if (!node.isBoolean()) {
            return defaultValue;
        }
        return node.asBoolean(defaultValue);
    }

    public static int getIntWithDefault(JsonNode jsonNode, String key, int defaultValue)  {
        JsonNode node = getNode(jsonNode, key);
        if (!node.isInt()) {
            return defaultValue;
        }
        return node.asInt(defaultValue);
    }

    public static void main(String[] args) {

        try {
            JsonNode jsonNode = loadJsonNode("jsons/test.json");
            System.out.println(getStrWithDefault(jsonNode, "name", ""));
            System.out.println(getStrWithDefault(jsonNode, "cust.name", ""));
            System.out.println(getBooleanWithDefault(jsonNode, "cust.auto.brand", false));
            System.out.println(getIntWithDefault(jsonNode, "cust.auto.model", 1));
            System.out.println(getArrayAsList(jsonNode, "cust.orders"));
            System.out.println(getArrayAsStringList(jsonNode, "cust.address.details"));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
