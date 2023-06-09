plugins {
    id("com.android.library").version("7.4.1").apply(false)
    id("com.android.application").version("7.4.1").apply(false)
    kotlin("android").version("1.8.20").apply(false)
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
