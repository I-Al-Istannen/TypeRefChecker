package de.ialistannen.typerefchecker;

import de.ialistannen.typerefchecker.spoon.CheckProcessor;
import de.ialistannen.typerefchecker.util.AllowList;
import java.util.regex.Pattern;
import spoon.Launcher;
import spoon.processing.AbstractProcessor;
import spoon.reflect.CtModel;
import spoon.reflect.declaration.CtType;

public class TypeRefChecker {

	public static void main(String[] args) {
		Launcher launcher = new Launcher();
		launcher.addInputResource("src/test/java/sampleproject");
		launcher.getEnvironment().shouldCompile();
		launcher.getEnvironment().setCommentEnabled(false);
		launcher.getEnvironment().setComplianceLevel(11);

		AllowList allowList = new AllowList()
			.addAllowed(Pattern.compile("java\\.lang\\..*"))
			.addAllowed("java.io.PrintStream");

		CtModel model = launcher.buildModel();

		whitelistUserTypes(allowList, model);

		model.processWith(new CheckProcessor(allowList));
	}

	private static void whitelistUserTypes(AllowList allowList, CtModel model) {
		model.processWith(new AbstractProcessor<CtType<?>>() {
			@Override
			public void process(CtType<?> element) {
				System.out.println("Whitelisting user type '" + element.getQualifiedName() + "'");
				allowList.addAllowed(element.getQualifiedName());
			}
		});
	}
}
