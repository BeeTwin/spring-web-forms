<#import "parts/common.ftl" as c>
<#import "parts/loginForm.ftl" as lf>
<@c.page>
    <div>
        <@lf.logout/>
        <span><a href="/user">Список пользователей</a></span>
    </div>
    <div>Список форм:</div>
    <div id="form-list">
        <#list forms as form>
            <div>
                <b>${form.id}</b>
                <a href="/answer/${form.id}">${form.name}</a>
                <span>от ${form.author.username}</span>
            </div>
            <#else>
            <div id="none">
                Никто еще не добавил форм :(
            </div>
        </#list>
        <br/><span><a href="/form">Полный список форм</a></span>
    </div>
</@c.page>