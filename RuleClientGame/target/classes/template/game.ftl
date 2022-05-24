package game${ruleName};
import com.game.rule.model.EventEntity;
import com.game.rule.model.GameEntity;
import java.util.*;
global GameEntity ruleGame;
dialect "mvel"
rule ${ruleName}
when
$rule : GameEntity(<@convertToGameEntity gameEntity = gameEntitys/>)
then
<#assign eventEntityList>
<@convertToEventEntity events = eventEntitys/>
</#assign>
$rule.setEvent([${eventEntityList}]);
end


<#macro convertToGameEntity gameEntity>
<#if gameEntity.gameName?has_content>
${"(gameName" +" == "+ "\""+ gameEntity.gameName +"\"" +") && "}
</#if>
<#if gameEntity.gameLocation?has_content>
${"(gameLocation" +" == "+ "\"" + gameEntity.gameLocation +"\"" +") && "}
</#if>
<#if gameEntity.gameStatus?has_content>
${"(gameStatus" +" == "+ "\"" +gameEntity.gameStatus +"\"" +")"}
</#if>
</#macro>


<#macro convertToEventEntity events>
<#list events as event>
{
"name" : ${"\""+event.name+"\""},
"type" : ${"\""+event.type+"\""},
"description" : ${"\""+event.description+"\""},
"sequence" : ${"\""+event.sequence+"\""}
},
</#list>
</#macro>