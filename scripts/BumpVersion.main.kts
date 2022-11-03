#!/usr/bin/env kotlin
@file:Import("Hermes.main.kts")
@file:Import("Hades.kts")
@file:Import("Version.kts")
import java.util.concurrent.TimeUnit
import java.nio.file.Paths
val TokenArgumentKey = "--token"
if(!args.contains(TokenArgumentKey)){
    val usage = """
        Use this tool to bump the version
        Usage: ./BumpVersion.kts --token <token>
        Options:
         --token : Token to access the repository
    """
    throw IllegalStateException(usage)
}
val ParentPath = Paths.get(System.getProperty("user.dir")).parent.toAbsolutePath().toString()
val TimeOutInMinutes = 1L
val token = getArgument(TokenArgumentKey)
val baseRepository = "github.com/cebroker/android-evercheck-wallet-2.0"
val repository = "https://$token@$baseRepository"
val currentVersion = Version.createFromProperties()
Hermes.display("current version: $currentVersion")
runCatching {
    Hermes.display("Performing git pull")
    Hades.exec(getCommand("git pull $repository"))
    Hermes.display("Adding changes in gradle.properties")
    Hades.exec(getCommand("git add gradle.properties"))
    Hermes.display("Who I am ")
    Hades.exec(getCommand("git config user.name condorbot"))
    Hades.exec(getCommand("git config user.email fgarcia@condorlabs.io"))
    Hermes.display("Performing commit with message: \"bump version $currentVersion\"")
    Hades.exec(getCommand("git commit -m \"$currentVersion\""))
    Hermes.display("remote just in case")
    Hermes.display("Performing push")
    Hades.exec(getCommand("git push --force $repository"))
    Hermes.display("Changes pushed")
}.onFailure { error ->
    Hermes.display("There was an error: $error")
    throw error
}
fun getArgument(argumentKey: String): String  {
    return  if (args.contains(argumentKey)){
        args[1 + args.indexOf(argumentKey)]
    } else {
        throw IllegalArgumentException("Argument $argumentKey not found")
    }
}
fun getCommand(command: String) = Hades.Command(
    command = command,
    path = ParentPath,
    commandOutputListener = object : Hades.CommandOutputListener {
        override fun onOutput(output: String) {
            Hermes.display(output)
        }
    }, timeOut = TimeOutInMinutes
)
