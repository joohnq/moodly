-keepattributes Signature
# Ignore annotation used for build tooling.
-dontwarn io.ktor.utils.io.jvm.nio.WritingKt
-dontwarn okio.**
## Kotlinx serialization
-keepattributes *Annotation*, InnerClasses
-dontnote kotlinx.serialization.SerializationKt
-keep,includedescriptorclasses class com.joohnq.moodapp.**$$serializer { *; } # <-- change package name to your app's
-keepclassmembers class com.joohnq.moodapp.** { # <-- change package name to your app's
    *** Companion;
}
-keepclasseswithmembers class com.joohnq.moodapp.** { # <-- change package name to your app's
    kotlinx.serialization.KSerializer serializer(...);
}
-keep,includedescriptorclasses class com.joohnq.security.**$$serializer { *; } # <-- change package name to your app's
-keepclassmembers class com.joohnq.security.** { # <-- change package name to your app's
    *** Companion;
}
-keepclasseswithmembers class com.joohnq.security.** { # <-- change package name to your app's
    kotlinx.serialization.KSerializer serializer(...);
}
-keep,includedescriptorclasses class com.joohnq.navigation.**$$serializer { *; } # <-- change package name to your app's
-keepclassmembers class com.joohnq.navigation.** { # <-- change package name to your app's
    *** Companion;
}
-keepclasseswithmembers class com.joohnq.navigation.** { # <-- change package name to your app's
    kotlinx.serialization.KSerializer serializer(...);
}

-keep class * extends androidx.room.RoomDatabase { <init>(); }
-keep class androidx.sqlite.driver.bundled.** { *; }