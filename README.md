sbt_executable_jar
==================

A sbt plugin to create a executable jar when doing a package. And the dependency jars collected in a libs/ directory.

## Usage
In your `plugin.sbt` 
```scala
lazy val root = project.in( file(".") ).dependsOn( exeJarPlugin )
lazy val exeJarPlugin = uri("git://github.com/zhanggl/sbt_executable_jar")
```

and `build.sbt`
```scala
executableJarSettings
```
*Don't forget the blank lines!*

then, doing `package` as usual, and get your cargoes under `target/scala-x.x.x/` and `target/scala-x.x.x/libs/`

Run it by `java -jar target/scala-x.x.x/my_awesome.jar`
