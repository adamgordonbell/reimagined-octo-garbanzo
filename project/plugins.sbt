resolvers ++= Seq(
  Classpaths.sbtPluginReleases,
  Resolver.typesafeRepo("releases"),
  Resolver.sonatypeRepo("releases"),
  Resolver.sonatypeRepo("snapshots")
)

addSbtPlugin("com.typesafe.sbteclipse" % "sbteclipse-plugin" % "4.0.0")
addSbtPlugin("com.github.mpeltonen" % "sbt-idea" % "1.6.0")
addSbtPlugin("org.scalastyle" %% "scalastyle-sbt-plugin" % "0.7.0")
addSbtPlugin("org.scalariform" % "sbt-scalariform" % "latest.release")
addSbtPlugin("com.sksamuel.scapegoat" %% "sbt-scapegoat" % "latest.release")
//addSbtPlugin("de.johoop" % "cpd4sbt" % "1.2.0")
addSbtPlugin("org.wartremover" % "sbt-wartremover" % "1.1.1")