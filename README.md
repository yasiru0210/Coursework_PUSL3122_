# Phoenix Furniture Management System

A professional, enterprise-grade furniture management system built with Java Swing, featuring modern UI design, 3D room visualization, and comprehensive business functionality.

![Java](https://img.shields.io/badge/Java-21+-orange.svg)
![Swing](https://img.shields.io/badge/UI-Java%20Swing-blue.svg)
![License](https://img.shields.io/badge/License-MIT-green.svg)
![Status](https://img.shields.io/badge/Status-Active%20Development-brightgreen.svg)

## 🌟 Features

### Core Functionality
- **📊 Modern Dashboard** - Real-time analytics, sales overview, and business metrics
- **🪑 Furniture Management** - Complete inventory, catalog, and product management
- **🏗️ 3D Room Design** - Interactive room planning and furniture visualization
- **👥 Staff Management** - Employee tracking, administration, and role management
- **📦 Inventory Control** - Stock management, tracking, and automated alerts
- **📈 Reports & Analytics** - Business intelligence, sales reports, and data insights

### Technical Features
- **Modern UI/UX** - Professional interface with FlatLaf look and feel
- **Responsive Design** - Adaptive layouts for different screen sizes
- **Real-time Updates** - Live data synchronization and status updates
- **Data Persistence** - Reliable data storage using properties files
- **3D Visualization** - Advanced 3D rendering for room design
- **Modular Architecture** - Clean, maintainable, and extensible codebase

## 🚀 Technology Stack

| Component | Technology | Purpose |
|-----------|------------|---------|
| **Frontend** | Java Swing + FlatLaf | Modern desktop UI framework |
| **3D Graphics** | Java 3D API | 3D room visualization and modeling |
| **Data Storage** | Properties Files | Lightweight data persistence |
| **Architecture** | MVC Pattern | Clean separation of concerns |
| **Build System** | Native Java | Cross-platform compilation |

## 📋 Prerequisites

- **Java Development Kit (JDK) 21+**
- **Java 3D Libraries** (included in project dependencies)
- **Operating System**: Windows, macOS, or Linux
- **Memory**: Minimum 512MB RAM (1GB+ recommended)
- **Display**: 1024x768 resolution or higher

## 🛠️ Installation & Setup

### Quick Start

1. **Clone the repository**
   ```bash
   git clone https://github.com/your-username/phoenix-furniture.git
   cd phoenix-furniture
   ```

2. **Compile the application**
   
   **Windows:**
   ```batch
   compile.bat
   ```
   
   **Linux/macOS:**
   ```bash
   chmod +x compile.sh
   ./compile.sh
   ```

3. **Run the application**
   
   **Windows:**
   ```batch
   run.bat
   ```
   
   **Linux/macOS:**
   ```bash
   chmod +x run.sh
   ./run.sh
   ```

### Manual Compilation

```bash
# Compile all Java files
javac -cp "src" src/Main.java src/ui/*.java src/ui/components/*.java src/ui/panels/*.java src/utils/*.java

# Run the application
java -cp "src" Main
```

## 📁 Project Structure

```
phoenix-furniture/
├── 📄 README.md                    # Project documentation
├── 📄 compile.bat/sh              # Compilation scripts
├── 📄 run.bat/sh                  # Execution scripts
├── 📂 src/                        # Source code
│   ├── 📄 Main.java               # Application entry point
│   ├── 📂 ui/                     # User interface layer
│   │   ├── 📄 MainApplication.java # Main window controller
│   │   ├── 📂 components/         # Reusable UI components
│   │   │   ├── 📄 ModernSidebar.java
│   │   │   ├── 📄 StatusBar.java
│   │   │   ├── 📄 ModernCard.java
│   │   │   └── 📄 ChartPanel.java
│   │   └── 📂 panels/             # Feature-specific panels
│   │       ├── 📄 DashboardPanel.java
│   │       ├── 📄 FurniturePanel.java
│   │       ├── 📄 RoomDesignPanel.java
│   │       ├── 📄 StaffPanel.java
│   │       ├── 📄 InventoryPanel.java
│   │       └── 📄 ReportsPanel.java
│   ├── 📂 utils/                  # Utility classes
│   │   ├── 📄 Constants.java      # Application constants
│   │   ├── 📄 IconManager.java    # Icon management
│   │   └── 📄 ThemeManager.java   # Theme configuration
│   ├── 📂 Objects/                # 3D models and objects
│   │   ├── 📄 Chair3D.java
│   │   ├── 📄 Bed3D.java
│   │   ├── 📄 Cupboard3D.java
│   │   └── 📄 Desk3D.java
│   └── 📂 Frames/                 # Legacy UI components
├── 📂 Saved_Items/               # Data storage
└── 📂 src/Resources/             # Application resources
```

## 🎨 Design Philosophy

### Modern UI/UX Principles
- **Minimalist Design** - Clean, uncluttered interface
- **Consistent Theming** - Unified color scheme and typography
- **Intuitive Navigation** - User-friendly menu structure
- **Responsive Layouts** - Adaptive to different screen sizes
- **Professional Aesthetics** - Business-grade visual design

### Architecture Patterns
- **Model-View-Controller (MVC)** - Clear separation of concerns
- **Component-Based Design** - Reusable UI components
- **Singleton Pattern** - Resource management and configuration
- **Observer Pattern** - Event-driven updates
- **Factory Pattern** - Object creation and management

## 🔧 Configuration

### Theme Customization
The application supports theme customization through the `Constants.java` file:

```java
// Primary colors
public static final Color PRIMARY = new Color(59, 130, 246);
public static final Color SUCCESS = new Color(34, 197, 94);
public static final Color WARNING = new Color(251, 191, 36);
public static final Color ERROR = new Color(239, 68, 68);
```

### Font Configuration
```java
// Font settings
public static final Font TITLE_FONT = new Font("Segoe UI", Font.BOLD, 28);
public static final Font HEADER_FONT = new Font("Segoe UI", Font.BOLD, 18);
public static final Font BODY_FONT = new Font("Segoe UI", Font.PLAIN, 14);
```

## 📊 Features Overview

### Dashboard
- **Real-time Metrics** - Live business statistics
- **Sales Charts** - Visual representation of sales data
- **Recent Activity** - Latest system activities
- **Quick Actions** - Fast access to common tasks

### Furniture Management
- **Product Catalog** - Comprehensive furniture database
- **Category Management** - Organized product classification
- **Image Gallery** - Visual product representation
- **Pricing Management** - Dynamic pricing controls

### 3D Room Design
- **Interactive 3D Environment** - Real-time room visualization
- **Drag & Drop Interface** - Intuitive furniture placement
- **Multiple View Modes** - Different perspective options
- **Export Capabilities** - Save and share designs

### Staff Management
- **Employee Profiles** - Comprehensive staff information
- **Role Management** - Permission-based access control
- **Activity Tracking** - Monitor staff activities
- **Performance Metrics** - Staff performance analytics

## 🤝 Contributing

We welcome contributions from the community! Here's how you can help:

### Getting Started
1. **Fork** the repository
2. **Create** a feature branch (`git checkout -b feature/amazing-feature`)
3. **Commit** your changes (`git commit -m 'Add amazing feature'`)
4. **Push** to the branch (`git push origin feature/amazing-feature`)
5. **Open** a Pull Request

### Development Guidelines
- Follow Java coding conventions
- Write clear, documented code
- Include unit tests for new features
- Update documentation as needed
- Ensure cross-platform compatibility

### Code Style
- Use meaningful variable and method names
- Follow camelCase naming convention
- Add JavaDoc comments for public methods
- Maintain consistent indentation (4 spaces)
- Keep methods focused and concise

## 🐛 Bug Reports & Feature Requests

### Reporting Bugs
1. Check existing issues to avoid duplicates
2. Use the bug report template
3. Include system information and steps to reproduce
4. Attach screenshots if applicable

### Requesting Features
1. Search existing feature requests
2. Use the feature request template
3. Provide detailed use cases and benefits
4. Consider implementation complexity

## 📝 License

This project is licensed under the **MIT License** - see the [LICENSE](LICENSE) file for details.

```
MIT License

Copyright (c) 2024 Phoenix Furniture Management System

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.
```

## 🙏 Acknowledgments

- **FlatLaf** - Modern look and feel library
- **Java 3D Community** - 3D graphics support
- **Open Source Contributors** - Community support and feedback
- **Design Inspiration** - Modern UI/UX principles

## 📞 Support & Contact

- **Documentation**: [Wiki](https://github.com/your-username/phoenix-furniture/wiki)
- **Issues**: [GitHub Issues](https://github.com/your-username/phoenix-furniture/issues)
- **Discussions**: [GitHub Discussions](https://github.com/your-username/phoenix-furniture/discussions)
- **Email**: support@phoenix-furniture.com

---

<div align="center">
  <p><strong>Built with ❤️ for the furniture industry</strong></p>
  <p>© 2024 Phoenix Furniture Management System. All rights reserved.</p>
</div>