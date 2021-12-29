<#import "parts/common.ftl" as c>
<@c.page>
    <div>
        <h1>${form.name} от ${form.author.username}</h1>
    </div>
    <form action="${form.id}" method="post">
        <div>Ваше имя: <label><input type="text" name="username" required></label></div>
        <#list form.questions as question>
            <div>${question.question} <label><input type="text" name="answer-${question.id}" required></label></div>
        </#list>
        <input type="hidden" name="_csrf" value="${ _csrf.token }"/>
        <div><input type="submit" value="Отправить"/></div>
    </form>
</@c.page>