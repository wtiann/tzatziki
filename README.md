# Cucumber+ & Translation+

Developed by Maxime HAMM  
_Maxime.HAMM@nimbly-consulting.com_

IntelliJ IDEA plugins for Cucumber/Gherkin development and translation features.

## Compatibility

| Plugin Version | IntelliJ IDEA Versions | Java Version | Kotlin Version |
|---------------|------------------------|--------------|----------------|
| 17.10.0+      | 2022.2 - 2025.3.x      | 21           | 2.2.0          |
| 16.x - 17.9.x | 2022.2 - 2024.3.x      | 11           | 2.0.21         |

## Project Structure

```
tzatziki/
├── plugin-tzatziki/        # Cucumber+ plugin (main product)
│   ├── src/main/kotlin/    # Plugin implementation
│   ├── src/main/resources/ # Plugin resources, icons, color schemes
│   └── build.gradle.kts    # Plugin build configuration
├── plugin-i18n/            # Translation+ plugin (separate product)
│   ├── src/main/kotlin/    # Translation engine implementations
│   └── build.gradle.kts    # Plugin build configuration
├── extensions/             # Language-specific extensions
│   ├── java-cucumber/      # Java Cucumber support
│   ├── kotlin/             # Kotlin support
│   └── scala/              # Scala support
├── common/                 # Shared utilities and services
│   └── src/main/kotlin/    # Core utilities, icon management
├── i18n/                   # Translation core library
│   └── src/main/kotlin/    # Translation engines, dictionary API
├── tests/                  # Integration tests
├── sample/                 # Sample projects for testing
│   ├── java-easy-as-pie/   # Simple Java Cucumber project
│   ├── kotlin-easy-as-pie/ # Simple Kotlin project
│   └── rich-example/       # Complex multi-module example
├── build.gradle.kts        # Root build configuration
└── settings.gradle.kts     # Gradle settings and module declarations
```

## Market Place

- **Cucumber+**: https://plugins.jetbrains.com/plugin/16289-cucumber-
- **Translation+**: https://plugins.jetbrains.com/plugin/23656-translation-

## Key Features

### Cucumber+ Plugin

- **Smart Table Editing**: Auto-formatting Gherkin tables with Excel integration (copy/paste)
- **Step Completion**: Intelligent step suggestions with usage counts and deprecation status
- **Test Execution**: Run scenarios with visual success/failure indicators
- **Breakpoint System**: Debug Gherkin files with bidirectional breakpoint sync (Java/Kotlin/Scala)
- **PDF Export**: Export features to professionally styled PDF documents
- **Tool Window**: Organize and filter features by tags, run tests directly
- **Line Markers**: See step usage counts and navigate to implementations
- **Markdown Support**: Render images and links in feature files

### Translation+ Plugin

- **Multiple Translation Engines**: Google Translate (free), DeepL, Microsoft Translator, Baidu, ChatGPT/OpenAI
- **Smart Refactoring**: Translate code identifiers while preserving naming conventions (camelCase, snake_case, etc.)
- **In-Editor Translation**: Select and translate any text with Ctrl+T
- **Dictionary View**: English word definitions with pronunciation
- **Format Preservation**: Maintains structure for HTML, JSON, XML, CSV files
- **120+ Languages**: Comprehensive language support

## Quick Start

### Using PDF Export

1. **Right-click** a `.feature` file in the editor or project view
2. Select **"Export feature to PDF"** (or "Export features to PDF" for multiple files)
3. Choose **Portrait** or **Landscape** orientation
4. PDF is automatically generated and opened

**Customize PDF output:**
- Create `.cucumber+` folder in your resources directory
- Add `cucumber+.properties` for headers, footers, front page content
- Add `cucumber+.css` for custom styling
- Add `cucumber+.template.ftl` for custom front page template

See [TESTING_GUIDE.md](TESTING_GUIDE.md) for detailed PDF export configuration options.

### Using Translation

1. **Select text** in any file
2. Press **Ctrl+T** (or right-click → Translate)
3. Choose target language and engine
4. **Apply** to replace text or **Refactor** to rename code identifiers

Configure translation engines in **Settings → Tools → Translation+**

## Development Setup

### Prerequisites
- **Java 21** (JDK 21 or higher required)
- **Gradle 8.13+** (included via wrapper - `./gradlew`)
- **IntelliJ IDEA 2022.2+** (for plugin development and testing)
- **Git** (for version control)

