package extensions

import jetbrains.buildServer.configs.kotlin.*
import jetbrains.buildServer.configs.kotlin.buildSteps.script

class SampleMainBuildType(
    private val projectId: Id,
    buildTypes: List<BuildType>,
)  : BuildType({
    name = "Build Conf Main"
    id = AbsoluteId("${projectId}_${name.toId()}")

    steps {
        script {
            scriptContent = """
                echo "Empty build type"
            """.trimIndent()
        }
    }

    dependencies {
        buildTypes.forEach {
            snapshot(it) {
                reuseBuilds = ReuseBuilds.NO
                onDependencyFailure = FailureAction.FAIL_TO_START
            }
        }
    }
})