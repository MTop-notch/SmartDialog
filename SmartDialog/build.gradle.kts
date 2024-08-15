plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-android-extensions")
    `maven-publish`
    id("cl.franciscosolis.sonatype-central-upload") version "1.0.2"
}

java {
    withSourcesJar()
    withJavadocJar()
}

android {
    namespace = "com.mt.smartdialog"
    compileSdk = 33

    defaultConfig {
        minSdk = 26

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        viewBinding = true
    }
    androidExtensions {
        isExperimental = true
    }
}

dependencies {

    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.3.72")
    implementation("androidx.core:core-ktx:1.6.0")
    implementation("androidx.appcompat:appcompat:1.4.2")
    implementation("com.google.android.material:material:1.4.0")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.3")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.6.1")

//    implementation("androidx.core:core-ktx:1.13.1")
//    implementation("androidx.appcompat:appcompat:1.7.0")
//    implementation("com.google.android.material:material:1.12.0")
//    testImplementation("junit:junit:4.13.2")
//    androidTestImplementation("androidx.test.ext:junit:1.2.1")
//    androidTestImplementation("androidx.test.espresso:espresso-core:3.6.1")

}


publishing {
    publications {
        register<MavenPublication>("release") {
            group = "com.mt"
            artifactId = "SmartDialog"
            version = "1.0.0"
            afterEvaluate {
                from(components["release"])
            }
        }
    }
    repositories {
        maven {
            name = "SmartDialog"
            url = uri(layout.buildDirectory.dir("repo"))
        }
    }
}
publishing {
    publications {
        register<MavenPublication>("maven") {
            pom {
                name.set(project.name)
                // 説明
                description.set("Custom assertion to check JSON string matches pattern.")
                // URL
                url.set("https://github.com/orangain/json-fuzzy-match")
                // ライセンス
                licenses {
                    license {
                        name.set("MIT License")
                        url.set("https://github.com/orangain/json-fuzzy-match/blob/master/LICENSE")
                        distribution.set("repo")
                    }
                }
                // 開発者
                developers {
                    developer {
                        id.set("MTop-notch")
                        name.set("Masaki Taguchi")
                        email.set("taguchi@corexia.co.jp")
                    }
                }
                // バージョン管理システム
                scm {
                    url.set("https://github.com/orangain/json-fuzzy-match")
                }
            }
        }
    }
}