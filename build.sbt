import com.typesafe.sbt.SbtScalariform.ScalariformKeys

//import de.johoop.cpd4sbt.CopyPasteDetector._
//import de.johoop.cpd4sbt.{OutputType, ReportType}

import scalariform.formatter.preferences._

name := "skeleton"

organization := "com.cascadeofinsights"

version := "1.2"

scalaVersion := "2.11.7"

resolvers += Resolver.sonatypeRepo("snapshots")


libraryDependencies ++= Seq(
    "org.scalatest"   %% "scalatest"    % "2.2.4"   % "test,it"
)
// code style from marathon
lazy val formatSettings = SbtScalariform.scalariformSettings ++ Seq(
  ScalariformKeys.preferences := FormattingPreferences()
    .setPreference(AlignArguments, false)
    .setPreference(AlignParameters, false)
    .setPreference(AlignSingleLineCaseStatements, false)
    .setPreference(CompactControlReadability, false)
    .setPreference(DoubleIndentClassDeclaration, true)
    .setPreference(DanglingCloseParenthesis, Preserve)
    .setPreference(FormatXml, true)
    .setPreference(IndentSpaces, 2)
    .setPreference(IndentWithTabs, false)
    .setPreference(MultilineScaladocCommentsStartOnFirstLine, false)
    .setPreference(PlaceScaladocAsterisksBeneathSecondAsterisk, true)
    .setPreference(PreserveSpaceBeforeArguments, true)
    .setPreference(SpacesAroundMultiImports, true)
    .setPreference(SpaceBeforeColon, false)
    .setPreference(SpaceInsideBrackets, false)
    .setPreference(SpaceInsideParentheses, false)
    .setPreference(SpacesWithinPatternBinders, true)
)

scalacOptions ++= List("-feature","-deprecation", "-unchecked", "-Xlint")

/*
Static Analysis
 */
addCompilerPlugin("org.psywerx.hairyfotr" %% "linter" % "0.1-SNAPSHOT")


//wartremoverWarnings ++= Warts.all // Some false positives
wartremoverWarnings ++= Warts.unsafe //No false positive

//add Scapegoat to compile
(compile in Compile) <<= (compile in Compile) dependsOn scapegoat

//Style Check section
scalastyleConfig <<= baseDirectory { _ / "src/main/config" / "scalastyle-config.xml" }


//cpdSettings - not working
//enablePlugins(CopyPasteDetector)
//cpdReportType := ReportType.Simple
//cpdOutputType := OutputType.Console