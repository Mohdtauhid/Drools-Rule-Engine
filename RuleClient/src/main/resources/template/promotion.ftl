package promotion${ruleName};
import com.rule.model.RuleValidationDTO;
import com.rule.model.RuleValidationResponseDTO;
import java.util.*;
global RuleValidationDTO rulePromotion;
dialect "mvel"
rule ${ruleName}
when
$rule : RuleValidationDTO(<@convertToConditions/>)
then
RuleValidationResponseDTO $action = new RuleValidationResponseDTO();
<@convertToActionData action = actions/>
$rule.setActions($action);
end
<#macro convertToConditions>
<#list conditions as condition>(<@criteriaList criteria=condition/>)<#sep> && </#sep></#list>
</#macro>
<#macro criteriaList criteria>
<#if criteria.operator?upper_case == "BETWEEN">
${criteria.conditionName +">=\""+criteria.values[0]+"\" && "+criteria.conditionName +"<=\""+criteria.values[1]+"\""}
<#elseif criteria.operator?upper_case == "IS LESS THAN">
${criteria.conditionName +"<"+ criteria.values[0]}
<#elseif criteria.operator?upper_case == "IS GREATER THAN">
${criteria.conditionName +">"+ criteria.values[0]}
<#elseif criteria.operator?upper_case == "IS GREATER THAN OR EQUAL TO">
${criteria.conditionName +">="+ criteria.values[0]}
<#elseif criteria.operator?upper_case == "IS LESS THAN OR EQUAL TO">
${criteria.conditionName +"<="+ criteria.values[0]}
<#elseif criteria.conditionName?upper_case == "PRODUCTTYPE">
${criteria.conditionName} <@operatorString operator=criteria.operator?upper_case/>[
<#list criteria.values as value>
${" \""+value+"\""}<#sep> , </#sep>
</#list>]
<#elseif criteria.conditionName?upper_case == "OCCASION">
${criteria.conditionName} <@operatorString operator=criteria.operator?upper_case/>[
<#list criteria.values as value>
${" \""+value+"\""}<#sep> , </#sep>
</#list>]
<#elseif criteria.conditionName?upper_case == "RECIPIENT">
${criteria.conditionName} <@operatorString operator=criteria.operator?upper_case/>[
<#list criteria.values as value>
${" \""+value+"\""}<#sep> , </#sep>
</#list>]
<#elseif criteria.conditionName?upper_case == "CATEGORY">
${criteria.conditionName} <@operatorString operator=criteria.operator?upper_case/>[
<#list criteria.values as value>
${" \""+value+"\""}<#sep> , </#sep>
</#list>]
<#else>
<#list criteria.values as value>
${criteria.conditionName} <@operatorString operator=criteria.operator?upper_case/>${" \""+value+"\""}<#sep> || </#sep>
</#list>
</#if>
</#macro>
<#macro operatorString operator>
<#if operator == "DOES NOT CONTAINS" || operator == "NOT IN" >
${"!="}
<#else>
${"=="}
</#if>
</#macro>
<#macro convertToActionData action >
<#if action.actionName?has_content>
$action.setActionName(${"\""+action.actionName+"\""});
</#if>
<#if action.priceName?has_content>
$action.setPriceName(${"\""+action.priceName+"\""});
</#if>
<#if action.maxCap?has_content>
$action.setMaxCap(${action.maxCap});
</#if>
<#if action.amountPercentOff?has_content>
$action.setAmountPercentOff(${action.amountPercentOff});
</#if>
<#if action.amountOff?has_content>
$action.setAmountOff(${action.amountOff});
</#if>
<#if action.amount?has_content>
$action.setAmount(${action.amount});
</#if>
<#if action.values?has_content>
$action.setValues(${action.values});
</#if>
<#if action.range?has_content>
<#assign ranges>
<@convertToRange range=action.range/>
</#assign>
$action.setRange([${ranges}]);
</#if>
</#macro>
<#macro convertToRange range>
<#list range as r>{
"minimumAmount" : ${r.minimumAmount?replace(",","")},
"maximumAmount" : ${r.maximumAmount?replace(",","")},
"amountOff" : ${r.amountOff},
"maxCap" : ${r.maxCap}
}<sep>,<sep>
</#list>
</#macro>