<#ftl>
h4. Last check for [week: ${currentWeek}] ${time?string("EEE, MMM d, HH:mm")}
<#assign o = xml["shiporder/orderperson"]>
<#assign count = 0>
${o}
<#list xml["shiporder/item"] as item>
<#assign count = count + 1>
${count} price: ${item["price"]}
</#list>