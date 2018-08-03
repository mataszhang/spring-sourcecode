import lombok.Data;
import lombok.extern.log4j.Log4j;

/**
 * 测试 Lombok ,GenerateAllSetter插件
 *
 * @author matas
 * @date 2018/8/3 11:30
 * @email mataszhang@163.com
 */

@Data
@Log4j
public class TestLombok {
    private String name;
    private String age;

    public static void main(String[] args) {
        TestLombok testLombok = new TestLombok();
        testLombok.setName("test");
        testLombok.setAge("123");
        log.debug(testLombok);
    }
}
