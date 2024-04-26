plugins {
  application
  java
}

configure<JavaPluginExtension> {
  sourceCompatibility = JavaVersion.VERSION_11
  targetCompatibility = JavaVersion.VERSION_11
}

application {
  mainClass.set("task1.Main")
}