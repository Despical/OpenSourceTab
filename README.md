<p align="center">
  <img src="assets/icon.svg" width="128" height="128" alt="Open Source in Editor Tab icon">
</p>

<h1 align="center">Open Source in Editor Tab</h1>

<p align="center">Jump from a changed file in the Commit window straight to its normal editor tab in IntelliJ IDEA.</p>

<p align="center">
  <img src="https://img.shields.io/badge/Java-25-007396.svg" alt="Java 25">
  <img src="https://img.shields.io/badge/IntelliJ_IDEA-2026.2%2B-6B57FF?logo=intellijidea&amp;logoColor=white" alt="IntelliJ IDEA 2026.2+">
  <img src="https://img.shields.io/badge/Gradle-9.8-079ec0?logo=gradle&amp;logoColor=white" alt="Gradle">
  <a href="LICENSE"><img src="https://img.shields.io/badge/License-MIT-green.svg" alt="MIT license"></a>
  <a href="https://github.com/Despical/OpenSourceTab/releases/latest"><img src="https://img.shields.io/github/v/release/Despical/OpenSourceTab" alt="Latest release"></a>
</p>

<p align="center"><a href="https://github.com/Despical/OpenSourceTab/releases/latest">Download</a> · <a href="#installation">Installation</a> · <a href="#usage">Usage</a> · <a href="#building">Building</a></p>

---

## Features

- **Open Source in Editor Tab** is the first entry in the Commit tool window's right-click menu.
- Opens the real file in a regular editor tab, or focuses the tab if the file is already open.

## Installation

1. Download `OpenSourceTab-<version>.zip` from the [latest release](https://github.com/Despical/OpenSourceTab/releases/latest).
2. In IntelliJ IDEA open **Settings → Plugins → ⚙ → Install Plugin from Disk…** and pick the zip.
3. Restart the IDE.

Requires IntelliJ IDEA 2026.2 (build 262) or newer.

## Usage

- In the **Commit** tool window, right-click a changed file and choose **Open Source in Editor Tab**.

You can also assign a shortcut under **Settings → Keymap → Open Source in Editor Tab**.

## Building

Requires JDK 25 or newer.

```bash
./gradlew buildPlugin
```

The plugin zip is written to `build/distributions`. To build against a locally installed IDE instead of downloading one:

```bash
./gradlew buildPlugin -PideaPath="C:/Program Files/JetBrains/IntelliJ IDEA"
```

## License

Released under the [MIT License](LICENSE). Copyright © 2026 Berke Akçen ([Despical](https://despical.dev)).
