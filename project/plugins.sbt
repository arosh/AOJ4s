// sbt-scalaxb
resolvers += "sonatype-public" at "https://oss.sonatype.org/content/groups/public"

addSbtPlugin("org.scalaxb" % "sbt-scalaxb" % "0.7.3")

// sbteclipse
addSbtPlugin("com.typesafe.sbteclipse" % "sbteclipse-plugin" % "2.1.0")

// sbt-onejar
resolvers += Resolver.url(
  "sbt-plugin-releases",
  new URL("http://scalasbt.artifactoryonline.com/scalasbt/sbt-plugin-releases/")
)(Resolver.ivyStylePatterns)

addSbtPlugin("com.github.retronym" % "sbt-onejar" % "0.8")
