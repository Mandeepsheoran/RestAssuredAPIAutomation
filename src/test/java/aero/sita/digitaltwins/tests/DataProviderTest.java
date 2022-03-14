package aero.sita.digitaltwins.tests;

import java.util.HashMap;
import java.util.Map;

import org.assertj.core.api.Assertions;
import org.assertj.core.util.Arrays;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DataProviderTest {
	@Test(dataProvider="getData2")
	public void dPTest(Map<String, Object> map) {		
			System.out.println(map.get("name"));
			System.out.println(map.get("age"));
			System.out.println(map.get("gender"));
			Assertions.assertThat("true").isEqualTo("false");
	}
	@DataProvider
	public Object[][] getData1(){
		return new Object[][] {
			{"Mandeep", 24, 9865432, "mandeep@gmail.com", "Male"},
			{"Sudhansu", 30, 5757657, "sudhansu@gmail.com", "Male"},
			{"Singh", 22, 67676, "singh@gmail.com", "Female"}
		};
	}
	
	@DataProvider
	public Object[][] getData2(){		
		Object[][] mapdata = new Object[3][1];
			Map<String, Object> map1 = new HashMap<>();
			map1.put("name", "mandeep");
			map1.put("age", 24);
			map1.put("mobno", 67687);
			map1.put("email", "mandeep@gmail.com");
			map1.put("gender", "Male");
			
			Map<String, Object> map2 = new HashMap<>();
			map1.put("name", "sudhansu");
			map1.put("age", 30);
			map1.put("mobno", 44444);
			map1.put("email", "su@gmail.com");
			map1.put("gender", "Male");
			
			Map<String, Object> map3 = new HashMap<>();
			map1.put("name", "singh");
			map1.put("age", 20);
			
			map1.put("mobno", 888888);
			map1.put("email", "singh@gmail.com");
			map1.put("gender", "female");
			
			mapdata[0][0]= map1;
			mapdata[1][0]= map2;
			mapdata[2][0] = map3;
			
			return mapdata;	
	}
}
