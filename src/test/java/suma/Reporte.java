package suma;

import java.io.File;

import org.apache.tools.ant.Project;
import org.apache.tools.ant.taskdefs.optional.junit.FormatterElement;
import org.apache.tools.ant.taskdefs.optional.junit.JUnitTask;
import org.apache.tools.ant.taskdefs.optional.junit.JUnitTest;



public class Reporte {
	public void PintarReporte() throws Exception {
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
}
