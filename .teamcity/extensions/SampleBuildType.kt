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
    val paddedSuffix = nameSuffix.toString().padStart(3, '0')
    name = "Build Conf $paddedSuffix"
    id = AbsoluteId("${projectId}_bc-$paddedSuffix")

    steps {
        script {
            scriptContent = """
                echo "Will build diligently for %sleepDuration% seconds..."
                sleep %sleepDuration%
            """.trimIndent()
        }
    }
})

