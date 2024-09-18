package extensions

import jetbrains.buildServer.configs.kotlin.BuildType
import jetbrains.buildServer.configs.kotlin.Project

fun Project.addSubprojects(subprojectCount: Int, buildTypesPerSubproject: Int, sleepDurationSec: Int = 0) : List<BuildType> {
    val subprojectList = mutableListOf<Project>()
    val innerBuildTypes = mutableListOf<BuildType>()

    for (i in 1..subprojectCount) {
        val subproject = SampleProject(i, buildTypesPerSubproject, sleepDurationSec)
        subprojectList.add(subproject)
        innerBuildTypes.add(subproject.mainBuildType)
    }

    subProjects(*subprojectList.toTypedArray())

    return innerBuildTypes
}