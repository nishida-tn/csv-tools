import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
    alias(libs.plugins.composeHotReload)
}

kotlin {
    jvm()
    
    sourceSets {
        commonMain.dependencies {
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material3)
            implementation(compose.ui)
            implementation(compose.components.resources)
            implementation(compose.components.uiToolingPreview)
            implementation(libs.androidx.lifecycle.viewmodelCompose)
            implementation(libs.androidx.lifecycle.runtimeCompose)
            implementation(libs.koin.core)
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
        jvmMain.dependencies {
            implementation(compose.desktop.currentOs)
            implementation(libs.kotlinx.coroutinesSwing)
            implementation(libs.koin.jvm)
        }
    }
}


compose.desktop {

    application {
        mainClass = "br.com.acqio.MainKt"
        jvmArgs += listOf("-Xmx2G")
        args += listOf("-customArgument")
        fromFiles(project.fileTree("libs/") { include("**/*.jar") })
        mainJar.set(project.file("build/libs/main.jar"))
        dependsOn("mainJarTask")
        jvmArgs += listOf("-Xmx2G", "-Dprogram.version=1.0.0")


        nativeDistributions {
            targetFormats(TargetFormat.Msi, TargetFormat.Exe, TargetFormat.Deb, TargetFormat.Dmg)
            packageName = "CSV TOOLS"
            packageVersion = "1.0.0"
            includeAllModules = true
            appResourcesRootDir.set(project.layout.projectDirectory.dir("resources"))

            windows {
                console = false
                msiPackageVersion = "1.0.0"
                exePackageVersion = "1.0.0"
                dirChooser = true
                installationPath = "C:\\Program Files\\CSV TOOLS"
                shortcut = true
                menuGroup = "Acqio"
                iconFile.set(project.file("src/jvmMain/composeResources/drawable/icon.ico"))
            }

            linux {
                packageVersion = "1.0.0"
                debPackageVersion = "1.0.0"
                rpmPackageVersion = "1.0.0"
            }
            macOS {
                packageVersion = "1.0.0"
                dmgPackageVersion = "1.0.0"
                pkgPackageVersion = "1.0.0"
                packageBuildVersion = "1.0.0"
            }
        }

    }
}
