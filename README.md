java-data-off
=============

[![Build Status](https://travis-ci.org/mokiat/java-data-off.svg?branch=master)](https://travis-ci.org/mokiat/java-data-off)

A Java library that enables you to read Object File Format (*.OFF) files and resources.

The Object File Format allows one to store 3D models into simple plain text format. Though not as popular as the alternative OBJ file format, it is still used in some applications. Files of this format generally look something like the following.

```
OFF
4 2 0
-1.0 1.0 0.0
-1.0 -1.0 0.0
1.0 -1.0 0.0
1.0 1.0 0.0
3 0 1 2
3 0 2 3
```

You can find more information and sample models at these links:

- [Wikipedia](http://en.wikipedia.org/wiki/OFF_\(file_format\))
- [Princeton](http://shape.cs.princeton.edu/benchmark/documentation/off_format.html)
- [Holmes3D](http://www.holmes3d.net/graphics/offfiles/)


## Loading OFF models

Using this library is meant to be easy and straightforward. All you need to do is instantiate an
`OffLoader` and pass it an `InputStream` to your OFF resource.

**Example:**

```java
// Open a stream to your OFF resource
try (InputStream in = new FileInputStream("example.off")) {
	// Create an OffLoader and parse the resource
	final IOffLoader loader = new OffLoader();
	final OffObject object = loader.load(in);

	// Use the model representation to get some basic info
	System.out.println(MessageFormat.format(
			"OFF object loaded with {0} vertices and {1} faces.",
			object.getVertices().size(),
			object.getFaces().size()));
}
```

The idea behind the library's API is that you end up with an `OffObject` Java model representation of the resource that is parsed. By navigating through the model, you could easily reconstruct the 3D model. Additionally, the Java model is mutable, so you are able to make corrections to the 3D object.

**Example:**

```java
try (InputStream in = new FileInputStream("example.off")) {
	final IOffLoader loader = new OffLoader();
	final OffObject object = loader.load(in);

	System.out.println("OFF Object with faces:");
	int faceIndex = 0;
	for (OffFace face : object.getFaces()) {
		faceIndex++;
		System.out.println(MessageFormat.format(
				"\tFace with vertices:", faceIndex));

		for (Integer reference : face.getVertexReferences()) {
			final OffVertex vertex = object.getVertex(reference);
			System.out.println(MessageFormat.format(
					"\t\tVertex with coordinates: ({0}, {1}, {2})", vertex.x, vertex.y, vertex.z));
		}
	}
}
```


## Setting Up

Even though this project relies on Maven for packaging, it has not been published to the central Maven repository. Following are a number of approaches to get the library imported in your project.

### JitPack

An amazing web page that allows one to import Maven projects directly from GitHub. It is ideal for publishing new and small projects like this one.
One only needs to add the following configuration in their `pom.xml` file to get the library included.

```xml
<repositories>
	<repository>
		<id>jitpack.io</id>
		<url>https://jitpack.io</url>
	</repository>
</repositories>

<dependencies>
	<dependency>
		<groupId>com.github.mokiat</groupId>
		<artifactId>java-data-off</artifactId>
		<version>v2.0.0</version>
	</dependency>
</dependencies>
```

JitPack works with other packaging frameworks as well. Check the [official webpage](https://jitpack.io/) for more information.

### Packaging

If `JitPack` is not an option for your use case, then you could package the `jar` files into your project. They are available for download from the [Releases](https://github.com/mokiat/java-data-off/releases) section of the repository.


### Local Maven repository

You can use a set of commands to import the `jar` files into your local Maven repository. Following are two available approaches. (I find the first one to do the job)

* [http://maven.apache.org/plugins/maven-install-plugin/examples/custom-pom-installation.html](http://maven.apache.org/plugins/maven-install-plugin/examples/custom-pom-installation.html)
* [http://maven.apache.org/guides/mini/guide-3rd-party-jars-local.html](http://maven.apache.org/guides/mini/guide-3rd-party-jars-local.html)
