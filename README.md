# SenAPI
SenAPI is an API that provides some utilities.
These utilities are mostly just for strings, but also contains a few 
utilities for Players as well.

## Implementation:
### Gradle:

```groovy
repositories {
    maven { url 'https://jitpack.io' }
}
```

```groovy
dependencies {
    implementation 'com.github.True-cc:SenAPI:master-SNAPSHOT'
}
```
### Maven:

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
	    <version>master-SNAPSHOT</version>
	</dependency>
```

## Getting API
##### Getting an instance:
```Java
Bukkit.getServicesManager().load(SenAPI.class);
```
##### Getting different utilities:
The StringUtils:
```java
senAPI.getStringUtils();
```
The PlayerUtils:
```java
senAPI.getPlayerUtils();
```
<br>
There will be more to this sometime soon.