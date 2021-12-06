package de.ialistannen.typerefchecker.util;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.regex.Pattern;

public class AllowList {

	private final List<Predicate<String>> allowed;

	public AllowList() {
		this.allowed = new ArrayList<>();
	}

	public AllowList addAllowed(Pattern regex) {
		this.allowed.add(regex.asMatchPredicate());

		return this;
	}

	public AllowList addAllowed(String fullyQualifiedName) {
		this.allowed.add(fullyQualifiedName::equals);

		return this;
	}

	public boolean isAllowed(String fullyQualifiedName) {
		return allowed.stream().anyMatch(pattern -> pattern.test(fullyQualifiedName));
	}
}
