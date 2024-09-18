package extensions

import jetbrains.buildServer.configs.kotlin.BuildType
import jetbrains.buildServer.configs.kotlin.Project
import jetbrains.buildServer.configs.kotlin.RelativeId
import jetbrains.buildServer.configs.kotlin.toId

class SampleProject(
    private val nameSuffix: Int,
    private val buildConfCount: Int,
    private val sleepDurationSec: Int = 0
) : Project() {
    val mainBuildType: BuildType
    init {
        val paddedSuffix = nameSuffix.toString().padStart(3, '0')
        name = "Project $paddedSuffix"
        val relativeId = RelativeId("p_$paddedSuffix")
        id = relativeId

        val buildTypes = (1..buildConfCount).map { i -> SampleBuildType(relativeId, i) }

        mainBuildType = SampleMainBuildType(relativeId, buildTypes)

        buildTypes.forEach { buildType(it) }
        buildType(mainBuildType)

        val sleepDurationSecLocal = sleepDurationSec
        params {
            param("sleepDuration", "$sleepDurationSecLocal")
        }
    }
}