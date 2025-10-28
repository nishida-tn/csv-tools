# 🧩 CSV Tools

**CSV Tools** is a desktop application built with **Kotlin Multiplatform** and **JetBrains Compose for Desktop**. It provides an intuitive interface to **generate and manipulate CSV files** used for importing Serial Numbers (SNs) into the **MDM system**, helping teams to efficiently manage SN groups and automate repetitive CSV operations.

## 🚀 Features

- 📂 **Load existing CSV files** to view or modify their contents
- ✏️ **Manually edit or paste SNs** directly into the input area
- 🧾 **Generate CSV files** (`output.csv`) with the format `identifier,name` for MDM import
- 🔁 **Convert SN lists** into a quoted format for POST requests (used for whitelisting)
- 💾 **Save CSV files** to a custom location, automatically adding `.csv` extension if missing
- ⚙️ **Cross-platform builds:** Windows (.exe/.msi), Linux (.deb), and macOS (.dmg)
- 🧠 **Automatic validation** for empty lists or invalid file selections

## 🖥️ Application Flow

### 1. Load CSV
The app allows the user to select an existing `.csv` file using a file chooser.  
If the selected file is valid, its contents are displayed in the input area. Otherwise, an error message is shown (`"Select a valid CSV file"`).

### 2. Generate CSV
The input text (SN list) is parsed, cleaned, and formatted into a standard CSV format:

```csv
identifier,name
123456789,123456789
987654321,987654321
```
The resulting file can be saved anywhere using the system file chooser.
It is commonly used to update SN groups inside MDM, using the “Import and Select Group” option.

### 3. Convert SNs for Whitelist
Converts a plain SN list into a JSON-friendly format for HTTP POST requests:
```
"123456789",
"987654321",
"654987321"
```
This is useful for backend operations where a quoted list is required.

### 🛠️ Tech Stack
| Category                 | Libraries                                                                             |
| ------------------------ | ------------------------------------------------------------------------------------- |
| **UI Framework**         | [JetBrains Compose Multiplatform](https://github.com/JetBrains/compose-multiplatform) |
| **UI Components**        | Compose Material 3, Compose Foundation, Compose UI                                    |
| **Architecture**         | AndroidX Lifecycle (ViewModel + Runtime Compose)                                      |
| **Dependency Injection** | [Koin](https://insert-koin.io/)                                                       |
| **Async Processing**     | Kotlin Coroutines (Swing)                                                             |
| **Testing**              | Kotlin Test                                                                           |


### 💻 Project Setup
## ✅ Requirements

- **JDK 17+**
- **Gradle 8+**
- **Kotlin 2.0+** (Multiplatform enabled)

## 🧾 Clone the Project
```bash
git clone https://github.com/nishida-tn/csv-tools.git
cd csv-tools
```
### ▶️ Run in Development Mode
```bash
./gradlew run
```
This will start the Compose desktop application directly from your environment.

### 🏗️ Build the Application
```bash 
./gradlew package
```
The build process generates native installers for:
```
| Platform | Format         |
| -------- | -------------- |
| Windows  | `.msi`, `.exe` |
| Linux    | `.deb`         |
| macOS    | `.dmg`         |
```

The build output can be found in:
```bash 
build/compose/binaries/main
```
### 📦 Distribution Configuration
```
Main class: br.com.acqio.MainKt
Package name: CSV TOOLS
Version: 1.0.0
JVM arguments: -Xmx2G, -Dprogram.version=1.0.0
Custom argument: -customArgument
Icon file: src/jvmMain/composeResources/drawable/icon.ico
```

### 🪟 Windows Installation
After building, run the generated .exe or .msi file.
By default, the application installs to:
```bahs
C:\Program Files\CSV TOOLS
```
