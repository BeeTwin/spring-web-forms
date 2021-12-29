<#import "parts/common.ftl" as c>
<@c.page>
    <#if form??>
        <form action="/formEdition/${form.id}" method="post">
            <div><label>Имя: <input type="text" value="${form.name}" name="formName" required/></label></div>
            <div><label>Активна: <input type="checkbox" ${form.active?string("checked", "")} name="isActive"></label></div>
            <div>Вопросы: <input type="button" value="Добавить вопрос" id="addButton">
                <div id="questions">
                    <#list form.questions as question>
                        <input type='text' name='question-${question.question}' value="${question.question}" placeholder='Введите вопрос' required><br/>
                    </#list>
                </div>
            </div>
            <input type="hidden" name="_csrf" value="${ _csrf.token }"/>
            <div><input type="submit" value="Сохранить"/></div>
        </form>
    </#if>
    <script src="/form.js"></script>
</@c.page>