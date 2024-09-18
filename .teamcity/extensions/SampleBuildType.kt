package extensions

import jetbrains.buildServer.configs.kotlin.AbsoluteId
import jetbrains.buildServer.configs.kotlin.BuildType
import jetbrains.buildServer.configs.kotlin.RelativeId
import jetbrains.buildServer.configs.kotlin.buildSteps.script
import jetbrains.buildServer.configs.kotlin.toId

class SampleBuildType(
    private val projectId: RelativeId,
    private val nameSuffix: Int
) : BuildType({
    name = "Build Conf ${nameSuffix.toString().padStart(3, '0')}"
    id = AbsoluteId("${projectId}_${name.toId()}")

    steps {
        script {
            scriptContent = """
                echo "Will build diligently for %sleepDuration% seconds..."
                sleep %sleepDuration%
            """.trimIndent()
        }
    }
})

