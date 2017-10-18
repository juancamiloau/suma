package suma;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;

import org.apache.tools.ant.Project;
import org.apache.tools.ant.taskdefs.optional.junit.FormatterElement;
import org.apache.tools.ant.taskdefs.optional.junit.JUnitTask;
import org.apache.tools.ant.taskdefs.optional.junit.JUnitTest;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

public class CalculatorTestSuccessful {
	private static ICalculator calculator;

	@BeforeClass
	public static void initCalculator() throws Exception {
		calculator = new Calculator();
		Project project = new Project();
		JUnitTask task = new JUnitTask();
		project.setProperty("java.io.tmpdir","src/test/java");
		task.setProject(project);
		JUnitTask.SummaryAttribute sa = new JUnitTask.SummaryAttribute();
		sa.setValue("withOutAndErr");
		task.setFork(false);
		task.setPrintsummary(sa);
		FormatterElement formater = new FormatterElement();         
		FormatterElement.TypeAttribute type = new FormatterElement.TypeAttribute();
		type.setValue("xml");
		formater.setType(type);
		task.addFormatter(formater);
		JUnitTest test = new JUnitTest(CalculatorTestSuccessful.class.getName());
		File destDir = new File("D:\\results");
		test.setTodir(destDir);
		task.addTest(test);         
		task.execute();
	}

	@Before
	public void beforeEachTest() {
		System.out.println("This is executed before each Test");
	}

	

	@Test
	public void testSum() {
		int result = calculator.sum(3, 4);
		assertEquals(7, result);
	}

	@Test
	public void testDivison() {
		try {
			int result = calculator.divison(10, 2);

			assertEquals(5, result);
		} catch (Exception e) {
			e.printStackTrace(System.err);
		}
	}

	@Test(expected = Exception.class)
	public void testDivisionException() throws Exception {
		calculator.divison(10, 0);
	}

	@Ignore
	@Test
	public void testEqual() {
		boolean result = calculator.equalIntegers(20, 20);

		assertFalse(result);
	}

	@Ignore
	@Test
	public void testSubstraction() {
		int result = 10 - 3;

		assertTrue(result == 9);
	}
}