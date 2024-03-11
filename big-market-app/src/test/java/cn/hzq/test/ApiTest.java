package cn.hzq.test;

import cn.hzq.infrastructure.persistent.redis.IRedisService;
import cn.hzq.types.common.Constants;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.redisson.api.RMap;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class ApiTest {

    @Resource
    private IRedisService redisService;

    @Test
    public void test() {
        RMap<Object, Object> map = redisService.getMap("strategy_id_100001");
        map.put(1, 101);
        map.put(2, 101);
        map.put(3, 101);
        map.put(4, 102);
        map.put(5, 102);
        map.put(6, 102);
        map.put(7, 103);
        map.put(8, 103);
        map.put(9, 104);
        map.put(10, 105);
        log.info("测试结果；{}", redisService.getFromMap("strategy_id_100001", 1).toString());

    }

    /*@Test
    public void testString() {

        String ruleValue = "6000:102,103,104,105,106,107,108 5000:101,102,103";
        String[] ruleValueGroups = ruleValue.split(Constants.SPACE);
        Map<String, List<Integer>> resultMap = new HashMap<>();
        for (String ruleValueGroup : ruleValueGroups) {
            //检查输入是否为空
            if (ruleValueGroup == null || ruleValueGroup.isEmpty()) {
                System.out.println("ruleValue为空");
                return;
            }
            //分割字符串获取键和值
            String[] parts = ruleValueGroup.split(Constants.COLON);
            if (parts.length != 2) {
                throw new IllegalArgumentException("rule_weight rule_rule invalid input format " + ruleValueGroup);
            }
            //解析值
            String[] valueStrings = parts[1].split(Constants.SPLIT);
            ArrayList<Integer> values = new ArrayList<>();
            for (String valueString : valueStrings) {
                values.add(Integer.parseInt(valueString));
            }
            resultMap.put(parts[0], values);
        }
        System.out.println(resultMap);
    }*/
}


