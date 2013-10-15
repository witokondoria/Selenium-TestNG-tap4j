package test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.lang.reflect.Constructor;
import java.util.Iterator;
import java.util.List;

import org.testng.ITestContext;
import org.testng.annotations.DataProvider;

import com.google.common.collect.Lists;

public class TestDataProvider {

	@DataProvider
	public static Iterator<Object[]> textDataProvider(ITestContext context, Constructor<?> testConstructor) throws Exception {
	
		List<Object[]> lSUT = Lists.newArrayList();			
		String SUT = "";
		BufferedReader br;

		br = new BufferedReader(new FileReader("src/test/resources/SUTs.dat"));

		while ((SUT = br.readLine()) != null) {
			lSUT.add(new Object[] { SUT });
		}
		br.close();
		br = null;
					
		return lSUT.iterator();
	}
}
