package zhanggl

object Plugin extends sbt.Plugin {
	import sbt._
	import sbt.Keys._

	lazy val collectDependentJars = taskKey[java.io.File]("collect all denpency jars into the libs directory")

	val executableJarSettings = Seq(
		collectDependentJars <<= (crossTarget, externalDependencyClasspath in Compile) map { (targetDir, depenCP) =>
			val outputDirectory = targetDir / "libs"
			depenCP.files.filter(x=> !x.isDirectory) foreach { file =>
				IO.copyFile(file,outputDirectory / file.getName)
			}
			outputDirectory
		},
		packageOptions in (Compile, packageBin) += {
			val libs = (collectDependentJars in Compile).value
			val cp = libs.listFiles.toSeq.map("  libs/" + _.getName).mkString
			Package.ManifestAttributes( java.util.jar.Attributes.Name.CLASS_PATH -> cp )
		}
	)

}
