<#ftl ns_prefixes={"tk":"http://com.tomtom.pc.sonar.probe/schemas"}>
h4. Last check for [week: ${currentWeek}] ${time?string("EEE, MMM d, HH:mm")}
{chart:type=line|title=Blockers/Criticals|xLabel=Week|yLabel=Count|width=400|colors=#FF4500, #0000CD|rangeAxisLowerBound=0|rangeAxisUpperBound=17|dataOrientation=vertical}
||week||blockers||
<#list xml["tk:report/tk:stats/tk:week"] as week>
| ${week["@number"]} | ${week["tk:blockers"]} |
</#list>

||week||criticals||
<#list xml["tk:report/tk:stats/tk:week"] as week>
| ${week["@number"]} | ${week["tk:critical"]} |
</#list>
{chart}

<#assign count = 0>
<#list xml["tk:report/tk:project"] as project>
<#assign count = count + 1>
h4. ${count}) ${project["@name"]}

----
<#assign upperUnit = project["tk:testing/tk:unit/@top"]>
<#assign lowerUnit = project["tk:testing/tk:unit/@bottom"]>
<#assign upperOverall = project["tk:testing/tk:overall/@top"]>
<#assign lowerOverall = project["tk:testing/tk:overall/@bottom"]>
<#assign isOverallDiagramShown = ((upperOverall?number > 0) && (lowerOverall?number > 0))>
<#assign isUnitDiagramShown = ((upperUnit?number > 0) && (lowerUnit?number > 0))>
<#if isUnitDiagramShown>
    {chart:type=line|title=Unit Tests |xLabel=Week|yLabel=%|width=400|colors=#FF4500, #0000CD|rangeAxisLowerBound=0|rangeAxisUpperBound=100|dataOrientation=vertical}
           ||week||stretch||
        <#list project["tk:week"] as week>
             |${week["@number"]}| ${upperUnit} |
        </#list>

            ||week||minimal||
        <#list project["tk:week"] as week>
             |${week["@number"]}| ${lowerUnit} |
        </#list>

            ||week||unit||
        <#list project["tk:week"] as week>
             |${week["@number"]}| ${week["tk:unit"]} |
        </#list>

    {chart}
<#else>
No Unit Tests diagram.
</#if>
<#-- this is a comment how to assign a value from xml param <#assign weekNo = project["tk:week"][0].@number> -->
<#if isOverallDiagramShown>
    {chart:type=line|title=Overall Coverage |xLabel=Week|yLabel=%|width=400|colors=#FF4500, #0000CD|rangeAxisLowerBound=0|rangeAxisUpperBound=100|dataOrientation=vertical}
           ||week||stretch||
        <#list project["tk:week"] as week>
             |${week["@number"]}| ${upperOverall} |
        </#list>

            ||week||minimal||
        <#list project["tk:week"] as week>
            |${week["@number"]}| ${lowerOverall} |
        </#list>

            ||week||overall||
        <#list project["tk:week"] as week>
             |${week["@number"]}| ${week["tk:overall"]} |
        </#list>
    {chart}
<#else>
No Overall Coverage diagram.
</#if>
<#if (!isOverallDiagramShown && !isUnitDiagramShown)>
    This project is monitored for Blockers / Critical only.
</#if>
</#list>

