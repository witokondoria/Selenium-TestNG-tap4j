package test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.lang.reflect.Constructor;
import java.util.Iterator;
import java.util.List;

import org.testng.ITestContext;
import org.testng.annotations.DataProvider;

import com.google.common.collect.Lists;

public class SUT_PlatformDataProvider {

	@DataProvider
	public static Iterator<String[]> textDataProvider(ITestContext context, Constructor<?> testConstructor) throws Exception {
	
		List<String> lSUT = Lists.newArrayList();
		List<String> lPlatform = Lists.newArrayList();
		List<String[]> lData = Lists.newArrayList();			
		String SUT = "";
		String platform = "";
		BufferedReader br;
		
		
		if (System.getProperty("platform") != null) {		
			lPlatform.add(System.getProperty("platform"));
		}
		else {
			br = new BufferedReader(new FileReader("src/test/resources/Platforms.dat"));
			
			while ((platform = br.readLine()) != null) {
				if (!platform.startsWith("#")) {
					lPlatform.add(platform);
				}
			}
			br.close();
			br = null;			
		}
				
		br = new BufferedReader(new FileReader("src/test/resources/SUTs.dat"));
		
		while ((SUT = br.readLine()) != null) {
			if (!SUT.startsWith("#")) {
				lSUT.add(SUT);
			}
		}
		br.close();
		br = null;

		for(String p : lPlatform) {
			for(String s : lSUT) {
				lData.add (new String[] { s, p });
			}
		}
		
		return lData.iterator();
	}
}
