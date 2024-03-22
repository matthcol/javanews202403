package tu.news.util;

import news.util.CityUtil;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CityUtilTests {
    @Test
    void testCityCode_ok(){
        var cityCode = CityUtil.code("Toulouse", List.of("Pau", "Toulouse", "Rennes"));
        assertEquals("TOU", cityCode);
    }

    @Test
    void testCityCode_ko(){
        var ex = assertThrows(IllegalArgumentException.class,
                () -> CityUtil.code("Toulouse", List.of("Pau", "Montauban", "Rennes"))
        );
        assertEquals("No city found", ex.getMessage());
    }

}