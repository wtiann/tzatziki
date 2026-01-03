# Testing Guide for Cucumber+ & Translation+ Plugins

## Build Information
- **Build Date**: January 2, 2026
- **Cucumber+ Version**: 17.10.0
- **Translation+ Version**: 10.5.0
- **IntelliJ Compatibility**: 2022.2 - 2025.3.x
- **Java Version**: 21
- **Kotlin Version**: 2.2.0

## Plugin Locations

The built plugins are ready for installation:

```
plugin-tzatziki/build/distributions/plugin-tzatziki-17.10.0.zip (17 MB)
plugin-i18n/build/distributions/plugin-i18n-10.5.0.zip (4.9 MB)
```

## Installation Instructions

### Method 1: Install from Disk (Recommended for Testing)

1. Open IntelliJ IDEA (2022.2 or later, preferably 2025.3)
2. Navigate to: **Settings/Preferences** → **Plugins**
3. Click the ⚙️ (gear icon) in the top bar
4. Select **Install Plugin from Disk...**
5. Navigate to and select:
   - `plugin-tzatziki/build/distributions/plugin-tzatziki-17.10.0.zip` (for Cucumber+)
   - `plugin-i18n/build/distributions/plugin-i18n-10.5.0.zip` (for Translation+)
6. Click **OK** to install
7. **Restart IntelliJ IDEA** when prompted

### Method 2: Run in Test IDE (Development/Debugging)

```bash
# Run Cucumber+ in sandboxed IDE
./gradlew :plugin-tzatziki:runIde

# OR run Translation+ in sandboxed IDE
./gradlew :plugin-i18n:runIde
```

This launches a separate IntelliJ instance with the plugin pre-installed.

---

## Testing Checklist

### 🥒 Cucumber+ Plugin Testing

#### 1. Table Formatting & Editing
- [ ] Open/Create a `.feature` file with a Gherkin table
- [ ] Type `|` characters - table should auto-format with aligned columns
- [ ] Press Tab/Shift+Tab to navigate between cells
- [ ] Press Enter at end of row to create new row
- [ ] Use Shift+Ctrl+Arrow keys to move rows/columns
- [ ] Double-click column header to select entire column
- [ ] Double-click table cell to select entire row

#### 2. Excel Integration
- [ ] Copy cells from Excel (Ctrl+C)
- [ ] Paste into Gherkin table (Ctrl+V)
- [ ] Copy from Gherkin table
- [ ] Paste into Excel - should preserve structure

#### 3. Step Completion
- [ ] In a scenario, type a step keyword (Given/When/Then)
- [ ] Press Ctrl+Space
- [ ] Verify completion suggestions appear with:
  - Existing step implementations
  - Steps from other feature files
  - Usage counts (e.g., "+2")
  - Deprecated steps shown with strikethrough

#### 4. Tag Completion
- [ ] Type `@` in feature file
- [ ] Press Ctrl+Space
- [ ] Verify tag suggestions from project

#### 5. Run Configuration
- [ ] Right-click on a Scenario
- [ ] Select "Run 'Scenario: ...'"
- [ ] Verify test executes (requires Cucumber test setup)
- [ ] Check if passed/failed colors appear in editor after run

#### 6. Tool Window
- [ ] Open: **View** → **Tool Windows** → **Cucumber+**
- [ ] Verify features tree loads
- [ ] Test grouping options (by tag, by module)
- [ ] Test filter functionality
- [ ] Try running a feature from tool window

#### 7. PDF Export
- [ ] Right-click a `.feature` file
- [ ] Select **Export to PDF**
- [ ] Configure options if dialog appears
- [ ] Verify PDF is generated and viewable

#### 8. Breakpoints (Java/Kotlin projects only)
- [ ] Set breakpoint on Gherkin step
- [ ] Verify breakpoint marker appears
- [ ] Debug a test
- [ ] Check if debugger stops at Gherkin breakpoint

#### 9. Line Markers
- [ ] In Java/Kotlin step definition files
- [ ] Look for usage count icons next to `@Given/@When/@Then` methods
- [ ] Click icon to navigate to usages

---

### 🌍 Translation+ Plugin Testing

#### 1. Basic Translation
- [ ] Select any text in any file
- [ ] Press **Ctrl+T** (or right-click → **Translate**)
- [ ] Verify translation dialog appears
- [ ] Select target language
- [ ] Click Translate
- [ ] Verify translation appears
- [ ] Apply translation if desired

#### 2. Settings Configuration
- [ ] Open: **Settings/Preferences** → **Tools** → **Translation+**
- [ ] Set default input language
- [ ] Set default output language
- [ ] Configure translation engine (Google/DeepL/etc.)
- [ ] Add API keys if using paid services
- [ ] Test connection

