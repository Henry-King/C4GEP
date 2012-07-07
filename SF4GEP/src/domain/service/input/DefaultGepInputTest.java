package domain.service.input;

import static org.junit.Assert.*;

import java.io.IOException;

import jxl.read.biff.BiffException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import domain.core.inputmodel.DataTable;

public class DefaultGepInputTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testRead() {
		IgepInput di = new DefaultGepInput();
		DataTable dt = new DataTable();
		try {
			di.read(dt);
		} catch (BiffException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		
		
	}

}
