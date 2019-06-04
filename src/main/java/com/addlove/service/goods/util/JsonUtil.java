package com.addlove.service.goods.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.PropertyFilter;
import com.alibaba.fastjson.serializer.SimplePropertyPreFilter;

import static com.alibaba.fastjson.serializer.SerializerFeature.WriteNullListAsEmpty;
import static com.alibaba.fastjson.serializer.SerializerFeature.WriteNullStringAsEmpty;

/**
 * Json 工具类
 * @author lw
 *
 */
public class JsonUtil {

    /**
     * obj转为字节数组
     *
     * @param obj obj
     * @return 字节数组
     */
    public static byte[] toJsonBytes(Object obj) {
        return JSON.toJSONBytes(obj, WriteNullListAsEmpty, WriteNullStringAsEmpty);
    }

    /**
     * obj转为String
     *
     * @param obj obj
     * @return String
     */
    public static String toJSONString(Object obj) {
        return JSON.toJSONString(obj, WriteNullListAsEmpty, WriteNullStringAsEmpty);
    }


    /**
     * json转为指定类对象
     *
     * @param json json字符串
     * @param <T> clazz 类
     * @param clazz 类类型
     * @return <T> 类对象
     */
    public static <T> T parseObject(String json, Class<T> clazz){
        return JSON.parseObject(json, clazz);
    }

    /**
     * json转为指定List对象
     *
     * @param json json字符串
     * @param <T>  类
     * @param clazz 类类型
     * @return <T> 类对象
     */
    public static <T> List<T> parseList(String json, Class<T> clazz) {
        return JSONObject.parseArray(json, clazz);

    }

    /**
     * 对象转为json字符串
     *
     * @param obj
     *            对象
     * @return json字符串
     */
    public static String toJson(Object obj) {
        return JSON.toJSONString(obj);
    }

    /**
     * json字符串转为对象
     *
     * @param json
     *            字符串
     * @return json 对象
     */
    public static Object parse(String json) {
        return JSON.parse(json);
    }

    /**
     * 根据排除指定类型的字段列表，转为json
     *
     * @param obj
     *            需转为json的对象
     * @param clazz
     *            类型
     * @param setProperties
     *            需转为json的属性
     * @return json内容
     */
    public static JSONObject filter(Object obj, Class<?> clazz, Set<String> setProperties) {
        Map<Class<?>, Set<String>> mapClass = new HashMap<Class<?>, Set<String>>();
        mapClass.put(clazz, setProperties);
        return filter(obj, mapClass);
    }

    /**
     * 根据排除指定类型的字段列表，转为json eg: List<PageInfoModel> lst = new ArrayList<PageInfoModel>(); PageInfoModel p = new
     * PageInfoModel(); p.setOrderBy("213as"); p.setPageNo(2); p.setPageSize(30); lst.add(p);
     *
     * PageModel n = new PageModel(); n.setPageNo(1); n.setPageSize(6); p.setChild(n);
     *
     * Map<Class, Set<String>> map = new HashMap<Class, Set<String>>(); Set<String> set = new HashSet<String>();
     * set.add("pageNo"); map.put(PageModel.class, set); String rs = JsonUtil.filter(p, map); System.out.println(rs);
     *
     * @param obj
     *            需转为json的对象
     * @param mapClass
     *            需转为json的属性
     * @return json内容
     */
    public static JSONObject filter(Object obj, Map<Class<?>, Set<String>> mapClass) {
        PropertyFilter profilter = new PropertyFilter() {

            @Override
            public boolean apply(Object object, String name, Object value) {
                Set<String> properties = mapClass.get(object.getClass());
                if (null != properties && properties.contains(name)) { // false表示last字段将被排除在外
                    return false;
                }
                return true;
            }
        };

        return JSONObject.parseObject(JSONObject.toJSONString(obj, profilter));
    }

    /**
     * 根据指定字段，转为json eg: String rs = JsonUtil.toJson(lst, PageInfoModel.class, 3, new String[] { "pageSize", "pageNo",
     * "child" });
     *
     * @param obj
     *            需转为json的对象
     * @param filterClass
     *            需转为json的类型，
     * @param maxLeve
     *            转为json深度
     * @param properties
     *            需转为json的属性
     * @return json内容
     */
    public static String toJson(Object obj, Class<?> filterClass, int maxLeve, String[] properties) {
        SimplePropertyPreFilter filter = new SimplePropertyPreFilter(filterClass, properties);
        filter.setMaxLevel(maxLeve);
        return JSONObject.toJSONString(obj, filter);
    }
}
