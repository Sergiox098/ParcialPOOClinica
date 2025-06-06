plugins {
    id("java-library")
    alias(libs.plugins.google.gms.google.services)
}
java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}
dependencies{
    implementation(libs.firebase.database)
}
