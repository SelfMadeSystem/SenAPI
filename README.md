# SenAPI
SenAPI is more of a utility library for Spigot and BungeeCord.<br>
SenAPI currently contains utilities for strings, a few player utilities, a config manager of sorts,
and some SQL utilities, just MySQL and SQLite for now.<br>

# Implementation:
## Gradle:

```groovy
repositories {
    maven { url 'https://jitpack.io' }
}
```

```groovy
dependencies {
    implementation 'com.github.True-cc:SenAPI:version-here'
}
```
## Maven:

```xml
	<repositories>
		<repository>
		    <id>jitpack.io</id>
		    <url>https://jitpack.io</url>
		</repository>
	</repositories>
```
```xml
	<dependency>
	    <groupId>com.github.True-cc</groupId>
	    <artifactId>SenAPI</artifactId>
	    <version>version-here</version>
	</dependency>
```
# Adding to dependency manager.
## Gradle

```groovy
repositories {
    maven { url 'https://jitpack.io' }
}
```

```groovy
dependencies {
    implementation 'com.github.True-cc:SenAPI:version-here'
}
```
## Maven:

```xml
	<repositories>
		<repository>
		    <id>jitpack.io</id>
		    <url>https://jitpack.io</url>
		</repository>
	</repositories>
```
```xml
	<dependency>
	    <groupId>com.github.True-cc</groupId>
	    <artifactId>SenAPI</artifactId>
	    <version>version-here</version>
	</dependency>
```

You can shade the SenAPI into your jar or you can use [Lilliputian](https://github.com/GoDead/Lilliputian)

#### Thanks:<br>
- Thank you Vagdedes for helping me make the SQL methods!<br>
- Thank you koloslolya for the name lol.

<!-- # TODO: Update this. -->
<br>
There will be more to this sometime soon™.