### Technology Stack
- **Language**: Kotlin 2.2.0
- **Build Tool**: Gradle 8.13 with Kotlin DSL
- **Plugin Framework**: IntelliJ Platform Gradle Plugin 2.10.4
- **Target Platform**: IntelliJ IDEA 2025.3.1 (Build 253.29346.138)
- **Supported IntelliJ Versions**: 2022.2 through 2025.3.x

### Initial Setup

1. **Clone the repository**
   ```bash
   git clone https://github.com/maximehamm/tzatziki.git
   cd tzatziki
   ```

2. **Verify Java version**
   ```bash
   java -version  # Should be 21 or higher
   ```

3. **Build all modules** (first-time setup)
   ```bash
   ./gradlew clean build -x test
   ```
   
   This will:
   - Download Gradle dependencies
   - Compile all Kotlin code
   - Build plugin distributions
   - Skip tests (use `./gradlew clean build` to include tests)

### Building Plugins

**Build both plugins**
```bash
./gradlew :plugin-tzatziki:buildPlugin :plugin-i18n:buildPlugin
```

**Build Cucumber+ plugin only**
```bash
./gradlew :plugin-tzatziki:buildPlugin
```

**Build Translation+ plugin only**
```bash
./gradlew :plugin-i18n:buildPlugin
```

**Build outputs:**
- Cucumber+: `plugin-tzatziki/build/distributions/plugin-tzatziki-17.10.0.zip`
- Translation+: `plugin-i18n/build/distributions/plugin-i18n-10.5.0.zip`

### Installing Built Plugins Locally

1. Open IntelliJ IDEA (version 2022.2 or higher)
2. Go to **Settings → Plugins** (⌘, on Mac / Ctrl+Alt+S on Windows/Linux)
3. Click **⚙️ (Settings icon) → Install Plugin from Disk...**
4. Navigate to and select the `.zip` file from `build/distributions/`
5. Click **OK** and **Restart IDE**

### Running & Testing

**Run IntelliJ IDEA with plugin**
```bash
./gradlew :plugin-tzatziki:runIde
# or
./gradlew :plugin-i18n:runIde
```

**Run tests**
```bash
./gradlew test
```

**Verify plugin compatibility**
```bash
./gradlew :plugin-tzatziki:verifyPlugin
./gradlew :plugin-i18n:verifyPlugin
```

### Testing with Sample Projects

The repository includes sample projects in the `sample/` directory for testing:

- **`java-easy-as-pie/`** - Simple Java Cucumber project with basic features
- **`kotlin-easy-as-pie/`** - Kotlin implementation example
- **`javascript-easy-as-pie/`** - JavaScript/Node.js Cucumber project
- **`rich-example/`** - Complex multi-module Gradle project with multiple sub-projects

**To test manually:**
1. Run `./gradlew :plugin-tzatziki:runIde` to launch IntelliJ with the plugin
2. Open one of the sample projects in the test IDE
3. Test features like table editing, PDF export, test execution, etc.
4. See [TESTING_GUIDE.md](TESTING_GUIDE.md) for comprehensive testing checklists

## Performance Optimizations

### Cucumber+ Reference Resolution

The plugin implements aggressive performance optimizations for step reference resolution, which was previously causing slowdowns in large projects:

**Multi-level Caching Strategy:**
1. **ResolveCache** - IntelliJ's built-in cache with automatic PSI invalidation
2. **CachedValuesManager** - Per-file step definition caching (invalidated on code changes)
3. **LAST_VALID fallback** - Returns last successful resolution when current resolution fails
4. **ProgressManager.checkCanceled()** - Allows IDE to interrupt long operations
5. **Background processing** - Non-blocking resolution with EmptyProgressIndicator

**Results:**
- Instant step navigation even in projects with 1000+ step definitions
- No UI freezing during file editing
- Proper cache invalidation when step definitions change

See `TzCucumberStepReference.kt` for implementation details.

## Publishing to JetBrains Marketplace

### Setup Publishing Token

Set up your JetBrains Marketplace token (contact Maxime HAMM for token):

**Option 1: Environment Variable (Recommended)**
```bash
# Add to ~/.bash_profile or ~/.zshrc
export ORG_GRADLE_PROJECT_intellijPublishToken='your-token-here'
source ~/.bash_profile  # or ~/.zshrc
```

**Option 2: Gradle Properties**
```bash
# Add to ~/.gradle/gradle.properties
intellijPublishToken=your-token-here
```

**Option 3: Command Line**
```bash
./gradlew publishPlugin -DPublishToken=your-token-here
```

### Release Checklist

Before publishing a new release:

1. **Update Version Numbers**
   - Edit `version` in `./build.gradle.kts` (line 7) for Cucumber+
   - Edit `version` in `./plugin-i18n/build.gradle.kts` (line 7) for Translation+

