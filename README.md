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

- `plugin-tzatziki` : Cucumber+ plugin code
- `plugin-i18n` : Translation+ plugin code  
- `extensions` : Language extensions (Java, Kotlin, Scala)
- `common` : Shared code used by plugins and extensions
- `i18n` : Internationalization utilities
- `tests` : Test suite

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
- **Gradle 8.13+** (included via wrapper)
- **IntelliJ IDEA 2022.2+** (for testing)

### Building the Project

1. **Clone the repository**
   ```bash
   git clone https://github.com/maximehamm/tzatziki.git
   cd tzatziki
   ```

2. **Build all modules**
   ```bash
   ./gradlew clean build -x test
   ```

3. **Build specific plugins**
   ```bash
   # Cucumber+ plugin
   ./gradlew :plugin-tzatziki:buildPlugin
   
   # Translation+ plugin
   ./gradlew :plugin-i18n:buildPlugin
   ```

   Plugin distributions will be in:
   - `plugin-tzatziki/build/distributions/plugin-tzatziki-{version}.zip`
   - `plugin-i18n/build/distributions/plugin-i18n-{version}.zip`

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

## Migration Notes

### Migrating to IntelliJ 2025.3

The project has been updated for IntelliJ IDEA 2025.3 compatibility:
- Upgraded to IntelliJ Platform Gradle Plugin 2.10.4
- Updated to Kotlin 2.2.0
- Migrated to Plugin Model v2
- Fixed API breaking changes (ProblemNode, nullability improvements)
- Gradle wrapper updated to 8.13

For plugin users, versions 17.10.0+ support IntelliJ IDEA 2025.3.

## Testing

A comprehensive testing guide with detailed feature walkthroughs is available in [TESTING_GUIDE.md](TESTING_GUIDE.md).

Sample projects for testing are included:
- `sample/java-easy-as-pie/` - Java with Cucumber
- `sample/kotlin-easy-as-pie/` - Kotlin with Cucumber
- `sample/javascript-easy-as-pie/` - JavaScript with Cucumber
- `sample/rich-example/` - Multi-module Gradle project

## Credits

*Many thanks to Pierre-Michel BRET for his contribution, the great PDF generator, allowing to realize a complete layout with page numbers, summary, paragraphs management, etc.*

## License

See individual plugin pages for license information.

Enjoy! 🥒✨


