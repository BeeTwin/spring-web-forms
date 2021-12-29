<#import "parts/common.ftl" as c>
<@c.page>
    <table>
        <thead>
        <tr>
            <th>Имя</th>
            <th>Автор</th>
            <th>Активна</th>
        </tr>
        </thead>
        <tbody>
        <#list forms as form>
            <tr id="row">
                <td>
                    ${form.name}
                </td>
                <td>
                    ${form.author.username}
                </td>
                <td>
                    ${form.active?string("Да", "Нет")}
                </td>
                <td>
                    <a href="/formEdition/${form.id}">Редактировать</a>
                </td>
                <td>
                    <a href="/formEdition/delete/${form.id}">Удалить</a>
                </td>
                <td>
                    <a href="/answer/stats/${form.id}">Ответы</a>
                </td>
            </tr>
        <#else>
            Никто еще не добавил форм :(
        </#list>
        </tbody>
    </table>
    <div>
        <a href="/formEdition/add">Добавить форму</a>
    </div>

</@c.page>