#### 3. Translation View
- [ ] Open: **View** → **Tool Windows** → **Translation+**
- [ ] Enter text in input area
- [ ] Select languages
- [ ] Click translate button
- [ ] Verify translation appears
- [ ] Test swap languages button

#### 4. Dictionary View (English words only)
- [ ] Select an English word
- [ ] Open: **View** → **Tool Windows** → **Dictionary**
- [ ] Verify definition, pronunciation, examples appear
- [ ] Test audio pronunciation if available

#### 5. Smart Refactoring Translation
- [ ] Select a variable/method name
- [ ] Translate it
- [ ] Choose "Refactor" option
- [ ] Verify IntelliJ's rename refactoring dialog appears
- [ ] Check that it preserves naming style (camelCase/snake_case/UPPER_CASE)
- [ ] Verify all references are found

#### 6. Translation Engines
Test each available engine:
- [ ] **Google Translate** (free, no API key)
- [ ] **DeepL** (requires API key)
- [ ] **Microsoft Translator** (requires API key)
- [ ] **Baidu** (requires API key)
- [ ] **ChatGPT/OpenAI** (requires API key)

#### 7. Format Support
Test translation preserves format:
- [ ] HTML tags
- [ ] JSON structure
- [ ] XML elements
- [ ] CSV format
- [ ] Properties file format

---

## Common Issues & Solutions

### Plugin Not Appearing After Install
- **Solution**: Make sure you restarted IntelliJ IDEA completely
- Check: **Settings** → **Plugins** → **Installed** to verify plugins are enabled

### "Unsupported Java Version" Error
- **Solution**: Ensure you're using Java 21 or higher
- Check: **Settings** → **Build, Execution, Deployment** → **Build Tools** → **Gradle** → **Gradle JVM**

### "Plugin is incompatible" Error
- **Solution**: Verify IntelliJ version is 2022.2 or later
- Check: **Help** → **About** to see your IntelliJ version
- Upgrade IntelliJ if needed

### Cucumber+ Features Not Working
- **Required**: Must have Gherkin plugin and Cucumber for Java/Kotlin plugin installed
- Install from: **Settings** → **Plugins** → **Marketplace**
  - Search for "Gherkin"
  - Search for "Cucumber for Java" (or Kotlin/Scala)

### Translation+ Not Translating
- **Check network connection** - translation requires internet
- **Try Google Translate first** - doesn't require API key
- **Check Settings** → **Tools** → **Translation+** for proper configuration

### Performance Issues
- **Large projects**: Disable "Reference Contributor" if available in settings
- **Clear caches**: **File** → **Invalidate Caches / Restart**

---

## Reporting Issues

If you encounter problems:

1. **Check IntelliJ IDEA logs**:
   - **Help** → **Show Log in Finder/Explorer**
   - Look for exceptions related to `io.nimbly.tzatziki` or `io.nimbly.i18n`

2. **Capture screenshots** of the issue

3. **Note your environment**:
   - IntelliJ IDEA version
   - Operating System
   - Java version
   - Project type (Gradle/Maven/etc.)

4. **Report to**: 
   - Original repo: https://github.com/maximehamm/tzatziki/issues
   - Or your fork: https://github.com/wtiann/tzatziki/issues

---

## What's New in Version 17.10 / 10.5

### Cucumber+ 17.10
- ✅ IntelliJ IDEA 2025.3 compatibility
- ✅ Upgraded to Kotlin 2.2.0
- ✅ Migrated to Plugin Model v2
- ✅ Fixed all deprecated API warnings
- ✅ Updated copyright years
- ✅ Enhanced documentation

### Translation+ 10.5
- ✅ IntelliJ IDEA 2025.3 compatibility  
- ✅ Upgraded to Kotlin 2.2.0
- ✅ Migrated to Plugin Model v2
- ✅ Fixed all deprecated API warnings
- ✅ Updated copyright years

---

## Sample Test Project

For testing Cucumber+, you can use the sample projects in this repo:

```bash
# Java example
cd sample/java-easy-as-pie/

# Kotlin example  
cd sample/kotlin-easy-as-pie/

# JavaScript example
cd sample/javascript-easy-as-pie/
```

Open these in IntelliJ to test Cucumber+ features with real feature files.

---

## Quick Uninstall

If you need to remove the plugins:

1. **Settings/Preferences** → **Plugins**
2. Find "Cucumber+" or "Translation+" in **Installed** tab
3. Click the ⚙️ next to the plugin
4. Select **Uninstall**
5. Restart IntelliJ IDEA

---

## Next Steps After Testing

- ✅ Test thoroughly and note any issues
- ✅ Provide feedback on what works well
- ✅ Suggest improvements or new features
- ✅ Consider contributing to upstream repository
- ✅ Create pull request with improvements

**Happy Testing! 🥒✨🌍**
