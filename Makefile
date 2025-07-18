# Format the code
.PHONY: format
format:
	mvn spotless:apply spring-javaformat:apply

# Run tests and verify
.PHONY: mvn-verify
mvn-verify: format
	mvn clean verify

# Skip tests and verify
.PHONY: mvn-verify-skip-tests
mvn-verify-skip-tests: format
	mvn clean verify -DskipTests

.PHONY: mvn-verify-ci
mvn-verify-ci:
	mvn -B -s ./.mvn/settings.xml verify

# Run tests and install locally
.PHONY: mvn-install
mvn-install: format
	mvn clean install

# Skip tests and install locally
.PHONY: mvn-install-skip-tests
mvn-install-skip-tests: format
	mvn clean install -DskipTests

.PHONY: mvn-deploy-cd
mvn-deploy-cd:
	mvn -B -s ./.mvn/settings.xml deploy

# Check if version is a SNAPSHOT
.PHONY: check-if-snapshot-version
check-if-snapshot-version:
	version="$(shell mvn help:evaluate -Dexpression=spring-forge.version -q -DforceStdout)" ; \
	echo "version is $$version" ; \
	if [[ $$version == *-SNAPSHOT ]]; then \
		echo "Release version is a SNAPSHOT, so manual deploy can proceed." ; \
	else \
		echo "Release version is NOT a SNAPSHOT. Use a SNAPSHOT version to deploy or deploy with GitHub Actions CD." ; \
		exit 1 ; \
	fi

# Run tests and deploy SNAPSHOT version
.PHONY: mvn-deploy-snapshot
mvn-deploy-snapshot: check-if-snapshot-version format
	mvn clean deploy

# Skip tests and deploy SNAPSHOT version
.PHONY: mvn-deploy-snapshot-skip-tests
mvn-deploy-snapshot-skip-tests: check-if-snapshot-version format
	mvn clean deploy -DskipTests