2. **Update Change Notes**
   - Edit `notes` variable in `./build.gradle.kts` (Cucumber+)
   - Edit `notes` variable in `./plugin-i18n/build.gradle.kts` (Translation+)

3. **Clean Build & Test**
   ```bash
   ./gradlew clean
   ./gradlew build
   ./gradlew test
   ```

4. **Verify Plugins**
   ```bash
   ./gradlew :plugin-tzatziki:verifyPlugin
   ./gradlew :plugin-i18n:verifyPlugin
   ```

5. **Test Manually**
   ```bash
   ./gradlew :plugin-tzatziki:runIde
   # Test key features manually
   ```

6. **Build Plugin Distributions**
   ```bash
   ./gradlew :plugin-tzatziki:buildPlugin
   ./gradlew :plugin-i18n:buildPlugin
   ```

### Publishing

Publish to JetBrains Marketplace:

```bash
# Publish Cucumber+ plugin
./gradlew :plugin-tzatziki:publishPlugin

# Publish Translation+ plugin  
./gradlew :plugin-i18n:publishPlugin
```

**Note:** Plugins are reviewed by JetBrains and may take 1-2 business days to appear in the marketplace.

## Common Development Tasks

### Gradle Commands Reference

```bash
# Clean build artifacts
./gradlew clean

# Build everything (compile + test)
./gradlew build

# Build without tests (faster)
./gradlew build -x test

# Run specific module tests
./gradlew :plugin-tzatziki:test
./gradlew :common:test

# Run single test class
./gradlew :plugin-tzatziki:test --tests "io.nimbly.tzatziki.MyTest"

# Check for dependency updates
./gradlew dependencyUpdates

# Generate coverage report
./gradlew test jacocoTestReport
# Report at: build/reports/jacoco/test/html/index.html
```

### IDE Setup for Development

1. **Open project in IntelliJ IDEA**
   - Use IntelliJ IDEA 2022.2 or higher
   - Open `tzatziki/build.gradle.kts` as a project
   - Wait for Gradle sync to complete

2. **Configure SDK**
   - Go to **File → Project Structure → Project**
   - Set **SDK** to Java 21
   - Set **Language Level** to 21

3. **Enable Kotlin plugin**
   - Already bundled with IntelliJ IDEA

4. **Run configurations**
   - Gradle tasks are available in the Gradle tool window (right side)
   - To debug plugin: Right-click `plugin-tzatziki:runIde` → Debug

### Debugging the Plugin

**Method 1: Using Gradle**
```bash
# Run with debugger attached
./gradlew :plugin-tzatziki:runIde --debug-jvm
```
Then attach IntelliJ debugger to port 5005.

**Method 2: Using IDE**
1. Open Gradle tool window
2. Navigate to `tzatziki → plugin-tzatziki → Tasks → intellij → runIde`
3. Right-click → Debug
4. Set breakpoints in plugin code
5. Test IDE will launch with debugger attached

### Troubleshooting

**Build fails with "Java version" error**
```bash
# Check Java version
java -version  # Must be 21+

# If multiple Java versions installed
export JAVA_HOME=/path/to/jdk-21
./gradlew clean build
```

**Gradle daemon issues**
```bash
# Stop all Gradle daemons
./gradlew --stop

# Clean and rebuild
./gradlew clean build --no-daemon
```

**Plugin not loading in test IDE**
```bash
# Clear IntelliJ caches
rm -rf build/idea-sandbox/

# Rebuild plugin
./gradlew clean :plugin-tzatziki:buildPlugin
./gradlew :plugin-tzatziki:runIde
```

**OutOfMemoryError during build**
```bash
# Increase Gradle memory in gradle.properties
echo "org.gradle.jvmargs=-Xmx2048m -XX:MaxMetaspaceSize=512m" >> gradle.properties
```

## Migration Notes

### Migrating to IntelliJ 2025.3

The project has been updated for IntelliJ IDEA 2025.3 compatibility:
- Upgraded to IntelliJ Platform Gradle Plugin 2.10.4
- Updated to Kotlin 2.2.0
- Migrated to Plugin Model v2
- Fixed API breaking changes (ProblemNode, nullability improvements)
- Gradle wrapper updated to 8.13

For plugin users, versions 17.10.0+ support IntelliJ IDEA 2025.3.

## Credits

*Many thanks to Pierre-Michel BRET for his contribution, the great PDF generator, allowing to realize a complete layout with page numbers, summary, paragraphs management, etc.*

## License

See individual plugin pages for license information.

Enjoy! 🥒✨


