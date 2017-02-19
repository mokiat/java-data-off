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
The library is distributed as a JAR file and is available in the Releases section of the GitHub project, together with its source code and javadoc.


### Classpath
If you have a very simple Java project setup that does not depend on any dependency management framework, you could easily add the JAR to the Classpath of your Java project and it should work without any additional setup.

### Maven
The build framework that is used for this project is Maven but the resulting JAR library artefacts are not registered in any Maven repository at the moment.

If you want to use the Maven dependency management to include the library to your project, you will need to register the JAR manually in your local Maven repository. You can read more about it at the following links. (I find the first one to do the job)

* [http://maven.apache.org/plugins/maven-install-plugin/examples/custom-pom-installation.html](http://maven.apache.org/plugins/maven-install-plugin/examples/custom-pom-installation.html)
* [http://maven.apache.org/guides/mini/guide-3rd-party-jars-local.html](http://maven.apache.org/guides/mini/guide-3rd-party-jars-local.html)
