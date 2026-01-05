# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

This is a Spring Boot 3.4.3 application (`jeff-playground-7`) using Java 17. The project uses Gradle as its build tool and includes Lombok for code generation. Package structure is `com.ej31.jeffplayground7`.

## Build & Development Commands

### Building the Application
```bash
./gradlew build
```

### Running the Application
```bash
./gradlew bootRun
```

### Running Tests
```bash
# Run all tests
./gradlew test

# Run a specific test class
./gradlew test --tests "com.ej31.jeffplayground7.JeffPlayground7ApplicationTests"

# Run a specific test method
./gradlew test --tests "com.ej31.jeffplayground7.ClassName.testMethodName"
```

### Clean Build
```bash
./gradlew clean build
```

## Release Process

This project uses **semantic-release** for automated version management and releases. The release workflow is triggered on pushes to the `main` branch.

### Commit Message Convention
Follow conventional commits format for semantic versioning:
- `feat:` - triggers a minor version bump
- `fix:` - triggers a patch version bump
- `BREAKING CHANGE:` - triggers a major version bump

### Version Management
- Versions are automatically determined by semantic-release based on commit messages
- The `RELEASE_VERSION` environment variable can override the version in build.gradle
- Default version is `0.0.1-SNAPSHOT` when not in a release build

### Release Workflow
The GitHub Actions workflow (`.github/workflows/release.yml`) automatically:
1. Analyzes commits since the last release
2. Determines the next version number
3. Generates changelog entries
4. Creates a GitHub release
5. Commits the updated CHANGELOG.md

## Architecture Notes

### Technology Stack
- **Framework**: Spring Boot 3.4.3
- **Java Version**: 17
- **Build Tool**: Gradle
- **Key Dependencies**:
  - Spring Web (REST APIs)
  - Lombok (code generation)
  - Spring Boot DevTools (hot reload in development)
  - JUnit 5 (testing)

### Project Structure
```
src/
  main/
    java/com/ej31/jeffplayground7/  - Main application code
    resources/                       - Configuration files
  test/
    java/com/ej31/jeffplayground7/  - Test code
```

### Configuration
- Application properties: `src/main/resources/application.properties`
- Main application class: `JeffPlayground7Application.java`
