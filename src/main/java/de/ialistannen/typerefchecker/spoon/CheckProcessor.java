package de.ialistannen.typerefchecker.spoon;

import de.ialistannen.typerefchecker.util.AllowList;
import spoon.processing.AbstractProcessor;
import spoon.reflect.declaration.CtNamedElement;
import spoon.reflect.declaration.CtType;
import spoon.reflect.reference.CtTypeReference;

public class CheckProcessor extends AbstractProcessor<CtTypeReference<?>> {

	private final AllowList allowList;

	public CheckProcessor(AllowList allowList) {
		this.allowList = allowList;
	}

	@Override
	public void process(CtTypeReference<?> typeRef) {
		if (typeRef.isImplicit()) {
			return;
		}
		System.out.println("Checking " + typeRef);

		if (typeRef.isPrimitive()) {
			return;
		}

		if (!allowList.isAllowed(typeRef.getQualifiedName())) {
			CtNamedElement namedParent = typeRef.getParent(CtNamedElement.class);
			CtType<?> typeParent = typeRef.getParent(CtType.class);

			String parentName = typeParent != null ? typeParent.getQualifiedName() : "<unknown>";
			String namedParentName = namedParent != null
				? namedParent.getReference().toString()
				: "<unknown>";

			throw new IllegalArgumentException(
				"Found '" + typeRef.getQualifiedName()
					+ "' in " + parentName + "#" + namedParentName
					+ " at " + typeRef.getParent().getPosition()
			);
		}
	}
}
